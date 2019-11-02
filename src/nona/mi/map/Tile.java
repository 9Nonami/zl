package nona.mi.map;

import nona.mi.loader.ImageLoader;
import nona.mi.main.MyGame;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Tile {

    private HashMap<Integer, BufferedImage> tileMap;

    public Tile(MyGame myGame, String path){

        tileMap = new HashMap<Integer, BufferedImage>();

        BufferedImage spriteSheet = ImageLoader.loadImage(path);

        int spritesInWidth = (spriteSheet.getWidth() / myGame.getTileSize());
        int spritesInHeight = (spriteSheet.getHeight() / myGame.getTileSize());
        int total = spritesInWidth * spritesInHeight;

        int x = 0;
        int y = 0;

        for (int i = 0; i < total; i++) {

            tileMap.put(i, spriteSheet.getSubimage(x, y, myGame.getTileSize(), myGame.getTileSize()));

            x += myGame.getTileSize();
            if (x != 0 && x % myGame.getWidth() == 0){
                x = 0;
                y += myGame.getTileSize();
            }
        }

    }

    public BufferedImage get(int key){
        if (tileMap.containsKey(key)){
            return tileMap.get(key);
        }
        return null;
    }

}