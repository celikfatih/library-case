package com.fati.librarybe.exception;

/**
 * Date: 27/10/2022
 * @author fati
 * @version 1.0
 */

public class BookNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7258348925264196775L;

    public BookNotFoundException() {
        super("Book not found for the given Id!");
    }
}
