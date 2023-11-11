package mate.bookshopapp.repository.impl;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import mate.bookshopapp.model.Book;
import mate.bookshopapp.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory factory;

    public BookRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.factory = (SessionFactory) entityManagerFactory;
    }

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Couldn't save book " + book + " to DB");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM books", Book.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't find all book at DB");
        }
    }
}
