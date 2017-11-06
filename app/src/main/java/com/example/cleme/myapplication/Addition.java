package com.example.cleme.myapplication;

/**
 * Created by cleme on 02/11/2017.
 */

public class Addition {
    private int terme1;
    private int terme2;

    public Addition() {
        initAdd();
    }

    public void initAdd(){
        setTerme1();
        setTerme2();
    }

    public int getTerme2() {
        return terme2;
    }

    public void setTerme1() {
        this.terme1 = 1 + (int)(Math.random() * ((10 - 1) + 1));
    }

    public void setTerme2() {
        this.terme2 =
                1 + (int)(Math.random() * ((10 - 1) + 1));
    }

    public int getTerme1() {
        return terme1;
    }

    public int result(){
        return terme1+terme2;
    }

}
