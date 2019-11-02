package nona.mi.map;

import nona.mi.main.MyGame;
import nona.mi.loader.TextLoader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Map {

    private Tile tile;
    private MyGame myGame;
    private int[] intMap;

    public Map(MyGame myGame, String mapTxtPath){

        this.myGame = myGame;
        this.tile = myGame.getTile();

        String temp = TextLoader.loadText(mapTxtPath);
        this.intMap = new int[temp.length()];

        for (int i = 0; i < intMap.length; i++) {
            int id = Integer.parseInt(String.valueOf(temp.charAt(i)));
            intMap[i] = id;
        }
    }

    public void render(Graphics g){
        int x = 0;
        int y = 0;
        for (int i = 0; i < myGame.getTilesInWidth(); i++) {
            for (int j = 0; j < myGame.getTilesInHeight(); j++) {
                int id = intMap[((i * myGame.getTilesInWidth()) + j)];
                BufferedImage tempImage = tile.get(id);
                g.drawImage(tempImage, (int)(x * myGame.getScale()), (int)(y * myGame.getScale()), (int)(tempImage.getWidth()*myGame.getScale()), (int)(tempImage.getHeight()*myGame.getScale()), null);
                x += myGame.getTileSize();
            }
            x = 0;
            y += myGame.getTileSize();
        }
    }

    public int[] getIntMap() {
        return intMap;
    }

}