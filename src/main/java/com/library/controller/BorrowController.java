package com.library.controller;

import com.library.dto.BorrowRecordDTO;
import com.library.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React frontend
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/borrow")
    public BorrowRecordDTO borrowBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        return borrowService.borrowBook(studentId, bookId);
    }

    @PostMapping("/return")
    public BorrowRecordDTO returnBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        return borrowService.returnBook(studentId, bookId);
    }

    @GetMapping("/student/{studentId}")
    public List<BorrowRecordDTO> getBorrowRecordsByStudent(@PathVariable Long studentId) {
        return borrowService.getBorrowRecordsByStudent(studentId);
    }

    @GetMapping
    public List<BorrowRecordDTO> getAllBorrowRecords() {
        return borrowService.getAllBorrowRecords();
    }
}
