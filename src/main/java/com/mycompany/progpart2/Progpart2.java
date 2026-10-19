package com.mycompany.progpart2;

import javax.swing.JOptionPane;

public class Progpart2 {

    public static void main(String[] args) {

        //  REGISTRATION (Pop-up boxes) 
        String firstName = JOptionPane.showInputDialog(null, "Enter your first name:", "REGISTRATION", JOptionPane.QUESTION_MESSAGE);
        if (firstName == null) return;

        String lastName = JOptionPane.showInputDialog(null, "Enter your last name:", "REGISTRATION", JOptionPane.QUESTION_MESSAGE);
        if (lastName == null) return;

        String username = JOptionPane.showInputDialog(null, "Enter your username (must contain _ and <=5 chars):", "REGISTRATION", JOptionPane.QUESTION_MESSAGE);
        if (username == null) return;

        String password = JOptionPane.showInputDialog(null, "Enter your password (8 chars, capital, number, special):", "REGISTRATION", JOptionPane.QUESTION_MESSAGE);
        if (password == null) return;

        String cellPhoneNumber = JOptionPane.showInputDialog(null, "Enter your South African cellphone number (+27...):", "REGISTRATION", JOptionPane.QUESTION_MESSAGE);
        if (cellPhoneNumber == null) return;

        Login login = new Login(username, password, cellPhoneNumber, firstName, lastName);
        String registrationMessage = login.registerUser();

        JOptionPane.showMessageDialog(null, registrationMessage, "Registration Status", JOptionPane.INFORMATION_MESSAGE);

        if (!registrationMessage.equals("The two above conditions have been met, and the user has been registered successfully.")) {
            JOptionPane.showMessageDialog(null, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // LOGIN 
        String loginUsername = JOptionPane.showInputDialog(null, "Login - Enter your username:", "LOGIN", JOptionPane.QUESTION_MESSAGE);
        if (loginUsername == null) return;

        String loginPassword = JOptionPane.showInputDialog(null, "Login - Enter your password:", "LOGIN", JOptionPane.QUESTION_MESSAGE);
        if (loginPassword == null) return;

        login.loginUser(loginUsername, loginPassword);
        JOptionPane.showMessageDialog(null, login.returnLoginStatus(), "Login Status", JOptionPane.INFORMATION_MESSAGE);

        if (!login.returnLoginStatus().toLowerCase().contains("welcome")) {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting QuickChat.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // QUICKCHAT 
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.", "QuickChat", JOptionPane.INFORMATION_MESSAGE);

        int totalMessagesSent = 0;
        int messageCounter = 0;
        boolean running = true;

        while (running) {
            String[] options = {"1) Send Messages", "2) Show recently sent messages", "3) Quit"};
            int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an option:\n1) Send Messages\n2) Show recently sent messages (Coming Soon)\n3) Quit",
                "QuickChat Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == 0) { // Send Messages
                String numStr = JOptionPane.showInputDialog(null, "How many messages do you wish to send?", "Send Messages", JOptionPane.QUESTION_MESSAGE);
                if (numStr == null) continue;

                int numMessages;
                try {
                    numMessages = Integer.parseInt(numStr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                for (int i = 0; i < numMessages; i++) {
                    String recipient = JOptionPane.showInputDialog(null, "Message " + (i+1) + "/" + numMessages + "\nEnter recipient cell (+27...):", "Recipient", JOptionPane.QUESTION_MESSAGE);
                    if (recipient == null) { i--; continue; }

                    String content = JOptionPane.showInputDialog(null, "Enter message (max 250 chars):", "Message", JOptionPane.QUESTION_MESSAGE);
                    if (content == null) { i--; continue; }

                    Message msg = new Message(recipient, content, messageCounter);

                    String recipientCheck = msg.checkRecipientCell();
                    JOptionPane.showMessageDialog(null, recipientCheck);
                    if (!recipientCheck.equals("Cell phone number successfully captured.")) {
                        JOptionPane.showMessageDialog(null, "Message not sent. Enter again.", "Error", JOptionPane.ERROR_MESSAGE);
                        i--;
                        continue;
                    }

                    String lengthCheck = msg.checkMessageLength();
                    JOptionPane.showMessageDialog(null, lengthCheck);
                    if (!lengthCheck.equals("Message ready to send.")) {
                        JOptionPane.showMessageDialog(null, "Message not sent. Enter again.", "Error", JOptionPane.ERROR_MESSAGE);
                        i--;
                        continue;
                    }

                    JOptionPane.showMessageDialog(null, "Message ID: " + msg.getMessageId() + "\nMessage Hash: " + msg.createMessageHash(), "Message Details", JOptionPane.INFORMATION_MESSAGE);

                    String[] actionOptions = {"Send", "Store", "Disregard"};
                    int action = JOptionPane.showOptionDialog(null, "What would you like to do?", "Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, actionOptions, actionOptions[0]);

                    if (action == 0) {
                        totalMessagesSent++;
                        messageCounter++;
                        JOptionPane.showMessageDialog(null, "Message Sent!\n\n" + msg.printMessages(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else if (action == 1) {
                        msg.storeMessage();
                        messageCounter++;
                        JOptionPane.showMessageDialog(null, "Message stored.", "Stored", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Message disregarded.", "Disregarded", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessagesSent);

            } else if (choice == 1) {
                JOptionPane.showMessageDialog(null, "Coming Soon.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else if (choice == 2 || choice == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Quitting QuickChat. Total messages sent: " + totalMessagesSent, "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                running = false;
            }
        }
    }
}