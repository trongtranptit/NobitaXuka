package com.example.syl.nobitaxuka;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Syl on 10/28/2016.
 */
public class Character implements Serializable{
    private int numLife;
    private ArrayList<Miracle> miracles;

    public Character() {
    }

    public int getNumLife() {
        return numLife;
    }

    public void setNumLife(int numLife) {
        this.numLife = numLife;
    }

    public ArrayList<Miracle> getMiracles() {
        return miracles;
    }

    public void setMiracles(ArrayList<Miracle> miracles) {
        this.miracles = miracles;
    }

    public Character(int numLife, ArrayList<Miracle> miracles) {
        this.numLife = numLife;
        this.miracles = miracles;
    }
}
