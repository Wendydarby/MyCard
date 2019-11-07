package com.wendydarby.mycard.model;

public class NewContact {
    private String mContactName;
    private String mContactEmail;
    private String mContactMobile;
    private String mContactEvent;

    public NewContact(String mContactName) {
        this.mContactName = mContactName;
    }

    public NewContact() {
    }

    public NewContact(String mContactName, String mContactEmail, String mContactMobile, String mContactEvent) {
        this.mContactName = mContactName;
        this.mContactEmail = mContactEmail;
        this.mContactMobile = mContactMobile;
        this.mContactEvent = mContactEvent;
    }

    public String getContactName() {
        return mContactName;
    }

    public String getContactEmail() {
        return mContactEmail;
    }

    public String getContactMobile() {
        return mContactMobile;
    }

    public String getContactEvent() {
        return mContactEvent;
    }

    public void setContactName(String mContactName) {
        this.mContactName = mContactName;
    }

    public void setContactEmail(String mContactEmail) {
        this.mContactEmail = mContactEmail;
    }

    public void setContactMobile(String mContactMobile) {
        this.mContactMobile = mContactMobile;
    }

    public void setContactEvent(String mContactEvent) {
        this.mContactEvent = mContactEvent;
    }
}
