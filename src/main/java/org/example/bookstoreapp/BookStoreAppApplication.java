package org.example.bookstoreapp;

import java.math.BigDecimal;
import org.example.bookstoreapp.model.Book;
import org.example.bookstoreapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreAppApplication {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book book = new Book();
                book.setAuthor("Andrzej Sapkowski");
                book.setTitle("Wiedzmin");
                book.setDescription(""" 
            Book tells the story about Geralt from Rivia and his 
             adventures through combat with monsters, complete some missions. """);
                book.setIsbn("123asd");
                book.setPrice(BigDecimal.valueOf(19));
                book.setCoverImage("URLOfImage");

                System.out.println(bookService.save(book));
                System.out.println(bookService.findAll());
            }
        };
    }

}
