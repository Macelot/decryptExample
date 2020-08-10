package org.marcelot;

import javax.swing.table.TableColumn;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Word {

    private ArrayList<String> words;
    private String secret;
    private char[] associated;
    private ArrayList<String[]> secretsWords;
    private int[] ts;
    private int key;
    private String[] decrypt;

    public void setWords(String file){
        words = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();
            words.add(line);
            while(line!=null){
                words.add(line);
                line = br.readLine();
            }
        }catch(Exception e){
            System.out.println("Error on load file "+file);
        }
    }

    public void setSecret(String file){
        secret="";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while(true){
                line = br.readLine();
                if(line!=null){
                    secret = secret + line+"#";;
                }else{
                    break;
                }
            }

            //System.out.println(secret);
        }catch(Exception e){
            System.out.println("Error on load file "+file);
        }
    }

    public void setAssociated(){
        associated = new char[31];
        for (int i = 0; i<26;i++){
            associated[i]=(char)(i+65);
        }
        associated[26]='.';
        associated[27]=',';
        associated[28]=';';
        associated[29]='!';
        associated[30]='?';
    }

    public ArrayList getWords(){
        return words;
    }

    public String getSecret(){
        return secret;
    }

    public char[] getAssociated(){
        return associated;
    }

    public String makeKey(int key, String message){
        String result="";
        char charPos='\0';
        int pos=0;
        for (int i=0;i<message.length();i++){
            charPos=message.charAt(i);
            for (int j=0; j<associated.length;j++){
                if(charPos==associated[j]){
                    pos=j+key;
                    if(pos>=31){
                        //System.out.println("k "+key);
                        //System.out.println("j "+j);
                        pos-=31;
                    }
                    result=result+associated[pos];
                }
            }
        }
        return result;
    }

    public ArrayList<String[]> makekey0to31(){
        //test key 0 up to 31
        secretsWords = new ArrayList<String[]>();
        //split message
        String[] secretWords = getSecret().split("#");
        String[] line;
        for (int i=0;i<31;i++){
            //cript all words with the key i
            //System.out.println("Key "+i+" ======================================");
            line = new String[secretWords.length];
            for (int j=0;j<secretWords.length;j++){
                //System.out.println("Word "+secretWords[j]);
                line[j] = makeKey(i,secretWords[j]);
            }
            secretsWords.add(line);
        }
        return secretsWords;
    }

    public String[] makekeyx(int x){
        //test only key X
        String[] secretWords = getSecret().split("#");
        String[] line;
        line = new String[secretWords.length];
        for (int j=0;j<secretWords.length;j++){
            line[j] = makeKey(x,secretWords[j]);
        }
        return line;
    }

    public int[] countMatchs(){
        ts = new int[secretsWords.size()];
        for (int i=0; i<secretsWords.size(); i++){
            for (int j=0;j<secretsWords.get(i).length;j++){
                System.out.print(secretsWords.get(i)[j]);
                //verify if word secretsWords.get(i)[j] exists in dictionary
                if(words.contains(secretsWords.get(i)[j].toLowerCase())){
                    //System.out.println("FOUND");
                    ts[i]++;
                }
            }
            System.out.println();
        }
        return ts;
    }

    public int biggerCount(){
        int m=0;
        int position=0;
        for(int i=0;i<ts.length;i++){
            if(ts[i]>m){
                m=ts[i];//amout of identicals
                position=i;
            }
        }
        return position;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getDecrypt() {
        String temp="";
        for (String a: decrypt) {
            temp+=a+" ";
        }
        return temp;
    }

    public void setDecrypt(String[] decrypt) {
        this.decrypt = decrypt;
    }
}
