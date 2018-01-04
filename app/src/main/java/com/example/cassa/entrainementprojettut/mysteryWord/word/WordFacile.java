package com.example.cassa.entrainementprojettut.mysteryWord.word;

/**
 * Created by clement on 04/01/18.
 */

public class WordFacile implements I_Word {

    private String mot;
    private String motCode;
    private String consigne="Décale les lettres du mot codé de -1 lettre dans l'alphabet pour trouver le mot caché";

    public String getMot() {
        return mot;
    }

    public String getMotCode() {
        return motCode;
    }

    public String getConsigne() {
        return consigne;
    }

    public WordFacile(String mot) {
        this.mot=mot;
        coderMot();
    }

    @Override
    public void coderMot() {
        StringBuilder codedWord = new StringBuilder();
        for (char c : mot.toCharArray())
        {
            c = coderLettre(c);
            codedWord.append(c);
        }
        motCode=codedWord.toString();
    }

    private char coderLettre(char c) {
        int ascii=(int)c;
        ascii-=1;
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
