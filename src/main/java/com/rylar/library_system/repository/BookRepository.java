package com.rylar.library_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rylar.library_system.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
