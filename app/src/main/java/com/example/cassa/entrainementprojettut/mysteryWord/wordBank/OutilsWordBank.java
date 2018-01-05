package com.example.cassa.entrainementprojettut.mysteryWord.wordBank;

import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 05/01/18.
 */

public abstract class OutilsWordBank implements I_WordBank {

    protected List<I_Word> setMots;

    public List<I_Word> getSetMots() {
        return setMots;
    }

    protected boolean motDejaSelectionne(String mot, List<String> motSelectionne) {
        return motSelectionne.contains(mot);
    }

    public String selectionnerMotDansLaListe(String[] listeMots) {
        String mot;
        mot = listeMots[(genererNombre(listeMots.length - 1,0))];
        return mot;
    }

    public int genererNombre(int borneSup, int borneInf){

        int nombre = (int)(Math.random() * (borneSup) + borneInf);

        return nombre;
    }

}
