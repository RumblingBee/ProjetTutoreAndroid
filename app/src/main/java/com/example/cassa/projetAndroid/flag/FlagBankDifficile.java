package com.example.cassa.projetAndroid.flag;

import com.example.cassa.projetAndroid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by LeBoss on 09/01/2018.
 */

public class FlagBankDifficile implements I_FlagBank {
    private ArrayList<Flag> mListeChoixFlag;
    private ArrayList<Integer> mListeAdresseImage;
    private ArrayList<String> mListeNomPays;
    private static String[] mPays = {"France","Allemagne","Italie","Espagne","Pays-Bas","Portugal","Suisse","Royaume-Uni","Belgique", "Russie",
            "Canada", "Etats-Unis", "Brésil", "Chine", "Australie", "Afrique Du Sud", "Japon", "Argentine", "Algérie", "Mexique",
            "Autriche", "Maroc", "Tunisie", "Turquie", "Nouvelle-Zélande", "Inde", "Pérou", "Corée Du Sud", "Egypte", "Chili"};

    private Random rand = new Random();


    public FlagBankDifficile(ArrayList<Flag> listFlag){
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

    public FlagBankDifficile(){
        mListeNomPays = new ArrayList<String>();
        mListeAdresseImage = new ArrayList<Integer>();
        mListeChoixFlag = new ArrayList<Flag>();

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
        int x = rand.nextInt(30);
        return x;
    }


    private int gererDoublon(ArrayList<Integer> dispo, int i, int x) {
        Flag mFLag = new Flag(mListeNomPays.get(x),mListeAdresseImage.get(x));
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
        //Pays faciles
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

        //Pays moins facile
        mListeAdresseImage.add(R.drawable.canada);
        mListeAdresseImage.add(R.drawable.etats_unis);
        mListeAdresseImage.add(R.drawable.bresil);
        mListeAdresseImage.add(R.drawable.chine);
        mListeAdresseImage.add(R.drawable.australie);
        mListeAdresseImage.add(R.drawable.afrique_du_sud);
        mListeAdresseImage.add(R.drawable.japon);
        mListeAdresseImage.add(R.drawable.argentine);
        mListeAdresseImage.add(R.drawable.algerie);
        mListeAdresseImage.add(R.drawable.mexique);

        //Pays difficiles
        mListeAdresseImage.add(R.drawable.autriche);
        mListeAdresseImage.add(R.drawable.maroc);
        mListeAdresseImage.add(R.drawable.tunisie);
        mListeAdresseImage.add(R.drawable.turquie);
        mListeAdresseImage.add(R.drawable.nouvelle_zelande);
        mListeAdresseImage.add(R.drawable.inde);
        mListeAdresseImage.add(R.drawable.perou);
        mListeAdresseImage.add(R.drawable.coree_cu_sud);
        mListeAdresseImage.add(R.drawable.egypte);
        mListeAdresseImage.add(R.drawable.chili);
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
