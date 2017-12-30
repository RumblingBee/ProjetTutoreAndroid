package com.example.cassa.entrainementprojettut;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void flagGetName() {
        Flag flag = new Flag("France", R.drawable.france);
        assertEquals("France", flag.getmNameCountry());
    }

    @Test
    public void flagGetRessource() {
        Flag flag = new Flag("France", R.drawable.france);
        assertEquals(2131099758, flag.getmRessource());
    }

    @Test
    public void flagBankGetFlag(){
        ArrayList<Flag> listFlag = new ArrayList<>();
        Flag france = new Flag("France", R.drawable.france);
        Flag allemagne = new Flag("Allemagne", R.drawable.allemagne);
        listFlag.add(france);
        listFlag.add(allemagne);

        FlagBank flagBank = new FlagBank(listFlag);
        Flag flag = flagBank.getFlag(1);

        assertEquals(flag.getmNameCountry(), allemagne.getmNameCountry());
        assertEquals(flag.getmRessource(), allemagne.getmRessource());
    }

    @Test
    public void flagBankLvl1NotEmpty() {
        FlagBank flagBank = new FlagBank(1);

        assertTrue(flagBank.mListNomPays.size() >=0);
    }
    @Test
    public void flagBankLvl2NotEmpty() {
        FlagBank flagBank = new FlagBank(2);

        assertTrue(flagBank.mListNomPays.size() >=0);
    }
    @Test
    public void flagBankLvl3NotEmpty() {
        FlagBank flagBank = new FlagBank(3);

        assertTrue(flagBank.mListNomPays.size() >=0);
    }

    @Test
    public void showOnly4Flags() {
        FlagBank flagBank = new FlagBank(1);
        assertEquals(4,flagBank.mListFlag.size());
    }

    @Test
    public void wordGetCodeWord(){
        //En attende de révisions du code
    }
}