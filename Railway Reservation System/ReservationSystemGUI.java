package OnlineReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReservationSystemGUI {
    private List<User> users = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private int reservationCounter = 1;
    private JFrame frame;

    // Path to your background image
    private static final String BACKGROUND_IMAGE_PATH = "images/background.jpg";

    public ReservationSystemGUI() {
        frame = new JFrame("Online Reservation System");

        // Load background image
        JLabel background = new JLabel(new ImageIcon(BACKGROUND_IMAGE_PATH));
        frame.setContentPane(background);

        // Set layout manager for background JLabel
        background.setLayout(new GridLayout(5, 1));

        // Buttons for different functionalities with color and animation
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(Color.CYAN);
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.GREEN);
        JButton reserveButton = new JButton("Make Reservation");
        reserveButton.setBackground(Color.YELLOW);
        JButton cancelButton = new JButton("Cancel Reservation");
        cancelButton.setBackground(Color.ORANGE);
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.RED);

        background.add(registerButton);
        background.add(loginButton);
        background.add(reserveButton);
        background.add(cancelButton);
        background.add(exitButton);

        // Animation Timer
        Timer timer = new Timer(50, new ActionListener() {
            int x = 0;
            int direction = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Simple horizontal movement animation for the buttons
                x += direction;
                if (x > 10 || x < -10) {
                    direction *= -1;
                }
                registerButton.setLocation(registerButton.getX() + direction, registerButton.getY());
                loginButton.setLocation(loginButton.getX() + direction, loginButton.getY());
                reserveButton.setLocation(reserveButton.getX() + direction, reserveButton.getY());
                cancelButton.setLocation(cancelButton.getX() + direction, cancelButton.getY());
                exitButton.setLocation(exitButton.getX() + direction, exitButton.getY());
            }
        });
        timer.start();

        frame.setSize(600, 400); // Increased size to accommodate the background image
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        registerButton.addActionListener(e -> registerUser());
        loginButton.addActionListener(e -> loginUser());
        reserveButton.addActionListener(e -> makeReservation());
        cancelButton.addActionListener(e -> cancelReservation());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void registerUser() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Register", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    JOptionPane.showMessageDialog(frame, "Username already taken. Please try again.");
                    return;
                }
            }

            User newUser = new User(username, password);
            users.add(newUser);
            JOptionPane.showMessageDialog(frame, "User registered successfully!");
        }
    }

    private void loginUser() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful! Welcome " + username);
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Invalid login credentials. Please try again.");
        }
    }

    private void makeReservation() {
        JTextField nameField = new JTextField();
        JTextField trainNumberField = new JTextField();
        JTextField trainNameField = new JTextField();
        JTextField classTypeField = new JTextField();
        JTextField dateOfJourneyField = new JTextField();
        JTextField fromField = new JTextField();
        JTextField toField = new JTextField();

        Object[] message = {
            "Name:", nameField,
            "Train Number:", trainNumberField,
            "Train Name:", trainNameField,
            "Class Type (e.g., AC, Sleeper):", classTypeField,
            "Date of Journey (DD-MM-YYYY):", dateOfJourneyField,
            "From:", fromField,
            "To:", toField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Make Reservation", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String trainNumber = trainNumberField.getText();
            String trainName = trainNameField.getText();
            String classType = classTypeField.getText();
            String dateOfJourney = dateOfJourneyField.getText();
            String from = fromField.getText();
            String to = toField.getText();

            Reservation reservation = new Reservation(name, trainNumber, trainName, classType, dateOfJourney, from, to, reservationCounter++);
            reservations.add(reservation);
            JOptionPane.showMessageDialog(frame, "Reservation made successfully. Your Reservation ID (PNR) is: " + reservation.getReservationId());
        }
    }

    private void cancelReservation() {
        String reservationIdStr = JOptionPane.showInputDialog(frame, "Enter Reservation ID (PNR) to cancel:");
        if (reservationIdStr != null) {
            int reservationId = Integer.parseInt(reservationIdStr);

            Reservation toRemove = null;
            for (Reservation reservation : reservations) {
                if (reservation.getReservationId() == reservationId) {
                    toRemove = reservation;
                    break;
                }
            }

            if (toRemove != null) {
                String details = String.format("Reservation Details:\nName: %s\nTrain Number: %s\nTrain Name: %s\nClass Type: %s\nDate of Journey: %s\nFrom: %s\nTo: %s",
                        toRemove.getName(), toRemove.getTrainNumber(), toRemove.getTrainName(), toRemove.getClassType(), toRemove.getDateOfJourney(), toRemove.getFrom(), toRemove.getTo());

                int confirmation = JOptionPane.showConfirmDialog(frame, details + "\n\nAre you sure you want to cancel this reservation?", "Cancel Reservation", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    reservations.remove(toRemove);
                    JOptionPane.showMessageDialog(frame, "Reservation canceled successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Cancellation aborted.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Reservation ID not found.");
            }
        }
    }

    public static void main(String[] args) {
        new ReservationSystemGUI();
    }
}
