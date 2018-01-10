package com.example.cassa.projetAndroid.geographieTest;

import com.example.cassa.projetAndroid.geographie.Etiquette;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by prax on 09/01/2018.
 */

public class EtiquetteTest {
    //  Jeu Etiquette


    @Test
    public void etiquetteGetNom(){
        Etiquette etiquette = new Etiquette("nomEtiquette",10/12,10/12,1/12);
        assertEquals("nomEtiquette",etiquette.getNom());
    }

    @Test
    public void etiquetteGetZone(){
        Etiquette etiquette = new Etiquette("nomEtiquette",10/12,10/12,1/12);
        float[]tabZoneVictoire;
        tabZoneVictoire = etiquette.getZoneVictoire();

        assertEquals(10/12,tabZoneVictoire[0],0);
        assertEquals(11/12,tabZoneVictoire[1],0);
        assertEquals(10/12,tabZoneVictoire[2],0);
        assertEquals(11/12,tabZoneVictoire[3],0);
    }


}
