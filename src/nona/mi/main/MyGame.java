package nona.mi.main;

import nona.mi.map.Map;
import nona.mi.map.Tile;
import nona.mi.player.Player;
import nona.mi.scene.MapScene;
import nona.mi.scene.Scene;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class MyGame extends Game {

    private Tile tile;
    private Player player;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private HashMap<Integer, Scene> allScenes;
    private Scene sceneBasis;

    public MyGame(int width, int height, float scale, int fps, String title, byte gameLoopStyle, int tileSize) {

        super(width, height, scale, fps, title, gameLoopStyle, tileSize);
        tile = new Tile(this, "/res/spritesheet.png");
        player = new Player(this, 30, 30, 1);

        allScenes = new HashMap<Integer, Scene>();
        allScenes.put(1, new MapScene(player, new Map(this, "/res/map1.txt", new int[]{2, 3, 4, 5})));
        allScenes.put(2, new MapScene(player, new Map(this, "/res/map2.txt", new int[]{1})));
        allScenes.put(3, new MapScene(player, new Map(this, "/res/map3.txt", new int[]{1})));
        allScenes.put(4, new MapScene(player, new Map(this, "/res/map4.txt", new int[]{1})));
        allScenes.put(5, new MapScene(player, new Map(this, "/res/map5.txt", new int[]{1})));
        sceneBasis = allScenes.get(1);
    }

    @Override
    public void updateClass() {
        sceneBasis.update();
    }

    @Override
    public void renderClass(Graphics g) {
        sceneBasis.render(g);
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

    public HashMap<Integer, Scene> getAllScenes() {
        return allScenes;
    }

    public void setSceneBasis(Scene sceneBasis) {
        this.sceneBasis = sceneBasis;
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