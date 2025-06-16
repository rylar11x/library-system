package com.rylar.library_system.exception;


public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BookNotFoundException(Long id) {
		super("Book is not existing with an id of " + id);
	}

}
