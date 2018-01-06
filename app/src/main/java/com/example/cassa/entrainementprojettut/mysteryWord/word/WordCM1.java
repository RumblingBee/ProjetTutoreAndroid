package com.example.cassa.entrainementprojettut.mysteryWord.word;

/**
 * Created by clement on 05/01/18.
 */

public class WordCM1 extends OutilsWord implements I_Word {

    public WordCM1(String mot) {
        this.mot = mot;
        coderMot(1);
        consigne="Décale les lettres du mot codé de -1 lettre dans l'alphabet pour trouver le mot caché";
    }

    @Override
    public void coderMot(int i) {
        StringBuilder codedWord = new StringBuilder();
        for (char c : mot.toCharArray())
        {
            c = coderLettreEnPositif(c, i);
            codedWord.append(c);
        }
        motCode=codedWord.toString();
    }

}
