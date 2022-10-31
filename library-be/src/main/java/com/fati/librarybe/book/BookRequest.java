package com.fati.librarybe.book;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Date: 27/10/2022
 * @author fati
 * @version 1.0
 */

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest implements Serializable {
    private static final long serialVersionUID = 6318367204218351216L;

    String id;

    @NotBlank(message = "Name must not be null!")
    String name;
    @NotBlank(message = "Author must not be null!")
    String author;
}
