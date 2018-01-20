package com.example.cassa.entrainementprojettut.pianoGame;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeBoss on 16/01/2018.
 */

public class ControlerMusic {

    Music music;
    Note actualKey;
    int positionSequence;

    public ControlerMusic() {

        List<Note> noteList = generateNotes();
        this.music = new Music(noteList);
        music.generateSequence(3);
        this.positionSequence = 0;
        actualKey = music.getSequence().get(positionSequence);

    }

    public boolean checkKey(int id){
        //Si touche correct
        if (id == actualKey.getId()){
            //si pas encore derniere touche
            if (positionSequence < music.getPosition()-1) {
                System.out.println("valentin: Bravo !");
                positionSequence++;
                actualKey = music.getSequence().get(positionSequence);
                return true;
            }
            //si derniere touche
            else{
                System.out.println("valentin: Gagné !");
                return true;
            }
        }
        //si mauvaise touche
        else{
            //loseLife();
            //replaySequence();
            System.out.println("valentin: Erreur !");
            positionSequence = 0;
            actualKey = music.getSequence().get(0);
            return false;
        }
    }

    private List<Note> generateNotes() {
        List<Note>notes=new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
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

}
