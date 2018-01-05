package com.example.cassa.entrainementprojettut.mysteryWord.word;

/**
 * Created by clement on 05/01/18.
 */

public abstract class OutilsWord implements I_Word {

    protected String mot;
    protected String motCode;
    protected String consigne;

    public String getMot() {
        return mot;
    }
    public String getMotCode() {
        return motCode;
    }
    public String getConsigne() {
        return consigne;
    }

    protected char coderLettreEnNegatif(char c, int i) {
        int ascii=(int)c;
        ascii-=i;
        ascii = ajustementValeurDuCaractere(ascii);
        return (char)ascii;
    }

    public int ajustementValeurDuCaractere(int ascii) {
        if(ascii > 122){
            ascii = ascii - 26;
        }
        else if(ascii < 97){
            ascii = ascii + 26;
        }
        return ascii;
    }

}
