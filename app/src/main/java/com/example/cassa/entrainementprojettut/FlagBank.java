package com.example.cassa.entrainementprojettut;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by prax on 23/11/17.
 */

public class FlagBank {
    ArrayList<Flag> mListFlag;
    ArrayList<Integer> mListAdresseImage;
    ArrayList<String> mListNomPays;






    Flag mFLag;

   public FlagBank(){



       mListFlag = new ArrayList<Flag>();
       mListNomPays = new ArrayList<String>();
       mListAdresseImage = new ArrayList<Integer>();

       //Génération des images drapeaux

       mListAdresseImage.add(R.drawable.andorre);
       mListAdresseImage.add(R.drawable.allemagne);
       mListAdresseImage.add(R.drawable.albanie);
       mListAdresseImage.add(R.drawable.autriche);
       mListAdresseImage.add(R.drawable.belgique);




       //Génération des titres des drapeaux


       mListNomPays.add("Andore");
       mListNomPays.add("Allemagne");
       mListNomPays.add("Albanie");
       mListNomPays.add("Autriche");
       mListNomPays.add("Belgique");



       // Générations des FLags

      mListFlag = new ArrayList<Flag>(4);
       for(int i=0; i <4;i++){

            mFLag = new Flag(mListNomPays.get(i),mListAdresseImage.get(i));
            mListFlag.add(mFLag);

       }

       Collections.shuffle(mListFlag);



   }
  Flag getFlag(int i){

       return  mListFlag.get(i);

   }

}
