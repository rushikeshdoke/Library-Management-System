package com.example.demo.dto;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String category;  // Category as a String (Category name)
    private int stock;
    private Long categoryId;  // Used for create/update operations

    // No-argument constructor (required for serialization)
    public BookDTO() {}

    // Constructor for initializing fields with categoryId (for create/update operations)
    public BookDTO(Long id, String title, String author, String category, int stock, Long categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    // Constructor for initializing fields without categoryId (for returning data from service layer)
    public BookDTO(Long id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
    
    
}