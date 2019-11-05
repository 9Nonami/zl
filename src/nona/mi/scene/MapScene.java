package nona.mi.scene;

import nona.mi.map.Map;
import nona.mi.player.Player;

import java.awt.Graphics;

public class MapScene extends Scene {

    private Player player;
    private Map map;

    public MapScene(Player player, Map map){
        this.player = player;
        this.map = map;
    }

    @Override
    public void update(){
        player.update(map);
    }

    @Override
    public void render(Graphics g){
        map.render(g);
        player.render(g);
    }
}
