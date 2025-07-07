/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

/**
 *
 * @author Mohand
 */
import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable{
    private int bookingCount = 0;
    private int bookingId = 1;
    private int clientId;
    private String bookISBN;
    private Date bookingDate;

     public Booking( int clientId, String bookISBN, Date bookingDate) {
        
        bookingCount++;
        this.bookingId = bookingCount;
        this.clientId = clientId;
        this.bookISBN = bookISBN;
        this.bookingDate = bookingDate;
    }

     public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingCount=" + bookingCount + ", bookingId=" + bookingId + ", clientId=" + clientId + ", bookISBN=" + bookISBN + ", bookingDate=" + bookingDate + '}';
    }

   
}
