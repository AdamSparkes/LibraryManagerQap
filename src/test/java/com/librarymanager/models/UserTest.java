package com.librarymanager.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testUserCreation() {
        User user = new User("U001", "Alice");
        assertEquals("U001", user.getId());
        assertEquals("Alice", user.getName());
        assertTrue(user.getBorrowedBooks().isEmpty(), "New user should have no borrowed books");
    }

    @Test
    void testBorrowBookSuccessful() {
        User user = new User("U002", "Bob");
        Book book = new Book("B201", "Networking", 2);

        boolean borrowed = user.borrowBook(book);

        assertTrue(borrowed, "Borrow should succeed with inStock > 0 and < 3 borrowed books");
        assertEquals(1, book.getInStock(), "Book stock should reduce by 1");
        assertEquals(1, user.getBorrowedBooks().size(), "User should have 1 borrowed book");
    }

    @Test
    void testBorrowBookFailsWhenUserAlreadyHas3Books() {
        User user = new User("U003", "Charlie");
        Book book1 = new Book("B301", "Book 1", 5);
        Book book2 = new Book("B302", "Book 2", 5);
        Book book3 = new Book("B303", "Book 3", 5);
        Book book4 = new Book("B304", "Book 4", 5);

        // Borrow 3 books successfully
        user.borrowBook(book1);
        user.borrowBook(book2);
        user.borrowBook(book3);

        // Attempt to borrow 4th book
        boolean borrowed4th = user.borrowBook(book4);

        assertFalse(borrowed4th, "Borrow should fail after 3 books");
        assertEquals(3, user.getBorrowedBooks().size(), "User should only have 3 borrowed books");
        assertEquals(5, book4.getInStock(), "Stock should remain if borrowing fails");
    }

    @Test
    void testReturnBook() {
        User user = new User("U004", "Diana");
        Book book = new Book("B401", "Security Essentials", 2);

        // Borrow one book
        user.borrowBook(book);
        assertEquals(1, book.getInStock());

        // Return that book
        user.returnBook(book);
        assertEquals(2, book.getInStock(), "Stock should go back up after returning");
        assertTrue(user.getBorrowedBooks().isEmpty(), "User should have 0 borrowed books after return");
    }
}
