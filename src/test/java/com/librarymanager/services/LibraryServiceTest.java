package com.librarymanager.services;

import com.librarymanager.models.Book;
import com.librarymanager.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {

    private LibraryService library;

    @BeforeEach
    void setUp() {
        library = new LibraryService();
        // Optionally add some sample data
        library.addBook(new Book("B100", "Intro to Java", 2));
        library.registerUser(new User("U100", "Eva"));
    }

    @Test
    void testAddAndSearchBook() {
        Book book = new Book("B101", "Data Structures", 4);
        library.addBook(book);

        // searchBook might filter on exact title ignoring case
        Book result = library.searchBook("Data Structures");
        assertNotNull(result, "Expected to find the book");
        assertEquals("B101", result.getId());
    }

    @Test
    void testSearchBookNotFound() {
        // Searching for a book that doesn't exist or is out of stock
        assertNull(library.searchBook("Does Not Exist"),
                "Should return null if book doesn't exist or no stock"
        );
    }

    @Test
    void testRegisterUserAndBorrowBookSuccess() {
        User user = new User("U101", "Frank");
        library.registerUser(user);

        // Borrow "Intro to Java" (added in @BeforeEach)
        library.borrowBook("U101", "Intro to Java");

        Book borrowedBook = library.searchBook("Intro to Java");
        assertNotNull(borrowedBook, "Book is still in library, but stock changes");
        assertEquals(1, borrowedBook.getInStock(), "Stock should decrement from 2 -> 1");

        User actualUser = library.getUsers().get("U101");
        assertNotNull(actualUser);
        assertEquals(1, actualUser.getBorrowedBooks().size());
    }

    @Test
    void testBorrowBookForNonExistentUser() {
        // Attempt borrowing with an invalid user ID
        library.borrowBook("U999", "Intro to Java");

        // Should do nothing or show error
        // We expect the stock to remain at 2 (from initial setup)
        Book book = library.searchBook("Intro to Java");
        assertNotNull(book);
        assertEquals(2, book.getInStock(),
                "Stock should not change for invalid user or if borrowing fails"
        );
    }

    @Test
    void testDeleteUser() {
        library.registerUser(new User("U102", "Grace"));
        assertNotNull(library.getUsers().get("U102"), "User should exist before deletion");

        library.deleteUser("U102");
        assertNull(library.getUsers().get("U102"), "User should be removed after deletion");
    }
}
