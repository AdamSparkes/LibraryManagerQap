package com.librarymanager.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private List<Book> borrowedBooks = new ArrayList<>();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= 3) {
            System.out.println(name + " cannot borrow more than 3 books.");
            return false;
        }
        if (book.getInStock() > 0) {
            borrowedBooks.add(book);
            book.borrowBook();
            return true;
        }
        return false;
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnBook();
        }
    }
}
