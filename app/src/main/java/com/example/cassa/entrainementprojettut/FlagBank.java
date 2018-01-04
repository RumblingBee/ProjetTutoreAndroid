package com.example.cassa.entrainementprojettut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by prax on 23/11/17.
 */

public class FlagBank {
    ArrayList<Flag> mListChoixFlag;
    ArrayList<Integer> mListAdresseImage;
    ArrayList<String> mListNomPays;
    private static String[] mPays = {"France", "Allemagne", "Italie", "Espagne", "Pays-Bas", "Portugal", "Suisse", "Royaume-Uni", "Belgique", "Russie",
            "Canada", "Etats-Unis", "Brésil", "Chine", "Australie", "Afrique Du Sud", "Japon", "Argentine", "Algérie", "Mexique",
            "Autriche", "Maroc", "Tunisie", "Turquie", "Nouvelle-Zélande", "Inde", "Pérou", "Corée Du Sud", "Egypte", "Chili"};


    //Rand de séléction de pays
    private Random rand = new Random();



    public FlagBank(ArrayList<Flag> listFlag){
        if(listFlag.size() > 0) {
            mListChoixFlag = listFlag;
        }else{
            initialisationImagesPays();
            initialisationNomPays(1);
            initialisationChoixFlag(1);
        }
    }



    public FlagBank(int diff){

        mListChoixFlag = new ArrayList<Flag>();
        mListNomPays = new ArrayList<String>();
        mListAdresseImage = new ArrayList<Integer>();
        mListChoixFlag = new ArrayList<Flag>(4);

        initialisationImagesPays();
        initialisationNomPays(diff);
        initialisationChoixFlag(diff);
    }



    private void initialisationChoixFlag(int diff) {
        //ArrayList pour éviter les doublons
        ArrayList<Integer> dispo = new ArrayList<Integer>();
        for(int i=0; i <4;i++){

            int x = random(diff);

            i = gererDoublon(dispo, i, x);
        }

        Collections.shuffle(mListChoixFlag);
    }


    private int random(int diff) {
        int x=0;
        if(diff == 1){
            x = rand.nextInt(10);
        }else if(diff == 2){
            x = rand.nextInt(20);
        }else if(diff == 3){
            x = rand.nextInt(30);
        }
        return x;
    }


    private int gererDoublon(ArrayList<Integer> dispo, int i, int x) {
        Flag mFLag = new Flag(mListNomPays.get(x),mListAdresseImage.get(x));
        //Pour éviter d'ajouter deux fois le même Flag
        if(dispo.contains(x)) {
            i--;
        }else {
            mListChoixFlag.add(mFLag);
            dispo.add(x);
        }
        return i;
    }



    private void initialisationNomPays(int diff) {
        switch (diff){
            case 1:
                for(int i=0; i<10; i++){
                    mListNomPays.add(mPays[i]);
                }
            case 2:
                for (int i=0; i<20; i++){
                    mListNomPays.add(mPays[i]);
                }
            case 3:
                for (int i=0; i<30; i++){
                    mListNomPays.add(mPays[i]);
                }
        }
    }


    private void initialisationImagesPays() {
        //Pays faciles
        mListAdresseImage.add(R.drawable.france);
        mListAdresseImage.add(R.drawable.allemagne);
        mListAdresseImage.add(R.drawable.italie);
        mListAdresseImage.add(R.drawable.espagne);
        mListAdresseImage.add(R.drawable.pays_bas);
        mListAdresseImage.add(R.drawable.portugal);
        mListAdresseImage.add(R.drawable.suisse);
        mListAdresseImage.add(R.drawable.royaume_uni);
        mListAdresseImage.add(R.drawable.belgique);
        mListAdresseImage.add(R.drawable.russie);

        //Pays moins facile
        mListAdresseImage.add(R.drawable.canada);
        mListAdresseImage.add(R.drawable.etats_unis);
        mListAdresseImage.add(R.drawable.bresil);
        mListAdresseImage.add(R.drawable.chine);
        mListAdresseImage.add(R.drawable.australie);
        mListAdresseImage.add(R.drawable.afrique_du_sud);
        mListAdresseImage.add(R.drawable.japon);
        mListAdresseImage.add(R.drawable.argentine);
        mListAdresseImage.add(R.drawable.algerie);
        mListAdresseImage.add(R.drawable.mexique);

        //Pays difficiles
        mListAdresseImage.add(R.drawable.autriche);
        mListAdresseImage.add(R.drawable.maroc);
        mListAdresseImage.add(R.drawable.tunisie);
        mListAdresseImage.add(R.drawable.turquie);
        mListAdresseImage.add(R.drawable.nouvelle_zelande);
        mListAdresseImage.add(R.drawable.inde);
        mListAdresseImage.add(R.drawable.perou);
        mListAdresseImage.add(R.drawable.coree_cu_sud);
        mListAdresseImage.add(R.drawable.egypte);
        mListAdresseImage.add(R.drawable.chili);
    }


    Flag getFlag(int i){ return  mListChoixFlag.get(i); }

}
