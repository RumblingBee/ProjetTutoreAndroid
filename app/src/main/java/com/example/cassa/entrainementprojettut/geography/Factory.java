package com.example.cassa.entrainementprojettut.geography;

import com.example.cassa.entrainementprojettut.geography.gameSetter.I_Set;
import com.example.cassa.entrainementprojettut.geography.gameSetter.SetEurope;
import com.example.cassa.entrainementprojettut.geography.gameSetter.SetWorld;
import com.example.cassa.entrainementprojettut.geography.gameSetter.SetOccitanie;

/**
 * Created by prax on 09/01/2018.
 */

public class Factory {

    public I_Set createNewLevel(int difficulte){

        I_Set newLevel;
        switch (difficulte){
            case 1:
                newLevel = new SetWorld();
                break;
            case 2:
                newLevel = new SetEurope();
                break;
            case 3:
                newLevel = new SetOccitanie();
                break;
            default:
                newLevel = new SetWorld();
                break;
        }
        return newLevel;
    }

}
