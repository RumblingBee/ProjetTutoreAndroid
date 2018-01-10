package com.example.cassa.entrainementprojettut.geography;

import com.example.cassa.entrainementprojettut.geography.gameSetter.I_Set;
import com.example.cassa.entrainementprojettut.geography.gameSetter.SetEurope;
import com.example.cassa.entrainementprojettut.geography.gameSetter.SetWorld;
import com.example.cassa.entrainementprojettut.geography.gameSetter.SetOccitanie;

/**
 * Created by prax on 09/01/2018.
 */

public class Factory {

    public I_Set createNouveauNiveau(int difficulte){

        I_Set nouveauNiveau;
        switch (difficulte){
            case 1:
                nouveauNiveau = new SetWorld();
                break;
            case 2:
                nouveauNiveau = new SetOccitanie();
                break;
            case 3:
                nouveauNiveau = new SetEurope();
                break;
            default:
                nouveauNiveau = new SetWorld();
                break;
        }
        return nouveauNiveau;
    }

}
