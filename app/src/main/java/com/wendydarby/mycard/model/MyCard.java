package com.wendydarby.mycard.model;

public class MyCard {
    public MyCard() {
    }

    public static String getEcardUrl() {
        return ECARD_URL;
    }

    public static void setEcardUrl(String ecardUrl) {
        ECARD_URL = ecardUrl;
    }

    static public String ECARD_URL = "https://ClickMy.info/i/8lGZ/Wendy_Darby";

}
