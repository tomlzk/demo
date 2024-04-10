package com.example.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener
{
    public Integer width = 800;
    public Integer hight = 600;
    UI ui;

    public Game()
    {
        ui = new UI();
        init();
    }

    public void init()
    {
        initMenu();
        setVisible(true);
        setSize(width, hight);
        setTitle("LianLianKan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu game = new JMenu("游戏");
        JMenu select = new JMenu("选择");
        JMenu help = new JMenu("帮助");

        JMenuItem item;
        game.add(item = new JMenuItem("开始"));
        item.addActionListener(this);
        game.add(item = new JMenuItem("退出"));
        item.addActionListener(this);

        select.add(item = new JMenuItem("提示"));
        item.addActionListener(this);
        select.add(item = new JMenuItem("炸弹"));
        item.addActionListener(this);
        select.add(item = new JMenuItem("重新洗牌"));
        item.addActionListener(this);
        select.add(item = new JMenuItem("暂停"));
        item.addActionListener(this);

        JMenu musicbg = new JMenu("背景音乐");
        ButtonGroup bg1 = new ButtonGroup();
        musicbg.add(item = new JCheckBoxMenuItem("音乐开")).setSelected(true);
        bg1.add(item);
        item.addActionListener(this);
        musicbg.add(item = new JCheckBoxMenuItem("音乐关"));
        bg1.add(item);
        item.addActionListener(this);
        select.add(musicbg);

        JMenu musicXg = new JMenu("音效声音");
        ButtonGroup bg3 = new ButtonGroup();
        musicXg.add(item = new JCheckBoxMenuItem("音效开")).setSelected(true);
        bg3.add(item);
        item.addActionListener(this);
        musicXg.add(item = new JCheckBoxMenuItem("音效关"));
        bg3.add(item);
        item.addActionListener(this);
        select.add(musicXg);
        help.add(item = new JMenuItem("关于"));
        item.addActionListener(this);


        menuBar.add(game);
        menuBar.add(select);
        menuBar.add(help);

        setJMenuBar(menuBar);
    }

    public void start()
    {
        MusicPlay.stop();
        Cache.timeRunning = false;// 禁止原计时器运行
        try
        {
            Thread.sleep(300);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Cache.timeRunning = true;// 允许新计时器运行

//		if (Cache.musicBgPlaying) {
//			MusicPlay.play();
//		}
        this.remove(Cache.ui);
        Cache.reSet();
        MusicTool.playStart();

        ui = new UI();
        this.add(ui);
        ui.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {
        String s = evt.getActionCommand();
        if (s.equals("开始"))
        {
            start();
        } else if (s.equals("退出"))
        {
            System.exit(0);
        } else if (s.equals("音乐开"))
        {
            Cache.musicBgPlaying = true;
        } else if (s.equals("音乐关"))
        {
            Cache.musicBgPlaying = false;
        } else if (s.equals("音效开"))
        {
            Cache.musicXgPlaying = true;
        } else if (s.equals("音效关"))
        {
            Cache.musicXgPlaying = false;
        } else if (s.equals("提示"))
        {
            if (ui.getHintNum() <= 0)
            {
                return;
            }
            UITool.showHint();
            ui.lostHint(1);
        } else if (s.equals("炸弹"))
        {
            if (ui.getBombNum() <= 0)
            {
                return;
            }
            UITool.useBomb();
            ui.lostBomb(1);
        } else if (s.equals("重新洗牌"))
        {
            if (ui.getRefreshNum() <= 0)
            {
                return;
            }
            UITool.refresh();
            ui.lostRefresh(1);
        } else if (s.equals("暂停"))
        {
            UITool.holdon();
        }
    }
}
