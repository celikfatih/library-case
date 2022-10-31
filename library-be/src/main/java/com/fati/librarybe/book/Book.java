package com.fati.librarybe.book;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Date: 26/10/2022
 * @author fati
 * @version 1.0
 */

@Getter
@Setter
@Builder
@ToString
@Document("book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;

    private String name;
    private String author;
}
