package com.rookies4.myspringbootlab.dto;

import com.rookies4.myspringbootlab.entity.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class BookDTO {


    @Getter
    @Setter
    public static class BookCreateRequest {

        @NotBlank(message = "제목은 필수 입력 항목입니다.")
        private String title;
        @NotBlank(message = "저자는 필수 입력 항목입니다.")
        private String author;
        @NotBlank(message = "isbn은 필수 입력 항목입니다.")
        private String isbn;
        @NotNull(message = "가격은 필수 입력 항목입니다.")
        private Integer price;
        @NotNull(message = "출판일은 필수 입력 항목입니다.")
        private LocalDate publishDate;

        public Book toEntity() {
            return new Book(title,author,isbn,price,publishDate);
        }
    }
    @Getter
    @Setter
    public static class BookResponse {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;

        public BookResponse(Book book) {
            this.id = book.getId();
            this.title = book.getTitle();
            this.author = book.getAuthor();
            this.isbn = book.getIsbn();
            this.price = book.getPrice();
            this.publishDate = book.getPublishDate();

        }
    }
    @Getter
    @Setter
    public static class BookUpdateRequest {

        private String title;
        private String author;
        private Integer price;
        private LocalDate publishDate;


    }

}
