package com.gamebase.etity;

import com.gamebase.ClockFrame;
import com.gamebase.Screen;
import com.gamebase.Value;

import java.awt.*;

public class Mob extends Rectangle {
    public static int mobSize = Screen.room.blocksize;
    public  int mobId=Value.mobSodi;
    public boolean inGame = false;
    public int blockX,blockY;
    public int mobwalk=0 ,right=1,left=2,up=3,down=4,myMob;
    public int frametime =0 ,speedTime=Value.Speepd;
    public boolean hasUp=false,hasDown=false,hasRight=false,hasLeft=false;
    public boolean isShooting=false;
    public int heathMob = 50;
    public int costMob = 25;
    //public Rectangle heathBar;
    public Mob(){
    }
    public void spawnMod(int mobId,Point SpamPoint){

            blockX = SpamPoint.x;
            blockY = SpamPoint.y;
            setBounds(Screen.room.blocks[blockY][blockX].x, Screen.room.blocks[blockY][blockX].y, mobSize, mobSize);
            //setBounds(10,13,135,131);
            //heathBar.setBounds(x,y,width,20);
            this.mobId = mobId;
//            if (mobId==2) {
//                speedTime=speedTime-2;
//            }
//            if (mobId==1) {
//                speedTime=speedTime-4;
//            }
            inGame = true;
        try {
            if (!hasUp) {

                if( (Screen.room.blocks[blockY + 1][blockX].lengthToEnd<Screen.room.blocks[blockY][blockX].lengthToEnd)
                        &&(Screen.room.blocks[blockY + 1][blockX].groundID==Value.groundRoad)){
                    myMob = down;
                }

            }
        } catch (Exception e) { }
        try {
            if (!hasDown) {

                if ((Screen.room.blocks[blockY - 1][blockX].lengthToEnd<Screen.room.blocks[blockY][blockX].lengthToEnd)
                        &&(Screen.room.blocks[blockY - 1][blockX].groundID==Value.groundRoad)){
                    myMob = up;
                }

            }
        } catch (Exception e) { }
        try {
            if (!hasLeft) {
                if ((Screen.room.blocks[blockY ][blockX+1].lengthToEnd<Screen.room.blocks[blockY][blockX].lengthToEnd)
                        &&(Screen.room.blocks[blockY ][blockX+1].groundID==Value.groundRoad)){
                    myMob = right;
                }

            }
        } catch (Exception e) { }
        try {
            if (!hasRight) {
                if ((Screen.room.blocks[blockY ][blockX-1].lengthToEnd<Screen.room.blocks[blockY][blockX].lengthToEnd)
                        &&(Screen.room.blocks[blockY ][blockX-1].groundID==Value.groundRoad)){
                    myMob = left;
                }

            }

        } catch (Exception e) { }

    }

    public void deleteMod(){
        inGame = false;
        hasUp=false;
        hasDown=false;
        hasRight=false;
        hasLeft=false;
        //myMob=right;
        isShooting=false;
        //blockX=0;
        //blockY=1;
        speedTime=Value.Speepd;
        heathMob=50;
        mobwalk=0;
    }
    public boolean isDie(){
        if (heathMob<=0) return true;
        return false;
    }
    public int t =0;
    public void mobDie (){
        if (isDie()){
            Screen.coin+=costMob;
            deleteMod();
        }
    }
//    public void subHeathLogic(){
//        heathMob--;
//        if (isDie()){
//            Screen.coin+=costMob;
//            deleteMod();
//        }
//    }
    public void looseHealth(){
        Screen.heart--;
    }

    public void physic(){
            if ((frametime>=speedTime)) {
                if (myMob == right) {
                    x++;
                } else if (myMob == up) {
                    y--;
                } else if (myMob == down) {
                    y++;
                } else if (myMob == left) {
                    x--;
                }

                mobwalk++;
                try {
                    if (Screen.room.blocks[blockY][blockX].groundID == Value.groundBase) {
                        deleteMod();
                        looseHealth();
                    }
                }catch (Exception e){}
                if (mobwalk==Screen.room.blocksize){
                    if (myMob==right){
                        blockX++;
                        hasRight=true;
                    }else if(myMob==up){
                        blockY--;
                        hasUp=true;
                    }else if (myMob==down){
                        blockY++;
                        hasDown=true;
                    }else if (myMob==left){
                        blockX--;
                        hasLeft=true;
                    }
                    try {
                        if (!hasUp) {

                            if( (Screen.room.blocks[blockY + 1][blockX].lengthToEnd<Screen.room.blocks[blockY][blockX].lengthToEnd)
                            &&(Screen.room.blocks[blockY + 1][blockX].groundID==Value.groundRoad)){
                                myMob = down;
                            }

                        }
                    } catch (Exception e) { }
                    try {
                        if (!hasDown) {

                            if ((Screen.room.blocks[blockY - 1][blockX].lengthToEnd<Screen.room.blocks[blockY][blockX].lengthToEnd)
                            &&(Screen.room.blocks[blockY - 1][blockX].groundID==Value.groundRoad)){
                                myMob = up;
                            }

                        }
                    } catch (Exception e) { }
                    try {
                        if (!hasLeft) {
                            if ((Screen.room.blocks[blockY ][blockX+1].lengthToEnd<Screen.room.blocks[blockY][blockX].lengthToEnd)
                            &&(Screen.room.blocks[blockY ][blockX+1].groundID==Value.groundRoad)){
                                myMob = right;
                            }

                        }
                    } catch (Exception e) { }
                    try {
                        if (!hasRight) {
                            if ((Screen.room.blocks[blockY ][blockX-1].lengthToEnd<Screen.room.blocks[blockY][blockX].lengthToEnd)
                            &&(Screen.room.blocks[blockY ][blockX-1].groundID==Value.groundRoad)){
                                myMob = left;
                            }

                        }

                    } catch (Exception e) { }
                    hasUp=false;
                    hasDown=false;
                    hasRight=false;
                    hasLeft=false;
                    mobwalk=0;
                }
                frametime=0;
            }else frametime++;
            //subHeath();
    }

    public void draw(Graphics g){
        if (inGame) {
            g.drawImage(Screen.tileset_mod[mobId],x,y,width,height,null);
            g.drawString(""+heathMob,x,y);
            g.fillRect(x,y,width,5);
            g.setColor(new Color(255,0,0));
            g.fillRect(x,y,width-(50-heathMob)*(width/50),5);
            g.setColor(Color.black);
        }
    }
}
