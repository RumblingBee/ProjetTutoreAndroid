package com.example.cassa.entrainementprojettut.geographie;

import com.example.cassa.entrainementprojettut.R;

import java.util.List;

/**
 * Created by prax on 09/01/2018.
 */

public class SetEurope implements I_Set{
    private  int imageFond;

    public SetEurope(){

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
        float tailleCote = 1/12;

        listEtiquette.add(new Etiquette("Amerique \n du Nord",10/48,7/24,tailleCote));
        listEtiquette.add(new Etiquette("Afrique",1/2,9/24,tailleCote));
        listEtiquette.add(new Etiquette("Europe",25/48,7/48,tailleCote));
        listEtiquette.add(new Etiquette("Asie",33/48,4/24,tailleCote));
        listEtiquette.add(new Etiquette("Amerique\n du Sud",15/48,13/24,tailleCote));
        listEtiquette.add(new Etiquette("Océanie",10/12,15/24,tailleCote));
        listEtiquette.add(new Etiquette("Antarctique",6/12,11/12,tailleCote));

    }
}
