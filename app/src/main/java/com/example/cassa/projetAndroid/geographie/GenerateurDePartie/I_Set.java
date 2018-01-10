package com.example.cassa.projetAndroid.geographie.GenerateurDePartie;

import com.example.cassa.projetAndroid.geographie.Etiquette;

import java.util.List;

/**
 * Created by prax on 09/01/2018.
 */
public interface I_Set {




    //SETTERS

    void setImageFond();
    void setListEtiquette();

    List <Etiquette> getListEtiquette();
    int getImageFond();



}
