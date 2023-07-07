package com.example.shopapplication;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.TimerTask;

public class Timer {
    private LocalDateTime uploadDate;
    private LocalDateTime endTime;
    public java.util.Timer timer;
    public Label timerLabel;
    private Button bidButton;
    private TextField bidField;

    public Timer(LocalDateTime uploadDate, Button bidButton, TextField bidField, String textFormat) {
        this.uploadDate = uploadDate;
        this.bidButton = bidButton;
        this.bidField = bidField;

        endTime = uploadDate.plusHours(24);
        //endTime = uploadDate.plusMinutes(2);

        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTimer(textFormat);
            }
        }, 0, 1000);

        timerLabel = new Label();
        updateTimer(textFormat);
    }

    private void updateTimer(String textFormat) {
        // Calculate the remaining time
        Duration remainingTime = Duration.between(LocalDateTime.now(), endTime);

        // If the remaining time is negative, stop the timer
        if (remainingTime.isNegative()) {
            Platform.runLater(() -> {
                timerLabel.setText("Time's up");
                bidButton.setDisable(true);
                bidField.setDisable(true);
            });
            timer.cancel();
        } else {
            // Format the remaining time as hours, minutes, and seconds
            long remainingHours = remainingTime.toHours();
            long remainingMinutes = remainingTime.toMinutesPart();
            long remainingSeconds = remainingTime.toSecondsPart();
            Platform.runLater(() -> {
                timerLabel.setText(String.format(textFormat, remainingHours, remainingMinutes, remainingSeconds));
            });
        }
    }
}

