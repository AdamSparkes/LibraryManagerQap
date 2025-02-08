package com.librarymanager.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    void testCreateBook() {
        Book book = new Book("B101", "Java Basics", 5);
        assertEquals("B101", book.getId());
        assertEquals("Java Basics", book.getTitle());
        assertEquals(5, book.getInStock());
    }

    @Test
    void testBorrowBookSuccessfully() {
        Book book = new Book("B102", "Data Structures", 3);
        book.borrowBook(); // should decrement inStock
        assertEquals(2, book.getInStock());
    }

    @Test
    void testBorrowBookFailsWhenOutOfStock() {
        Book book = new Book("B103", "Algorithms", 0);
        // Using a lambda that calls book.borrowBook()
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                book::borrowBook,
                "Expected borrowBook() to throw if no copies are available"
        );
        assertEquals("No copies available.", exception.getMessage());
    }

    @Test
    void testReturnBookIncreasesStock() {
        Book book = new Book("B104", "Design Patterns", 1);
        book.borrowBook();  // inStock goes from 1 -> 0
        book.returnBook();  // inStock goes from 0 -> 1
        assertEquals(1, book.getInStock());
    }
}


