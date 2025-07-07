/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.io.Serializable;

/**
 *
 * @author Mohand
 */
public class Book implements Serializable{
    private String ISBN;
    private String title;
    private String author;
    private boolean available;
    private double fare;
    private int numberOfBookings = 0;

    public Book(String ISBN, String title, String author, double fare) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.available = true; 
        this.fare = fare;
    }

     public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public int getNumberOfBookings() {
        return numberOfBookings;
    }

    public void setNumberOfBookings(int numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
    }

    
    
    
     public void borrowBook() {
        if (available) {
            available = false;
            numberOfBookings++;
            System.out.println("Book '" + title + "' borrowed successfully.");
        } else {
            System.out.println("Sorry, the book '" + title + "' is not available for borrowing.");
        }
    }

     public void returnBook() {
        if (!available) {
            available = true;
            System.out.println("Book '" + title + "' returned successfully.");
        } else {
            System.out.println("The book '" + title + "' has already been returned.");
        }
    }

    @Override
    public String toString() {
        return "Book{" + "ISBN=" + ISBN + ", title=" + title + ", author=" + author + ", available=" + available + ", fare=" + fare + ", numberOfBookings=" + numberOfBookings + '}';
    }

    
}