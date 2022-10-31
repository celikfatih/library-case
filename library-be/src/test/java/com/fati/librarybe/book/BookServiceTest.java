package com.fati.librarybe.book;

import com.fati.librarybe.exception.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks private BookService bookService;

    @Mock private BookRepository bookRepository;

    @Mock private BookMapper bookMapper;

    @Test
    void testGetAll() {
        Book book = Book.builder().build();
        BookResponse response = BookResponse.builder().build();
        Pageable pageable = Pageable.ofSize(1);

        when(bookRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(Collections.singletonList(book), pageable, 1));
        when(bookMapper.toResponse(book)).thenReturn(response);

        Page<BookResponse> actual = bookService.getAll(pageable);

        assertFalse(actual.isEmpty());
        assertEquals(1, actual.getSize());
    }

    @Test
    void testSave() {
        String name = "name";
        BookRequest request = BookRequest.builder().name(name).build();
        Book book = Book.builder().name(name).build();
        BookResponse response = BookResponse.builder().name(name).build();

        when(bookMapper.toEntity(request)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toResponse(book)).thenReturn(response);

        BookResponse actual = bookService.save(request);

        assertEquals(name, actual.getName());
    }

    @Test
    void testUpdateWhenRecordExist() {
        String id = "123";
        BookRequest request = BookRequest.builder().id(id).build();
        BookResponse response = BookResponse.builder().id(id).build();

        BookService spyBookService = spy(bookService);

        when(bookRepository.existsById(id)).thenReturn(true);
        doReturn(response).when(spyBookService).save(request);

        BookResponse actual = spyBookService.update(id, request);

        verify(spyBookService, times(1)).save(request);
        assertEquals(id, actual.getId());
    }

    @Test
    void testUpdateWhenRecordNotExist() {
        String id = "123";
        BookRequest request = BookRequest.builder().id(id).build();

        BookService spyBookService = spy(bookService);

        when(bookRepository.existsById(id)).thenReturn(false);

        assertThrows(BookNotFoundException.class, () -> spyBookService.update(id, request));
        verify(spyBookService, never()).save(request);
    }

    @Test
    void testDeleteWhenIdExist() {
        String id = "123";

        when(bookRepository.existsById(id)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(id);

        bookService.delete(id);

        verify(bookRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteWhenIdNotExist() {
        String id = "123";

        when(bookRepository.existsById(id)).thenReturn(false);

        assertThrows(BookNotFoundException.class, () -> bookService.delete(id));

        verify(bookRepository, never()).deleteById(id);
    }
}