package org.example.bookstoreapp.service;

import java.util.List;
import org.example.bookstoreapp.dto.BookDto;
import org.example.bookstoreapp.model.CreateBookRequestDto;

public interface BookService {
    BookDto createBook(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll();

    BookDto getBookById(Long id);
}
