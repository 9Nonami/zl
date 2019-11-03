package nona.mi.map;

import nona.mi.main.MyGame;
import nona.mi.loader.TextLoader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Map {

    private Tile tile;
    private MyGame myGame;
    private int[] intMap;
    private HashMap<Integer, Door> doors;

    public Map(MyGame myGame, String mapTxtPath, int[] nextMapIDs){

        this.myGame = myGame;
        this.tile = myGame.getTile();

        doors = new HashMap<Integer, Door>();

        String temp = TextLoader.loadText(mapTxtPath);
        this.intMap = new int[temp.length()];

        int cont = 0;

        for (int i = 0; i < intMap.length; i++) {
            int id = Integer.parseInt(String.valueOf(temp.charAt(i)));
            intMap[i] = id;
            if (id == 3){
                doors.put(i, new Door(myGame, nextMapIDs[cont]));
                cont++;
            }
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

    public HashMap<Integer, Door> getDoors() {
        return doors;
    }
}