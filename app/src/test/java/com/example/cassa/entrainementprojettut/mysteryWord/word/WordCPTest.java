package com.example.cassa.entrainementprojettut.mysteryWord.word;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 04/01/18.
 */
public class WordCPTest {

    private WordCP wordCP;

    @Test
    public void testFonctionnementStandard() throws Exception {
        wordCP =new WordCP("test");
        StringBuilder codedWord = new StringBuilder();
        for (char c: wordCP.getMot().toCharArray()) {
            c-=1;
            codedWord.append((char) wordCP.ajustementValeurDuCaractere(c));
        }
        assertEquals(codedWord.toString(), wordCP.getMotCode());
    }

    @Test
    public void TestBouclageDeLalphabet() throws Exception {
        wordCP =new WordCP("abaa");
        assertEquals(wordCP.getMotCode(),"zazz");
    }
}