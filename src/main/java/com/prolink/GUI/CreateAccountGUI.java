package com.prolink.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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


        JLabel CreateAccountLabel = new JLabel("Create an Account For ProLink");
        CreateAccountLabel.setForeground(Color.WHITE);
        CreateAccountLabel.setBounds(135, 150, 200, 50);
        panel.add(CreateAccountLabel);


        //make a text field to get user to create account into ProLink
        JTextField emailTextField = new JTextField("email address...");
        emailTextField.setForeground(Color.GRAY);
        emailTextField.setBounds(50, 120, 300, 50);
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

        //add username
        JTextField username = new JTextField("username...");
        username.setForeground(Color.GRAY);
        username.setBounds(95, 250, 200, 50);
        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(emailTextField.getText().equals("password...")){
                    emailTextField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(emailTextField.getText().isEmpty()){
                    emailTextField.setText("password...");
                }
            }
        });
        panel.add(username);

        add(panel);

    }

}
