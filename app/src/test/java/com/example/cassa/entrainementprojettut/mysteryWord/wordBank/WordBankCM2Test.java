package com.example.cassa.entrainementprojettut.mysteryWord.wordBank;

import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by clement on 05/01/18.
 */
public class WordBankCM2Test {

    private WordBankCM2 wordBankCM2;

    @Before
    public void setUp() throws Exception {
        wordBankCM2 = new WordBankCM2();
    }

    @Test
    public void testPasDeDoublon() throws Exception {
        for (int j = 0; j <1000; j++) {
            List<String> liste=new ArrayList<>();
            wordBankCM2.genererListeMots();
            List<I_Word> words= wordBankCM2.getSetMots();
            for (I_Word word:words) {
                liste.add(word.getMot());
            }

            for (String mot:liste){
                int apparation=0;
                for (int y = 0; y <liste.size(); y++) {
                    if(liste.get(y)==mot){
                        apparation++;
                    }
                }
                assertEquals(1,apparation);
            }
        }
    }

    @Test
    public void testTailleDesWordsBank() throws Exception {
        List<I_Word> list;
        for (int i = 0; i <1000; i++) {
            wordBankCM2.genererListeMots();
            list= wordBankCM2.getSetMots();
            assertEquals (5,list.size());
        }
    }

    @Test
    public void testFonctionnementStandard() throws Exception {

        for (int i = 0; i <1000; i++) {
            wordBankCM2.genererListeMots();
            List<I_Word>list= wordBankCM2.getSetMots();
            for (I_Word word:list) {
                StringBuilder codedWord = new StringBuilder();
                for (char c:word.getMot().toCharArray()) {
                    c+=2;
                    codedWord.append((char)word.ajustementValeurDuCaractere(c));
                }
                assertEquals(codedWord.toString(),word.getMotCode());
            }
        }
    }

}