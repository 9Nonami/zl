package nona.mi.main;

public class Main {

    public static void main(String[] args) {
        MyGame mg = new MyGame(300, 300, 2, 30, "ZL", MyGame.HARD_GAME_LOOP, 30);
        mg.setResizable(true);
        mg.start();
    }

}
