package org.example.bookstoreapp.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.example.bookstoreapp.dto.BookDto;
import org.example.bookstoreapp.exception.EntityNotFoundException;
import org.example.bookstoreapp.mapper.BookMapper;
import org.example.bookstoreapp.model.Book;
import org.example.bookstoreapp.model.CreateBookRequestDto;
import org.example.bookstoreapp.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Override
    public BookDto createBook(CreateBookRequestDto createBookRequestDto) {
        Book model = bookMapper.toModel(createBookRequestDto);
        bookRepository.save(model);
        return bookMapper.toDto(model);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> bookMapper.toDto(book))
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Optional<Book> bookById = bookRepository.getBookById(id);
        return bookMapper.toDto(bookById.orElseThrow(
                () -> new EntityNotFoundException("Can't find book with id: " + id)));
    }
}
