package com.example.cassa.entrainementprojettut.geographie;

import com.example.cassa.entrainementprojettut.R;

import java.util.List;

/**
 * Created by prax on 09/01/2018.
 */

public class SetMonde implements I_Set {
    int imageFond;

    public SetMonde(){

        setImageFond();
        setListEtiquette();



    }
    public List<Etiquette> getListEtiquette(){

        return listEtiquette;
    }
    public int getImageFond(){

        return imageFond;
    }

    @Override
    public void setImageFond() {
        imageFond = R.drawable.carte_europe;
    }

    @Override
    public void setListEtiquette() {
        float tailleCote = 9/100;

        listEtiquette.add(new Etiquette("Islande", 27/100, 6/100,tailleCote));
        listEtiquette.add(new Etiquette("Irlande", 27/100, 41/100, tailleCote));
        listEtiquette.add(new Etiquette("Royaume-\nUni", 33/100, 45/100, tailleCote));
        listEtiquette.add(new Etiquette("France", 36/100, 61/100,tailleCote));
        listEtiquette.add(new Etiquette("Espagne", 28/100, 76/100,tailleCote));
        listEtiquette.add(new Etiquette("Portugal", 19/100, 76/100,tailleCote));
        listEtiquette.add(new Etiquette("Italie", 49/100, 77/100, tailleCote));
        listEtiquette.add(new Etiquette("Allemagne", 45/100, 51/100, tailleCote));
        listEtiquette.add(new Etiquette("Pologne", 55/100, 49/100, tailleCote));
        listEtiquette.add(new Etiquette("Russie", 70/100, 33/100, tailleCote));
        listEtiquette.add(new Etiquette("Norvège", 45/100, 27/100, tailleCote));
        listEtiquette.add(new Etiquette("Suède", 53/100, 14/100, tailleCote));
        listEtiquette.add(new Etiquette("Finlande", 59/100,22/100, tailleCote));
        listEtiquette.add(new Etiquette("Turquie", 74/100, 82/100, tailleCote));
        listEtiquette.add(new Etiquette("Ukraine", 69/100, 54/100, tailleCote));

    }
}
