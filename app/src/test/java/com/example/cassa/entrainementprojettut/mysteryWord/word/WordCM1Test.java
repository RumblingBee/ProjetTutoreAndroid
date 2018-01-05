package com.example.cassa.entrainementprojettut.mysteryWord.word;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 05/01/18.
 */
public class WordCM1Test {

    private WordCM1 wordCM1;

    @Test
    public void testFonctionnementStandard() throws Exception {
        wordCM1 = new WordCM1("test");
        StringBuilder codedWord = new StringBuilder();
        for (char c: wordCM1.getMot().toCharArray()) {
            c+=1;
            codedWord.append((char) wordCM1.ajustementValeurDuCaractere(c));
        }
        assertEquals(codedWord.toString(), wordCM1.getMotCode());
    }

    @Test
    public void TestBouclageDeLalphabet() throws Exception {
        wordCM1 = new WordCM1("zzaz");
        assertEquals(wordCM1.getMotCode(),"aaba");
    }

}