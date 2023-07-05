package com.example.shopapplication;


import java.util.Timer;
import java.util.TimerTask;

public abstract class MyTimer {

    private static double seconds_digit_1;
    private static double seconds_digit_2;
    private static double minute_digit_1;
    private static double minute_digit_2;
    private static double hours_digit_1;
    private static double hours_digit_2;

    private static int i ;

    private static Boolean seconds_digit_2_add_SW ;
    private static Boolean minutes_digit_1_add_SW ;
    private static Boolean minutes_digit_2_add_SW ;
    private static Boolean hours_digit_1_add_SW ;
    private static Boolean hours_digit_2_add_SW ;

    private static String seconds_digit_1_String = String.format("%.1d" , seconds_digit_1);
    private static String seconds_digit_2_String = String.format("%.1d" , seconds_digit_2);
    private static String minutes_digit_1_String = String.format("%.1d" , minute_digit_1);
    private static String minutes_digit_2_String = String.format("%.1d" , minute_digit_2);
    private static String hours_digit_1_String = String.format("%.1d" , hours_digit_1);
    private static String hours_digit_2_String = String.format("%.1d" , hours_digit_2);

    private static String[] imageNameList = new String[6];

    public static void time () {

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                seconds_digit_1+=0.2;
                if(seconds_digit_1==10) {
                    seconds_digit_1=0;
                    seconds_digit_2_add_SW = true;
                }
                if(seconds_digit_2==7) {
                    seconds_digit_2=0;
                    minutes_digit_1_add_SW = true;
                }
                if(minute_digit_1==10) {
                    minute_digit_1=0;
                    minutes_digit_2_add_SW = true;
                }
                if(minute_digit_2==7) {
                    minute_digit_2=0;
                    hours_digit_1_add_SW = true;
                }
                if(hours_digit_1==10) {
                    hours_digit_1=0;
                    hours_digit_2_add_SW = true;
                }
                if (seconds_digit_2_add_SW || minutes_digit_1_add_SW || minutes_digit_2_add_SW || hours_digit_1_add_SW || hours_digit_2_add_SW) {
                    if (seconds_digit_2_add_SW) seconds_digit_2+=0.2;
                    if (minutes_digit_1_add_SW) minute_digit_1+=0.2;
                    if (minutes_digit_2_add_SW) minute_digit_2+=0.2;
                    if (hours_digit_1_add_SW) hours_digit_1+=0.2;
                    if (hours_digit_2_add_SW) hours_digit_2+=0.2;
                    i++;
                    if (i==6) {
                        seconds_digit_2_add_SW = false ;
                        minutes_digit_1_add_SW = false ;
                        minutes_digit_2_add_SW = false ;
                        hours_digit_1_add_SW = false ;
                        hours_digit_2_add_SW = false ;
                        i=0 ;
                    }
                }
                seconds_digit_1_String = String.format("%.1d" , seconds_digit_1);
                seconds_digit_2_String = String.format("%.1d" , seconds_digit_2);
                minutes_digit_1_String = String.format("%.1d" , minute_digit_1);
                minutes_digit_2_String = String.format("%.1d" , minute_digit_2);
                hours_digit_1_String = String.format("%.1d" , hours_digit_1);
                hours_digit_2_String = String.format("%.1d" , hours_digit_2);
            }
        }, 0, 200);
    }

    public static String[] getImageNameList () {
        imageNameList[0] = seconds_digit_1_String + ".jpg";
        imageNameList[1] = seconds_digit_2_String + ".jpg";
        imageNameList[2] = minutes_digit_1_String + ".jpg";
        imageNameList[3] = minutes_digit_2_String + ".jpg";
        imageNameList[4] = hours_digit_1_String + ".jpg";
        imageNameList[5] = hours_digit_2_String + ".jpg";
        return null;
    }
}
