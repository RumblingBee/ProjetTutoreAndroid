package com.example.cassa.projetAndroid.mysteryWord.word;

/**
 * Created by clement on 04/01/18.
 */

public interface I_Word {

    String getWord();
    String getCodedWord();
    int adjustCharValue(int ascii);

    void codeWord(int i);

    String getOrder();
}
