/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohand
 */
public class Client implements Serializable {

    private static int clientsNumber = 0;
    private int clientId = 1;
    private String name;
    private String email;
    private List<Book> borrowedBooks;

    public Client(String name, String email) {
        clientsNumber++;
        this.clientId = clientsNumber;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public static Client createAccount(String name, String email) {
        return new Client(name, email);
    }

    public void manageAccount(String name, String email) {
        this.name = name;
        this.email = email;
        System.out.println("Account information updated successfully.");
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.borrowBook();
            System.out.println("Book '" + book.getTitle() + "' borrowed successfully.");
        } else {
            System.out.println("Sorry, the book '" + book.getTitle() + "' is not available for borrowing.");
        }
    }

    public void cancelBorrowing(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
            System.out.println("Borrowing for book '" + book.getTitle() + "' canceled successfully.");
        } else {
            System.out.println("The book '" + book.getTitle() + "' is not borrowed by this client.");
        }
    }

    public void updateBorrowing(Book oldBook, Book newBook) {
        cancelBorrowing(oldBook);
        borrowBook(newBook);
    }

    public void viewBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            System.out.println("Borrowed books for client '" + name + "':");
            for (Book book : borrowedBooks) {
                System.out.println(book);
            }
        }
    }

    public String viewBorrowedBooksGUI() {

        StringBuilder message = new StringBuilder();
        message.append("Borrowed  Books:\n");

        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            System.out.println("Borrowed books for client '" + name + "':");
            for (Book book : borrowedBooks) {
                if (book.isAvailable()) {
                    message.append(book).append("\n");
                }
            }
        }

        return message.toString();
    }

    @Override
    public String toString() {
        return "Client{" + "clientId=" + clientId + ", name=" + name + ", email=" + email + ", borrowedBooks=" + borrowedBooks + '}';
    }

}
