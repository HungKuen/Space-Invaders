package com.academy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Skott {

    private int x,y;
    private int speed = 10;

    public Skott(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void update() {
        this.y -= this.speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}




