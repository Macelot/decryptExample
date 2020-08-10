import org.marcelot.Word;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class Tests {

    @Test
    public void TestWords(){
        System.out.println("-------Test Words");
        Word w = new Word();
        w.setWords(".//usa//usa.txt");
        ArrayList<String> words=w.getWords();
        for (String word:words) {
            System.out.println(word);
        }
    }

    @Test
    public void TestSecret(){
        System.out.println("-------Test Secret");
        Word w = new Word();
        w.setSecret("SecretMessage.txt");
        String secret=w.getSecret();
        System.out.println(secret);
    }

    @Test
    public void TestAssociated(){
        System.out.println("-------Test Associated");
        Word w = new Word();
        w.setAssociated();
        char[] assocciateds=w.getAssociated();
        int p=0;
        for (char associated:assocciateds) {
            System.out.print("p " + p + " ");
            p++;
            System.out.println(associated);
        }
    }

    @Test
    public void TestMakeKey(){
        System.out.println("-------Test makeKey");
        Word w = new Word();
        w.setAssociated();
        String m = w.makeKey(1,";!?");
        System.out.println(m);
        //EFOJT
        assertEquals(m, "!?A");
    }

    @Test
    public void TestCountMatchs(){
        System.out.println("-------Test CountMatchs");
        Word w = new Word();
        w.setWords(".//usa//usa.txt");
        w.setAssociated();
        w.setSecret("SecretMessage.txt");
        w.makekey0to31();
        w.countMatchs();
    }

    @Test
    public void TestBiggerCount(){
        System.out.println("-------Test BiggerCount");
        Word w = new Word();
        w.setWords(".//usa//usa.txt");
        w.setAssociated();
        w.setSecret("SecretMessage.txt");
        w.makekey0to31();
        w.countMatchs();
        int k = w.biggerCount();
        System.out.println(k);
    }

    @Test
    public void finalTest(){
        System.out.println("-------Test");
        Word w = new Word();
        w.setWords(".//usa//usa.txt");
        w.setAssociated();
        w.setSecret("SecretMessage.txt");
        w.makekey0to31();
        w.countMatchs();
        w.setKey(w.biggerCount());
        w.setDecrypt(w.makekeyx(w.getKey()));
        System.out.println(" == == ");
        System.out.println(w.getDecrypt());
    }


}
