package com.example.cassa.entrainementprojettut.pianoGame;

import com.example.cassa.entrainementprojettut.PlayerUtils.PlayerLifes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeBoss on 16/01/2018.
 */

public class ControlerMusic {

    Music music;
    Note actualKey;
    PlayerLifes lifes;
    int positionSequence;
    private int size;

    public ControlerMusic() {

        lifes=new PlayerLifes();
        List<Note> noteList = generateNotes();
        this.music = new Music(noteList);
        size = 3;
        music.generateSequence(size);
        this.positionSequence = 0;
        actualKey = music.getSequence().get(positionSequence);

    }

    public ControlerMusic(Music music) {
        lifes=new PlayerLifes();
        this.music = music;
        music.generateSequence(3);
        this.positionSequence = 0;
        actualKey = music.getSequence().get(positionSequence);
    }

    public boolean checkKey(int id){
        //Si touche correct
        if (id == actualKey.getId()){
            if (sequenceNotFinished()) {
                progressSequence();
                return true;
            }else{
                size++;
                music.generateSequence(size);
                positionSequence=0;
                actualKey = music.getSequence().get(0);
                return true;
            }
        }
        else{
            lifes.loseLife();
            positionSequence = 0;
            actualKey = music.getSequence().get(0);
            return false;
        }
    }

    public boolean isDead() {
        return lifes.isDead();
    }

    private boolean sequenceNotFinished() {
        return positionSequence < music.getPosition()-1;
    }

    private void progressSequence() {
        positionSequence++;
        actualKey = music.getSequence().get(positionSequence);
    }

    private List<Note> generateNotes() {
        List<Note>notes=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Note note=new Note((int)(Math.random() * (7) + 1),0);
            notes.add(note);
        }
        return notes;
    }

    public List<Integer> getIdSequence(){
        List<Integer> listId = new ArrayList<>();
        List<Note> sequence = music.getSequence();
        for(int i=0; i<sequence.size(); i++){
            Note note = sequence.get(i);
            int id = note.getId();
            listId.add(id);
        }
        return  listId;
    }

    public boolean songFinished(){
        return music.musicEnded();
    }

}
