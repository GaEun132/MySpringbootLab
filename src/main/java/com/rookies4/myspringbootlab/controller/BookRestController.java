package com.rookies4.myspringbootlab.controller;

import com.rookies4.myspringbootlab.entity.Book;
import com.rookies4.myspringbootlab.exception.BusinessException;
import com.rookies4.myspringbootlab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepository bookRepository;

    //새 도서 등록
    @PostMapping
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    //모든 도서 조회
    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    //ID로 특정 도서 조회
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        Book existBook = getExistBook(id);
        return existBook;
    }

    //ISBN으로 도서 조회
    @GetMapping("/isbn/{isbn}/")
    public Book getBookByIsbn(@PathVariable String isbn) {
        Book existBook = getExistBookByIsbn(isbn);
        return existBook;
    }

    //도서 정보 수정
    @PatchMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));

        existBook.setTitle(book.getTitle());
        existBook.setIsbn(book.getIsbn());
        existBook.setAuthor(book.getAuthor());
        existBook.setPrice(book.getPrice());
        existBook.setPublishDate(book.getPublishDate());

        //DB에 저장
        Book updateBook = bookRepository.save(existBook);
        return updateBook;
    }

    //도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Book existBook = getExistBook(id);
        //DB에 삭제 요청
        bookRepository.delete(existBook);
        return ResponseEntity.ok("Book이 삭제되었습니다.");

    }

    private Book getExistBook(Long id)  {
        return bookRepository.findById(id)
                .map(book -> book)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
    }
    private Book getExistBookByIsbn(String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        Book existBook = optionalBook
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        return existBook;
    }

}
