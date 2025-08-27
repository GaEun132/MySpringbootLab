package com.rookies4.myspringbootlab.entity;

import com.rookies4.myspringbootlab.dto.BookDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;

@Table(name = "books")
@Getter
@Setter
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private LocalDate publishDate;

    @OneToOne(fetch = FetchType.LAZY,
            mappedBy="book",
    cascade = CascadeType.ALL)
    private BookDetail bookDetail;



}
