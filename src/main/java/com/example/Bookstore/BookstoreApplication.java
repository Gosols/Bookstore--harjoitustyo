package com.example.Bookstore;

import com.example.Bookstore.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {
    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(BookRepository brepo, CategoryRepository crepo, UserRepository urepo) {
        return (args) -> {
            log.info("save a couple of books");

            crepo.save(new Category("Horror"));
            crepo.save(new Category("Thriller"));
            crepo.save(new Category("Drama"));
            crepo.save(new Category("Fable"));
            crepo.save(new Category("Comedy"));

            brepo.save(new Book("Keijo Kakkula", "Silmälasien A-Ö", "2342342-46", 2017, crepo.findByName("Horror").get(0)));
            brepo.save(new Book("Juha Yypeli", "Musigs", "234342542-26", 2020, crepo.findByName("Thriller").get(0)));
            brepo.save(new Book("Janne Nacci", "Rakas päiväkirjani", "4596834-905", 2019, crepo.findByName("Comedy").get(0)));

            User user1 = new User("user"
                    ,
                    "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            User user2 = new User("admin"
                    ,
                    "$2y$12$VFD6dmaRBB5gnw9Gb7pJ8OBuBRFCvd8f82sVa0va7y5G6wL76Zade", "ADMIN");
            urepo.save(user1);
            urepo.save(user2);

            log.info("fetch all books");
            for (Book book : brepo.findAll()) {
                log.info(book.toString());
            }
            System.out.println("----DONE----");

        };
    }

}
