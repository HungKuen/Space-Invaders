
package com.academy;

import java.awt.*;

public class Player {
    protected int x,y;
    protected ID id;
    protected int velX, velY;


   public Player(int x, int y,ID id){
       this.x=x;
       this.y=y;
       this.id=id;
   }


    public void tick() {

    }

    public void render(Graphics g){

    }

    public void setX(int x){
       this.x=x;
    }
    public void setY(int y){
       this.y=y;
    }
}
