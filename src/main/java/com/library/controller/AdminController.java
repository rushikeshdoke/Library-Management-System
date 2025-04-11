package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookDTO;
import com.library.dto.BorrowRecordDTO;
import com.library.dto.CategoryDTO;
import com.library.dto.StudentDTO;
import com.library.service.BookService;
import com.library.service.BorrowService;
import com.library.service.CategoryService;
import com.library.service.StudentService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired private BookService bookService;
    @Autowired private StudentService studentService;
    @Autowired private BorrowService borrowService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/books")
    public List<BookDTO> getBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/books")
    public BookDTO addBook(@RequestBody BookDTO dto) {
        return bookService.saveBook(dto);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/students")
    public List<StudentDTO> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/records")
    public List<BorrowRecordDTO> getRecords() {
        return borrowService.getAllBorrowRecords();
    }
    
    

    @PostMapping("/categories")
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.addCategory(categoryDTO);
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getCategories() {
        return categoryService.getAllCategories();
    }
}
