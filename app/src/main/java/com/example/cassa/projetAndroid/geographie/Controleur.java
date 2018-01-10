package com.example.cassa.projetAndroid.geographie;

import com.example.cassa.projetAndroid.geographie.GenerateurDePartie.I_Set;

import java.util.List;

/**
 * Created by prax on 09/01/2018.
 */

public class Controleur {

    private I_Set nouveauNiveau;

    public Controleur(int difficulte) {

        Factory factory = new Factory();
        nouveauNiveau = factory.createNouveauNiveau(difficulte);

    }

    public List<Etiquette> getListEtiquette() {

       return nouveauNiveau.getListEtiquette();

    }
    public int getImageFond(){
        return nouveauNiveau.getImageFond();
    }

}
