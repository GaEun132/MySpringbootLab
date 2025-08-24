package com.rookies4.myspringbootlab.service;

import com.rookies4.myspringbootlab.dto.BookDTO;
import com.rookies4.myspringbootlab.entity.Book;
import com.rookies4.myspringbootlab.exception.BusinessException;
import com.rookies4.myspringbootlab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookDTO.BookResponse createBook(BookDTO.BookCreateRequest request) {

        bookRepository.findByIsbn(request.getIsbn())
                .ifPresent(entity -> {
                    throw new BusinessException("Book with this Isbn already Exist", HttpStatus.CONFLICT);
                });
        Book book = request.toEntity();
        Book savedBook = bookRepository.save(book);
        return new BookDTO.BookResponse(savedBook);
    }

    public List<BookDTO.BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDTO.BookResponse::new)
                .toList();
    }

    public BookDTO.BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        return new BookDTO.BookResponse(book);
    }

    public BookDTO.BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        return new BookDTO.BookResponse(book);
    }
    public List<BookDTO.BookResponse> getBooksByAuthor(String author) {
        List<Book> book = bookRepository.findByAuthor(author)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        List<BookDTO.BookResponse> books = book.stream()
                .map(BookDTO.BookResponse::new)
                .toList();
        return books;
    }

    @Transactional
    public BookDTO.BookResponse updateBook(Long id,
                                           BookDTO.BookUpdateRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        if(request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if(request.getAuthor()!= null) {
            book.setAuthor(request.getAuthor());
        }
        if(request.getPrice()!= null) {
            book.setPrice(request.getPrice());
        }
        if(request.getPublishDate()!= null) {
            book.setPublishDate(request.getPublishDate());
        }
        return new BookDTO.BookResponse(book);

    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
    }

}
