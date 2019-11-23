package com.gamebase.tower;

import com.gamebase.Block;
import com.gamebase.Screen;
import com.gamebase.Value;
import com.gamebase.etity.Mob;
import com.gamebase.etity.SpamerMob;
import com.gamebase.etity.amo;

import javax.swing.*;
import java.awt.*;

public abstract class  Tower extends Block implements physic {
    public int Range;
    public int xCenter,yCenter;
    public boolean isFight =false;
    public int fightId=-1;
    public amo am ;

    public  Tower(Block a) {
        super(a.x, a.y, a.width, a.height, a.groundID, a.airID);
        this.xCenter= x+width/2;
        this.yCenter= y+height/2;
        this.hasTower=true;
        am = new amo(xCenter,yCenter);
    }
    public abstract boolean insideRange(int x,int y);
    public abstract void fightingMob(Graphics g);
    public abstract void subHeal();
    public abstract void ShootingRange(Graphics g);
    public abstract void draw(Graphics g);
}
