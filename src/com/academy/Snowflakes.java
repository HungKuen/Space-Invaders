/*package com.academy;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Snowflakes implements UI {
    public Screen screen;

    public Snowflakes(Screen screen) {
        this.screen = screen;
    }

    public boolean isTaken(List<Flake> shapes, int x, int y) {
        for(Flake flake : shapes) {
            if(flake.x == x && flake.y == y) {
                return true;
            }
        }
        return false;
    }

    public void draw(List<Flake> shapes) throws IOException, InterruptedException {
*/        /*          För att ändra bakgrundsfärgen och text samt textbackgrund
         *
         *           TextGraphics screenGraphics = screen.newTextGraphics();
         *           screenGraphics.setBackgroundColor(TextColor.Indexed.fromRGB(255, 0, 0));
         *           screenGraphics.fill(' ');
         *           screenGraphics.setForegroundColor(TextColor.Indexed.fromRGB(0,255,255));
         */
 /*       while(true) {
            screen.clear();

            for(Flake shape : shapes) {
                TextCharacter colorChar = new TextCharacter('*');
                screen.setCharacter(shape.x, shape.y, colorChar);
                if(shape.y < screen.getTerminalSize().getRows()-1 && !isTaken(shapes, shape.x, shape.y+1)) {
                    shape.y++;
                }
            }
            screen.refresh();
            TimeUnit.MILLISECONDS.sleep(33);
        }
    }
}
*/


