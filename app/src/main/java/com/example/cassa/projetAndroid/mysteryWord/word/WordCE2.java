package com.example.cassa.projetAndroid.mysteryWord.word;

/**
 * Created by clement on 05/01/18.
 */

public class WordCE2 extends OutilsWord implements I_Word {

    public WordCE2(String mot) {
        this.mWord = mot;
        codeWord(3);
        mOrder ="Décale les lettres du mWord codé de +3 lettre dans l'alphabet pour trouver le mWord caché";
    }

    @Override
    public void codeWord(int i) {
        StringBuilder codedWord = new StringBuilder();
        for (char c : mWord.toCharArray())
        {
            c = codeLetterDownward(c, i);
            codedWord.append(c);
        }
        mCodedWord =codedWord.toString();
    }
}
