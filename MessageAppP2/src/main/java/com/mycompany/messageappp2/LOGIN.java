/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messageappp2;

import java.util.function.BooleanSupplier;
import java.util.regex.Pattern;

/**
 *Title: ChatApp.java
 * Authour: OpenAi
 *Date: 20 April
 * It help me create and solve issues about the POE
 */
public class LOGIN {
    private String registeredUsername;
    private String registeredPassword;
    private String registeredPhoneNumber;
    private String firstName;
    private String surname;

    public LOGIN (String firstName, String surname, String string) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasSpecial = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        boolean hasDigit = Pattern.compile("\\d").matcher(password).find();
        boolean hasUpper = Pattern.compile("[a-z]").matcher(password).find();
        return password.length() >= 8 && hasUpper && hasDigit && hasSpecial;
    }

    // Referenced from: OpenAI's ChatGPT — https://chat.openai.com ()
    public boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+27\\d{9}$");
    }

    public String regUser(String username, String password, String phoneNumber) {
        boolean validUsername = checkUserName(username);
        boolean validPassword = checkPasswordComplexity(password);
        boolean validPhone = checkCellPhoneNumber(phoneNumber);

        StringBuilder output = new StringBuilder();

        if (!validUsername) {
            output.append("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.\n");
        } else {
            output.append("Username successfully captured.\n");
        }

        if (!validPassword) {
            output.append("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.\n");
        } else {
            output.append("Password successfully captured.\n");
        }

        if (!validPhone) {
            output.append("Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.");
        } else {
            output.append("Cell number successfully captured.");
        }

        if (validUsername && validPassword && validPhone) {
            this.registeredUsername = username;
            this.registeredPassword = password;
            this.registeredPhoneNumber = phoneNumber;
        }

        return output.toString().trim(); 
    }

    public boolean loginUser(String inputUsername, String inputPassword) {
        return inputUsername.equals(registeredUsername) && inputPassword.equals(registeredPassword);
    }

    public String returnLoginStatus(String kyle, String smith, boolean loginStatus) {
        if (loginStatus) {
            return "Welcome " + firstName + " " + surname + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
        
    }

    String registerUser(String username, String password, String phone) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    BooleanSupplier checkCellPhoneNumber() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}