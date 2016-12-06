package com.BowenBrooks;

import java.math.BigInteger;

/**
 * Created by bowenbrooks on 12/04/15.
 */
public class Conversion {

    int SIZEOFSTRING;

    //returns an array of 0s and 1s constructed by representing each character in by its assigned bit pattern
    public int[] stringToBitseq(String str){
        int length = str.length() * 6;
        int[] bitArray = new int[length];
        int start = 0;
        for (int index = 0; index < str.length(); index++){
            char c = str.charAt(index);
            int Num = charToNum(c);
            int[] bitseq = NumToBitseq(Num);
            //adding bitseq to the total bit array
            for (int i = 0; i < 6; i ++){
                bitArray[start] = bitseq[i];
                start++;
            }
        }
//        for (int i = 0; i < length; i++){
//            System.out.print(bitArray[i]);
//        }
        return bitArray;
    }
    public int[] NumToBitseq(int Num){
        int  SIZE = 6;
        int index = 0;
        int[] binaryArray = new int[SIZE];
        while (Num > 0){
            int Num2 = Num/2;
            binaryArray[index] = Num - (2*Num2);
            Num = Num2;
            index++;
        }


        return flipArray(binaryArray);
    }


    public int[] BitseqTodigitseq(int[]bit, int k){
        return null;
    }
    public int[] stringToNum(String str){
        return null;
    }
    public int getSIZE(){
        return SIZEOFSTRING;
    }



    public BigInteger BitseqToBigNum(int[] bs){
        int size = ((bs.length) - 1);
        BigInteger bigNum = BigInteger.valueOf(0);
        BigInteger power = BigInteger.valueOf(1);
        BigInteger base = BigInteger.valueOf(2);

        for (int i = size; i >= 0; i--){
            if(bs[i] == 1){
                bigNum = bigNum.add(power);
            }

            power = power.multiply(base);
        }
        return bigNum;
    }




    //flipping an array of bits
    private int[] flipArray(int[] bs){
        int size = bs.length;
        int[] temp = new int[size];
        for (int i = 0; i < size; i++){
            temp[(size-1)-i] = bs[i];   //flipping the bits size-1 cause array goes from 0-(size-1)
        }

        return temp;
    }

    //converting char c into a number 0-61
    private int charToNum(char c){
        //Lowercase Letter
        if(c >= 'a' && c <= 'z')
            return c - 87;
            //Upercase Letters A-Z
        else if (c >= 'A' && c <= 'Z')
            return c - 29;
            //Number 0-9
        else
            return c - '0';
    }

}
