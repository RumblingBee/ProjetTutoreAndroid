package com.example.cassa.entrainementprojettut.mysteryWord.word;

/**
 * Created by clement on 04/01/18.
 */

public interface I_Word {

    String getMot();
    String getMotCode();
    int ajustementValeurDuCaractere(int ascii);

    void coderMot(int i);

}
