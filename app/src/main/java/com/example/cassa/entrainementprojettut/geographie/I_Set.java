package com.example.cassa.entrainementprojettut.geographie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prax on 09/01/2018.
 */
public interface I_Set {

    List<Etiquette> listEtiquette = new ArrayList<>();


    //SETTERS

    void setImageFond();
    void setListEtiquette();

    List <Etiquette> getListEtiquette();
    int getImageFond();



}
