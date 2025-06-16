package com.rylar.library_system.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rylar.library_system.dto.BookCreateDTO;
import com.rylar.library_system.dto.BookDTO;
import com.rylar.library_system.exception.BookNotFoundException;
import com.rylar.library_system.exception.handler.LibrarySystemResponse;
import com.rylar.library_system.model.Book;
import com.rylar.library_system.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	
	private BookRepository bookRepository;
	private ModelMapper modelMapper;
	
	public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
		this.bookRepository = bookRepository;
		this.modelMapper = modelMapper;
	}
	
	private Book toEntity(BookDTO bookDTO) {
		return  modelMapper.map(bookDTO, Book.class);
	}
	
	private Book createToEntity(BookCreateDTO bookCreateDTO) {
		return  modelMapper.map(bookCreateDTO, Book.class);
	}
	
	private BookCreateDTO toCreateDTO(Book entity) {
		return modelMapper.map(entity, BookCreateDTO.class);
	}
	
	private BookDTO toDTO(Book entity) {
		return modelMapper.map(entity, BookDTO.class);
	}

	@Override
	public BookCreateDTO add(BookCreateDTO bookCreate) {
		LOGGER.info("Saving " + bookCreate.toString());
		return toCreateDTO(bookRepository.save(createToEntity(bookCreate)));
	}

	@Override
	public BookDTO getById(Long id) {
		LOGGER.info("Get book with an id of " + id);
		
		return toDTO(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
	}

	@Override
	public Page<BookDTO> getAll(Pageable pageable) {
		LOGGER.info("Getting All Book");
		return bookRepository.findAll(pageable).map(book -> toDTO(book));
	}

	@Override
	public BookDTO update(Long id, BookDTO book) {
		LOGGER.info("Updating the value of Book with an ID of " + id);
		BookDTO updateBook = getById(id);
		
		updateBook.setTitle(book.getTitle());
		updateBook.setAuthor(book.getAuthor());
		
		Book updatedBook = bookRepository.save(toEntity(updateBook)); 
		
		return toDTO(updatedBook);
	}
	
	@Override
	public LibrarySystemResponse delete(Long id) {
		LOGGER.info("Delete Book with an id of " + id);
		BookDTO findBook = getById(id);
		
		bookRepository.delete(toEntity(findBook));
		
		return new LibrarySystemResponse(HttpStatus.OK.value(), "Successfully deleted the book with an ID of " + id) ; 
	}
	
	
}
