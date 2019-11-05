package nona.mi.player;

import nona.mi.main.MyGame;
import nona.mi.map.Door;
import nona.mi.map.Map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player{

    private int x;
    private int y;
    private int speed;
    private BufferedImage sprite;
    private MyGame myGame;

    private boolean outUp;
    private boolean outDown;
    private boolean outLeft;
    private boolean outRight;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public Player(MyGame myGame, int x, int y, int speed) {
        this.myGame = myGame;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.sprite = myGame.getTile().get(2);
    }

    public void update(Map map){

        up = myGame.isUp();
        down = myGame.isDown();
        left = myGame.isLeft();
        right = myGame.isRight();

        if (isOutOfMap()){
            changeMap(map);
        } else {
            move(map);
        }

        resetOuts();
    }

    public void render(Graphics g){
        g.drawImage(sprite, (int)(x * myGame.getScale()), (int)(y * myGame.getScale()), (int)(sprite.getWidth() * myGame.getScale()), (int)(sprite.getHeight() * myGame.getScale()), null);
    }

    private boolean willUpCollide(Map map){

        int topleftx = x / myGame.getTileSize();
        int toplefty = (y - speed) / myGame.getTileSize();
        int topleftid = (topleftx + (toplefty * myGame.getTilesInWidth()));

        int toprightx = (x + myGame.getTileSize() - 1) / myGame.getTileSize(); //precisa ser 29, pois como é base 0, 30 ficaria com um pixel a mais e já seria outro square
        int toprighty = (y - speed) / myGame.getTileSize();
        int toprightid = (toprightx + (toprighty * myGame.getTilesInWidth()));

        if  (map.getIntMap()[topleftid] == 1 || map.getIntMap()[toprightid] == 1){
            return true; //colidiu
        }

        return false; //livre
    }

    private boolean willDownCollide(Map map){

        int bottomleftx = x / myGame.getTileSize();
        int bottomlefty = ((y + myGame.getTileSize() - 1) + speed) / myGame.getTileSize();
        int bottomleft = (bottomleftx + (bottomlefty * myGame.getTilesInWidth()));

        int bottomrightx = (x + myGame.getTileSize() - 1) / myGame.getTileSize();
        int bottomrighty = ((y + myGame.getTileSize() - 1) + speed) / myGame.getTileSize();
        int bottomright = (bottomrightx + (bottomrighty * myGame.getTilesInWidth()));

        if  (map.getIntMap()[bottomleft] == 1 || map.getIntMap()[bottomright] == 1){
            return true;
        }

        return false;
    }

    private boolean willLeftCollide(Map map){

        int topleftx = (x - speed) / myGame.getTileSize();
        int toplefty = y / myGame.getTileSize();
        int topleftid = (topleftx + (toplefty * myGame.getTilesInWidth()));

        int bottomleftx = (x - speed) / myGame.getTileSize();
        int bottomlefty = (y + myGame.getTileSize() - 1) / myGame.getTileSize();
        int bottomleftid = (bottomleftx + (bottomlefty * myGame.getTilesInWidth()));

        if (map.getIntMap()[topleftid] == 1 || map.getIntMap()[bottomleftid] == 1){
            return true;
        }

        return false;
    }

    private boolean willRightCollide(Map map){

        int toprightx = ((x + myGame.getTileSize() - 1) + speed) / myGame.getTileSize();
        int toprighty = y / myGame.getTileSize();
        int topright = (toprightx + (toprighty * myGame.getTilesInWidth()));

        int bottomrightx = ((x + myGame.getTileSize() - 1) + speed) / myGame.getTileSize();
        int bottomrighty = (y + myGame.getTileSize() - 1) / myGame.getTileSize();
        int bottomright = (bottomrightx + (bottomrighty * myGame.getTilesInWidth()));

        if  (map.getIntMap()[topright] == 1 || map.getIntMap()[bottomright] == 1){
            return true;
        }

        return false;
    }

    private boolean isOutOfMap(){

        if (up && y - speed < 0){
            outUp = true;
            return true;
        }

        if (down && (y + myGame.getTileSize() + speed) > myGame.getHeight()){
            outDown = true;
            return true;
        }

        if (left && x - speed < 0){
            outLeft = true;
            return true;
        }

        if (right && (x + myGame.getTileSize() + speed) > myGame.getWidth()){
            outRight = true;
            return true;
        }

        return false;
    }

    private void changeMap(Map map){

        int playerCenterX = ((x + (myGame.getTileSize() / 2)) / myGame.getTileSize());
        int playerCenterY = ((y + (myGame.getTileSize() / 2)) / myGame.getTileSize());
        int id = (playerCenterX + (playerCenterY * myGame.getTilesInWidth()));

        Door tempDoor = map.getDoors().get(id);
        tempDoor.defineXY(outUp, outDown, outLeft, outRight, x, y);

        x = tempDoor.getNewPlayerX();
        y = tempDoor.getNewPlayerY();

        myGame.setSceneBasis(myGame.getAllScenes().get(tempDoor.getNexSceneID()));
    }

    private void move(Map map){
        if (up && !willUpCollide(map)){
            y -= speed;
        }
        if (down && !willDownCollide(map)){
            y += speed;
        }
        if (left && !willLeftCollide(map)){
            x -= speed;
        }
        if (right && !willRightCollide(map)){
            x += speed;
        }
    }

    private void resetOuts(){
        outUp = false;
        outDown = false;
        outLeft = false;
        outRight = false;
    }

}