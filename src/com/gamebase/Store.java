package com.gamebase;

import com.gamebase.tower.GunTower;
import com.gamebase.tower.Tower;
import com.gamebase.tower.lazerTower;

import java.awt.*;

public class Store {
    public static int shopwidth = 8;
    public static int buttonsize = 58;
    public static int sellSpace = 4;
    public static int iconsize = 25;
    public static int textSize = 16;
    public static int itemIn = 3;
    public static int heldId = -1;
    public static int[] buttonID ={Value.gunTower,Value.doubleGunTower,Value.lazerTower,Value.nullTower,Value.nullTower,Value.nullTower,Value.nullTower,Value.nullTower} ;
    public static int[] buttonCost= {95,100,110,0,0,0,0,0};
    public Rectangle[] button =new Rectangle[shopwidth];
    public Rectangle buttonHealth ;
    public Rectangle buttonCoin ;

    public static boolean holdItem =false;

    public Store(){
        define();

    }
    public void click(int mouseButton){
        if(mouseButton==1){ // click mouse left
            for (int i = 0; i <button.length ; i++) {
                if (( button[i].x<=Screen.mse.x)&& (Screen.mse.x<=button[i].x+buttonsize)&&
                        ( button[i].y+buttonsize<=Screen.mse.y)&& (Screen.mse.y<=button[i].y+buttonsize*2) ){
                    if(buttonID[i] != Value.nullTower) {
                        heldId = buttonID[i];
                        holdItem = true;
                    }
                }
            }
        }
        if (holdItem){
            if (Screen.coin >= buttonCost[heldId]){
                for (int i = 0; i < Screen.room.blocks.length ; i++) {
                    for (int j = 0; j < Screen.room.blocks[0].length ; j++) {
                        if(Screen.room.blocks[i][j].containsIn(Screen.mse)){
                            if ((Screen.room.blocks[i][j].groundID!=Value.groundRoad)&&(Screen.room.blocks[i][j].groundID==Value.groundGrass)
                                    &&(!Screen.room.blocks[i][j].hasTower)&&(Screen.room.blocks[i][j].airID==Value.AirId))  {
                                chooceTower(i,j,heldId);
                                Screen.coin-= buttonCost[heldId];
                            }
                        }

                    }
                }

            }else holdItem=false;
        }
        if (mouseButton==3){ // clik mouse right
            holdItem=false;
        }
    }
    private void  chooceTower(int i,int j,int a){
       if (a==1){
                Tower t=new GunTower((Screen.room.blocks[i][j]));
                Screen.room.blocks[i][j]=t;
                Screen.room.towers.add(t);
            }
            if(a==2) {

                Tower t=new lazerTower((Screen.room.blocks[i][j]));
                Screen.room.blocks[i][j]=t;
                Screen.room.towers.add(t);
            }
    }
    public void define(){
        for (int i = 0; i <button.length ; i++) {
            button[i] = new Rectangle(50+(Screen.myWidth/2)-(shopwidth*(buttonsize+sellSpace))/2 +i*(buttonsize+sellSpace),
                    Screen.room.blocks[Screen.room.worldHeight-1][0].y +Screen.room.blocksize + sellSpace*4
                    ,buttonsize,buttonsize);
        }
        buttonHealth = new Rectangle(Screen.room.blocks[0][0].x,button[0].y,iconsize,iconsize);
        buttonCoin = new Rectangle(Screen.room.blocks[0][0].x,button[0].y+button[0].height-iconsize,iconsize,iconsize);
    }
    public void draw(Graphics g){
        for (int i = 0; i <button.length ; i++) {
            if (( button[i].x<=Screen.mse.x)&& (Screen.mse.x<=button[i].x+buttonsize)&&
                ( button[i].y+buttonsize<=Screen.mse.y)&& (Screen.mse.y<=button[i].y+buttonsize*2) ) {
                g.drawImage(Screen.tileset_res[0],button[i].x,button[i].y,button[i].width,button[i].height,null);
            }
            g.drawImage(Screen.tileset_res[1],button[i].x,button[i].y,button[i].width,button[i].height,null);
            g.drawImage(Screen.tileset_tower[i],button[i].x+itemIn,button[i].y+itemIn,button[i].width-itemIn*3,button[i].height-itemIn*3,null);
            if (buttonCost[i]>0){
                g.setColor(new Color(203, 223, 65));
                g.setFont(new Font("Courier New",Font.BOLD,textSize));
                g.drawString("$"+buttonCost[i],button[i].x+sellSpace,button[i].y);
            }
        }

        g.drawImage(Screen.tileset_res[3],buttonHealth.x,buttonHealth.y,buttonHealth.width,buttonHealth.height,null);
        g.drawImage(Screen.tileset_res[2],buttonCoin.x,buttonCoin.y,buttonCoin.width,buttonCoin.height,null);
        g.setFont(new Font("Courier New",Font.BOLD,textSize));
        g.drawString(": "+Screen.heart,buttonHealth.x+iconsize+sellSpace,buttonHealth.y +sellSpace*4);
        g.drawString(": "+Screen.coin,buttonCoin.x+iconsize+sellSpace,buttonCoin.y +sellSpace*4);
        if (holdItem){
            g.drawImage(Screen.tileset_tower[heldId],Screen.mse.x-30,Screen.mse.y-80,button[0].width-itemIn*3,button[0].height-itemIn*3,null);
        }
    }
}
