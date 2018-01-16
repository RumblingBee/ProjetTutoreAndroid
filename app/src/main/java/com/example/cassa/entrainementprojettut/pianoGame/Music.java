package com.example.cassa.entrainementprojettut.pianoGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 16/01/18.
 */

public class Music {

    private List<Note> notes;
    private List<Note> sequence;
    private int position;

    public Music(List<Note> notes) {
        this.notes = notes;
        this.sequence=new ArrayList<>();
    }

    public List<Note> generateSequence(int size){
        for (int j = 0; j <size ; j++) {
            sequence.add(notes.get(j));
        }
        position=size;
        return sequence;
    }

    public List<Note> incrementSequence(int i) {
        for (int j = position; j <position+i ; j++) {
            sequence.add(notes.get(j));
        }
        position+=i;
        return sequence;
    }
}
