package com.academy;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;


//EFtersom panelen får fokus direkt när man rör fönstret så kanske keylisternern bör ligga här?
//Tror man kan köra panel.requestFocus för att få fokus direkt för att slippa klicka i fönstret då.

public class Game extends Canvas implements Runnable, KeyListener {

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH /12 *9;
    private Thread thread;
    private boolean running = false;

    //Använd denna för att "flagga" att skeppet svänger medan den är true
    private boolean steering = false;

    public Player player;
    public Window window;
    public int score = 0;

    List<Skott> skottList = new ArrayList<>();
    public List <Enemy> enemies = new ArrayList<>();

    public Game(){
        this.addKeyListener(this);

        this.window = new Window(WIDTH, HEIGHT, "Project X Alpha", this);
        this.player = new Player(WIDTH/2,HEIGHT-60,1);




        Random rand = new Random();
        for (int i=0; i<1000; i++) {
            enemies.add(new Enemy(rand.nextInt(WIDTH-30),rand.nextInt(100000)-100000));
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

        int frames = 0;
        while(running){


            if (running)
                render();
            frames ++;

            for(int i=0; i< enemies.size(); i++) {
                enemies.get(i).y++;
            }
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

        Font monoFont = new Font("Monospaced", Font.BOLD
                | Font.ITALIC, 20);
        g.setFont(monoFont);
        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth("score");
        int h = fm.getAscent();
        g.drawString("score: " + score + "", 0, 20);



        for(int i = skottList.size()-1; i >= 0; i--) {
            Skott bullet = skottList.get(i);
            bullet.update();
            g.fillRect(bullet.getX(), bullet.getY(), 5, 10);
            if(bullet.getY() < 0) {
                skottList.remove(bullet);
            }

            //Jämför alla skott med alla fiender på koordinater.
            //Om fienden är 20pixlar bred måste vi kolla X och nästa 20 pixlar för träff.

            for (int j =0; j < enemies.size(); j++){

                if (bullet.getY() >= enemies.get(j).y){
                    //System.out.println("rätt y");
                    if (bullet.getY() <= enemies.get(j).y + 20){


                        if (bullet.getX() >= enemies.get(j).x){
                            //System.out.println("typ samma x");

                            if (bullet.getX() <= enemies.get(j).x + 20){
                                g.setColor(Color.orange);
                                g.fillRect(enemies.get(j).x-5, enemies.get(j).y-5, 30, 30);
                                skottList.remove(bullet);
                                enemies.remove(j);
                                score ++;





                            }
                        }
                    }
                }



            }

        }
        int fiendewidth = 20;
        int fiendeheight = 20;
        //Den här funktionen kör över alla andra förändringar (på träff tex).
        for(int i=0; i< enemies.size(); i++) {
            g.setColor(Color.red);
            g.fillRect(enemies.get(i).x, enemies.get(i).y, fiendewidth, fiendeheight);
        }

        int counter = 0;
        for(int i = skottList.size()-1; i>= 0; i-- ){
            Skott bullet = skottList.get(i);
            for (int j = enemies.size()-1; j >= 0; j--){
                Enemy fiende = enemies.get(j);

                if (bullet.getX() >= fiende.getX() && bullet.getX() <= fiende.getX() + fiendewidth && bullet.getY() >= (fiende.getY()) && bullet.getY() <= fiende.getY() + fiendeheight
                        || bullet.getX()+4 >= fiende.getX() && bullet.getX()+4 <= fiende.getX() + fiendewidth && bullet.getY() >= (fiende.getY()) && bullet.getY() <= fiende.getY() + fiendeheight  ){
                   enemies.remove(fiende);
                   skottList.remove(bullet);
                   counter = counter +1;


                }

            }

        }

        g.dispose();
        bs.show();
    }

    public static void main(String args[]) throws NullPointerException {
        new Game();

        }
    public void keyTyped (KeyEvent e){
    }
    public void keyReleased(KeyEvent e){
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 37 ){
            System.out.println("realae");
            this.steering = false;
        }
        if (e.getKeyCode() == 39) {
            this.steering = false;
        }
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            //lade till +10 så skottet hamnar i mitten av kuben
            skottList.add(new Skott(player.getX()+10,player.getY()));

        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            if(player.getX() >0) {
                player.setX(player.getX() - 5);
                System.out.println("Left"); // styra vänster
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT){

                if(player.getX() < Game.WIDTH-35) {
                    player.setX(player.getX() + 5);

                }

           // if(player.getX() < Game.WIDTH-35) {
             //   player.setX(player.getX() + 5);
                //System.out.println("Right"); // styra höger
            //}

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

}
