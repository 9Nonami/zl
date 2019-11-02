package nona.mi.main;

import nona.mi.map.Map;
import nona.mi.map.Tile;

import java.awt.Graphics;

public class MyGame extends Game {

    private Tile tile;
    private Map mapBasis;

    public MyGame(int width, int height, float scale, int fps, String title, byte gameLoopStyle, int tileSize) {

        super(width, height, scale, fps, title, gameLoopStyle, tileSize);

        this.tile = new Tile(this, "/res/asd.png");
        mapBasis = new Map(this, "/res/huse.txt");
    }

    @Override
    public void updateClass() {

    }

    @Override
    public void renderClass(Graphics g) {
        mapBasis.render(g);
    }

    public Tile getTile() {
        return tile;
    }

}