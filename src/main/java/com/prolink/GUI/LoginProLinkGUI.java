package com.prolink.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.SocketOption;
import java.sql.SQLOutput;

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
        LoginLabel.setBounds(150, 150, 200, 50);
        panel.add(LoginLabel);


        //make a text field to get user to login into ProLink
        JTextField emailTextField = new JTextField("email address...");
        emailTextField.setForeground(Color.GRAY);
        emailTextField.setBounds(110, 200, 200, 50);

        //added focus listener to show what input each text box should have
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

        JTextField passwordTextField = new JTextField(("password..."));
        passwordTextField.setForeground(Color.GRAY);
        passwordTextField.setBounds(110,250,200,50);
        passwordTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(passwordTextField.getText().equals("password...")){
                    passwordTextField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(passwordTextField.getText().isEmpty()){
                    passwordTextField.setText("password...");
                }
            }
        });
        panel.add(passwordTextField);

        JButton LoginButton = new JButton(loadImage("src/main/java/assets/login1.png", 220,150));
        LoginButton.setBounds(110, 320, 180,50);
        LoginButton.setBorderPainted(false);
        LoginButton.setBackground(null);
        panel.add(LoginButton);

        JButton CreateAccountButton = new JButton(loadImage("src/main/java/assets/register-button-png-11.png", 220,120));
        CreateAccountButton.setBounds(110,360,180,50);
        CreateAccountButton.setBorderPainted(false);
        CreateAccountButton.setBackground(null);
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

}
