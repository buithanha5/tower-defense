package com.gamebase;

import com.gamebase.etity.Mob;
import com.gamebase.etity.SpamerMob;
import com.gamebase.etity.amo;

import java.awt.*;

public class Block extends Rectangle {
    public int groundID;
    public int airID;
    public int hasTower=-1;
    public int lengthToEnd=0;
    public int Range=52*8;
    private int xCenter,yCenter;
    private boolean isFight =false;
    private int fightId=-1;
    private amo a ;

    public Block(int x,int y,int width,int height,int groundID,int airID){
        setBounds(x,y,width,height);
        this.groundID=groundID;
        this.airID=airID;
        this.xCenter= x+width/2;
        this.yCenter= y+height/2;
        a=new amo(x+width/2,y+height/2);
    }
    private boolean insideRange(int x,int y){
        if(Math.pow(x-xCenter,2)+Math.pow(y-yCenter,2)-Math.pow(Range/2,2)<=0) return true;
        return false;
    }
    public void fightingMob(Graphics g){
        if ((hasTower>-1)){
            for (int i = 0; i < SpamerMob.mobs.length; i++) {
                if (SpamerMob.mobs[i].inGame){
                    if (insideRange(SpamerMob.mobs[i].x+Mob.mobSize/2,SpamerMob.mobs[i].y+Mob.mobSize/2)){
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
                        g.drawLine(xCenter-1, yCenter-1, SpamerMob.mobs[fightId].x -1+ Mob.mobSize / 2, SpamerMob.mobs[fightId].y-1+ Mob.mobSize / 2);
                        g.drawLine(xCenter+1, yCenter+1, SpamerMob.mobs[fightId].x +1+Mob.mobSize / 2, SpamerMob.mobs[fightId].y+1 + Mob.mobSize / 2);
                        g.setColor(Color.black);
                        a.shootingAmo = true;
//                        a.physic(SpamerMob.mobs[fightId].x +1+Mob.mobSize / 2, SpamerMob.mobs[fightId].y+1 + Mob.mobSize / 2,1);//mili tick
//                        a.draw(g);
//                        if(a.vaCham(SpamerMob.mobs[fightId].x +1+Mob.mobSize / 2, SpamerMob.mobs[fightId].y+1 + Mob.mobSize / 2)){
//                            SpamerMob.mobs[fightId].subHeathLogic();
//                            a.delete(x-width/2,y-height/2);
//                            a=new amo(x-width/2,y-height/2);
//                        }
                        SpamerMob.mobs[fightId].isShooting =true;
                }else {
                    a.shootingAmo =false;
                    SpamerMob.mobs[fightId].isShooting =false;
                }
                if(a.vaCham(SpamerMob.mobs[fightId].x +1+Mob.mobSize / 2, SpamerMob.mobs[fightId].y+1 + Mob.mobSize / 2)){
                    a.delete(xCenter,yCenter);
                    SpamerMob.mobs[fightId].subHeathLogic();
                }
                if (a.shootingAmo)  a.physic(SpamerMob.mobs[fightId].x +1+Mob.mobSize / 2, SpamerMob.mobs[fightId].y+1 + Mob.mobSize / 2,0);

                 if(a.shootingAmo)  a.draw(g);
            }catch (Exception e) {}
//System.out.println(""+fightId);
        }
    }
    public void ShootingRange(Graphics g){
        if(hasTower>-1){
            //g.setColor(new Color(100,12,234));
            //g.drawOval(xCenter-(Range)/2,yCenter-(Range)/2,Range,Range);
            //g.fillOval(x+(height-Range)/2,y+(width-Range)/2,Range,Range);

        }
    }
    public void draw(Graphics g){

        if (groundID == Value.groundBase){
            g.drawImage(Screen.tileset_ground[Value.groundRoad],x,y,width,height,null);
        }
        g.drawImage(Screen.tileset_ground[groundID],x,y,width,height,null);
        //g.drawRect(x,y,width,height);
        if (hasTower>=0){
            g.drawImage(Screen.tileset_tower[hasTower],x,y,width,height,null);
        }
        if(airID==1){
            g.drawImage(Screen.tileset_ground[3],x,y,width,height,null);
        }
        //g.drawString(""+airID,x+Screen.room.blocksize/2,y+Screen.room.blocksize/2);
    }
public boolean containsIn(Point a){
    if (( x<=a.x)&& (a.x<=x+height)&&
            (y+width<=a.y)&& (a.y<=y+width*2) ) return true;
    return false;
}
}
