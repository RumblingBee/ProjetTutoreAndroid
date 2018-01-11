package com.example.cassa.entrainementprojettut.geographie.GenerateurDePartie;

import com.example.cassa.entrainementprojettut.R;
import com.example.cassa.entrainementprojettut.geographie.Etiquette;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prax on 09/01/2018.
 */

public class SetEurope implements I_Set{
    private  int imageFond;
    private List<Etiquette> listEtiquette;

    public SetEurope(){
       listEtiquette  = new ArrayList<>();

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
        float tailleCote = 9/100F;

        listEtiquette.add(new Etiquette("Islande", 27/100F, 5/100F,tailleCote));
        listEtiquette.add(new Etiquette("Irlande", 27/100F, 40/100F, tailleCote));
        listEtiquette.add(new Etiquette("Royaume-\nUni", 33/100F, 45/100F, tailleCote));
        listEtiquette.add(new Etiquette("France", 36/100F, 61/100F,tailleCote));
        listEtiquette.add(new Etiquette("Espagne", 28/100F, 77/100F,tailleCote));
        listEtiquette.add(new Etiquette("Portugal", 18/100F, 74/100F,tailleCote));
        listEtiquette.add(new Etiquette("Italie", 48/100F, 77/100F, tailleCote));
        listEtiquette.add(new Etiquette("Allemagne", 45/100F, 51/100F, tailleCote));
        listEtiquette.add(new Etiquette("Pologne", 55/100F, 49/100F, tailleCote));
        listEtiquette.add(new Etiquette("Russie", 71/100F, 33/100F, tailleCote));
        listEtiquette.add(new Etiquette("Norvège", 44/100F, 27/100F, tailleCote));
        listEtiquette.add(new Etiquette("Suède", 52/100F, 14/100F, tailleCote));
        listEtiquette.add(new Etiquette("Finlande", 59/100F,22/100F, tailleCote));
        listEtiquette.add(new Etiquette("Turquie", 74/100F, 80/100F, tailleCote));
        listEtiquette.add(new Etiquette("Ukraine", 69/100F, 53/100F, tailleCote));


    }
}
