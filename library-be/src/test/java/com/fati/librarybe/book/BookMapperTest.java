package com.fati.librarybe.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {

    private final String id = "id";
    private final String name = "name";
    private final String author = "author";

    private final Book book = Book.builder().id(id).name(name).author(author).build();
    private final BookRequest bookRequest = BookRequest.builder().id(id).name(name).author(author).build();

    private final BookMapper bookMapper = new BookMapper();

    @Test
    void testToResponse() {
        BookResponse actual = bookMapper.toResponse(book);

        assertEquals(id, actual.getId());
        assertEquals(name, actual.getName());
        assertEquals(author, actual.getAuthor());
    }

    @Test
    void testToEntity() {
        Book actual = bookMapper.toEntity(bookRequest);

        assertEquals(id, actual.getId());
        assertEquals(name, actual.getName());
        assertEquals(author, actual.getAuthor());
    }
}