package com.prolink.GUI;

import javax.swing.*;
import java.awt.*;

public class LoggedInGUI extends JFrame {

    public LoggedInGUI(){
        super("ProLink");

        setSize(400,600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        setLayout(null);

        getContentPane().setBackground(new Color(59, 48, 217));

        LoggedInFunctionality();
    }

    private void LoggedInFunctionality(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setLayout(null);

        JLabel LoginLabel = new JLabel("Login To ProLink");
        LoginLabel.setForeground(Color.black);
        LoginLabel.setBounds(138, 150, 200, 50);
        panel.add(LoginLabel);

        add(panel);
    }

}
