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
                mListEtiquette.add(new Etiquette("Amerique \n du Nord",largeurEcran*10/48,largeurEcran*7/24,hauteurEcran*4/24,hauteurEcran*6/24));
                mListEtiquette.add(new Etiquette("Afrique",largeurEcran/2,largeurEcran*7/12,hauteurEcran*9/24,hauteurEcran*11/24));
                mListEtiquette.add(new Etiquette("Europe",largeurEcran*25/48,largeurEcran*29/48,hauteurEcran*7/48,hauteurEcran*11/48));
                mListEtiquette.add(new Etiquette("Asie",largeurEcran*33/48,largeurEcran*37/48,hauteurEcran*4/24,hauteurEcran*6/24));
                mListEtiquette.add(new Etiquette("Amerique\n du Sud",largeurEcran*15/48,largeurEcran*19/48,hauteurEcran*13/24,hauteurEcran*15/24));
                mListEtiquette.add(new Etiquette("Océanie",largeurEcran*10/12,largeurEcran*11/12,hauteurEcran*15/24,hauteurEcran*17/24));
                mListEtiquette.add(new Etiquette("Antarctique",largeurEcran*6/12,largeurEcran*7/12,hauteurEcran*11/12,hauteurEcran));

                break;
            case 2:
                mListEtiquette.add(new Etiquette("Pyrénées \n Orientales",largeurEcran*6/12,largeurEcran*7/12,hauteurEcran*21/24,hauteurEcran*23/24));
                mListEtiquette.add(new Etiquette("Aude",largeurEcran*6/12,largeurEcran*7/12,hauteurEcran*8/12,hauteurEcran*9/12));
                mListEtiquette.add(new Etiquette("Hérault",largeurEcran*8/12,largeurEcran*9/12,hauteurEcran*6/12,hauteurEcran*7/12));
                mListEtiquette.add(new Etiquette("Gard",largeurEcran*10/12,largeurEcran*11/12,hauteurEcran*9/24,hauteurEcran*11/24));
                mListEtiquette.add(new Etiquette("Ariege",largeurEcran*15/48,largeurEcran*19/48,hauteurEcran*35/48,hauteurEcran*39/48));
                mListEtiquette.add(new Etiquette("Haute \n Garonne",largeurEcran*13/48,largeurEcran*17/48,hauteurEcran*27/48,hauteurEcran*31/48));
                mListEtiquette.add(new Etiquette("Tarn",largeurEcran*21/48,largeurEcran*25/48,hauteurEcran*5/12,hauteurEcran*6/12));
                mListEtiquette.add(new Etiquette("Aveyron",largeurEcran*13/24,largeurEcran*15/24,hauteurEcran*3/12,hauteurEcran*4/12));
                mListEtiquette.add(new Etiquette("Lozere",largeurEcran*33/48,largeurEcran*37/48,hauteurEcran*2/12,hauteurEcran*3/12));
                mListEtiquette.add(new Etiquette("Haute \n Pyrénées",largeurEcran*3/48,largeurEcran*7/48,hauteurEcran*35/48,hauteurEcran*39/48));
                mListEtiquette.add(new Etiquette("Gers",largeurEcran*3/24,largeurEcran*5/24,hauteurEcran*11/24,hauteurEcran*13/24));
                mListEtiquette.add(new Etiquette("Tarn et \n Garonne",largeurEcran*3/12,largeurEcran*4/12,hauteurEcran*4/12,hauteurEcran*5/12));
                mListEtiquette.add(new Etiquette("Lot",largeurEcran*4/12,largeurEcran*5/12,hauteurEcran*3/24,hauteurEcran*5/24));

                break;
            case 3:
                mListEtiquette.add(new Etiquette("Islande", largeurEcran*27/100, largeurEcran*34/100, hauteurEcran*6/100, hauteurEcran*13/100));
                mListEtiquette.add(new Etiquette("Irlande", largeurEcran*27/100, largeurEcran*34/100, hauteurEcran*41/100, hauteurEcran*48/100));
                mListEtiquette.add(new Etiquette("Royaume-\nUni", largeurEcran*33/100, largeurEcran*42/100, hauteurEcran*45/100, hauteurEcran*55/100));
                mListEtiquette.add(new Etiquette("France", largeurEcran*36/100, largeurEcran*43/100, hauteurEcran*61/100, hauteurEcran*68/100));
                mListEtiquette.add(new Etiquette("Espagne", largeurEcran*28/100, largeurEcran*36/100, hauteurEcran*76/100, hauteurEcran*83/100));
                mListEtiquette.add(new Etiquette("Portugal", largeurEcran*19/100, largeurEcran*27/100, hauteurEcran*76/100, hauteurEcran*83/100));
                mListEtiquette.add(new Etiquette("Italie", largeurEcran*49/100, largeurEcran*55/100, hauteurEcran*77/100, hauteurEcran*84/100));
                mListEtiquette.add(new Etiquette("Allemagne", largeurEcran*45/100, largeurEcran*54/100, hauteurEcran*51/100, hauteurEcran*58/100));
                mListEtiquette.add(new Etiquette("Pologne", largeurEcran*55/100, largeurEcran*63/100, hauteurEcran*49/100, hauteurEcran*56/100));
                mListEtiquette.add(new Etiquette("Russie", largeurEcran*70/100, largeurEcran*77/100, hauteurEcran*33/100, hauteurEcran*40/100));
                mListEtiquette.add(new Etiquette("Norvège", largeurEcran*45/100, largeurEcran*53/100, hauteurEcran*27/100, hauteurEcran*34/100));
                mListEtiquette.add(new Etiquette("Suède", largeurEcran*53/100, largeurEcran*60/100, hauteurEcran*14/100, hauteurEcran*21/100));
                mListEtiquette.add(new Etiquette("Finlande", largeurEcran*59/100, largeurEcran*67/100, hauteurEcran*22/100, hauteurEcran*29/100));
                mListEtiquette.add(new Etiquette("Turquie", largeurEcran*74/100, largeurEcran*81/100, hauteurEcran*82/100, hauteurEcran*89/100));
                mListEtiquette.add(new Etiquette("Ukraine", largeurEcran*69/100, largeurEcran*76/100, hauteurEcran*54/100, hauteurEcran*61/100));
                break;
        }
    }

    public List getEtiquetteList(){
        return mListEtiquette;
    }

}
