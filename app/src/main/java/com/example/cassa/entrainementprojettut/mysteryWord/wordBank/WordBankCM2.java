package com.example.cassa.entrainementprojettut.mysteryWord.wordBank;

import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;
import com.example.cassa.entrainementprojettut.mysteryWord.word.WordCM2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 05/01/18.
 */

public class WordBankCM2 extends OutilsWordBank implements I_WordBank {

    private static String listeMotsLongs[] = {"vendre", "violet", "voisin", "dauphin", "patate",
            "requin", "baleine", "laitue", "maison", "triangle", "tambour", "sucette", "crayon",
            "poisson", "cercle", "robinet", "fantome", "lunette", "guitare", "canard", "manger",
            "jardin", "volant", "souris", "quatre"};

    public WordBankCM2() {
        genererListeMots();
    }

    @Override
    public void genererListeMots() {

        List<I_Word> words = new ArrayList<>();
        String mot;
        List<String> motSelectionne=new ArrayList<>();

        while (words.size()<5){
            mot = selectionnerMotDansLaListe(listeMotsLongs);
            if (motDejaSelectionne(mot, motSelectionne)) {
                continue;
            }
            words.add(genererMot(mot));
            motSelectionne.add(mot);
        }
        motSelectionne.clear();
        this.setMots=words;
    }


    private I_Word genererMot(String mot) {
        return new WordCM2(mot);
    }
}
