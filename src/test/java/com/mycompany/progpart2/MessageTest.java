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
import org.junit.jupiter.api.Assertions;

public class MessageTest {

    @Test
    public void testLengthOk() {
        Message m = new Message("+27718693002", "Hi tonight", 0);
        Assertions.assertEquals("Message ready to send.", m.checkMessageLength());
    }

    @Test
    public void testLengthFail() {
        String longMsg = "a".repeat(260);
        Message m = new Message("+27718693002", longMsg, 0);
        Assertions.assertTrue(m.checkMessageLength().contains("exceeds"));
    }

    @Test
    public void testCellOk() {
        Message m = new Message("+27718693002", "Hi", 0);
        Assertions.assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    @Test
    public void testCellFail() {
        Message m = new Message("08575975889", "Hi", 0);
        Assertions.assertTrue(m.checkRecipientCell().contains("incorrectly"));
    }

    @Test
    public void testHash() {
        Message m = new Message("+27718693002", "Hi tonight", 0);
        m.setMessageId("0012345678");
        Assertions.assertEquals("00:0:HITONIGHT", m.createMessageHash());
    }
}
