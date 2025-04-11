package com.library.repo;

import com.library.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    List<BorrowRecord> findByStudentId(Long studentId);

    Optional<BorrowRecord> findByStudentIdAndBookIdAndReturnDateIsNotNull(Long studentId, Long bookId);
}
