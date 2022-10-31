package com.fati.librarybe.book;

import org.springframework.stereotype.Component;

/**
 * Date: 27/10/2022
 * @author fati
 * @version 1.0
 */

@Component
public class BookMapper {

    public BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .build();
    }

    public Book toEntity(BookRequest request) {
        return Book.builder()
                .id(request.getId())
                .name(request.getName())
                .author(request.getAuthor())
                .build();
    }
}
