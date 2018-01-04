package com.example.cassa.entrainementprojettut.mysteryWord.wordBank;

import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by clement on 04/01/18.
 */
public class WordBankFacileTest {

    private WordBankFacile wordBankFacile;


    @Before
    public void setUp() throws Exception {
        wordBankFacile=new WordBankFacile();
    }

    @Test
    public void testPasDeDoublon() throws Exception {
        for (int j = 0; j <1000; j++) {
            List<String> liste=new ArrayList<>();
            wordBankFacile.genererListeMots();
            List<I_Word> words=wordBankFacile.getSetMots();
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
            wordBankFacile.genererListeMots();
            list=wordBankFacile.getSetMots();
            assertEquals (5,list.size());
        }
    }

    @Test
    public void testFonctionnementStandard() throws Exception {

        for (int i = 0; i <1000; i++) {
            wordBankFacile.genererListeMots();
            List<I_Word>list=wordBankFacile.getSetMots();
            for (I_Word word:list) {
                StringBuilder codedWord = new StringBuilder();
                for (char c:word.getMot().toCharArray()) {
                    c-=1;
                    codedWord.append((char)word.ajustementValeurDuCaractere(c));
                }
                assertEquals(codedWord.toString(),word.getMotCode());
            }
        }

    }
}