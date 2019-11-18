package com.gamebase.etity;

import com.gamebase.ClockFrame;

import javax.swing.*;
import java.awt.*;

public class amo extends Point{
    Image tileset_amo ;
    public boolean shootingAmo =false;
//    public int time;
    public amo(int x,int y){
        setLocation(x,y);
        this.tileset_amo = new ImageIcon("res/amo1.png").getImage();
    }
    public void delete(int xTower, int yTower){
        this.x=xTower;
        this.y=yTower;
    }
    public int t;
    public void physic(int x,int y,int speedAmo){
        if ((t >=speedAmo)) {
            if (this.x > x) this.x--;
            if (this.x < x) this.x++;
            if (this.y > y) this.y--;
            if (this.y < y) this.y++;

            t=0;
        }else t++;
    }
    public boolean vaCham(int xMob, int yMob){
        if(this.x-xMob==0&&this.y-yMob==0 ){
            return true;
        }
        else return false;
    }
    public void draw(Graphics g){
        g.drawImage(tileset_amo,x-32,y-32,64,64,null);
    }
}
