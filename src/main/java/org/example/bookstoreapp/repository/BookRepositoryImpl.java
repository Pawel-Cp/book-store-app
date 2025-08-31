package org.example.bookstoreapp.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.bookstoreapp.model.Book;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save book to the DB!", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        return entityManagerFactory.createEntityManager().createQuery(
                    "SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        Book bookById = entityManagerFactory.createEntityManager().createQuery(
                        "SELECT b FROM Book b WHERE :id = id", Book.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.ofNullable(bookById);
    }
}
