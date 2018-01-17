package com.example.cassa.entrainementprojettut.PlayerUtils;

/**
 * Created by clement on 17/01/18.
 */

public class PlayerLifes {

    private int life;

    public PlayerLifes() {
        this.life = 3;
    }

    public void loseLife(){
        life--;
    }

    public void resetLife(){
        life=3;
    }

    public boolean isDead(){
        return life==3;
    }
}
