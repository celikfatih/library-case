package com.fati.librarybe.book;

import com.fati.librarybe.advice.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.fati.librarybe.configuration.OpenApiConfiguration.BOOK_TAG;
import static com.fati.librarybe.constants.OpenApiConstants.INTERNAL_SERVER_ERROR_DESCRIPTION;
import static com.fati.librarybe.constants.OpenApiConstants.OPERATION_SUCCESS_DESCRIPTION;

/**
 * Date: 26/10/2022
 * @author fati
 * @version 1.0
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = {"/books"})
@Tag(name = BOOK_TAG, description = "Book related operations.")
public class BookController {

    private final BookService bookService;

    @GetMapping(path = {"/all"})
    @Operation(summary = "Returns all saved books in the library according to the stated page size.", tags = { BOOK_TAG })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = OPERATION_SUCCESS_DESCRIPTION,
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = BookResponse.class)))
            }),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR_DESCRIPTION,
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            })
    })
    public ResponseEntity<Page<BookResponse>> getAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(bookService.getAll(pageable));
    }

    @PostMapping
    @Operation(summary = "Saves the book in the sent request to the library.", tags = { BOOK_TAG })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object successfully created!",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BookResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request!",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR_DESCRIPTION,
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            })
    })
    public ResponseEntity<BookResponse> save(@Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookRequest));
    }

    @PutMapping(path = {"{id}"})
    @Operation(summary = "Updates the book in the sent request to the library according to given Id.", tags = { BOOK_TAG })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = OPERATION_SUCCESS_DESCRIPTION,
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BookResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid request!",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR_DESCRIPTION,
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
                    })
    })
    public ResponseEntity<BookResponse> update(@PathVariable("id") String id,
                                               @Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.update(id, bookRequest));
    }

    @DeleteMapping(path = {"{id}"})
    @Operation(summary = "Deletes the book in the library according to given Id.", tags = { BOOK_TAG })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Object successfully deleted!",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BookResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid request!",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR_DESCRIPTION,
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
                    })
    })
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
