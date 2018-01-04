package com.example.cassa.entrainementprojettut;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prax on 04/01/18.
 */

public class EtiquetteBank {

    private List mListEtiquette = new ArrayList<Etiquette>();

    public EtiquetteBank(int niveau,float largeurEcran,float hauteurEcran) {

        mListEtiquette.add(new Etiquette("Amerique du Nord",largeurEcran*8/48,largeurEcran*3/12,hauteurEcran*1/12,hauteurEcran*5/24));
        mListEtiquette.add(new Etiquette("Afrique",largeurEcran/2,largeurEcran*7/12,hauteurEcran*9/24,hauteurEcran*11/24));
        mListEtiquette.add(new Etiquette("Europe",largeurEcran*11/24,largeurEcran*13/24,hauteurEcran/12,hauteurEcran*2/12));
        mListEtiquette.add(new Etiquette("Asie",largeurEcran*8/12,largeurEcran*18/24,hauteurEcran*2/12,hauteurEcran*5/24));
        mListEtiquette.add(new Etiquette("Amerique du Sud",largeurEcran*3/12,largeurEcran*4/12,hauteurEcran*5/12,hauteurEcran*13/24));
        mListEtiquette.add(new Etiquette("Océanie",largeurEcran*19/24,largeurEcran*21/24,hauteurEcran*13/24,hauteurEcran*15/24));
        mListEtiquette.add(new Etiquette("Antarctique",largeurEcran*3/12,largeurEcran*17/24,hauteurEcran*10/12,hauteurEcran*11/12));

    }
    public List getEtiquetteList(){
        return mListEtiquette;
    }
    public int getNbEtiquette(){
       return 7;
    }
}
