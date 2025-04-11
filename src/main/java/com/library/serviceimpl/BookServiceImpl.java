package com.library.serviceimpl;

import com.library.dto.BookDTO;
import com.library.model.Book;
import com.library.model.Category;
import com.library.repo.BookRepository;
import com.library.repo.CategoryRepository;
import com.library.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return toDTO(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setQuantity(bookDTO.getQuantity());

        if (bookDTO.getCategory() != null) {
            Category category = categoryRepository.findByName(bookDTO.getCategory())
                    .orElseGet(() -> categoryRepository.save(new Category(bookDTO.getCategory())));
            book.setCategory(category);
        }

        Book saved = bookRepository.save(book);
        return toDTO(saved);
    }

    @Override
    public List<BookDTO> getBooksByCategory(String categoryName) {
        return bookRepository.findByCategoryName(categoryName).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBookQuantity(Long bookId, int quantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setQuantity(book.getQuantity() + quantity);
        Book updated = bookRepository.save(book);
        return toDTO(updated);
    }

    private BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setQuantity(book.getQuantity());
        if (book.getCategory() != null) {
            dto.setCategory(book.getCategory().getName());
        }
        return dto;
    }
}
