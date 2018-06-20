package com.academy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window implements KeyListener {

    public Window(int width, int height, String title, Game game){
       JFrame frame = new JFrame(title);
       frame.addKeyListener(this);
       frame.setPreferredSize((new Dimension(width, height)));
       frame.setMaximumSize((new Dimension(width, height)));
       frame.setMinimumSize((new Dimension(width, height)));

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.setLocationRelativeTo(null);
       frame.add(game);
       frame.setVisible(true);
       game.start();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println("Fire"); // kod för att skjuta
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left"); // styra vänster
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println("Right");
        }

 /*      Komande patch
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.out.println("Game Score");
            System.out.println("Exit");
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("gamespeed up");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            System.out.println("gamespeed down");
        }
*/

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
