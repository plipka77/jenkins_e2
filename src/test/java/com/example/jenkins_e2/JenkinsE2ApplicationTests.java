package com.example.jenkins_e2;

import com.example.jenkins_e2.model.Book;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JenkinsE2ApplicationTests {
    @LocalServerPort
    private int port;
    private Book testBook;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        testBook = generateBook();
    }

    @Test
    @Order(1)
    void putBook_test() {
        HttpEntity<Book> entity = new HttpEntity<>(testBook);
        ResponseEntity<Book> bookResponse = restTemplate.exchange("http://localhost:" + port + "/book", HttpMethod.PUT, entity, Book.class);
        Book book = bookResponse.getBody();
        Assertions.assertNotNull(book);
        Assertions.assertEquals("12345", book.getIsbn());
        Assertions.assertEquals("A Story", book.getName());
        Assertions.assertEquals("Paul Lipka", book.getAuthor());
    }

    @Test
    @Order(2)
    void getBook_test() {
        Book book = restTemplate.getForObject("http://localhost:" + port + "/book" + "/12345", Book.class);
        Assertions.assertNotNull(book);
        Assertions.assertEquals("12345", book.getIsbn());
        Assertions.assertEquals("A Story", book.getName());
        Assertions.assertEquals("Paul Lipka", book.getAuthor());

    }

    private static Book generateBook() {
        return new Book("12345", "A Story", "Paul Lipka");
    }
}
