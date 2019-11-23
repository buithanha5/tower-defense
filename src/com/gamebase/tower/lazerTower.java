package com.gamebase.tower;

import com.gamebase.Block;
import com.gamebase.ClockFrame;
import com.gamebase.Screen;
import com.gamebase.Value;
import com.gamebase.etity.Mob;
import com.gamebase.etity.SpamerMob;

import javax.swing.*;
import java.awt.*;

public class lazerTower extends Tower {
    private Image tileset_tower;
    public lazerTower(Block a) {
        super(a);
        tileset_tower= new ImageIcon("res/lazer.png").getImage();
        Range=5*52;
    }

    @Override
    public boolean insideRange(int x, int y) {
        if(Math.pow(x-xCenter,2)+Math.pow(y-yCenter,2)-Math.pow(Range/2,2)<=0) return true;
        return false;
    }

    @Override
    public void fightingMob(Graphics g) {
        for (int i = 0; i < SpamerMob.mobs.length; i++) {
            if (SpamerMob.mobs[i].inGame){
                if (insideRange(SpamerMob.mobs[i].x+ Mob.mobSize/2,SpamerMob.mobs[i].y+Mob.mobSize/2)){
                    isFight =true;
                    fightId =i;

                    break;
                } else {
                    isFight =false;
                }
            }
        }
        try {
            if (isFight) {
                g.setColor(Color.RED);
                g.drawLine(xCenter, yCenter, SpamerMob.mobs[fightId].x + Mob.mobSize / 2, SpamerMob.mobs[fightId].y + Mob.mobSize / 2);
                g.drawLine(xCenter - 1, yCenter - 1, SpamerMob.mobs[fightId].x - 1 + Mob.mobSize / 2, SpamerMob.mobs[fightId].y - 1 + Mob.mobSize / 2);
                g.drawLine(xCenter + 1, yCenter + 1, SpamerMob.mobs[fightId].x + 1 + Mob.mobSize / 2, SpamerMob.mobs[fightId].y + 1 + Mob.mobSize / 2);
                g.setColor(Color.black);
                subHeal();
                //System.out.println(timeFrame);
            }
        }catch (Exception e) {}
    }
public int time=30,timeFrame;
    @Override
    public void subHeal() {
        if(timeFrame>=time){
            SpamerMob.mobs[fightId].heathMob--;
            SpamerMob.mobs[fightId].mobDie();
            timeFrame=0;
            //System.out.println("gaf");
        }else timeFrame++;
    }

    @Override
    public void ShootingRange(Graphics g) {
        g.drawOval(xCenter-Range/2,yCenter-Range/2,Range,Range);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Screen.tileset_ground[Value.groundGrass],x,y,width,height,null);
        //g.drawImage(Screen.tileset_ground[groundID],x,y,width,height,null);
        g.drawImage(tileset_tower,x,y,width,height,null);
        //g.drawRect(x,y,width,height);

        if(airID>-1){
            g.drawImage(Screen.tileset_air[airID],x-height*3*width/(y) ,y-(x+y)/(x-y),width,height,null);
        }
    }
}
