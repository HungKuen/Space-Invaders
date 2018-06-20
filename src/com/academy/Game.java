package com.academy;

import java.awt.*;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 860, HEIGHT = WIDTH / 108;

    public Game(){
        new Window(WIDTH, HEIGHT, "Project X Alpha", this);

    }
    public synchronized void start() {

    }
    public void run() {
    }

    public static void main(String args[]) {
        new Game();

        }


}
