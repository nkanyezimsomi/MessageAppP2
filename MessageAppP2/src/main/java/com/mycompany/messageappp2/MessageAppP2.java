/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.messageappp2;


/**
 *Title: ChatApp.java
 * Authour: OpenAi
 *Date: 20 April
 * It help me create and solve issues about the POE
 */

/**
 *Title: ChatApp.java
 * Authour: OpenAi
 *Date: 23 May
 * It help me create and solve issues about the POE part 2
 */

import javax.swing.*;
import java.util.*;
import java.io.FileWriter;
import org.json.simple.JSONObject;

public class MessageAppP2 {
    static List<Message> sentMessages = new ArrayList<>();

    public static void main(String[] args) {
        // User Registration
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String surname = JOptionPane.showInputDialog("Enter your surname:");

        LOGIN login = new LOGIN(firstName, surname, "placeholder");

        String username = JOptionPane.showInputDialog("Create a username:");
        String password = JOptionPane.showInputDialog("Create a password:");
        String phone = JOptionPane.showInputDialog("Enter your cellphone number (e.g. +27821234567):");

        boolean validUsername = login.checkUserName(username);
        boolean validPassword = login.checkPasswordComplexity(password);
        boolean validPhone = login.checkCellPhoneNumber(phone);

        if (!validUsername) {
            JOptionPane.showMessageDialog(null, "Invalid username.\nMust contain an underscore and be no more than 5 characters.");
            System.exit(0);
        }

        if (!validPassword) {
            JOptionPane.showMessageDialog(null, "Invalid password.\nMust be at least 8 characters and include a lowercase letter, number, and special character.");
            System.exit(0);
        }

        if (!validPhone) {
            JOptionPane.showMessageDialog(null, "Invalid phone number.\nMust start with +27 followed by 9 digits.");
            System.exit(0);
        }

        String registrationResult = login.regUser(username, password, phone);
        JOptionPane.showMessageDialog(null, registrationResult);

        // User Login
        boolean loggedIn = false;
        while (!loggedIn) {
            String inputUsername = JOptionPane.showInputDialog("Enter your username to login:");
            String inputPassword = JOptionPane.showInputDialog("Enter your password:");

            loggedIn = login.loginUser(inputUsername, inputPassword);
            JOptionPane.showMessageDialog(null, login.returnLoginStatus(firstName, surname, loggedIn));
        }

        // Main menu
        while (true) {
            String[] options = {"Send Messages", "Show Recently Sent Messages", "Quit"};
            int option = JOptionPane.showOptionDialog(null, "Choose an option:", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (option) {
                case 0 -> handleMessageSending();
                case 1 -> JOptionPane.showMessageDialog(null, "Coming Soon.");
                case 2 -> {
                    JOptionPane.showMessageDialog(null, "Exiting. Total messages sent: " + Message.returnTotalMessages());
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }

    private static void handleMessageSending() {
        int total;
        try {
            total = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid number.");
            return;
        }

        for (int i = 0; i < total; i++) {
            String phone = JOptionPane.showInputDialog("Enter recipient phone number:");
            String msg = JOptionPane.showInputDialog("Enter your message:");

            if (msg == null || msg.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (msg.length() - 250) + ", please reduce size.");
                i--; // retry
                continue;
            }

            Message message = new Message(phone, msg);

            String[] actions = {"Send Message", "Disregard", "Store Message"};
            int choice = JOptionPane.showOptionDialog(null, "Select action:", "Action",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, actions, actions[0]);

            JOptionPane.showMessageDialog(null, message.sentMessage(choice + 1));

            if (choice == 0) { // Send
                sentMessages.add(message);
                JOptionPane.showMessageDialog(null, message.printMessage());
            } else if (choice == 2) { // Store
                storeMessage(message);
            }
        }
    }

    private static void storeMessage(Message message) {
        JSONObject msgObj = new JSONObject();
        msgObj.put("MessageID", message.getMessageID());
        msgObj.put("MessageHash", message.getMessageHash());
        msgObj.put("Recipient", message.getRecipient());
        msgObj.put("Message", message.getMessageText());

        try (FileWriter file = new FileWriter("message.json")) {
            file.write(msgObj.toJSONString());
            file.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving message: " + e.getMessage());
        }
    }
}