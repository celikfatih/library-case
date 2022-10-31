package com.fati.librarybe.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {BookController.class})
class BookControllerTest {

    private static final String BASE_PREFIX = "/books";
    private static ObjectMapper OM;

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setUp() {
        OM = new ObjectMapper();
    }

    @Test
    void testGetAll() throws Exception {
        BookResponse book = BookResponse.builder().build();
        Pageable pageable = Pageable.ofSize(1);

        when(bookService.getAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(book), pageable, 1));

        mockMvc.perform(get(BASE_PREFIX + "/all?page=1&size=10"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content").isNotEmpty());
    }

    @Test
    void testSaveWhenValidRequest() throws Exception {
        String author = "author";
        String name = "name";
        BookRequest request = BookRequest.builder().author(author).name(name).build();
        BookResponse response = BookResponse.builder().author(author).name(name).build();

        when(bookService.save(request)).thenReturn(response);

        mockMvc.perform(post(BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OM.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.author").value(author))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    void testSaveWhenNotValidRequest() throws Exception {
        BookRequest request = BookRequest.builder().build();

        mockMvc.perform(post(BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OM.writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.errors").isNotEmpty());
    }

    @Test
    void testSaveWhenInternalServerError() {
        BookRequest request = BookRequest.builder().author("author").name("name").build();
        RuntimeException ex = new RuntimeException("Test exception!");

        when(bookService.save(request)).thenThrow(ex);

        assertThatThrownBy(() -> mockMvc.perform(post(BASE_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OM.writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful()))
                .hasCause(ex);
    }

    @Test
    void testUpdateWhenValidRequest() throws Exception {
        String id = "123";
        String author = "author";
        String name = "name";
        String updatedAuthor = "authorU";
        BookRequest request = BookRequest.builder().id(id).author(author).name(name).build();
        BookResponse response = BookResponse.builder().author(updatedAuthor).name(name).build();

        when(bookService.update(id, request)).thenReturn(response);

        mockMvc.perform(put(BASE_PREFIX + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OM.writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.author").value(updatedAuthor));
    }

    @Test
    void testUpdateWhenNotValidRequest() throws Exception {
        String id = "123";
        BookRequest request = BookRequest.builder().build();

        mockMvc.perform(put(BASE_PREFIX + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OM.writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.errors").isNotEmpty());
    }

    @Test
    void testDeleteWhenExistId() throws Exception {
        String id = "123";

        doNothing().when(bookService).delete(id);

        mockMvc.perform(delete(BASE_PREFIX + "/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteWhenNotExistId() {
        String id = "123";
        RuntimeException ex = new RuntimeException("Test exception!");

        doThrow(ex).when(bookService).delete(id);

        assertThatThrownBy(() -> mockMvc.perform(delete(BASE_PREFIX + "/{id}", id))
                .andExpect(status().is2xxSuccessful())).hasCause(ex);
    }
}