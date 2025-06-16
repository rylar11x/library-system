package com.rylar.library_system.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rylar.library_system.dto.BookCreateDTO;
import com.rylar.library_system.dto.BookDTO;
import com.rylar.library_system.exception.handler.LibrarySystemResponse;
import com.rylar.library_system.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping("/add")
	public ResponseEntity<BookCreateDTO> addBook(@Valid @RequestBody BookCreateDTO book) {
		BookCreateDTO savedBook = bookService.add(book);
		return ResponseEntity.ok(savedBook);
	}

	@GetMapping("/{id}") 
	public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
		BookDTO book = bookService.getById(id);

		return ResponseEntity.ok(book);
	}
	
	@GetMapping("/")
	public ResponseEntity<Page<BookDTO>> getAll(@PageableDefault(size = 5, sort = "title") Pageable pageable) {
		Page<BookDTO> bookList = bookService.getAll(pageable);
		
		return ResponseEntity.ok(bookList);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BookDTO> updateById(@PathVariable Long id, @Valid @RequestBody BookDTO book) {
		BookDTO updatedBook = bookService.update(id, book);
		
		return ResponseEntity.ok(updatedBook);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LibrarySystemResponse> deleteById(@PathVariable Long id) {
		LibrarySystemResponse response = bookService.delete(id);
		
		return ResponseEntity.ok(response);
	}

}
