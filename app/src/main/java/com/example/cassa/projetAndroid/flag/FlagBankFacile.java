package com.example.cassa.projetAndroid.flag;

import com.example.cassa.projetAndroid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by LeBoss on 09/01/2018.
 */

public class FlagBankFacile implements I_FlagBank {
    private ArrayList<Flag> mListeChoixFlag;
    private ArrayList<Integer> mListeAdresseImage;
    private ArrayList<String> mListeNomPays;
    private static String[] mPays = {"France", "Allemagne", "Italie", "Espagne", "Pays-Bas",
            "Portugal", "Suisse", "Royaume-Uni", "Belgique", "Russie"};

    private Random rand = new Random();

    public FlagBankFacile(ArrayList<Flag> listFlag){
        mListeNomPays = new ArrayList<String>();
        mListeAdresseImage = new ArrayList<Integer>();
        if(listFlag.size() == 4) {
            mListeChoixFlag = listFlag;
        }else{
            mListeChoixFlag = new ArrayList<Flag>(4);
            /*initialisationImagesPays();
            initialisationNomPays();*/
            initialisationChoixFlag();
        }
    }

    public FlagBankFacile(){
        mListeNomPays = new ArrayList<String>();
        mListeAdresseImage = new ArrayList<Integer>();
        mListeChoixFlag = new ArrayList<Flag>(4);

        initialisationImagesPays();
        initialisationNomPays();
        initialisationChoixFlag();
    }


    private void initialisationChoixFlag() {
        //ArrayList pour éviter les doublons
        ArrayList<Integer> paysUtilises = new ArrayList<Integer>();
        for(int i=0; i <4;i++){
            int x = random();
            i = gererDoublon(paysUtilises, i, x);
        }

        Collections.shuffle(mListeChoixFlag);
    }


    private int random() {
        int x = rand.nextInt(10);
        return x;
    }


    private int gererDoublon(ArrayList<Integer> dispo, int i, int x) {
        Flag mFLag = new Flag(mListeNomPays.get(x), mListeAdresseImage.get(x));
        if(dispo.contains(x)) {
            i--;
        }else {
            mListeChoixFlag.add(mFLag);
            dispo.add(x);
        }
        return i;
    }


    private void initialisationNomPays() {
        for (String pays: mPays){
            mListeNomPays.add(pays);
        }
    }


    private void initialisationImagesPays() {
        mListeAdresseImage.add(R.drawable.france);
        mListeAdresseImage.add(R.drawable.allemagne);
        mListeAdresseImage.add(R.drawable.italie);
        mListeAdresseImage.add(R.drawable.espagne);
        mListeAdresseImage.add(R.drawable.pays_bas);
        mListeAdresseImage.add(R.drawable.portugal);
        mListeAdresseImage.add(R.drawable.suisse);
        mListeAdresseImage.add(R.drawable.royaume_uni);
        mListeAdresseImage.add(R.drawable.belgique);
        mListeAdresseImage.add(R.drawable.russie);
    }


    @Override
    public Flag getFlag(int i) {
        return mListeChoixFlag.get(i);
    }

    @Override
    public List<Flag> getChoixFlag() {
        return mListeChoixFlag;
    }


}

