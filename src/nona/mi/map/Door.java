package nona.mi.map;

import nona.mi.main.MyGame;

public class Door {

    private int newPlayerX;
    private int newPlayerY;
    private int nexSceneID;
    private MyGame myGame;

    public Door(MyGame myGame, int nexSceneID){
        this.myGame = myGame;
        this.nexSceneID = nexSceneID;
    }

    public void defineXY(boolean outUp, boolean outDown, boolean outLeft, boolean outRight, int playerX, int playerY){
        if (outUp){
            newPlayerX = playerX;
            newPlayerY = myGame.getHeight() - myGame.getTileSize();
        } else if (outDown){
            newPlayerX = playerX;
            newPlayerY = 0;
        } else if (outLeft){
            newPlayerX = myGame.getWidth() - myGame.getTileSize();
            newPlayerY = playerY;
        } else if (outRight){
            newPlayerX = 0;
            newPlayerY = playerY;
        }
    }

    public int getNewPlayerX() {
        return newPlayerX;
    }

    public int getNewPlayerY() {
        return newPlayerY;
    }

    public int getNexSceneID() {
        return nexSceneID;
    }

}