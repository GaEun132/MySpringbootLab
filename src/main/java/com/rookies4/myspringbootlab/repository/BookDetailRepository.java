package com.rookies4.myspringbootlab.repository;

import com.rookies4.myspringbootlab.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {
    //bookId로 상세정보 조회
    Optional<BookDetail> findByBookId(Long bookId);
    //id로 Book 정보를 함께 조회
    @Query("SELECT bd FROM BookDetail bd JOIN FETCH bd.book WHERE bd.id = :id")
    Optional<BookDetail> findByIdWithBook(Long id);
    //출판사로 상세정보 조회
    @Query("SELECT bd FROM BookDetail bd WHERE bd.publisher = :publisher")
    List<BookDetail> findByPublisher(String publisher);

}
