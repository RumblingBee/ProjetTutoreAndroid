package com.example.cassa.entrainementprojettut;

import android.widget.FrameLayout;

import com.example.cassa.entrainementprojettut.flag.ControllerFlagBank;
import com.example.cassa.entrainementprojettut.flag.Flag;
import com.example.cassa.entrainementprojettut.flag.FlagBank;
import com.example.cassa.entrainementprojettut.flag.FlagBankDifficile;
import com.example.cassa.entrainementprojettut.flag.FlagBankFacile;
import com.example.cassa.entrainementprojettut.flag.FlagBankMoyenne;
import com.example.cassa.entrainementprojettut.flag.I_FlagBank;

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
        I_FlagBank flagBank = new FlagBankFacile();

        assertTrue(flagBank.getChoixFlag().size() >=0);
    }


    @Test
    public void flagBankLvl2NotEmpty() {
        I_FlagBank flagBank = new FlagBankMoyenne();

        assertTrue(flagBank.getChoixFlag().size() >=0);
    }
    @Test
    public void flagBankLvl3NotEmpty() {
        I_FlagBank flagBank = new FlagBankDifficile();

        assertTrue(flagBank.getChoixFlag().size() >=0);
    }

    @Test
    public void showOnly4FlagsLvl1() {
        I_FlagBank flagBank = new FlagBankFacile();
        assertEquals(4,flagBank.getChoixFlag().size());
    }

    @Test
    public void showOnly4FlagsLvl2() {
        I_FlagBank flagBank = new FlagBankMoyenne();
        assertEquals(4,flagBank.getChoixFlag().size());
    }

    @Test
    public void showOnly4FlagsLvl3() {
        I_FlagBank flagBank = new FlagBankDifficile();
        assertEquals(4,flagBank.getChoixFlag().size());
    }

    @Test
    public void controllerGetFlagBank(){
        ControllerFlagBank controllerFlagBank = new ControllerFlagBank(1);
        I_FlagBank flagBank = controllerFlagBank.getFlagBank();
        assertTrue(flagBank.getFlag(3) != null);
    }

    @Test
    public void controllerNiveau1GetFlagBankNotEmpty(){
        for (int i=0; i<1000; i++) {

            ControllerFlagBank controllerFlagBank = new ControllerFlagBank(1);
            I_FlagBank flagBank = controllerFlagBank.getFlagBank();

            assertTrue(flagBank.getFlag(0) != null);
            assertTrue(flagBank.getFlag(1) != null);
            assertTrue(flagBank.getFlag(2) != null);
            assertTrue(flagBank.getFlag(3) != null);
        }
    }

    @Test
    public void controllerNiveau2GetFlagBankNotEmpty(){
        for (int i=0; i<1000; i++) {

            ControllerFlagBank controllerFlagBank = new ControllerFlagBank(2);
            I_FlagBank flagBank = controllerFlagBank.getFlagBank();

            assertTrue(flagBank.getFlag(0) != null);
            assertTrue(flagBank.getFlag(1) != null);
            assertTrue(flagBank.getFlag(2) != null);
            assertTrue(flagBank.getFlag(3) != null);
        }
    }

    @Test
    public void controllerNiveau3GetFlagBankNotEmpty(){
        for (int i=0; i<1000; i++) {

            ControllerFlagBank controllerFlagBank = new ControllerFlagBank(3);
            I_FlagBank flagBank = controllerFlagBank.getFlagBank();

            assertTrue(flagBank.getFlag(0) != null);
            assertTrue(flagBank.getFlag(1) != null);
            assertTrue(flagBank.getFlag(2) != null);
            assertTrue(flagBank.getFlag(3) != null);
        }
    }

    @Test
    public void controllerNiveau1(){
        for (int i=0; i<1000; i++){
            ControllerFlagBank controllerFlagBank = new ControllerFlagBank(1);

            Flag flag0 = controllerFlagBank.getFlag(0);
            Flag flag1 = controllerFlagBank.getFlag(1);
            Flag flag2 = controllerFlagBank.getFlag(2);
            Flag flag3 = controllerFlagBank.getFlag(3);

            //Argentine est u pays de niveau 2
            assertTrue(flag0.getmNameCountry() != "Argentine");
            assertTrue(flag1.getmNameCountry() != "Argentine");
            assertTrue(flag2.getmNameCountry() != "Argentine");
            assertTrue(flag3.getmNameCountry() != "Argentine");

        }
    }

    @Test
    public void controllerNiveau2(){
        for (int i=0; i<1000; i++){
            ControllerFlagBank controllerFlagBank = new ControllerFlagBank(2);

            Flag flag0 = controllerFlagBank.getFlag(0);
            Flag flag1 = controllerFlagBank.getFlag(1);
            Flag flag2 = controllerFlagBank.getFlag(2);
            Flag flag3 = controllerFlagBank.getFlag(3);

            //Maroc est un pays de niveau 3
            assertTrue(flag0.getmNameCountry() != "Maroc");
            assertTrue(flag1.getmNameCountry() != "Maroc");
            assertTrue(flag2.getmNameCountry() != "Maroc");
            assertTrue(flag3.getmNameCountry() != "Maroc");

        }
    }

    //  Jeu Etiquette

    @Test
    public void etiquetteGetNom(){
        Etiquette etiquette = new Etiquette("nomEtiquette",0,0,0,0);
        assertEquals("nomEtiquette",etiquette.getNom());
    }
    @Test
    public void etiquetteGetZone(){
        Etiquette etiquette = new Etiquette("nomEtiquette",10,20,30,40);
        float[]tabZoneVictoire;
        tabZoneVictoire = etiquette.getZoneVictoire();

        assertEquals(10,tabZoneVictoire[0],0);
        assertEquals(20,tabZoneVictoire[1],0);
        assertEquals(30,tabZoneVictoire[2],0);
        assertEquals(40,tabZoneVictoire[3],0);
    }

    @Test
    public void wordGetCodeWord(){
        //En attende de révisions du code
    }
}