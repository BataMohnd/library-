/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mohand
 */
public class LibrarySystem implements Serializable {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Book> books = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();
        Employee employee = new Employee(books);
        List<Client> clients = new ArrayList<>();

        books = (List<Book>) FileHandler.loadData("books.dat");
        bookings = (List<Booking>) FileHandler.loadData("bookings.dat");
        clients = (List<Client>) FileHandler.loadData("clients.dat");

        while (true) {
            System.out.println("Welcome to the Library System!");
            System.out.println("Choose user type:");
            System.out.println("1. Employee");
            System.out.println("2. Client");
            System.out.println("3. Exit");

            int userTypeChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userTypeChoice) {
                case 1:
                    System.out.println("Enter Password: ");
                    String password = scanner.nextLine();

                    if (password.equals("pass123456")) {
                        employeeMenu(employee, books, bookings, clients, scanner);
                    } else {
                        System.out.println("Wrong password Try again.");
                    }
                    break;
                case 2:
                    System.out.println("Enter Your Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter Your Email: ");
                    String email = scanner.nextLine();

                    int flagFound = 0;
                    Client foundClient = null;
                    for (Client client : clients) {
                        if (client.getEmail().equals(email)) {
                            flagFound = 1;
                            foundClient = client;
                        }
                    }

                    if (flagFound == 1) {
                        clientMenu(foundClient, books, bookings, clients, scanner);
                    } else {
                        Client c = new Client(name, email);
                        clients.add(c);
                        saveData(books, bookings, clients);
                        clientMenu(c, books, bookings, clients, scanner);
                    }

                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void employeeMenu(Employee employee, List<Book> books, List<Booking> bookings, List<Client> clients, Scanner scanner) {
        while (true) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Update Book");
            System.out.println("4. Generate Reports");
            System.out.println("5. Back to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter book ISBN:");
                    String ISBN = scanner.nextLine();
                    System.out.println("Enter book title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter book author:");
                    String author = scanner.nextLine();
                    System.out.println("Enter book fare:");
                    double fare = scanner.nextDouble();

                    employee.addBook(ISBN, title, author, fare);
                    saveData(books, bookings, clients);
                    break;
                case 2:
                    System.out.println("Enter book ISBN to remove:");
                    String removeISBN = scanner.nextLine();
                    employee.removeBook(removeISBN);
                    saveData(books, bookings, clients);
                    break;
                case 3:
                    System.out.println("Enter book ISBN to update:");
                    String updateISBN = scanner.nextLine();
                    System.out.println("Enter new title:");
                    String newTitle = scanner.nextLine();
                    System.out.println("Enter new author:");
                    String newAuthor = scanner.nextLine();
                    System.out.println("Enter book fare:");
                    double newFare = scanner.nextDouble();
                    employee.updateBook(updateISBN, newTitle, newAuthor, newFare);
                    saveData(books, bookings, clients);
                    break;
                case 4:
                    System.out.println("Available Books:");
                    for (Book book : books) {
                        System.out.println(book);
                    }

                    System.out.println("Choose a book to generate Report");
                    System.out.println("Enter book ISBN:");
                    String bookISBN = scanner.nextLine();

                    for (Book book : books) {
                        if (book.getISBN().equals(bookISBN)) {
                            employee.generateReports(book);
                        }
                    }

                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void clientMenu(Client client, List<Book> books, List<Booking> bookings, List<Client> clients, Scanner scanner) {
        while (true) {
            System.out.println("\nClient Menu:");
            System.out.println("1. Show Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. View Borrowed Books");
            System.out.println("5. Back to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Available Books:");
                    for (Book book : books) {
                        if (book.isAvailable()) {
                            System.out.println(book);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter book ISBN to borrow:");
                    String borrowISBN = scanner.nextLine();
                    Book borrowBook = findBookByISBN(books, borrowISBN);
                    if (borrowBook != null) {
                        client.borrowBook(borrowBook);
                        Booking b = new Booking(client.getClientId(), borrowBook.getISBN(),
                                new Date());
                        bookings.add(b);
                        saveData(books, bookings, clients);
                    } else {
                        System.out.println("Book with ISBN " + borrowISBN + " not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter book ISBN to return:");
                    String returnISBN = scanner.nextLine();
                    Book returnBook = findBookByISBN(books, returnISBN);
                    if (returnBook != null) {
                        client.cancelBorrowing(returnBook);
                        for (Booking booking : bookings) {
                            if (booking.getBookISBN().equals(returnBook.getISBN())) {
                                bookings.remove(booking);
                                saveData(books, bookings, clients);
                            }
                        }
                    } else {
                        System.out.println("Book with ISBN " + returnISBN + " not found.");
                    }
                    break;
                case 4:
                    System.out.println("Borrowed Books:");
                    client.viewBorrowedBooks();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static Book findBookByISBN(List<Book> books, String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }

    public static void saveData(List<Book> books, List<Booking> bookings,
            List<Client> clients) {
        FileHandler.saveData(books, "books.dat");
        FileHandler.saveData(bookings, "bookings.dat");
        FileHandler.saveData(clients, "clients.dat");
    }

  
}
