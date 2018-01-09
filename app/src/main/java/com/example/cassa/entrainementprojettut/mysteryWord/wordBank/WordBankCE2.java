package com.example.cassa.entrainementprojettut.mysteryWord.wordBank;

import com.example.cassa.entrainementprojettut.mysteryWord.word.I_Word;
import com.example.cassa.entrainementprojettut.mysteryWord.word.WordCE2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 05/01/18.
 */

public class WordBankCE2 extends OutilsWordBank implements I_WordBank {

    private static String listeMotsMoyensEtLongs[] = {"livre", "epine", "ferme", "finir", "fleur", "drole",
            "fusee", "froid", "futur", "soupe", "veste", "jaune", "vivre", "pomme", "hiver", "porte",
            "botte", "chaud", "lampe", "voler", "tasse", "renne", "chien", "chat", "avion", "barbe",
            "aigle", "pelle", "lapin", "jambe", "panda", "pieds", "verre", "genou","vendre", "violet", "voisin", "dauphin", "patate",
            "requin", "baleine", "laitue", "maison", "triangle", "tambour", "sucette", "crayon",
            "poisson", "cercle", "robinet", "fantome", "lunette", "guitare", "canard", "manger",
            "jardin", "volant", "souris", "quatre"};


    public WordBankCE2() {
        genererListeMots();
    }

    @Override
    public void genererListeMots() {

        List<I_Word> words = new ArrayList<>();
        String mot;
        List<String> motSelectionne=new ArrayList<>();

        while (words.size()<5){
            mot = selectionnerMotDansLaListe(listeMotsMoyensEtLongs);
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
        return new WordCE2(mot);
    }
}
