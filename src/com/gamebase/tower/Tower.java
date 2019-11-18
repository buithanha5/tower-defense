package com.gamebase.tower;

import com.gamebase.Block;

import javax.swing.*;
import java.awt.*;

public abstract class   Tower extends Block{


    public Tower(int x, int y, int width, int height, int groundID, int airID) {
        super(x, y, width, height, groundID, airID);
    }
public  static Tower reMade(Block a){
    return null;
};

    public void draw(Graphics g){
    }
}
