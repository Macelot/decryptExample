package org.marcelot;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        System.out.println("Cripto");
        ArrayList<String[]> secretsWords;
        Word w = new Word();
        w.setWords(".//usa//usa.txt");
        w.setSecret("SecretMessage.txt");
        w.setAssociated();

        //create 31 espaces for 31 possbile key;
        secretsWords = w.makekey0to31();
        //in secretsWords we have all cript message, each word in array position, cripted!

        //print first crypted message
        //System.out.println("size of message "+secretsWords.get(0).length+" words");
        //remove last one after #

        //show first decrypted message with key 0, is same original message.
        //System.out.println("key 0----------------------------");
        for (int i=0;i<secretsWords.get(0).length;i++) {
            //System.out.println(secretsWords.get(0)[i]);
        }
        //System.out.println("key 1----------------------------");
        //show first decrypted message with key 1, is same original message.
        for (int i=0;i<secretsWords.get(1).length;i++) {
            //System.out.println(secretsWords.get(1)[i]);
        }

        //check each decrypted message and count match words with dictionary
        w.countMatchs();

        int m = w.biggerCount();
        System.out.println("Key = "+m);

        String[] decrypt = w.makekeyx(m);
        for (int i=0;i<decrypt.length;i++) {
            System.out.print(decrypt[i]+" ");
        }
        System.out.println("Bye");
    }
}