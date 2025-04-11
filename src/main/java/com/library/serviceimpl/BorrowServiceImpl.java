package com.library.serviceimpl;

import com.library.dto.BorrowRecordDTO;
import com.library.model.Book;
import com.library.model.BorrowRecord;
import com.library.model.Student;
import com.library.repo.BookRepository;
import com.library.repo.BorrowRecordRepository;
import com.library.repo.StudentRepository;
import com.library.service.BorrowService;
import com.library.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Override
    public BorrowRecordDTO borrowBook(Long studentId, Long bookId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Book is currently out of stock.");
        }

        BorrowRecord record = new BorrowRecord();
        record.setStudent(student);
        record.setBook(book);
        record.setIssueDate(LocalDate.now());
        record.setReturnDate(LocalDate.now().plusDays(7));

        borrowRecordRepository.save(record);
        bookService.updateBookQuantity(bookId, -1); // Decrease quantity

        return toDTO(record);
    }

    @Override
    public BorrowRecordDTO returnBook(Long studentId, Long bookId) {
        BorrowRecord record = borrowRecordRepository
                .findByStudentIdAndBookIdAndReturnDateIsNotNull(studentId, bookId)
                .orElseThrow(() -> new RuntimeException("No active borrow record found"));

        record.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(record);

        bookService.updateBookQuantity(bookId, 1); // Increase quantity
        return toDTO(record);
    }

    @Override
    public List<BorrowRecordDTO> getBorrowRecordsByStudent(Long studentId) {
        return borrowRecordRepository.findByStudentId(studentId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowRecordDTO> getAllBorrowRecords() {
        return borrowRecordRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private BorrowRecordDTO toDTO(BorrowRecord record) {
        BorrowRecordDTO dto = new BorrowRecordDTO();
        dto.setStudentId(record.getStudent().getId());
        dto.setBookId(record.getBook().getId());
        dto.setIssueDate(record.getIssueDate());
        dto.setReturnDate(record.getReturnDate());
        return dto;
    }
}
