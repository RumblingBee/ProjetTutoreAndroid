package com.example.cassa.entrainementprojettut.mysteryWord.word;

/**
 * Created by clement on 04/01/18.
 */

public class WordCP extends OutilsWord implements I_Word{


    public WordCP(String mot) {
        this.mot=mot;
        coderMot(1);
        consigne="Décale les lettres du mot codé de -1 lettre dans l'alphabet pour trouver le mot caché";
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
