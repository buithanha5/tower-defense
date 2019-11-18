package com.gamebase;

import java.io.*;
import java.util.Scanner;

public class Save {
    public void loadSave(File pathload){
        try {
            Scanner scannerSave = new Scanner(pathload);
            while (scannerSave.hasNext()){
                for (int i = 0; i < Screen.room.blocks.length ; i++) {
                    for (int j = 0; j <Screen.room.blocks[0].length ; j++) {
                        Screen.room.blocks[i][j].groundID = scannerSave.nextInt();
                        if (Screen.room.blocks[i][j].groundID==2) {
                            Value.endX=j;
                            Value.endY=i;
                            //System.out.println(""+i+"as"+j);
                        }
                    }
                }
                for (int i = 0; i < Screen.room.blocks.length ; i++) {
                    for (int j = 0; j <Screen.room.blocks[0].length ; j++) {
                        Screen.room.blocks[i][j].airID = scannerSave.nextInt();
                    }
                }
            }
            scannerSave.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
