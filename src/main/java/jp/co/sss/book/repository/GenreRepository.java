package jp.co.sss.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.book.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findAllByOrderByIdAsc();
}
