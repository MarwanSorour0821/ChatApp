package com.prolink.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.SocketOption;
import java.sql.*;

public class LoginProLinkGUI extends JFrame {

    public static final Color FRAME_COLOR = new Color(59, 48, 217);
    public static final Color TEXT_COLOR = Color.WHITE;
    public LoginProLinkGUI(){

        super("ProLink");

        setSize(400,600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        //change background to white
        getContentPane().setBackground(FRAME_COLOR);

        addTextsOnGUI();
    }


    private void addTextsOnGUI(){

        JPanel panel = new JPanel();
        panel.setBackground(FRAME_COLOR);
        panel.setLayout(null);

        JLabel LoginLabel = new JLabel("Login To ProLink");
        LoginLabel.setForeground(TEXT_COLOR);
        LoginLabel.setBounds(138, 150, 200, 50);
        panel.add(LoginLabel);

        JLabel ErrorLabel = new JLabel("Wrong Email or Password");
        ErrorLabel.setForeground(Color.RED);
        ErrorLabel.setBounds(120,390,200,50);
        ErrorLabel.setVisible(false);
        panel.add(ErrorLabel);


        //make a text field to get user to login into ProLink
        JTextField emailTextField = new JTextField("email address...");
        emailTextField.setForeground(Color.GRAY);
        emailTextField.setBounds(95, 200, 200, 50);

        //added focus listener to show what input each text box should have i.e Placeholder
        emailTextField.addFocusListener(new FocusListener(){
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
        passwordField.setBounds(95, 250, 200, 50);
        panel.add(passwordField);

        //Login button
        JButton LoginButton = new JButton(loadImage("src/main/java/assets/Login23.png", 100,60));
        LoginButton.setBounds(85, 320, 110,70);
        LoginButton.setBorderPainted(false);
        LoginButton.setBackground(null);
        LoginButton.addActionListener(e -> {
            String url = "jdbc:mysql://localhost:3306/chatApp";
            String username = "root";
            String password = "yasserYasser12";

            String emailToCheck = emailTextField.getText(); // The email you want to check
            String passwordToCheck = passwordField.getText();

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "SELECT COUNT(*) FROM users WHERE user_email = ? AND user_password = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, emailToCheck);
                    stmt.setString(2, passwordToCheck);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            int count = rs.getInt(1);
                            if (count > 0) {
                                //We can implement functionality here after logging in
                                ErrorLabel.setVisible(false);
                                System.out.println("Email and password match.");
                                activateLogInGUI();
                            } else {
                                System.out.println("Email and password do not match.");
                                ErrorLabel.setVisible(true);
                            }
                        }
                    }
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        });
        panel.add(LoginButton);

        //To have the option to register in ProLink
        JButton CreateAccountButton = new JButton(loadImage("src/main/java/assets/Signup.png", 100,60));
        CreateAccountButton.setBounds(195,320,110,70);
        CreateAccountButton.setBorderPainted(false);
        CreateAccountButton.setBackground(null);
        CreateAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new CreateAccountGUI().setVisible(true);
                    }
                });
            }
        });
        panel.add(CreateAccountButton);


        add(panel);

    }

    //Load images to put onto GUI
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

    private void activateLogInGUI(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoggedInGUI().setVisible(true);
            }
        });
        dispose();
    }

}
