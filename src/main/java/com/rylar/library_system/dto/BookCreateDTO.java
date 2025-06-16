package com.rylar.library_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookCreateDTO {
	
	@NotBlank(message = "Title must not be blank")
	private String title;
	
	@Size(min = 3, message = "Author must be at least 3 characters")
	private String author;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + "]";
	}
	

}
