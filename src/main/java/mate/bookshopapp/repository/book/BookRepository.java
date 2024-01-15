package mate.bookshopapp.repository.book;

import java.util.List;
import java.util.Optional;
import mate.bookshopapp.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long>,
        JpaSpecificationExecutor<Book>,
        PagingAndSortingRepository<Book, Long> {

    @Query("FROM books")
    @EntityGraph(attributePaths = "categories")
    Page<Book> findAllBooks(Pageable pageable);

    @Query("FROM books b WHERE b.id = :id")
    @EntityGraph(attributePaths = "categories")
    Optional<Book> findBookById(@Param("id") Long id);

    @Query("FROM books b JOIN FETCH b.categories c WHERE c.id = :id")
    List<Book> findBooksByCategoryId(@Param("id") Long id);

}
