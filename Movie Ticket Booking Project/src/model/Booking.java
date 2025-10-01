package model;

import java.util.List;
import model.Movie;

public class Booking {
    private int bookingId;
    private Movie movie;
    private List<String> seats;
    private double totalPrice;

    public Booking(int bookingId, Movie movie, List<String> seats, double totalPrice) {
        this.bookingId = bookingId;
        this.movie = movie;
        this.seats = seats;
        this.totalPrice = totalPrice;
    }

    public int getBookingId() { return bookingId; }
    public Movie getMovie() { return movie; }
    public List<String> getSeats() { return seats; }
    public double getTotalPrice() { return totalPrice; }
}
