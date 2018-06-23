package com.academy;



import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class Window implements KeyListener {

    Game game;


    public Window(int width, int height, String title, Game game){
        this.game = game;

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
            game.skottList.add(new Skott(game.player.getX(),game.player.getY()));

        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(game.player.getX() >0) {
                game.player.setX(game.player.getX() - 5);
                System.out.println("Left"); // styra vänster
                System.out.println(game.player.getX());
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(game.player.getX() < Game.WIDTH-35) {
                game.player.setX(game.player.getX() + 5);
                System.out.println("Right"); // styra höger
                System.out.println(game.player.getX());
            }

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
