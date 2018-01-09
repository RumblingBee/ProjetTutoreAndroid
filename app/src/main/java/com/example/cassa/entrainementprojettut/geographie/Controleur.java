package com.example.cassa.entrainementprojettut.geographie;

import java.util.List;

/**
 * Created by prax on 09/01/2018.
 */

public class Controleur {

private I_Set nouveauNiveau;

    public Controleur(int difficulte) {



        switch (difficulte){
            case 1:
                nouveauNiveau = new SetMonde();
                break;

            case 2:
                nouveauNiveau = new SetEurope();
                break;

            case 3:
                nouveauNiveau = new SetOccitanie();

                break;
            default:
                nouveauNiveau = new SetMonde();
                break;
        }

    }

    public List<Etiquette> getListEtiquette() {

       return nouveauNiveau.getListEtiquette();

    }
    public int getImageFond(){
        return nouveauNiveau.getImageFond();
    }

}
