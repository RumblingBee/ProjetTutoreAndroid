package com.example.cassa.entrainementprojettut.geography.gameSetter;

import com.example.cassa.entrainementprojettut.R;
import com.example.cassa.entrainementprojettut.geography.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prax on 09/01/2018.
 */

public class SetEurope implements I_Set{
    private  int imageFond;
    private List<Tag> listTag;

    public SetEurope(){
       listTag = new ArrayList<>();

    setBackgroundImage();
    setTagList();



    }
    public List<Tag> getTagList(){

        return listTag;
    }
    public int getBackgroundImage(){

        return imageFond;
    }

    @Override
    public void setBackgroundImage() {
        imageFond = R.drawable.carte_europe;
    }

    @Override
    public void setTagList() {
        float tailleCote = 9/100F;

        listTag.add(new Tag("Islande", 27/100F, 6/100F,tailleCote));
        listTag.add(new Tag("Irlande", 27/100F, 41/100F, tailleCote));
        listTag.add(new Tag("Royaume-\nUni", 33/100F, 45/100F, tailleCote));
        listTag.add(new Tag("France", 36/100F, 61/100F,tailleCote));
        listTag.add(new Tag("Espagne", 29/100F, 78/100F,tailleCote));
        listTag.add(new Tag("Portugal", 18/100F, 74/100F,tailleCote));
        listTag.add(new Tag("Italie", 49/100F, 77/100F, tailleCote));
        listTag.add(new Tag("Allemagne", 45/100F, 51/100F, tailleCote));
        listTag.add(new Tag("Pologne", 55/100F, 49/100F, tailleCote));
        listTag.add(new Tag("Russie", 70/100F, 33/100F, tailleCote));
        listTag.add(new Tag("Norvège", 45/100F, 27/100F, tailleCote));
        listTag.add(new Tag("Suède", 53/100F, 14/100F, tailleCote));
        listTag.add(new Tag("Finlande", 59/100F,22/100F, tailleCote));
        listTag.add(new Tag("Turquie", 74/100F, 82/100F, tailleCote));
        listTag.add(new Tag("Ukraine", 69/100F, 54/100F, tailleCote));


    }
}
