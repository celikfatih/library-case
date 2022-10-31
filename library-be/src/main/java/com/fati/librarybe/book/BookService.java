package com.fati.librarybe.book;

import com.fati.librarybe.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Date: 27/10/2022
 * @author fati
 * @version 1.0
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    public Page<BookResponse> getAll(Pageable pageable) {
        List<BookResponse> allItems = bookRepository.findAll(pageable)
                .stream()
                .map(bookMapper::toResponse)
                .collect(toList());
        log.info("Total book count in the library: {}", allItems.size());
        return new PageImpl<>(allItems, pageable, bookRepository.count());
    }

    @Transactional
    public BookResponse save(BookRequest bookRequest) {
        log.info("Saving book with values: {}", bookRequest);
        Book book = bookMapper.toEntity(bookRequest);
        Book saved = bookRepository.save(book);
        return bookMapper.toResponse(saved);
    }

    @Transactional
    public BookResponse update(String id, BookRequest bookRequest) {
        if (!bookRepository.existsById(id)) {
            log.error("Book not found for Id: {}", id);
            throw new BookNotFoundException();
        }
        log.info("Updating book with values: {}", bookRequest);
        return this.save(bookRequest);
    }

    @Transactional
    public void delete(String id) {
        if (!bookRepository.existsById(id)) {
            log.error("Book not found for Id: {}", id);
            throw new BookNotFoundException();
        }
        bookRepository.deleteById(id);
        log.info("The book with Id {} has been deleted.", id);
    }
}
