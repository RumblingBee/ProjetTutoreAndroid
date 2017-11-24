package com.example.cassa.entrainementprojettut;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by prax on 23/11/17.
 */

public class Flag {
   private String mNameCountry;
    /**
     * l'adresse de l'image du drapeau
     */
   private int mRessource;




    public Flag(String name, int adresseImage){
        this.mNameCountry=name;
        this.mRessource= adresseImage;
    }

    public int getmRessource(){
        return this.mRessource;
    }

    public String getmNameCountry(){
        return this.mNameCountry;
    }


}
