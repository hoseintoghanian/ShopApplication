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

    private String seconds_digit_1_String = String.format("%.2d" , seconds_digit_1);
    private String seconds_digit_2_String = String.format("%.2d" , seconds_digit_2);
    private String minutes_digit_1_String = String.format("%.2d" , minute_digit_1);
    private String minutes_digit_2_String = String.format("%.2d" , minute_digit_2);
    private String hours_digit_1_String = String.format("%.2d" , hours_digit_1);
    private String hours_digit_2_String = String.format("%.2d" , hours_digit_2);

    private String seconds_digit_1_Image_Name ;
    private String seconds_digit_2_Image_Name;
    private String minutes_digit_1_Image_Name;
    private String minutes_digit_2_Image_Name;
    private String hours_digit_1_Image_Name;
    private String hours_digit_2_Image_Name;

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
            }
        }, 0, 200);
    }
}
