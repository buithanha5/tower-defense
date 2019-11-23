package com.gamebase.tower;

import java.awt.*;

public interface physic {
    boolean insideRange(int x, int y);
    void subHeal();
    void fightingMob(Graphics g);
    void ShootingRange(Graphics g);
    boolean containsIn(Point a);

}
