package com.example.cassa.entrainementprojettut.geographie;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by prax on 04/01/18.
 */

public class EtiquetteBank {

    private List mListEtiquette = new ArrayList<Etiquette>();

    public EtiquetteBank(int niveau,float largeurEcran,float hauteurEcran) {
        switch (niveau){
           case 1:
                mListEtiquette.add(new Etiquette("Amerique du Nord",largeurEcran*8/48,largeurEcran*3/12,hauteurEcran*1/12,hauteurEcran*5/24));
                mListEtiquette.add(new Etiquette("Afrique",largeurEcran/2,largeurEcran*7/12,hauteurEcran*9/24,hauteurEcran*11/24));
                mListEtiquette.add(new Etiquette("Europe",largeurEcran*11/24,largeurEcran*13/24,hauteurEcran/12,hauteurEcran*2/12));
                mListEtiquette.add(new Etiquette("Asie",largeurEcran*8/12,largeurEcran*18/24,hauteurEcran*2/12,hauteurEcran*5/24));
                mListEtiquette.add(new Etiquette("Amerique du Sud",largeurEcran*3/12,largeurEcran*4/12,hauteurEcran*5/12,hauteurEcran*13/24));
                mListEtiquette.add(new Etiquette("Océanie",largeurEcran*19/24,largeurEcran*21/24,hauteurEcran*13/24,hauteurEcran*15/24));
                mListEtiquette.add(new Etiquette("Antarctique",largeurEcran*3/12,largeurEcran*17/24,hauteurEcran*10/12,hauteurEcran*11/12));

                break;
            case 2:
                mListEtiquette.add(new Etiquette("Pyrénées-Orientales",largeurEcran*9/24,largeurEcran*6/12,hauteurEcran*9/12,hauteurEcran*10/12));
                mListEtiquette.add(new Etiquette("Aude",largeurEcran*21/48,largeurEcran*25/48,hauteurEcran*7/12,hauteurEcran*31/48));
                mListEtiquette.add(new Etiquette("Hérault",largeurEcran*15/24,largeurEcran*33/48,hauteurEcran*21/48,hauteurEcran*22/48));
                mListEtiquette.add(new Etiquette("Gard",largeurEcran*19/24,largeurEcran*21/24,hauteurEcran*14/48,hauteurEcran*17/48));
                mListEtiquette.add(new Etiquette("Ariege",largeurEcran*7/24,largeurEcran*17/48,hauteurEcran*31/48,hauteurEcran*17/24));
                mListEtiquette.add(new Etiquette("Haute-Garonne",largeurEcran*2/12,largeurEcran*14/48,hauteurEcran*23/48,hauteurEcran*26/48));

                mListEtiquette.add(new Etiquette("Tarn",largeurEcran*8/48,largeurEcran*3/12,hauteurEcran*1/12,hauteurEcran*5/24));
                mListEtiquette.add(new Etiquette("Aveyron",largeurEcran*8/48,largeurEcran*3/12,hauteurEcran*1/12,hauteurEcran*5/24));
                mListEtiquette.add(new Etiquette("Lozere",largeurEcran*8/48,largeurEcran*3/12,hauteurEcran*1/12,hauteurEcran*5/24));
                mListEtiquette.add(new Etiquette("Haute-Pyrénées",largeurEcran*8/48,largeurEcran*3/12,hauteurEcran*1/12,hauteurEcran*5/24));
                mListEtiquette.add(new Etiquette("Gers",largeurEcran*8/48,largeurEcran*3/12,hauteurEcran*1/12,hauteurEcran*5/24));
                mListEtiquette.add(new Etiquette("Tarn et Garonne",largeurEcran*8/48,largeurEcran*3/12,hauteurEcran*1/12,hauteurEcran*5/24));
                mListEtiquette.add(new Etiquette("Lot",largeurEcran*8/48,largeurEcran*3/12,hauteurEcran*1/12,hauteurEcran*5/24));

                break;
            case 3:
                mListEtiquette.add(new Etiquette("Islande", largeurEcran*26/100, largeurEcran*34/100, hauteurEcran*6/100, hauteurEcran*13/100));
                mListEtiquette.add(new Etiquette("Irlande", largeurEcran*27/100, largeurEcran*35/100, hauteurEcran*41/100, hauteurEcran*49/100));
                mListEtiquette.add(new Etiquette("Royaume-\nUni", largeurEcran*34/100, largeurEcran*41/100, hauteurEcran*45/100, hauteurEcran*52/100));
                break;
        }
    }

    public List getEtiquetteList(){
        return mListEtiquette;
    }

}
