package com.example.cassa.entrainementprojettut.pianoGame;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.cassa.entrainementprojettut.PlayerUtils.PlayerLifes;

import com.example.cassa.entrainementprojettut.GameActivity;
import com.example.cassa.entrainementprojettut.PianoActivity;
import com.example.cassa.entrainementprojettut.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeBoss on 16/01/2018.
 */

public class ControlerMusic extends GameActivity{

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
        size=3;
        music.generateSequence(size);
        this.positionSequence = 0;
        actualKey = music.getSequence().get(positionSequence);
    }

    public int checkKey(int id){
        //Si touche correct
        if (id == actualKey.getId()){
            //si pas encore derniere touche
            if (sequenceNotFinished()) {
                progressSequence();
                return 0;
            }
            //si derniere touche
            else{
                if(songFinished()){
                    return 2;
                }else{
                    size++;
                    music.generateSequence(size);
                    positionSequence=0;
                    actualKey = music.getSequence().get(0);
                    return 1;
                }
            }
        }
        else{
            lifes.loseLife();
            if(isDead()){
                return -2;
            }
            positionSequence = 0;
            actualKey = music.getSequence().get(0);
            return -1;
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

        Note[] notesTab = getNotesTab();

        for (int i = 0; i <5 ; i++) {
            //Note note=new Note((int)(Math.random() * (7)),0);
            notes.add(notesTab[(int)(Math.random() * (7))]);
        }
        return notes;
    }

    @NonNull
    private Note[] getNotesTab() {
        Note noteDo = new Note(1, R.raw.note_do);
        Note noteRe = new Note(2, R.raw.note_re);
        Note noteMi = new Note(3, R.raw.note_mi);
        Note noteFa = new Note(4, R.raw.note_fa);
        Note noteSol= new Note(5, R.raw.note_sol);
        Note noteLa = new Note(6, R.raw.note_la);
        Note noteSi = new Note(7, R.raw.note_si);

        return new Note[]{noteDo, noteRe, noteMi, noteFa, noteSol, noteLa, noteSi};
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

    public void resetSequence() {
        positionSequence = 0;
        actualKey = music.getSequence().get(positionSequence);
    }

    public boolean songFinished(){
        return music.musicEnded();
    }

    public void playSong(Activity activity, View view){
        String tmp = (String) view.getTag();
        int key = Integer.parseInt(tmp)-1;
        Note notesTab[] = getNotesTab();
        notesTab[key].playSong(activity);
    }

}
