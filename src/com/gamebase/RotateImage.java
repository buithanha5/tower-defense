package com.gamebase;

// Import the basic graphics classes.
import java.awt.*;
import javax.swing.*;
/**
 * Simple program that loads, rotates and displays an image.
 * Uses the file Duke_Blocks.gif, which should be in
 * the same directory.
 *
 * @author MAG
 * @version 20Feb2009
 */

public class RotateImage{

    // Declare an Image object for us to use.

    public static void main(String arg[]){
        JFrame frame = new JFrame("RotateImage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        JPanel jPanel=new JPanel();
        ImageIcon ii = new ImageIcon("res/gun.png");
        RotatedIcon ri = new RotatedIcon(ii, 90.0);
        //JButton button = new JButton( ri );
        Icon icon;
        JLabel jLabel=new JLabel(ri);
        //button.setIcon(  );
        JCheckBox jCheckBox= new JCheckBox(ri);
        jCheckBox.setBounds(193 ,13,134,13);
        //jLabel.setBounds(0,0,64,64);
        //jCheckBox.update();
        jLabel.setBackground(Color.RED);
        jPanel.add(jLabel);
        jPanel.setBounds(00,0,800,800);
        jPanel.add(jCheckBox);
        frame.setContentPane(jPanel);
        frame.setVisible(true);
    }
}



