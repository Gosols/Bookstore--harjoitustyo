package com.example.Bookstore;

import com.example.Bookstore.web.BookstoreController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTesting {

    @Autowired
    private BookstoreController controller;

    @Test
    public void contextLoads() throws Exception{
    assertThat(controller).isNotNull();
    }


}
