package com.fati.librarybe.advice;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Date: 27/10/2022
 * @author fati
 * @version 1.0
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {
    List<String> errors;
}
