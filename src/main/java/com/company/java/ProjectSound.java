package com.company.java;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public abstract class ProjectSound {

    private static Media mediaJazzMusic ;
    private static MediaPlayer mediaPlayerJazzMusic;

    private static Media mediaClickSoundEffect ;
    private static MediaPlayer mediaPlayerClickSoundEffect;

    private static Media mediaCashOutSoundEffect ;
    private static MediaPlayer mediaPlayerCashOutSoundEffect;

    private File directory ;
    private File[] files;

    private ArrayList<File> songs;

    private boolean running ;

    private static boolean soundSW ;

    public static void setSoundSW(boolean soundSW) {
        ProjectSound.soundSW = soundSW;
    }

    public ProjectSound () {

        songs = new ArrayList<File>();

        directory = new File("music and sound effects");

        files = directory.listFiles();

        for (File file : files) songs.add(file);

        mediaJazzMusic = new Media(songs.get(2).toURI().toString());
        mediaClickSoundEffect = new Media(songs.get(1).toURI().toString());
        mediaCashOutSoundEffect = new Media(songs.get(0).toURI().toString());

        mediaPlayerJazzMusic = new MediaPlayer(mediaJazzMusic);
        mediaPlayerClickSoundEffect = new MediaPlayer(mediaClickSoundEffect);
        mediaPlayerCashOutSoundEffect = new MediaPlayer(mediaCashOutSoundEffect);

    }

    public static void playJazzSong () {
        mediaPlayerJazzMusic.play();
    }

    public static void playClickSoundEffect () {
        mediaPlayerClickSoundEffect.play();
    }

    public static void playCashOutSoundEffect () {
        mediaPlayerCashOutSoundEffect.play();
    }
}
