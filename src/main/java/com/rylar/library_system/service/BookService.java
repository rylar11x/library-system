package com.rylar.library_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rylar.library_system.dto.BookCreateDTO;
import com.rylar.library_system.dto.BookDTO;
import com.rylar.library_system.exception.handler.LibrarySystemResponse;

public interface BookService {
	
	public BookCreateDTO add(BookCreateDTO bookCreate);
	public BookDTO getById(Long id);
	public Page<BookDTO> getAll(Pageable pageable);
	public BookDTO update(Long id, BookDTO book);
	public LibrarySystemResponse delete(Long id);

}
