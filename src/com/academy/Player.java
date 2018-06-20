/*package com.academy;

import java.awt.*;

public class Player extends GameObject {


    public Player(int x, int y, ID id){
        super(x,y,id);
    }

    public void tick() {

    }

    public void render(Graphics g){

    }
} */

package com.academy;

import java.awt.*;

public class Player {
    public int x ,y;
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


    public void tick() {

    }

    public void render(Graphics g){

    }




}