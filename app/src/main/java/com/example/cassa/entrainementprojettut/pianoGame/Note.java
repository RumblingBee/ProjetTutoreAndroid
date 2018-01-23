package com.example.cassa.entrainementprojettut.pianoGame;

import android.app.Activity;
import android.media.MediaPlayer;

/**
 * Created by clement on 16/01/18.
 */

class Note {
    private int id;
    private int adressSound;
    private MediaPlayer song = new MediaPlayer();

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

    public void playSong(Activity activity){
        if (song.isPlaying()){
            song.stop();
            song.release();
            song = new MediaPlayer();
        }
        song = MediaPlayer.create(activity, adressSound);
        song.start();
    }

}
