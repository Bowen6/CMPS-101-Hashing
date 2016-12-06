package com.BowenBrooks;

/**
 * Created by bowenbrooks on 12/04/15.
 */
public class SItem {

    private String userName;
    private int userID;
    SItem next;

    public SItem(){
        userName = "";
        userID = 0;
        next = null;
    }

    SItem(String userName, int customerID) {
        this.userName = userName;
        this.userID = customerID;
    }
    String getUserName() {
        return userName;
    }

    void setNext(SItem next) {
        this.next = next;
    }

    SItem getNext(){
        return next;
    }

    int getUserID(){
        return userID;
    }

    @Override
    public String toString() {
        return "USERNAME: " + userName;
    }
}
