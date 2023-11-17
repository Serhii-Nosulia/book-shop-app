package mate.bookshopapp.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.bookshopapp.exception.EntityNotFoundException;
import mate.bookshopapp.model.Book;
import mate.bookshopapp.repository.BookRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final EntityManagerFactory factory;

    @Override
    public Book save(Book book) {
        EntityTransaction transaction = null;
        try (EntityManager manager = factory.createEntityManager()) {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new EntityNotFoundException("Couldn't save book " + book + " to DB");
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager manager = factory.createEntityManager()) {
            return Optional.ofNullable(manager.find(Book.class, id));
        } catch (Exception e) {
            throw new EntityNotFoundException("Couldn't find book by id: " + id);
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager manager = factory.createEntityManager()) {
            return manager.createQuery("FROM books", Book.class).getResultList();
        } catch (Exception e) {
            throw new EntityNotFoundException("Couldn't find any book at DB");
        }
    }
}
