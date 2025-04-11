package com.library.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.library.dto.BookDTO;
import com.library.model.Book;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setQuantity(book.getQuantity());

        if (book.getCategory() != null) {
            dto.setCategoryId(book.getCategory().getId());
        }

        return dto;
    }

    public Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setQuantity(dto.getQuantity());
        // Category is set in the service layer after validation
        return book;
    }
}

