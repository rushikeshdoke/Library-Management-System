package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BookDTO;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getCategory().getName(),  // Category as a string (category name)
                        book.getStock(),
                        book.getCategory().getId())   // Passing categoryId for create/update
                )
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory().getName(),  // Returning category as a string
                book.getStock(),
                book.getCategory().getId()     // Returning categoryId
        );
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        // Find Category by ID
        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // Create and save new book with category
        Book book = new Book(bookDTO.getTitle(), bookDTO.getAuthor(), category, bookDTO.getStock(), true);
        book = bookRepository.save(book);

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory().getName(),  // Returning category as string
                book.getStock(),
                book.getCategory().getId()     // Returning categoryId
        );
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        // Find Book by ID
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        // Find Category by ID
        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // Update book details
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(category);
        book.setStock(bookDTO.getStock());
        book = bookRepository.save(book);

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory().getName(),  // Returning category as string
                book.getStock(),
                book.getCategory().getId()     // Returning categoryId
        );
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
