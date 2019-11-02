package nona.mi.main;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;

public abstract class Game implements Runnable, KeyListener {

    private JFrame jframe;

    private int width;
    private int height;
    private float scale;

    private Graphics g;
    private boolean running;
    private Thread t;
    private float limit;
    private BufferStrategy bs;
    private Canvas canvas;

    private boolean showLoopLog;

    public static final byte HARD_GAME_LOOP = 0;
    public static final byte SMOOTH_GAME_LOOP = 1;
    private byte gameLoopStyle;

    public Game(int width, int height, float scale, int fps, String title, byte gameLoopStyle) {

        this.width = width;
        this.height = height;
        this.scale = scale;

        this.gameLoopStyle = gameLoopStyle;

        limit = 1_000_000_000 / fps;

        jframe = new JFrame(title);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        Dimension d = new Dimension((int)(width * scale), (int)(height * scale));
        canvas.setPreferredSize(d);
        canvas.setMinimumSize(d);
        canvas.setMaximumSize(d);

        jframe.add(canvas, BorderLayout.CENTER);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);

        jframe.addKeyListener(this);

        canvas.createBufferStrategy(2);

        jframe.setIgnoreRepaint(true);
        canvas.setFocusable(false);

        t = new Thread(this);
    }

    public void start() {
        running = true;
        jframe.setVisible(true);
        t.start();
    }

    private void update() {
        updateClass();
    }

    public abstract void updateClass();

    private void render() {
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, (int)(width * scale), (int)(height * scale));
        renderClass(g);
        g.dispose();
        bs.show();
    }

    public abstract void renderClass(Graphics g);

    @Override
    public void run(){
        if (gameLoopStyle == HARD_GAME_LOOP){
            hardGameLoop();
        } else {
            smoothGameLoop();
        }
    }

    public void hardGameLoop() {
        double ini;
        double end;
        double delta;
        double wait;
        while (running) {
            ini = System.nanoTime();
            update();
            render();
            end = System.nanoTime();
            delta = end - ini;
            wait = limit - delta;
            if (wait > 0) {
                try {
                    wait = wait / 1_000_000;
                    if (showLoopLog) {
                        System.out.println("wait: " + (int) wait + " millis");
                    }
                    Thread.sleep((long) wait);
                } catch (Exception ex) {
                }
            } else {
                if (showLoopLog) {
                    System.out.println("overload: " + (int) wait / 1_000_000);
                }
            }
        }
    }

    public void smoothGameLoop(){
        double ini = 0;
        double end = 0;
        double delta = 0;

        double timer = 0;
        int fps = 0;
        int ups = 0;

        timer = System.currentTimeMillis();
        ini = System.nanoTime();
        while(running){
            end = System.nanoTime();
            delta += end - ini;
            ini = end;
            fps++;
            while (delta >= limit) {
                ups++;
                update();
                delta -= limit;
            }
            render();
            try{
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                if (showLoopLog) {
                    System.out.println("ups: " + ups);
                    System.out.println("fps: " + fps);
                    System.out.println("");
                }
                ups = 0;
                fps = 0;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void testFill() {
        g.setColor(Color.PINK);
        g.fillRect(0, 0, (int)(width * scale), (int)(height * scale));
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public float getScale(){
        return scale;
    }

    public void setResizable(boolean b){
        jframe.setResizable(b);
    }

    public void resize(float scale){
        this.scale = scale;
        Dimension d = new Dimension((int)(width * scale), (int)(height * scale));
        canvas.setPreferredSize(d);
        canvas.setMinimumSize(d);
        canvas.setMaximumSize(d);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
    }

    public void setShowLoopLog(boolean showLoopLog) {
        this.showLoopLog = showLoopLog;
    }
}
