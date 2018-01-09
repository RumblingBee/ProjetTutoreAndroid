package com.example.cassa.entrainementprojettut.flag;

/**
 * Created by LeBoss on 09/01/2018.
 */

public class FactoryFlagBank {

    public I_FlagBank genererFlagBank(int niveauChoisi){
        I_FlagBank flagBank;

        switch (niveauChoisi){
            case 1:
                flagBank = new FlagBankFacile();
                break;
            case 2:
                flagBank = new FlagBankMoyenne();
                break;
            case 3:
                flagBank = new FlagBankDifficile();
                break;
            default:
                flagBank = new FlagBankFacile();
                break;
        }
        return flagBank;
    }
}
