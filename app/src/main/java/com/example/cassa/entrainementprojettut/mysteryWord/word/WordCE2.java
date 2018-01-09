package com.example.cassa.entrainementprojettut.mysteryWord.word;

/**
 * Created by clement on 05/01/18.
 */

public class WordCE2 extends OutilsWord implements I_Word {

    public WordCE2(String mot) {
        this.mot = mot;
        coderMot(3);
        consigne="Décale les lettres du mot codé de +3 lettre dans l'alphabet pour trouver le mot caché";
    }

    @Override
    public void coderMot(int i) {
        StringBuilder codedWord = new StringBuilder();
        for (char c : mot.toCharArray())
        {
            c = coderLettreEnNegatif(c, i);
            codedWord.append(c);
        }
        motCode=codedWord.toString();
    }
}
