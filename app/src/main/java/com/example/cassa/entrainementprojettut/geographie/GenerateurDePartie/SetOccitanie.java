package com.example.cassa.entrainementprojettut.geographie.GenerateurDePartie;

import com.example.cassa.entrainementprojettut.R;
import com.example.cassa.entrainementprojettut.geographie.Etiquette;

import java.util.List;



public class SetOccitanie implements I_Set {

    int imageFond;

    public SetOccitanie(){

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
        float tailleCote = 1/12;

        listEtiquette.add(new Etiquette("Pyrénées \n Orientales",6/12,21/24, tailleCote));
        listEtiquette.add(new Etiquette("Aude", 6/12, 8/12, tailleCote));
        listEtiquette.add(new Etiquette("Hérault", 8/12, 6/12, tailleCote));
        listEtiquette.add(new Etiquette("Gard", 10/12, 9/24, tailleCote));
        listEtiquette.add(new Etiquette("Ariege", 15/48, 35/48, tailleCote));
        listEtiquette.add(new Etiquette("Haute \n Garonne", 13/48, 27/48, tailleCote));
        listEtiquette.add(new Etiquette("Tarn", 21/48, 5/12, tailleCote));
        listEtiquette.add(new Etiquette("Aveyron", 13/24, 3/12, tailleCote));
        listEtiquette.add(new Etiquette("Lozere", 33/48, 2/12, tailleCote));
        listEtiquette.add(new Etiquette("Haute \n Pyrénées", 7/48, 35/48, tailleCote));
        listEtiquette.add(new Etiquette("Gers", 3/24, 11/24, tailleCote));
        listEtiquette.add(new Etiquette("Tarn et \n Garonne", 3/12, 4/12, tailleCote));
        listEtiquette.add(new Etiquette("Lot", 4/12, 3/24, tailleCote));

    }
}
