package mate.bookshopapp;

import java.math.BigDecimal;
import mate.bookshopapp.model.Book;
import mate.bookshopapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookShopAppApplication {
    private final BookService bookService;

    @Autowired
    public BookShopAppApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {

        SpringApplication.run(BookShopAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setIsbn("10: 0307348075");
            book.setPrice(BigDecimal.valueOf(230));
            book.setAuthor("King");
            book.setTitle("Carrie");

            System.out.println(bookService.save(book));
            System.out.println(bookService.findAll());
        };
    }
}
