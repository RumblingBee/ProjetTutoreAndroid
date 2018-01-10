package com.example.cassa.projetAndroid.geographie.GenerateurDePartie;

import com.example.cassa.projetAndroid.R;
import com.example.cassa.projetAndroid.geographie.Etiquette;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prax on 09/01/2018.
 */

public class SetMonde implements I_Set {
    int imageFond;
    private List<Etiquette> listEtiquette;

    public SetMonde(){

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
        imageFond = R.drawable.continent;
    }

    @Override
    public void setListEtiquette() {
        float tailleCote = 1/12F;

        listEtiquette.add(new Etiquette("Amerique \n du Nord",10/48F,5/24F,tailleCote));
        listEtiquette.add(new Etiquette("Afrique",1/2F,9/24F,tailleCote));
        listEtiquette.add(new Etiquette("Europe",25/48F,7/48F,tailleCote));
        listEtiquette.add(new Etiquette("Asie",33/48F,4/24F,tailleCote));
        listEtiquette.add(new Etiquette("Amerique\n du Sud",15/48F,13/24F,tailleCote));
        listEtiquette.add(new Etiquette("Océanie",10/12F,15/24F,tailleCote));
        listEtiquette.add(new Etiquette("Antarctique",6/12F,11/12F,tailleCote));

    }
}
