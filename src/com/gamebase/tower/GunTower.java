package com.gamebase.tower;

import com.gamebase.Block;
import com.gamebase.Screen;
import com.gamebase.Value;
import com.gamebase.etity.Mob;
import com.gamebase.etity.SpamerMob;
import javax.swing.*;
import java.awt.*;

public class GunTower extends Tower{
    private Image tileset_tower1 ;

    public GunTower(Block a) {
        super(a);
        tileset_tower1= new ImageIcon("res/gun.png").getImage();
        Range=4*52;
    }
    public boolean insideRange(int x,int y){
        if(Math.pow(x-xCenter,2)+Math.pow(y-yCenter,2)-Math.pow(Range/2,2)<=0) return true;
        return false;
    }
    public void fightingMob(Graphics g){
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
                am.shootingAmo = true;
                SpamerMob.mobs[fightId].isShooting =true;
            }else {
                am.shootingAmo =false;
                SpamerMob.mobs[fightId].isShooting =false;
            }
            if(am.vaCham(SpamerMob.mobs[fightId].x +1+Mob.mobSize / 2, SpamerMob.mobs[fightId].y+1 + Mob.mobSize / 2)){
                am.delete(xCenter,yCenter);
                subHeal();
                am.shootingAmo=false;
            }
            if (!insideRange(SpamerMob.mobs[fightId].x+Mob.mobSize/2,SpamerMob.mobs[fightId].y+Mob.mobSize/2)){
                am.delete(xCenter,yCenter);
            }
            if (am.shootingAmo)  am.physic(SpamerMob.mobs[fightId].x +1+Mob.mobSize / 2, SpamerMob.mobs[fightId].y+1 + Mob.mobSize / 2,0);

            if(am.shootingAmo)  am.draw(g);
        }catch (Exception e) {}
    }

    @Override
    public void subHeal() {
        SpamerMob.mobs[fightId].heathMob--;
        SpamerMob.mobs[fightId].mobDie();
    }

    @Override
    public void ShootingRange(Graphics g) {
        g.drawOval(xCenter-Range/2,yCenter-Range/2,Range,Range);
    }

    public void draw(Graphics g){
        g.drawImage(Screen.tileset_ground[Value.groundGrass],x,y,width,height,null);
        //g.drawImage(Screen.tileset_ground[groundID],x,y,width,height,null);
        g.drawImage(tileset_tower1,x,y,width,height,null);
        //g.drawRect(x,y,width,height);

        if(airID>-1){
            g.drawImage(Screen.tileset_air[airID],x-height*3*width/(y) ,y-(x+y)/(x-y),width,height,null);
        }
    }
}
