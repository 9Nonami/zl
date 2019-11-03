package nona.mi.main;

import nona.mi.map.Map;
import nona.mi.map.Tile;
import nona.mi.player.Player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class MyGame extends Game {

    private Tile tile;
    private Map mapBasis;
    private Player player;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private HashMap<Integer, Map> allMaps;

    public MyGame(int width, int height, float scale, int fps, String title, byte gameLoopStyle, int tileSize) {

        super(width, height, scale, fps, title, gameLoopStyle, tileSize);
        tile = new Tile(this, "/res/spritesheet.png");
        player = new Player(this, 30, 30, 1);
        allMaps = new HashMap<Integer, Map>();

        allMaps.put(1, new Map(this, "/res/map1.txt", new int[]{2, 3, 4, 5}));
        allMaps.put(2, new Map(this, "/res/map2.txt", new int[]{1}));
        allMaps.put(3, new Map(this, "/res/map3.txt", new int[]{1}));
        allMaps.put(4, new Map(this, "/res/map4.txt", new int[]{1}));
        allMaps.put(5, new Map(this, "/res/map5.txt", new int[]{1}));

        mapBasis = allMaps.get(1);
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

    public HashMap<Integer, Map> getAllMaps() {
        return allMaps;
    }

    public void setMapBasis(Map mapBasis) {
        this.mapBasis = mapBasis;
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