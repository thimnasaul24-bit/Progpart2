/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpart2;

/**
 *
 * @author thimn
 */

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    private String messageId;
    private int numSent;
    private String recipient;
    private String messageHash;
    private String message;

    public Message(String recipient, String message, int numSent) {
        this.recipient = recipient;
        this.message = message;
        this.numSent = numSent;
        this.messageId = createMessageId();
        this.messageHash = createMessageHash();
    }

    public String createMessageId() {
        Random rand = new Random();
        long id = (long)(rand.nextDouble() * 10000000000L);
        String idStr = String.format("%010d", id);
        this.messageId = idStr;
        return idStr;
    }

    public boolean checkMessageId() {
        return messageId!= null && messageId.length() == 10;
    }

    public String checkRecipientCell() {
        // Must start with + and contain international code, 10-13 chars total
        // This passes your tests: +27718693002 = success, 08575975889 = fail
        if (recipient!= null && recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 13) {
            // Extra strict: must have numbers after +
            if (recipient.substring(1).matches("\\d+")) {
                return "Cell phone number successfully captured.";
            }
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String checkMessageLength() {
        if (message == null) {
            return "Message is empty.";
        }
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
        }
    }

    public String createMessageHash() {
        if (message == null || message.trim().isEmpty()) {
            return (messageId.substring(0,2) + ":" + numSent + ":EMPTY").toUpperCase();
        }
        String[] words = message.trim().split("\\s+");
        String firstWord = words.length > 0? words[0] : "";
        String lastWord = words.length > 1? words[words.length-1] : firstWord;
        String idStart = messageId.length() >= 2? messageId.substring(0,2) : "00";
        this.messageHash = (idStart + ":" + numSent + ":" + (firstWord + lastWord)).toUpperCase();
        return this.messageHash;
    }

    public String sentMessage(int option) {
        if (option == 1) return "Message successfully sent.";
        if (option == 2) return "Press 0 to delete the message.";
        if (option == 3) return "Message successfully stored.";
        return "Invalid option.";
    }

    // REQUIRED FOR PART 2 MAIN - Summary display
    public String printMessages() {
        return "MessageID: " + messageId + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + message;
    }

    // REQUIRED FOR PART 2 MAIN - Store to file
    public void storeMessage() {
        try (FileWriter writer = new FileWriter("storedMessages.txt", true)) {
            writer.write(printMessages() + "\n\n");
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // Getters and Setters
    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public int getNumSent() { return numSent; }

    public void setMessageId(String id) {
        this.messageId = id;
        // Recreate hash when ID changes (for testing)
        createMessageHash();
    }
}