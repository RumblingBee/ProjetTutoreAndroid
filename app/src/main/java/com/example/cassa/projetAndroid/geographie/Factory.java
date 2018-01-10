package com.example.cassa.projetAndroid.geographie;

import com.example.cassa.projetAndroid.geographie.GenerateurDePartie.I_Set;
import com.example.cassa.projetAndroid.geographie.GenerateurDePartie.SetEurope;
import com.example.cassa.projetAndroid.geographie.GenerateurDePartie.SetMonde;
import com.example.cassa.projetAndroid.geographie.GenerateurDePartie.SetOccitanie;

/**
 * Created by prax on 09/01/2018.
 */

public class Factory {

    public I_Set createNouveauNiveau(int difficulte){

        I_Set nouveauNiveau;
        switch (difficulte){
            case 1:
                nouveauNiveau = new SetMonde();
                break;
            case 2:
                nouveauNiveau = new SetOccitanie();
                break;
            case 3:
                nouveauNiveau = new SetEurope();
                break;
            default:
                nouveauNiveau = new SetMonde();
                break;
        }
        return nouveauNiveau;
    }

}
