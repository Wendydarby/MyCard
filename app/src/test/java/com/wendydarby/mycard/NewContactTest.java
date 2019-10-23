package com.wendydarby.mycard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NewContactTest {
    NewContact newContact;

    @Before
    public void setUp() throws Exception {
        newContact = new NewContact("Tester One", "tester@mycard.net", "9193765377", "WIN networking event.");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testNewContactConstructor(){
        String name = "Tester Alpha";
        newContact = new NewContact(name);
        assertNotNull(newContact);
        assertNotNull(newContact.getContactName());
        assertEquals(newContact.getContactName(), name);

    }

    @Test
    public void testGetContactName() {
        assertNotNull(newContact.getContactName());
    }

    @Test
    public void testGetContactEmail() {
        assertNotNull(newContact.getContactEmail());
    }

    @Test
    public void testGetContactMobile() {
        assertNotNull(newContact.getContactMobile());
    }

    @Test
    public void testGetContactEvent() {
        assertNotNull(newContact.getContactEvent());
    }

    @Test
    public void testSetContactName() {
        String name = "Test Two";
        NewContact newContactTest = new NewContact();

        newContactTest.setContactName(name);
        assertNotNull(newContactTest.getContactName());
        assertEquals(newContactTest.getContactName(), name);
    }

    @Test
    public void testSetContactEmail() {
        String email = "Two@tester.net";
        NewContact newContactTest = new NewContact();

        newContactTest.setContactEmail(email);
        assertNotNull(newContactTest.getContactEmail());
        assertEquals(newContactTest.getContactEmail(), email);
    }

    @Test
    public void testSetContactMobile() {
        String mobile = "9191234567";
        NewContact newContactTest = new NewContact();

        newContactTest.setContactMobile(mobile);
        assertNotNull(newContactTest.getContactMobile());
        assertEquals(newContactTest.getContactMobile(), mobile);
    }

    @Test
    public void testSetContactEvent() {
        String event = "Mock2 networking event.";
        NewContact newContactTest = new NewContact();

        newContactTest.setContactEvent(event);
        assertNotNull(newContactTest.getContactEvent());
        assertEquals(newContactTest.getContactEvent(), event);
    }

}