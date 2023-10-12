//usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17
//DEPS org.springframework.boot:spring-boot-starter-web:3.1.0
//SOURCES ./Book.java ./BookCreateCommand.java

package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@SpringBootApplication
@RestController
public class BookService {

    static Map<String,Book> books = new HashMap<>();

    public static void main(String[] args) {

        System.setProperty("server.port", "3000");
        SpringApplication.run(BookService.class, args);

        Book tdd = new Book(UUID.randomUUID().toString(),"XP explained", "Kent Beck");
        var refactoring = new Book(UUID.randomUUID().toString(),"Refactoring", "Martin Fowler");
        var mse = new Book(UUID.randomUUID().toString(),"Modern Software Engineering", "Dave Farley");

        books.put(tdd.id(),  tdd);
        books.put(refactoring.id(),  refactoring);
        books.put(mse.id(),  mse);
    }

    @GetMapping("/books")
    List<Book> books() {
        return books.values().stream().toList();
    }

    @PostMapping("/books")
    void addBook(@RequestBody BookCreateCommand book){
        var b =  new Book(UUID.randomUUID().toString(), book.title(), book.author());
        books.put(b.id(),b);

    }

}
