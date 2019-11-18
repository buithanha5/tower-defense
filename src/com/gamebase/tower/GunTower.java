package com.gamebase.tower;

import com.gamebase.Block;
import com.gamebase.Screen;
import com.gamebase.Value;

import javax.swing.*;
import java.awt.*;

public class GunTower extends Tower{
    public static Image tileset_tower ;
    public GunTower(int x, int y, int width, int height, int groundID, int airID) {
        super(x, y, width, height, groundID, airID);
        hasTower= Value.gunTower;
        tileset_tower= new ImageIcon("res/gun.png").getImage();
    }

    public static Tower reMade(Block a){
        return new GunTower(a.x,a.y,a.width,a.height,a.groundID,a.airID);

    }
    public void rotatelImage(Graphics g){

    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(Screen.tileset_ground[groundID],x,y,width,height,null);
        g.drawRect(x,y,width,height);
        rotatelImage(g);
        g.drawImage(tileset_tower,x,y,width,height,null);
        g.drawString(""+lengthToEnd,x+Screen.room.blocksize/2,y+Screen.room.blocksize/2);

    }
}
