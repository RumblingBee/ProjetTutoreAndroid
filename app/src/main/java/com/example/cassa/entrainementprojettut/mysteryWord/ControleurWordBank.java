package com.example.cassa.entrainementprojettut.mysteryWord;

import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;
import com.example.cassa.entrainementprojettut.mysteryWord.wordBank.I_WordBank;

import java.util.List;

/**
 * Created by clement on 09/01/18.
 */

public class ControleurWordBank {

    private I_WordBank wordBank;
    private FactoryWordBank factoryWordBank;

    public ControleurWordBank(int diff) {
        factoryWordBank=new FactoryWordBank();
        wordBank=factoryWordBank.createI_WordBank(diff);
    }

    public List<I_Word> getWords() {
        return wordBank.getSetMots();
    }

    public I_Word getUnMot(int i){
        List<I_Word> setMots = wordBank.getSetMots();
        return setMots.get(i);
    }
}
