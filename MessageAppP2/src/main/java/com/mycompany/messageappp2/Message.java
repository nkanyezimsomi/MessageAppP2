/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messageappp2;

/**
 *Title: ChatApp.java
 * Authour: OpenAi
 *Date: 23 May
 * It help me create and solve issues about the POE part 2
 */

import java.util.Random;

public class Message {
    private static int messageCounter = 0;
    private final String messageID;
    private final String recipient;
    private final String messageText;
    private final String messageHash;

    public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        messageCounter++;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random random = new Random();
        long id = 1000000000L + (long) (random.nextDouble() * 8999999999L);
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return recipient.startsWith("+") && recipient.length() <= 13;
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return messageID.substring(0, 2) + ":" + messageCounter + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
    }

    public String sentMessage(int option) {
        return switch (option) {
            case 1 -> "Message successfully sent.";
            case 2 -> "Press 0 to delete message.";
            case 3 -> "Message successfully stored.";
            default -> "Invalid option.";
        };
    }

    public String printMessage() {
        return "Message ID: " + messageID +
               "\nHash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + messageText;
    }

    public static int returnTotalMessages() {
        return messageCounter;
    }

    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
}
