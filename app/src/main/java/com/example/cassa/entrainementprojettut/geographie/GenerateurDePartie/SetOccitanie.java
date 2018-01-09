package com.example.cassa.entrainementprojettut.geographie.GenerateurDePartie;

import com.example.cassa.entrainementprojettut.R;
import com.example.cassa.entrainementprojettut.geographie.Etiquette;

import java.util.ArrayList;
import java.util.List;



public class SetOccitanie implements I_Set {

    int imageFond;
    private List<Etiquette> listEtiquette;

    public SetOccitanie(){

        listEtiquette = new ArrayList<>();
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
        imageFond = R.drawable.occitanie;
    }

    @Override
    public void setListEtiquette() {
        float tailleCote = 1/12F;

        listEtiquette.add(new Etiquette("Pyrénées \n Orientales",6/12F,21/24F, tailleCote));
        listEtiquette.add(new Etiquette("Aude", 6/12F, 8/12F, tailleCote));
        listEtiquette.add(new Etiquette("Hérault", 8/12F, 6/12F, tailleCote));
        listEtiquette.add(new Etiquette("Gard", 10/12F, 9/24F, tailleCote));
        listEtiquette.add(new Etiquette("Ariege", 15/48F, 35/48F, tailleCote));
        listEtiquette.add(new Etiquette("Haute \n Garonne", 13/48F, 27/48F, tailleCote));
        listEtiquette.add(new Etiquette("Tarn", 21/48F, 5/12F, tailleCote));
        listEtiquette.add(new Etiquette("Aveyron", 13/24F, 3/12F, tailleCote));
        listEtiquette.add(new Etiquette("Lozere", 33/48F, 2/12F, tailleCote));
        listEtiquette.add(new Etiquette("Haute \n Pyrénées", 3/48F, 35/48F, tailleCote));
        listEtiquette.add(new Etiquette("Gers", 3/24F, 11/24F, tailleCote));
        listEtiquette.add(new Etiquette("Tarn et \n Garonne", 3/12F, 4/12F, tailleCote));
        listEtiquette.add(new Etiquette("Lot", 4/12F, 3/24F, tailleCote));

    }
}
