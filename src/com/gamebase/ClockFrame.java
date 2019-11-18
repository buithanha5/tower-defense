package com.gamebase;

public class ClockFrame {

    public static int tick;
    public static int beftick;
    public static int detick;
    public static int befdetick;
    public static int militick;
    public static int befmilitick;
    public static double Start;
    public static double Now;
    public static void StartTime(){
        Start = System.currentTimeMillis();
    }
    public static void NowTime(){
        Now = System.currentTimeMillis();
    }
    public static boolean NextTick(){
        if (beftick==tick) return false;
        return true;
    }
    public static boolean NextDeTick(){
        if (befdetick==detick) return false;
        return true;
    }
    public static int CountTick(){
        beftick=tick;
        tick = (int)(Now-Start)/1000;
        return tick;
    }
    public static int deciTick(){
        befdetick=detick;
        detick = (int)(Now-Start)/100;
        return detick;
    }
    public static int miliTick(){
        befmilitick=militick;
        militick = (int)(Now-Start)/100;
        return militick;
    }
}
