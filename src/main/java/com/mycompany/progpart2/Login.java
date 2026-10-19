/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpart2;

/**
 *
 * @author thimn
 */

public class Login {

    // Variables
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    private boolean loginStatus;

    // Constructor
    public Login(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginStatus = false;
    }

    // Check username - must contain _ and <=5 chars
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    // Check password complexity
    public boolean checkPasswordComplexity() {
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        boolean hasEnoughCharacters = password.length() >= 8;
        return hasCapital && hasNumber && hasSpecial && hasEnoughCharacters;
    }

    // Check cellphone number - +27 + 9 digits
    public boolean checkCellPhoneNumber() {
        String regex = "^\\+27[0-9]{9}$";
        return cellPhoneNumber.matches(regex);
    }

    // Register user
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        return "The two above conditions have been met, and the user has been registered successfully.";
    }

    // Login user
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
            loginStatus = true;
            return true;
        }
        loginStatus = false;
        return false;
    }

    // Return login status
    public String returnLoginStatus() {
        if (loginStatus) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    //  Get login status and retrieve the user's first and last name
    public boolean isLoggedIn() {
        return loginStatus;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
}