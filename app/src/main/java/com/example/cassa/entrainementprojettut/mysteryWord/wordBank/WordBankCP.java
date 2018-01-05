package com.example.cassa.entrainementprojettut.mysteryWord.wordBank;

import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;
import com.example.cassa.entrainementprojettut.mysteryWord.word.WordCP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 04/01/18.
 */

public class WordBankCP extends OutilsWordBank implements I_WordBank {

    private static String listeMotsCourts[] = {"film", "doux", "defi", "flou", "fond", "epis", "epee",
            "fete", "fil", "gris", "bleu", "loup", "lune", "dent", "chez", "cent", "sol", "toi",
            "roi", "cle", "six", "coq", "dos", "jus", "ici", "lit", "vis", "noir", "sac", "kiwi",
            "huit", "cube", "robe", "ours", "rue", "bras", "main", "bus", "nez", "rire"};


    public WordBankCP() {
        genererListeMots();
    }


    protected I_Word genererMot(String mot) {

        return new WordCP(mot);
    }

    @Override
    public void genererListeMots() {
        List<I_Word> words = new ArrayList<>();
        String mot;
        List<String> motSelectionne=new ArrayList<>();

        while (words.size()<5){
            mot = selectionnerMotDansLaListe(listeMotsCourts);
            if (motDejaSelectionne(mot, motSelectionne)) {
                continue;
            }
            words.add(genererMot(mot));
            motSelectionne.add(mot);
        }
        motSelectionne.clear();
        this.setMots=words;
    }
}
