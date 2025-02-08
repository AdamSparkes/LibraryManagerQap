package com.librarymanager.services;

import com.librarymanager.models.Book;
import com.librarymanager.models.User;
import java.util.*;

public class LibraryService {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void registerUser(User user) {
        users.put(user.getId(), user);
    }

    public void deleteUser(String userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void borrowBook(String userId, String bookTitle) {
        User user = users.get(userId);
        Book book = searchBook(bookTitle);
        if (user != null && book != null) {
            user.borrowBook(book);
        } else {
            System.out.println("Book or User not found.");
        }
    }

    public void returnBook(String userId, String bookTitle) {
        User user = users.get(userId);
        if (user != null) {
            Book bookToReturn = user.getBorrowedBooks().stream()
                    .filter(b -> b.getTitle().equalsIgnoreCase(bookTitle))
                    .findFirst()
                    .orElse(null);
            if (bookToReturn != null) {
                user.returnBook(bookToReturn);
            } else {
                System.out.println("User has not borrowed this book.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public Book searchBook(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && book.getInStock() > 0)
                .findFirst()
                .orElse(null);
    }

    public void displayAvailableBooks() {
        books.values().stream()
                .filter(book -> book.getInStock() > 0)
                .forEach(book -> System.out.println(book.getTitle() + " - Available: " + book.getInStock()));
    }

    public void listUsers() {
        users.values().forEach(user ->
            System.out.println("User: " + user.getName() + " (ID: " + user.getId() + ") | Borrowed: " + user.getBorrowedBooks().size()));
    }
}
