package com.example.game;

import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MusicTool
{
    static InputStream bomb;
    static InputStream earse;
    static InputStream hint;
    static InputStream refresh;
    static InputStream select;
    static InputStream start;
    static InputStream end;
    static InputStream notime;
    static InputStream success;

    static String bombStr = "sound/bomb.wav";
    static String earseStr = "sound/earse.wav";
    static String hintStr = "sound/hint.wav";
    static String refreshStr = "sound/refresh.wav";
    static String selectStr = "sound/select.wav";
    static String startStr = "sound/start.wav";
    static String endStr = "sound/end.wav";
    static String notimeStr ="sound/notime.wav";
    static String successStr = "sound/success.wav";


    public static void playSuccess() {
        if (!Cache.musicXgPlaying) {return;}
        play(successStr, end);
    }

    public static void playNotime() {
        if (!Cache.musicXgPlaying) {return;}
        play(notimeStr, end);
    }

    public static void playEnd() {
        if (!Cache.musicXgPlaying) {return;}
        play(endStr, end);
    }

    public static void playStart() {
        if (!Cache.musicXgPlaying) {return;}
        play(startStr, start);
    }

    public static void playBomb() {
        if (!Cache.musicXgPlaying) {return;}
        play(bombStr, bomb);
    }

    public static void playEarse() {
        if (!Cache.musicXgPlaying) {return;}
        play(earseStr, earse);
    }

    public static void playHint() {
        if (!Cache.musicXgPlaying) {return;}
        play(hintStr, hint);
    }

    public static void playRefresh() {
        if (!Cache.musicXgPlaying) {return;}
        play(refreshStr, refresh);
    }

    public static void playSelect() {
        if (!Cache.musicXgPlaying) {return;}
        play(selectStr, select);
    }

    public static void play(String path, InputStream inputStream)
    {
        try
        {
            inputStream = new FileInputStream(new File(path));
            Player player = new Player(inputStream);
            player.play();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
