package nona.mi.player;

import nona.mi.main.MyGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

    private int x;
    private int y;
    private int speed;
    private BufferedImage sprite;
    private MyGame myGame;

    public Player(MyGame myGame, int x, int y, int speed) {
        this.myGame = myGame;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.sprite = myGame.getTile().get(2);
    }

    public void update(){
        boolean up = myGame.isUp();
        boolean down = myGame.isDown();
        boolean left = myGame.isLeft();
        boolean right = myGame.isRight();

        if (up && !willUpCollide()){
            y -= speed;
        }
        if (down && !willDownCollide()){
            y += speed;
        }
        if (left && !willLeftCollide()){
            x -= speed;
        }
        if (right && !willRightCollide()){
            x += speed;
        }

    }

    public void render(Graphics g){
        g.drawImage(sprite, (int)(x * myGame.getScale()), (int)(y * myGame.getScale()), (int)(sprite.getWidth() * myGame.getScale()), (int)(sprite.getHeight() * myGame.getScale()), null);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private boolean willUpCollide(){

        int topleftx = x / myGame.getTileSize();
        int toplefty = (y - speed) / myGame.getTileSize();
        int topleftid = (topleftx + (toplefty * myGame.getTilesInWidth()));

        int toprightx = (x + myGame.getTileSize() - 1) / myGame.getTileSize(); //precisa ser 29, pois como é base 0, 30 ficaria com um pixel a mais e já seria outro square
        int toprighty = (y - speed) / myGame.getTileSize();
        int toprightid = (toprightx + (toprighty * myGame.getTilesInWidth()));

        if  (myGame.getMapBasis().getIntMap()[topleftid] == 1 || myGame.getMapBasis().getIntMap()[toprightid] == 1){
            return true; //colidiu
        }

        return false; //livre
    }

    private boolean willDownCollide(){

        int bottomleftx = x / myGame.getTileSize();
        int bottomlefty = ((y + myGame.getTileSize() - 1) + speed) / myGame.getTileSize();
        int bottomleft = (bottomleftx + (bottomlefty * myGame.getTilesInWidth()));

        int bottomrightx = (x + myGame.getTileSize() - 1) / myGame.getTileSize();
        int bottomrighty = ((y + myGame.getTileSize() - 1) + speed) / myGame.getTileSize();
        int bottomright = (bottomrightx + (bottomrighty * myGame.getTilesInWidth()));

        if  (myGame.getMapBasis().getIntMap()[bottomleft] == 1 || myGame.getMapBasis().getIntMap()[bottomright] == 1){
            return true;
        }

        return false;
    }

    private boolean willLeftCollide(){

        int topleftx = (x - speed) / myGame.getTileSize();
        int toplefty = y / myGame.getTileSize();
        int topleftid = (topleftx + (toplefty * myGame.getTilesInWidth()));

        int bottomleftx = (x - speed) / myGame.getTileSize();
        int bottomlefty = (y + myGame.getTileSize() - 1) / myGame.getTileSize();
        int bottomleftid = (bottomleftx + (bottomlefty * myGame.getTilesInWidth()));

        if (myGame.getMapBasis().getIntMap()[topleftid] == 1 || myGame.getMapBasis().getIntMap()[bottomleftid] == 1){
            return true;
        }

        return false;
    }

    private boolean willRightCollide(){

        int toprightx = ((x + myGame.getTileSize() - 1) + speed) / myGame.getTileSize();
        int toprighty = y / myGame.getTileSize();
        int topright = (toprightx + (toprighty * myGame.getTilesInWidth()));

        int bottomrightx = ((x + myGame.getTileSize() - 1) + speed) / myGame.getTileSize();
        int bottomrighty = (y + myGame.getTileSize() - 1) / myGame.getTileSize();
        int bottomright = (bottomrightx + (bottomrighty * myGame.getTilesInWidth()));

        if  (myGame.getMapBasis().getIntMap()[topright] == 1 || myGame.getMapBasis().getIntMap()[bottomright] == 1){
            return true;
        }

        return false;
    }

}