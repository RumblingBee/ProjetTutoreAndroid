package com.example.cassa.entrainementprojettut.mysteryWord;

import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.I_WordBank;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.WordBankCE1;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.WordBankCE2;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.WordBankCP;

/**
 * Created by clement on 04/01/18.
 */

public class ControleurMysteryWord {

    private I_WordBank wordBank;

    public ControleurMysteryWord(int difficulte) {
        if(difficulte==1){
            wordBank=new WordBankCP();
        }else if (difficulte==2){
            wordBank=new WordBankCE1();
        }else if(difficulte==3){
            wordBank=new WordBankCE2();
        }

    }
}
