package com.librarymanager;

import com.librarymanager.models.Book;
import com.librarymanager.models.User;
import com.librarymanager.services.LibraryService;
import java.util.Scanner;

public class Main {
    private static final LibraryService library = new LibraryService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadSampleData();
        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Register User");
            System.out.println("2. Delete User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Available Books");
            System.out.println("6. List Users");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> deleteUser();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> library.displayAvailableBooks();
                case 6 -> library.listUsers();
                case 7 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        library.registerUser(new User(id, name));
        System.out.println("User registered successfully.");
    }

    private static void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        String id = scanner.nextLine();
        library.deleteUser(id);
    }

    private static void borrowBook() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String bookTitle = scanner.nextLine();
        library.borrowBook(userId, bookTitle);
    }

    private static void returnBook() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String bookTitle = scanner.nextLine();
        library.returnBook(userId, bookTitle);
    }

    private static void loadSampleData() {
        library.addBook(new Book("101", "Java Basics", 5));
        library.addBook(new Book("102", "Data Structures", 3));
        library.addBook(new Book("103", "Algorithms", 2));
        library.registerUser(new User("1", "Alice"));
        library.registerUser(new User("2", "Bob"));
    }
}
