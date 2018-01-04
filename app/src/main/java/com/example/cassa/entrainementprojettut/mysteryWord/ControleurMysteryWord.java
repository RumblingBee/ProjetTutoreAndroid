package com.example.cassa.entrainementprojettut.mysteryWord;

import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.I_WordBank;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.WordBankFacile;

/**
 * Created by clement on 04/01/18.
 */

public class ControleurMysteryWord {

    private I_WordBank wordBank;

    public ControleurMysteryWord(int difficulte) {
        if(difficulte==1){
            wordBank=new WordBankFacile();
        }

    }
}
