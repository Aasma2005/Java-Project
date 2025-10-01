package service;

import model.Booking;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private List<Booking> bookings = new ArrayList<>();
    private int bookingIdCounter = 1;

    public List<Booking> getAllBookings() { return bookings; }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        saveBooking(booking);
        bookingIdCounter++;
    }

    private void saveBooking(Booking booking) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/bookings.txt", true))) {
            bw.write("BookingID:" + booking.getBookingId() +
                     ",Movie:" + booking.getMovie().getName() +
                     ",Seats:" + booking.getSeats() +
                     ",Total:" + booking.getTotalPrice());
            bw.newLine();
        } catch(IOException e) { e.printStackTrace(); }
    }
}
