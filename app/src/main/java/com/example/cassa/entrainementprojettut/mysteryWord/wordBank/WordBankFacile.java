package com.example.cassa.entrainementprojettut.mysteryWord.wordBank;

import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;
import com.example.cassa.entrainementprojettut.mysteryWord.word.WordFacile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 04/01/18.
 */

public class WordBankFacile implements I_WordBank {

    private static String listeMotsCourts[] = {"film", "doux", "defi", "flou", "fond", "epis", "epee",
            "fete", "fil", "gris", "bleu", "loup", "lune", "dent", "chez", "cent", "sol", "toi",
            "roi", "cle", "six", "coq", "dos", "jus", "ici", "lit", "vis", "noir", "sac", "kiwi",
            "huit", "cube", "robe", "ours", "rue", "bras", "main", "bus", "nez", "rire"};

    private List<I_Word> setMots;

    public List<I_Word> getSetMots() {
        return setMots;
    }

    public WordBankFacile() {
        genererListeMots();
    }

    @Override
    public void genererListeMots() {

        List<I_Word> words = new ArrayList<>();
        String mot;
        List<String> motSelectionne=new ArrayList<>();

        while (words.size()<5){
            mot = selectionnerMotDansLaListe();
            if (motDejaSelectionne(mot, motSelectionne)) {
                continue;
            }
            words.add(genererMot(mot));
            motSelectionne.add(mot);
        }
        motSelectionne.clear();
        this.setMots=words;
    }

    private boolean motDejaSelectionne(String mot, List<String> motSelectionne) {
        return motSelectionne.contains(mot);
    }

    public String selectionnerMotDansLaListe() {
        String mot;
        mot = listeMotsCourts[(genererNombre(listeMotsCourts.length - 1,0))];
        return mot;
    }

    public int genererNombre(int borneSup, int borneInf){

        int nombre = (int)(Math.random() * (borneSup) + borneInf);

        return nombre;
    }

    private I_Word genererMot(String mot) {

        return new WordFacile(mot);
    }
}
