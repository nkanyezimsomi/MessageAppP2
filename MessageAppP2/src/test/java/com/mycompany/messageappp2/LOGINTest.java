/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.messageappp2;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lab_services_student
 */
public class LOGINTest {
    
    LOGIN login = new LOGIN("Kyle","Smith","");
    
    @Test
    public void testCheckUserName_Valid(){
        assertTrue(login.checkUserName("Ky_L1"));
    }
    
    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(login.checkPasswordComplexity("password"));
    }
    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testRegUser_AllValid() {
        String result = login.regUser("ky_l1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Username successfully captured."));
        assertTrue(result.contains("Password successfully captured."));
        assertTrue(result.contains("Cell number successfully captured."));
    }

    @Test
    public void testRegUser_InvalidUsername() {
        String result = login.regUser("kyle", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Username is not correctly formatted"));
    }

    @Test
    public void testLoginUser_Success() {
        login.regUser("ky_l1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("ky_l1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_Failure() {
        login.regUser("ky_l1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("wrong", "pass123"));
    }

    @Test
    public void testReturnLoginStatus_Success() {
        login.regUser("ky_l1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Welcome Kyle Smith, it is great to see you again.", login.returnLoginStatus("Kyle", "Smith", true));
    }
    
    @Test
    public void testReturnLoginStatus_Failure() {
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus("Kyle", "Smith", false));
    }
   
    
    @Test
    public void testUsernameTooLongEvenWithUnderscore() {
        assertFalse(login.checkUserName("long_user"));
    }
    //-----MESSAGE JUNIT TEST----//
    
    @Test
    public void testMesssageID_LengthValid(){
        Message msg = new Message("+27718693002","Dinner tonight?");
        assertTrue(msg.checkMessageID());
    }
    @Test
    public void testRecipientCell_Valid() {
        Message msg = new Message("+27718693002", "Dinner tonight?");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCell_Invalid() {
        Message msg = new Message("0823344556", "Dinner tonight?");
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash_Format() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        String hash = msg.getMessageHash();
        assertTrue(hash.matches("\\d{2}:[0-9]+:[A-Z]+[A-Z]+"));
    }

    @Test
    public void testSendMessage_Option1() {
        Message msg = new Message("+27718693002", "Hi Mike");
        String response = msg.sentMessage(1);
        assertEquals("Message successfully sent.", response);
    }

    @Test
    public void testSendMessage_Option2() {
        Message msg = new Message("+27718693002", "Hi Keegan");
        String response = msg.sentMessage(2);
        assertEquals("Press 0 to delete message.", response);
    }

    @Test
    public void testSendMessage_Option3() {
        Message msg = new Message("+27718693002", "Store this");
        String response = msg.sentMessage(3);
        assertEquals("Message successfully stored.", response);
    }

    @Test
    public void testSendMessage_InvalidOption() {
        Message msg = new Message("+27718693002", "Whatever");
        String response = msg.sentMessage(99);
        assertEquals("Invalid option.", response);
    }

    @Test
    public void testReturnTotalMessages() {
        int initialCount = Message.returnTotalMessages();
        new Message("+27718693002", "Test 1");
        new Message("+27718693002", "Test 2");
        assertEquals(initialCount + 2, Message.returnTotalMessages());
    }
    
}
