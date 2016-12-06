package com.BowenBrooks;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Random;

/**
 * Created by bowenbrooks on 12/04/15.
 */
public class UIMS {
    private int CustomerID = 0;
    private int tableSize  = 2;
    private int power = 1;
    private static final int SALTSIZE = 96;
    private Conversion conversion;
    private List[] table;
    private BigInteger salt;


    public UIMS(){
        conversion = new Conversion();
        salt = BigInteger.valueOf(0);
        generateSalt();
        table = new List[tableSize];
        for (int index = 0; index < table.length; index++){
            table[index] = new List();
        }

    }


    //checking if Username is available
    public boolean isAvailable(String uid, int key) {
        List list = table[key];
        if(list.find(uid) != null)
            return false;

        return true;
    }


    public void add(String uid){
        int key = hash(uid);
        if (isAvailable(uid,key)){
            double load = load();
            if(load >= 0.75){
                Reallocate();
            }
            CustomerID++;
            table[key].pushFront(uid,CustomerID);
        }else{
            String output = "Username: " + uid + " is already taken";
            System.out.println(output);
        }

    }

    private void Reallocate() {
        List[]tmp = table;
        tableSize *= 2;
        power++;
        table = new List[tableSize];
        //reinitializing table()
        for(int i =0; i < table.length; i++){
            table[i] = new List();
        }
        for(int index = 0; index < tmp.length; index++){
            List list = tmp[index];
            SItem TP = list.getHead();
            while(TP != null){
                String uid = TP.getUserName();
                int key = hash(uid);
                table[key].pushFront(uid,TP.getUserID());
                TP = TP.getNext();
            }
        }

    }
    //generating the hash value
    //postCondition: value  <= table.size-1
    public int hash(String id){
        int[] bitseq = conversion.stringToBitseq(id);
        BigInteger uidbigNum = conversion.BitseqToBigNum(bitseq);
        //making 2^95 in binary
        int[] maxNum = new int[96];
        maxNum[0] = 1;
        //this is 2^95 * 2 = 2^96
        BigInteger reallybigNum = conversion.BitseqToBigNum(maxNum).multiply(BigInteger.valueOf(2));

        //hashing
        BigInteger Numerator = (uidbigNum.multiply(getSalt()));
        Numerator = Numerator.mod(reallybigNum);
        BigInteger Denomenator = (reallybigNum.divide(BigInteger.valueOf(tableSize)));
        BigInteger result =  Numerator.divide(Denomenator);
        return result.intValue();
    }

    public void generateSalt(){
        int[] bits = new int [SALTSIZE];
        for (int i = 0; i < SALTSIZE; i++) {
            int ranNum = (int) (Math.random() * 10);

            if(ranNum >= 5)
                bits[i] = 1;
        }
        salt = conversion.BitseqToBigNum(bits);
        System.out.println("Salt: " + salt);
    }

    BigInteger getSalt() {
        return salt;
    }

    //displaying the hash table
    void display() {
        System.out.println("*******************************HASHTABLE*********************");
        for(int index = 0; index < table.length; index++){
            List list = table[index];
            SItem TP = list.getHead();
            if(TP != null){
                System.out.println(index + " - ");
            }
            while (TP != null){
                System.out.println(TP.toString());
                TP = TP.getNext();
            }
        }
        System.out.print("Number of users: " + CustomerID);
    }

    //writing hashtable to file
    public void writetoFile() {
        try {
            PrintWriter writer = new PrintWriter("OUTPUT.txt", "UTF-8");
           writer.println("*******************************HASHTABLE*********************");
            for(int index = 0; index < table.length; index++){
                List list = table[index];
                SItem TP = list.getHead();
                if(TP != null){
                   writer.println(index + " - ");
                }
                while (TP != null){
                    writer.println(TP.toString());
                    TP = TP.getNext();
                }
            }
            writer.print("Number of users: " + CustomerID);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // when adding a user id would put the load factor over 0.75 then reallocate the hash
    // table, doubling its size.
    public double load(){
        return (double)CustomerID/tableSize;
    }
}
