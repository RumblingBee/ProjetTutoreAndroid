package com.example.cassa.entrainementprojettut.mysteryWord.wordBank;

import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;
import com.example.cassa.entrainementprojettut.mysteryWord.word.WordCE1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 05/01/18.
 */

public class WordBankCE1 extends OutilsWordBank implements I_WordBank {

    private static String listeMotsCourtsEtMoyens[] = {"film", "doux", "defi", "flou", "fond", "epis", "epee",
            "fete", "fil", "gris", "bleu", "loup", "lune", "dent", "chez", "cent", "sol", "toi",
            "roi", "cle", "six", "coq", "dos", "jus", "ici", "lit", "vis", "noir", "sac", "kiwi",
            "huit", "cube", "robe", "ours", "rue", "bras", "main", "bus", "nez", "rire","livre", "epine", "ferme", "finir", "fleur", "drole",
            "fusee", "froid", "futur", "soupe", "veste", "jaune", "vivre", "pomme", "hiver", "porte",
            "botte", "chaud", "lampe", "voler", "tasse", "renne", "chien", "chat", "avion", "barbe",
            "aigle", "pelle", "lapin", "jambe", "panda", "pieds", "verre", "genou"};


    public WordBankCE1() {
        genererListeMots();
    }

    @Override
    public void genererListeMots() {

        List<I_Word> words = new ArrayList<>();
        String mot;
        List<String> motSelectionne=new ArrayList<>();

        while (words.size()<5){
            mot = selectionnerMotDansLaListe(listeMotsCourtsEtMoyens);
            if (motDejaSelectionne(mot, motSelectionne)) {
                continue;
            }
            words.add(genererMot(mot));
            motSelectionne.add(mot);
        }
        motSelectionne.clear();
        this.setMots=words;
    }


    protected I_Word genererMot(String mot) {
        return new WordCE1(mot);
    }
}
