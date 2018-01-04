package com.example.cassa.entrainementprojettut.mysteryWord.word;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clement on 04/01/18.
 */
public class WordFacileTest {

    private WordFacile wordFacile;

    @Test
    public void testFonctionnementStandard() throws Exception {
        wordFacile=new WordFacile("test");
        StringBuilder codedWord = new StringBuilder();
        for (char c:wordFacile.getMot().toCharArray()) {
            c-=1;
            codedWord.append((char)wordFacile.ajustementValeurDuCaractere(c));
        }
        assertEquals(codedWord.toString(),wordFacile.getMotCode());
    }

    @Test
    public void TestBouclageDeLalphabet() throws Exception {
        wordFacile=new WordFacile("abaa");
        assertEquals(wordFacile.getMotCode(),"zazz");
    }
}