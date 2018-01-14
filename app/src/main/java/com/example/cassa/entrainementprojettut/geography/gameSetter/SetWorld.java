package com.example.cassa.entrainementprojettut.geography.gameSetter;

import com.example.cassa.entrainementprojettut.R;
import com.example.cassa.entrainementprojettut.geography.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prax on 09/01/2018.
 */

public class SetWorld implements I_Set {
    int imageFond;
    private List<Tag> listTag;

    public SetWorld(){

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
        imageFond = R.drawable.continent;
    }

    @Override
    public void setTagList() {
        float tailleCote = 1/12F;

        listTag.add(new Tag("Amerique \n du Nord",10/48F,5/24F,tailleCote));
        listTag.add(new Tag("Afrique",1/2F,9/24F,tailleCote));
        listTag.add(new Tag("Europe",25/48F,7/48F,tailleCote));
        listTag.add(new Tag("Asie",33/48F,4/24F,tailleCote));
        listTag.add(new Tag("Amerique\n du Sud",15/48F,13/24F,tailleCote));
        listTag.add(new Tag("Océanie",10/12F,15/24F,tailleCote));
        listTag.add(new Tag("Antarctique",6/12F,11/12F,tailleCote));

    }
}
