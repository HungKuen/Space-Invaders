package com.academy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = WIDTH /12 *9;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    public Player player;
    public Window window;

    public Game(){
    //    handler = new Handler();
       // this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Project X Alpha", this);

        player = new Player(WIDTH/2,HEIGHT-60,1);

    }
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000; // amount OfTicks
        double delta =0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();

            if (running)
                render();
            frames ++;

            try {
                TimeUnit.MILLISECONDS.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        stop();
    }


    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }


        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.setColor(Color.white);
        g.fillRect(player.getX(),player.getY(),20,20 );


        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        new Game();

        }


}
