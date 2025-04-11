package com.library.service;

import com.library.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getBook(Long id);
    void deleteBook(Long id);
    BookDTO saveBook(BookDTO bookDTO);
    List<BookDTO> getBooksByCategory(String categoryName);
    BookDTO updateBookQuantity(Long bookId, int quantity); // ✅ Updated method
}
