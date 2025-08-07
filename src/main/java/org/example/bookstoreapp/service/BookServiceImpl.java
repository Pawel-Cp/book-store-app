package org.example.bookstoreapp.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.example.bookstoreapp.model.Book;
import org.example.bookstoreapp.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
