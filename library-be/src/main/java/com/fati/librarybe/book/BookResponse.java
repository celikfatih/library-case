package com.fati.librarybe.book;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Date: 27/10/2022
 * @author fati
 * @version 1.0
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse implements Serializable {
    private static final long serialVersionUID = 7629661737111521589L;

    String id;
    String name;
    String author;
}
