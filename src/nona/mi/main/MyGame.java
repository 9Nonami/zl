package nona.mi.main;

import nona.mi.map.Map;
import nona.mi.map.Tile;
import nona.mi.player.Player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class MyGame extends Game {

    private Tile tile;
    private Map mapBasis;
    private Player player;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public MyGame(int width, int height, float scale, int fps, String title, byte gameLoopStyle, int tileSize) {

        super(width, height, scale, fps, title, gameLoopStyle, tileSize);
        tile = new Tile(this, "/res/spritesheet.png");
        player = new Player(this, 30, 30, 1);
        mapBasis = new Map(this, "/res/map1.txt");
    }

    @Override
    public void updateClass() {

        player.update();

    }

    @Override
    public void renderClass(Graphics g) {
        mapBasis.render(g);
        player.render(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
        }
    }

    public Tile getTile() {
        return tile;
    }

    public Map getMapBasis() {
        return mapBasis;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

}