package com.academy;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
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
        this.setFocusable(true);

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


            if (running) {
                try {
                    render();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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


    private void render() throws NullPointerException, IOException, InterruptedException {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }


        Graphics g = bs.getDrawGraphics();


        Image img = new ImageIcon("Images/Background640480.jpg").getImage();
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        g.drawImage(img, 0, 0, null);




        Image imgplayer = new ImageIcon("Images/skeppet10.png").getImage();
        Dimension sizeplayer = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(sizeplayer);
        setMinimumSize(sizeplayer);
        setMaximumSize(sizeplayer);
        setSize(sizeplayer);
        g.drawImage(imgplayer,this.player.getX(),this.player.getY(),null);


        Font monoFont = new Font("Monospaced", Font.BOLD| Font.ITALIC, 20);
        g.setColor(Color.white);
        g.setFont(monoFont);
        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth("score");
        int h = fm.getAscent();
        g.drawString("score: " + score + "", 0, 20);



        Image imgskott = new ImageIcon("Images/skottet10.png").getImage();
        Dimension sizeskott = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(sizeskott);
        setMinimumSize(sizeskott);
        setMaximumSize(sizeskott);
        setSize(sizeskott);



        for(int i = skottList.size()-1; i >= 0; i--) {
            Skott bullet = skottList.get(i);
            bullet.update();
            g.drawImage(imgskott,bullet.getX(),bullet.getY(),null);

            if(bullet.getY() < 0) {
                skottList.remove(bullet);
            }



        }
        int fiendewidth = 20;
        int fiendeheight = 20;

        Image imgEnemy = new ImageIcon("Images/monster10.png").getImage();
        Dimension sizeEnemy = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(sizeEnemy);
        setMinimumSize(sizeEnemy);
        setMaximumSize(sizeEnemy);
        setSize(sizeEnemy);

        //Den här funktionen kör över alla andra förändringar (på träff tex).
        for(int i=0; i< enemies.size(); i++) {
            g.setColor(Color.red);

            g.drawImage(imgEnemy,enemies.get(i).x, enemies.get(i).y,null);

        }


        for(int i = skottList.size()-1; i>= 0; i-- ){
            Skott bullet = skottList.get(i);
            for (int j = enemies.size()-1; j >= 0; j--){
                Enemy fiende = enemies.get(j);

                if (bullet.getX() >= fiende.getX() && bullet.getX() <= fiende.getX() + fiendewidth && bullet.getY() >= (fiende.getY()) && bullet.getY() <= fiende.getY() + fiendeheight
                        || bullet.getX()+4 >= fiende.getX() && bullet.getX()+4 <= fiende.getX() + fiendewidth && bullet.getY() >= (fiende.getY()) && bullet.getY() <= fiende.getY() + fiendeheight  ){

                    g.setColor(Color.yellow);
                    g.fillRect(enemies.get(j).x-5, enemies.get(j).y-5, 30, 30);
                   enemies.remove(fiende);
                   skottList.remove(bullet);
                   score = score +10;


                }

            }

    }
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getY() > player.getY()) {

                g.setColor(Color.orange);
                g.fillRect(player.getX() - 5, player.getY() - 5, 30, 30);

                g.fillRect(player.getX() - 5, player.getY() - 5, 30, 30);
                for (int k = 0; k < enemies.size(); k++) {
                    g.setColor(Color.orange);
                    g.fillRect(enemies.get(k).x - 5, enemies.get(k).y - 5, 30, 30);
                }

                g.setColor(Color.pink);
                g.fillRect(player.getX() - 5, player.getY() - 5, 30, 30);


                String name = JOptionPane.showInputDialog("Skriv in ditt namn");
                List<String> HighScoreLista = new ArrayList<>();
                HighScoreLista.add(name +" " +score );  // add score
                BufferedWriter output = null;
                try {
                    File file = new File("./Highscore.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                        System.out.println("Highscore skapades");
                    } else {
                        System.out.println(HighScoreLista);
                        Scanner temp = new Scanner(new File("./Highscore.txt"));
                        while (temp.hasNextLine()) {
                            String line = temp.nextLine();
                            System.out.println(line);
                            HighScoreLista.add(line);
                        }
                    }
                    Collections.sort(HighScoreLista, new Comparator<String>() {
                        public int compare(String o1, String o2) {
                            return extractInt(o1) - extractInt(o2);
                        }

                        int extractInt(String s) {
                            String num = s.replaceAll("\\D", "");
                            // return 0 if no digits found
                            return num.isEmpty() ? 0 : Integer.parseInt(num);
                        }
                    });
                    output = new BufferedWriter(new FileWriter(file));

                    System.out.println(HighScoreLista);
                    for (int l = HighScoreLista.size() - 1; l >= 0; l--) {
                        output.write(HighScoreLista.get(l));
                        output.newLine();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (output != null) {
                        output.close();
                        TimeUnit.SECONDS.sleep(2);
                        enemies.clear();

                    }
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
