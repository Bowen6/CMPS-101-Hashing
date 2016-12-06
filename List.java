package com.BowenBrooks;

/**
 * Created by bowenbrooks on 12/04/15.
 */
public class List {
    private SItem head;

    public List(){
        head = null;
    }

    public SItem getHead(){
        return head;
    }

    public void setHead(SItem head) {
        this.head = head;
    }

    public SItem find(String str){
        SItem TP = head;
        while (TP != null){
            if(TP.getUserName().equals(str))
                return TP;
            TP = TP.getNext();
        }
        return TP;
    }

    public void pushFront(String uid, int cid){
        SItem item = new SItem(uid,cid);
        if (head == null){
            head = item;
            item.setNext(null);
        }else {
            item.setNext(head);
            head = item;
        }

    }

    public void display(){

    }

}
