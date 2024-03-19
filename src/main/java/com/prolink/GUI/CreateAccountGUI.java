package com.prolink.GUI;

import entities.UsersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class CreateAccountGUI extends JFrame {

    public static final Color FRAME_COLOR = new Color(59, 48, 217);
    public CreateAccountGUI(){

        super("ProLink");

        setSize(400,600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        setLayout(null);

        getContentPane().setBackground(FRAME_COLOR);

        GUIcomponents();

    }

    private void GUIcomponents(){

        JPanel panel = new JPanel();
        panel.setBackground(null);
        panel.setBounds(0, 0, getWidth() - 10, 600);
        panel.setLayout(null);

        //error label to appear if username and email exist
        JLabel ExistingErrorLabel = new JLabel("Email or Username already exists OR Password Field Empty");
        ExistingErrorLabel.setForeground(Color.RED);
        ExistingErrorLabel.setBounds(15,400,500,50);
        ExistingErrorLabel.setVisible(false);
        panel.add(ExistingErrorLabel);


        JLabel CreateAccountLabel = new JLabel("Create an Account For ProLink");
        CreateAccountLabel.setForeground(Color.WHITE);
        CreateAccountLabel.setBounds(98, 150, 200, 50);
        panel.add(CreateAccountLabel);

        JTextField username = new JTextField("username...");
        username.setForeground(Color.GRAY);
        username.setBounds(95, 200, 200, 50);
        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(username.getText().equals("username...")){
                    username.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(username.getText().isEmpty()){
                    username.setText("username...");
                }
            }
        });
        panel.add(username);


        //make a text field to get user to create account into ProLink
        JTextField emailTextField = new JTextField("email address...");
        emailTextField.setForeground(Color.GRAY);
        emailTextField.setBounds(95, 250, 200, 50);
        emailTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(emailTextField.getText().equals("email address...")){
                    emailTextField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(emailTextField.getText().isEmpty()){
                    emailTextField.setText("email address...");
                }
            }
        });
        panel.add(emailTextField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(95, 300, 200, 50);
        panel.add(passwordField);

        JButton CreateAccountButton = new JButton(loadImage("src/main/java/assets/Signup.png", 100,60));
        CreateAccountButton.setBounds(95,350,110,70);
        CreateAccountButton.setBorderPainted(false);
        CreateAccountButton.setBackground(null);

        CreateAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredEmail = emailTextField.getText();
                String enteredUsername = username.getText();
                String enteredPassword = new String(passwordField.getPassword());

                if (!enteredEmail.isEmpty() &&
                        enteredEmail.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")&&
                        !enteredPassword.isEmpty()) {
                    // Email format is valid
                    if (!isExistingUser(enteredEmail, enteredUsername)) {
                        // Email and username do not exist in the database, proceed with adding information
                        ExistingErrorLabel.setVisible(false);
                        addInformationToTheDatabase(enteredUsername, enteredEmail, enteredPassword);
                        System.out.println("Info added successfully");
                        SwingUtilities.invokeLater(() -> new LoginProLinkGUI().setVisible(true));
                        dispose();
                    } else {
                        // Email or username already exists in the database
                        emailTextField.setForeground(Color.RED);
                        username.setForeground(Color.RED);
                        System.out.println("Email or username already exists");
                        ExistingErrorLabel.setVisible(true);
                    }
                } else {
                    // Email format is invalid
                    ExistingErrorLabel.setVisible(true);
                    emailTextField.setForeground(Color.RED);
                    System.out.println("Invalid email format");
                }

            }
        });


        panel.add(CreateAccountButton);


        add(panel);

    }

    private ImageIcon loadImage(String imagePath, int width, int height){
        try {
            // Read the image file from the given path
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Resize the image
            Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Return the resized image as an ImageIcon
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Could not find resource
        return null;
    }

    private int emailChecker(String emailToBeChecked){

        int count = 0;
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatApp", "root", "yasserYasser12");

            // Create a prepared statement with a SELECT query to check if the email exists
            String sql = "SELECT COUNT(*) FROM users WHERE user_email = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, emailToBeChecked);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the count of emails found
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }

    private boolean isExistingUser(String emailToBeChecked, String usernameToBeChecked) {
        boolean exists = false;
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatApp", "root", "yasserYasser12");

            // Create a prepared statement with a SELECT query to check if the email or username exists
            String sql = "SELECT COUNT(*) FROM users WHERE user_email = ? OR user_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, emailToBeChecked);
            statement.setString(2, usernameToBeChecked);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the count of existing users found
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                exists = count > 0;
            }

            // Close the resources
            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }


    private void addInformationToTheDatabase(String username, String email, String password){
        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatApp", "root", "yasserYasser12");

            // Create a prepared statement with an INSERT query to add information to the database
            String sql = "INSERT INTO users (user_name, user_email, user_password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);

            // Execute the INSERT query
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new user has been inserted successfully.");
            }

            // Close the resources
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
