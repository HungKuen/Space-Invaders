package com.academy;



import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class Window {
    Skott skott;
    Game game;

    public Window(int width, int height, String title, Game game){
        this.game = game;

        JFrame frame = new JFrame(title);


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
}
