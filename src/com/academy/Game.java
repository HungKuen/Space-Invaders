package com.academy;

import com.googlecode.lanterna.terminal.swing.TerminalScrollController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH /12 *9;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    public Player player;
    public Window window;

    List<Skott> skottList = new ArrayList<>();
    public List <Enemy> enemies = new ArrayList<>();

    public Game(){
    //    handler = new Handler();
       // this.addKeyListener(new KeyInput(handler));

        this.window = new Window(WIDTH, HEIGHT, "Project X Alpha", this);
        this.player = new Player(WIDTH/2,HEIGHT-60,1);




        Random rand = new Random();
        for (int i=0; i<10; i++) {
            enemies.add(new Enemy(rand.nextInt(WIDTH-30),30));
        }

        /*enemies = new Enemy[10];

        int ax =10;
        int ay =10;

        for(int i=0; i<enemies.length; i++){
            enemies[i] =new Enemy(ax,ay);
            ax +=40;
            if (i==4){
                ax=10;
                ay+=40;
            }*/

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

    public void run() throws NullPointerException{
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


    private void render() throws NullPointerException{
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }


        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.setColor(Color.white);
        g.fillRect(this.player.getX(), this.player.getY(),20,20 );


        for(int i = skottList.size()-1; i >= 0; i--) {
            Skott bullet = skottList.get(i);
            bullet.update();
            g.fillRect(bullet.getX(), bullet.getY(), 5, 10);
            if(bullet.getY() < 0) {
                skottList.remove(bullet);
            }
        }


        System.out.println(skottList.size());


        for(int i=0; i< enemies.size(); i++) {
            g.setColor(Color.red);
            g.fillRect(enemies.get(i).x, enemies.get(i).y, 20, 20);
        }


        g.dispose();
        bs.show();
    }

    public static void main(String args[]) throws NullPointerException {
        new Game();

        }


}
