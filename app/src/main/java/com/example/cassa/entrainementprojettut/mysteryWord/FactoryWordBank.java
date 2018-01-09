package com.example.cassa.entrainementprojettut.mysteryWord;

import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.I_WordBank;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.WordBankCE1;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.WordBankCE2;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.WordBankCM1;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.WordBankCM2;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.WordBankCP;

import java.util.List;

/**
 * Created by clement on 04/01/18.
 */

public class FactoryWordBank {

    private I_WordBank wordBank;

    public void createI_WordBank(int difficulte) {
        switch (difficulte) {
            case 1:
                wordBank= new WordBankCP();
                break;
            case 2:
                wordBank= new WordBankCE1();
                break;
            case 3:
                wordBank= new WordBankCE2();
                break;
            case 4:
                wordBank= new WordBankCM1();
                break;
            default:
                wordBank= new WordBankCM2();
                break;
        }

    }

    public List<I_Word> getWords() {
        return wordBank.getSetMots();
    }

    public I_Word getUnMot(int i){
        List<I_Word> setMots = wordBank.getSetMots();
        return setMots.get(i);
    }
}
