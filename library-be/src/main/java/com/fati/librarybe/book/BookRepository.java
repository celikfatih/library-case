package com.fati.librarybe.book;

import com.fati.librarybe.book.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Date: 26/10/2022
 * @author fati
 * @version 1.0
 */

public interface BookRepository extends MongoRepository<Book, String> {
}
