
package com.academy;

import java.awt.*;

public class Player {
    public int x;
    public int y;
    protected int id;

    public Player(int x, int y,int id){
       this.x = x;
       this.y = y;
       this.id = id;
   }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {return y;}

    public void tick() {

    }

    public void render(Graphics g){

    }
}