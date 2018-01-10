package com.example.cassa.projetAndroid.geographieTest;

import com.example.cassa.projetAndroid.geographie.Controleur;
import com.example.cassa.projetAndroid.geographie.GenerateurDePartie.I_Set;
import com.example.cassa.projetAndroid.geographie.GenerateurDePartie.SetEurope;
import com.example.cassa.projetAndroid.geographie.GenerateurDePartie.SetMonde;
import com.example.cassa.projetAndroid.geographie.GenerateurDePartie.SetOccitanie;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by prax on 09/01/2018.
 */

public class ControleurTest {
    @Test
    public void recupererImageNiveau1()  {
        Controleur controleur = new Controleur(1);
        I_Set niveau = new SetMonde();

        assertEquals(niveau.getImageFond(),controleur.getImageFond());

    }
    @Test
    public void recupererImageNiveau2()  {
        Controleur controleur = new Controleur(2);
        I_Set niveau = new SetOccitanie();

        assertEquals(niveau.getImageFond(),controleur.getImageFond());

    }
    @Test
    public void recupererImageNiveau3()  {
        Controleur controleur = new Controleur(3);
        I_Set niveau = new SetEurope();

        assertEquals(niveau.getImageFond(),controleur.getImageFond());

    }
    @Test
    public void recupererNomListeEtiqueNiveau1()  {
        boolean listeIdentique = true;
        int i = 0;

        Controleur controleur = new Controleur(1);
        I_Set niveau = new SetMonde();

        while (i < niveau.getListEtiquette().size() && listeIdentique == true){
            if(niveau.getListEtiquette().get(i).getNom() != controleur.getListEtiquette().get(i).getNom()){
                listeIdentique = false;
            }
            i++;
        }
        assertTrue(listeIdentique);

    }
    @Test
    public void recupererNomListeEtiqueNiveau2()  {
        boolean listeIdentique = true;
        int i = 0;

        Controleur controleur = new Controleur(2);
        I_Set niveau = new SetOccitanie();

        while (i < niveau.getListEtiquette().size() && listeIdentique == true){
            if(niveau.getListEtiquette().get(i).getNom() != controleur.getListEtiquette().get(i).getNom()){
                listeIdentique = false;
            }
            i++;
        }
        assertTrue(listeIdentique);

    }
    @Test
    public void recupererNomListeEtiqueNiveau3()  {
        boolean listeIdentique = true;
        int i = 0;

        Controleur controleur = new Controleur(3);
        I_Set niveau = new SetEurope();

        while (i < niveau.getListEtiquette().size() && listeIdentique == true){
            if(niveau.getListEtiquette().get(i).getNom() != controleur.getListEtiquette().get(i).getNom()){
                listeIdentique = false;
            }
            i++;
        }
        assertTrue(listeIdentique);

    }
}
