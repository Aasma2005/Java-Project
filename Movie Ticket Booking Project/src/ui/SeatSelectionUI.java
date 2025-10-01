package ui;

import model.Movie;
import model.Booking;
import service.BookingService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SeatSelectionUI {
    private JFrame frame;
    private Movie movie;
    private JButton[][] seats;
    private List<String> selectedSeats = new ArrayList<>();
    private BookingService bookingService = new BookingService();

    public SeatSelectionUI(Movie movie){
        this.movie = movie;
        frame = new JFrame("Select Seats - " + movie.getName());
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());

        JPanel seatPanel = new JPanel(new GridLayout(5,5,5,5));
        seats = new JButton[5][5];

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                String seatNo = (char)('A'+i) + String.valueOf(j+1);
                JButton btn = new JButton(seatNo);
                btn.addActionListener(e -> {
                    if(selectedSeats.contains(seatNo)){
                        selectedSeats.remove(seatNo);
                        btn.setBackground(null);
                    } else {
                        selectedSeats.add(seatNo);
                        btn.setBackground(Color.GREEN);
                    }
                });
                seats[i][j] = btn;
                seatPanel.add(btn);
            }
        }

        JButton confirmBtn = new JButton("Confirm Booking");
        confirmBtn.addActionListener(e -> {
            if(selectedSeats.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Select at least one seat!");
                return;
            }
            double total = selectedSeats.size() * movie.getPrice();
            Booking booking = new Booking(1, movie, selectedSeats, total);
            bookingService.addBooking(booking);
            JOptionPane.showMessageDialog(frame, "Booking Confirmed! Total: ₹" + total);
            frame.dispose();
        });

        frame.add(seatPanel, BorderLayout.CENTER);
        frame.add(confirmBtn, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
