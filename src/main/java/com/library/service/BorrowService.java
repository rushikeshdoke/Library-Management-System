package com.library.service;

import java.util.List;

import com.library.dto.BorrowRecordDTO;

public interface BorrowService {
	 BorrowRecordDTO borrowBook(Long studentId, Long bookId);
	    BorrowRecordDTO returnBook(Long studentId, Long bookId);
	    List<BorrowRecordDTO> getBorrowRecordsByStudent(Long studentId);
	    List<BorrowRecordDTO> getAllBorrowRecords();
	    
	   
	   

	    

}



