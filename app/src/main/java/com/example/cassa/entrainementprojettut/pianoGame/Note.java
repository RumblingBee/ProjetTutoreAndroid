package com.example.cassa.entrainementprojettut.pianoGame;

/**
 * Created by clement on 16/01/18.
 */

class Note {
    private int id;
    private int adressSound;

    public Note(int id, int adressSound) {
        this.id = id;
        this.adressSound = adressSound;
    }

    public int getId() {
        return id;
    }

    public int getAdressSound() {
        return adressSound;
    }


}
