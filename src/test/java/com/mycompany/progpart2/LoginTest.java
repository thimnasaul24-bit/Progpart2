/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpart2;

/**
 *
 * @author thimn
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Thimna", "Saul");
        assertTrue(login.checkUserName());
    }
    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Thimna", "Saul");
        assertFalse(login.checkUserName());
    }
    @Test
    public void testPasswordMeetsComplexity() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Thimna", "Saul");
        assertTrue(login.checkPasswordComplexity());
    }
    @Test
    public void testPasswordFails() {
        Login login = new Login("kyl_1", "password", "+27838968976", "Thimna", "Saul");
        assertFalse(login.checkPasswordComplexity());
    }
    @Test
    public void testCellCorrect() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Thimna", "Saul");
        assertTrue(login.checkCellPhoneNumber());
    }
    @Test
    public void testCellIncorrect() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "08966553", "Thimna", "Saul");
        assertFalse(login.checkCellPhoneNumber());
    }
    @Test
    public void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Thimna", "Saul");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    @Test
    public void testLoginFailed() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Thimna", "Saul");
        assertFalse(login.loginUser("kyl_1", "wrong"));
    }
}
