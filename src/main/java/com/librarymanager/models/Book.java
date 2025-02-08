package com.librarymanager.models;

public class Book {
    private String id;
    private String title;
    private int inStock;

    public Book(String id, String title, int inStock) {
        this.id = id;
        this.title = title;
        this.inStock = inStock;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getInStock() {
        return inStock;
    }

    public void borrowBook() {
        if (inStock > 0) {
            inStock--;
        } else {
            throw new IllegalStateException("No copies available.");
        }
    }

    public void returnBook() {
        inStock++;
    }

    @Override
    public String toString() {
        return title + " (Available: " + inStock + ")";
    }
}
