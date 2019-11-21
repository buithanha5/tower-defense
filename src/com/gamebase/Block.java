package com.gamebase;

import com.gamebase.etity.Mob;
import com.gamebase.etity.SpamerMob;
import com.gamebase.etity.amo;
import com.gamebase.tower.physic;

import java.awt.*;

public class Block extends Rectangle  {
    public int groundID;
    public int airID;
    public boolean hasTower= false;
    public int lengthToEnd=0;

    public Block(int x,int y,int width,int height,int groundID,int airID){
        setBounds(x,y,width,height);
        this.groundID=groundID;
        this.airID=airID;
    }
    public void draw(Graphics g){

//        if (groundID == Value.groundBase){
//            g.drawImage(Screen.tileset_ground[0],x,y,width,height,null);
//        }
        g.drawImage(Screen.tileset_ground[Value.groundGrass],x,y,width,height,null);
        g.drawImage(Screen.tileset_ground[groundID],x,y,width,height,null);

        //g.drawRect(x,y,width,height);

        if(airID>-1){
            g.drawImage(Screen.tileset_air[airID],x-height*3*width/(y) ,y-(x+y)/(x-y),width,height,null);
        }
//        if ((hasTower>=0)){
//            g.drawImage(Screen.tileset_tower[hasTower],x,y,width,height,null);
//        }
        //g.drawString(""+airID,x+Screen.room.blocksize/2,y+Screen.room.blocksize/2);
    }
public boolean containsIn(Point a){
    if (( x<=a.x)&& (a.x<=x+height)&&
            (y+width<=a.y)&& (a.y<=y+width*2) ) return true;
    return false;
}
}
