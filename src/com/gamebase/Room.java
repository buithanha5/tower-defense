package com.gamebase;

import java.awt.*;

public class Room {
    public int worldHeight = 11;//15
    public int worldWidth = 17;//21
    public int blocksize = 52;

    //public static int x,y;
    public  Block[][] blocks;

    public Room(){

        define();
    }
    public void define(){
        blocks = new Block[worldHeight][worldWidth];
        int centerWidth =(Screen.myWidth /2 )-(worldWidth*blocksize/2);
        int centerHeight =(Screen.myHeight /2 )-(worldHeight*blocksize/2);
        for (int i = 0; i < blocks.length ; i++) {
            for (int j = 0; j < blocks[0].length ; j++) {
                blocks[i][j] = new Block(centerWidth+j*blocksize,(i*blocksize),blocksize,blocksize,Value.groundGrass,Value.groundRoad);
            }
        }


    }
    public void physic(int x,int y){
            if ((x==Value.startX)&&(y==Value.startY)) return;
            if (x>=worldWidth)return;
            try {
                if ((blocks[y-1][x].groundID==Value.groundRoad)&&(blocks[y-1][x].lengthToEnd==0)){
                    blocks[y-1][x].lengthToEnd = blocks[y][x].lengthToEnd+1;
                    //System.out.println(y+"j"+x);
                    //y--;
                    physic(x,y-1);
                }
            }catch (Exception e){ }
            try {
                if ((blocks[y+1][x].groundID==Value.groundRoad)&&(blocks[y+1][x].lengthToEnd==0)){
                    blocks[y+1][x].lengthToEnd = blocks[y][x].lengthToEnd+1;
                    //System.out.println(y+"j"+x);
                    //y++;
                    physic(x,y+1);
                }
            }catch (Exception e){ }
            try {
                if ((blocks[y][x+1].groundID==Value.groundRoad)&&(blocks[y][x+1].lengthToEnd==0)){
                    blocks[y][x+1].lengthToEnd = blocks[y][x].lengthToEnd+1;
                    //System.out.println(y+"j"+x);
                    //x++;
                    physic(x+1,y);
                }
            }catch (Exception e){ }
            try {
                if ((blocks[y][x-1].groundID==Value.groundRoad)&&(blocks[y][x-1].lengthToEnd==0)){
                    blocks[y][x-1].lengthToEnd = blocks[y][x].lengthToEnd+1;
                   // System.out.println(y+"j"+x);
                    //x--;
                    physic(x-1,y);

                }
            }catch (Exception e){ }
    }

    public void draw(Graphics g){
        for (int i = 0; i < blocks.length ; i++) {
            for (int j = 0; j < blocks[0].length ; j++) {
                blocks[i][j].draw(g);

            }
        }
        for (int i = 0; i < blocks.length ; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                blocks[i][j].fightingMob(g);
            }
        }
        for (int i = 0; i < blocks.length ; i++) {
            for (int j = 0; j < blocks[0].length ; j++) {
                blocks[i][j].ShootingRange(g);
            }
        }
    }
}
