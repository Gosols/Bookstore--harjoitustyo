package com.example.Bookstore;

import com.example.Bookstore.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RandomTesting {
    @Autowired
    private BookRepository brepo;
    @Autowired
    private CategoryRepository crepo;
    @Autowired
    private UserRepository urepo;

    @Test
    public void dunnoTest() {
        List<Book> books = brepo.findByTitle("Musigs");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Juha Yypeli");

        List<Category> categories = crepo.findByName("Horror");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Horror");

        User user = urepo.findByUsername("admin");
        assertThat(user.getRole()).isEqualTo("ADMIN");

    }

    @Test
    public void createEntitiesTest() {
        Book book = new Book("Mikki Hiiri", "Testikirja", "36776543", 2020, new Category("Horror"));
        brepo.save(book);
        assertThat(book.getId()).isNotNull();

        Category category = new Category("Bornoust");
        crepo.save(category);
        assertThat(category.getId()).isNotNull();

        User user = new User("Gosols", "testtest", "ADMIN");
        urepo.save(user);
        assertThat(user.getId()).isNotNull();
    }

}
