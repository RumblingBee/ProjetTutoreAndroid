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
public class WordBankCE2Test {

    private WordBankCE2 wordBankCE2;

    @Before
    public void setUp() throws Exception {
        wordBankCE2 =new WordBankCE2();
    }

    @Test
    public void testPasDeDoublon() throws Exception {
        for (int j = 0; j <1000; j++) {
            List<String> liste=new ArrayList<>();
            wordBankCE2.genererListeMots();
            List<I_Word> words= wordBankCE2.getSetMots();
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
            wordBankCE2.genererListeMots();
            list= wordBankCE2.getSetMots();
            assertEquals (5,list.size());
        }
    }

    @Test
    public void testFonctionnementStandard() throws Exception {

        for (int i = 0; i <1000; i++) {
            wordBankCE2.genererListeMots();
            List<I_Word>list= wordBankCE2.getSetMots();
            for (I_Word word:list) {
                StringBuilder codedWord = new StringBuilder();
                for (char c:word.getMot().toCharArray()) {
                    c-=3;
                    codedWord.append((char)word.ajustementValeurDuCaractere(c));
                }
                assertEquals(codedWord.toString(),word.getMotCode());
            }
        }
    }

    @Test
    public void testGenerationDeTousTypeDeMot() throws Exception {
        int motCourts=0;
        int motMoyens=0;
        int motLongs=0;

        for (int i = 0; i <1000; i++) {
            wordBankCE2.genererListeMots();
            List<I_Word>list= wordBankCE2.getSetMots();
            for (I_Word word:list) {
                if(word.getMot().length()==4){
                    motCourts++;
                }else if (word.getMot().length()==5){
                    motMoyens++;
                }else{
                    motLongs++;
                }
            }
        }
        assertEquals(true,motMoyens>0 && motCourts>0 && motLongs>0);
    }

}