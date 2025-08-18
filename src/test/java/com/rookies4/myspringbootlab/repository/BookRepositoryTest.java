package com.rookies4.myspringbootlab.repository;

import com.rookies4.myspringbootlab.entity.Book;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testCreateBook(){
        Book book1 = new Book("스프링 부트 입문",
                "홍길동",
                "9788956746425",
                30000,
                LocalDate.parse("2025-05-07")
        );
        Book book2 = new Book("JPA 프로그래밍",
                "박둘리",
                "9788956746432",
                35000,
                LocalDate.parse("2025-04-30")
        );
        bookRepository.save(book1);
        bookRepository.save(book2);
        assertThat(book1.getAuthor()).isEqualTo("홍길동");
    }

    @Test
    void testFindByIsbn() {
        Optional<Book> book =
                bookRepository.findByIsbn("9788956746425");
        if(book.isPresent()){
            Book book1= book.get();
            assertThat(book1.getId()).isEqualTo(1L);
        }

    }


    @Test
    void testFindByAuthor() {
        Optional<Book> book =
                bookRepository.findByAuthor("홍길동");
        assertThat(book.orElseGet(()-> new Book()).getTitle()).isEqualTo("스프링 부트 입문");

    }

    @Test
    @Transactional
    void testUpdateBook() {

        Book book = bookRepository.findByIsbn("9788956746425")
                .orElseThrow(() ->new RuntimeException("Book Not Found"));
        book.setTitle("스프링");
        bookRepository.save(book);

        assertThat(bookRepository.findByIsbn("9788956746425").orElseGet(() -> new Book()).getTitle()).isEqualTo("스프링");
    }


    @Test
    @Transactional
    void  testDeleteBook() {
        bookRepository.deleteById(1L);
        assertThat(bookRepository.findById(1L)).isEmpty();
    }





}
