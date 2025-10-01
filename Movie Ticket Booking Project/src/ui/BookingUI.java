package ui;

import model.Booking;
import service.BookingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookingUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private BookingService bookingService;

    public BookingUI() {
        bookingService = new BookingService();

        frame = new JFrame("My Bookings");
        frame.setSize(600,400);
        frame.setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Booking ID","Movie","Seats","Total"},0);
        table = new JTable(model);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        loadBookings();

        frame.setVisible(true);
    }

    private void loadBookings() {
        model.setRowCount(0);
        for(Booking b : bookingService.getAllBookings()) {
            model.addRow(new Object[]{
                    b.getBookingId(),
                    b.getMovie().getName(),
                    b.getSeats(),
                    b.getTotalPrice()
            });
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(BookingUI::new);
    }
}
