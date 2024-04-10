package com.example.game;

public class MusicPlayThread extends Thread
{
    @Override
    public void run()
    {
        MusicPlay.play();
    }
}