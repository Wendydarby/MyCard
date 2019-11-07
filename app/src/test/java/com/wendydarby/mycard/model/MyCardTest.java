package com.wendydarby.mycard.model;

import com.wendydarby.mycard.model.MyCard;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MyCardTest {

    MyCard myCard;

    @Before
    public void setup(){
        myCard= new MyCard();
    }

    @Test
    public void checkForValidURI(){
        assertSame(myCard.getEcardUrl(),myCard.ECARD_URL);
        try {
            URL url = new URL(myCard.getEcardUrl());
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            assertTrue("Url to e-Card works", urlConnection!= null);


        } catch (IOException e) {
            fail("E-Card URL has a problem.");
            e.printStackTrace();
        }


    }

    @Test
    public void testSetEcardUrl() {

        assertNotNull(myCard.getEcardUrl());

        myCard.setEcardUrl("https://ClickMy.info/i/8lGZ/Wendy_Darby");
        assertSame(myCard.getEcardUrl(), "https://ClickMy.info/i/8lGZ/Wendy_Darby");

        myCard.setEcardUrl("http://google.com");
        assertSame(myCard.getEcardUrl(), "http://google.com");
    }
}
