package com.example.shopapplication;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer {
    final Label timerLabel = new Label();
    Timeline timeline;
    LocalTime timeRemaining;

    public Timer(int hour, int minute, int second) {

        if (hour >= 0 && minute >= 0 && second >= 0) {
            LocalTime startTime = LocalTime.of(hour, minute, second);
            timeRemaining = startTime;

            // Create the timeline with a KeyFrame that updates the timer every second
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                timeRemaining = timeRemaining.minusSeconds(1);
                if (timeRemaining.equals(LocalTime.MIN)) {
                    // Stop the timeline when the time is up
                    timeline.stop();
                    timerLabel.setText("Time's up!");
                } else {
                    timerLabel.setText("Time remaining :        " + timeRemaining.format(DateTimeFormatter.ofPattern("HH : mm : ss")));
                }
            }));

            // Set the cycle count to INDEFINITE to keep the timer running until stopped manually
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }
}