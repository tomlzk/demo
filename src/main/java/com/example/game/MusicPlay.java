package com.example.game;

import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MusicPlay
{
    private static InputStream inputStream = null;
    private static Player player = null;
    public MusicPlay(){}

    public static void play(String music)
    {
        try
        {
            inputStream = new FileInputStream(new File(music));
            player = new Player(inputStream);
            player.play();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void play()
    {
        if (inputStream == null)
        {
            return;
        }
        else
        {
            try
            {
                inputStream = new FileInputStream(new File("sound/lefthand.mp3"));
                player = new Player(inputStream);
                player.play();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void stop()
    {
        System.out.println(inputStream);
        if (inputStream == null)
        {
            return;
        }
        else
        {
            try
            {
                inputStream.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        player.close();
    }

}
