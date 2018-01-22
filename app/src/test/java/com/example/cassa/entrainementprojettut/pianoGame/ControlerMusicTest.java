package com.example.cassa.entrainementprojettut.pianoGame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by clement on 17/01/18.
 */
public class ControlerMusicTest {

    private ControlerMusic controlerMusic ;
    private List<Note> notes=new ArrayList<>();

    @Before
    public void setUp() throws Exception {


        Note note=new Note(1,0);
        notes.add(note);
        note=new Note(2,0);
        notes.add(note);
        note=new Note(3,0);
        notes.add(note);
        note=new Note(4,0);
        notes.add(note);

        Music music=new Music(notes);

        controlerMusic=new ControlerMusic(music);
    }

    @Test
    public void noteSaisieOk() throws Exception {
        assertTrue(controlerMusic.checkKey(1));
    }

    @Test
    public void noteSaisieFalse() throws Exception {
        assertFalse(controlerMusic.checkKey(3));
    }

    @Test
    public void sequenceProgress() throws Exception {
        controlerMusic.checkKey(1);
        assertTrue(controlerMusic.actualKey==notes.get(1));
    }
}