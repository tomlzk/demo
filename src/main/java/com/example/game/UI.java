package com.example.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class UI extends JPanel implements ActionListener
{
    JButton hint = null; //提示
    JButton bomb = null; //炸弹
    JButton refresh = null; //洗牌
    JButton pause = null; //暂停
    JLabel time = null; //时间
    JLabel timeBar = null;
    JLabel timeBarBg = null;
    JLabel hintTip2 = null;
    JLabel bombTip2 = null;
    JLabel refreshTip2 = null;

    public UI()
    {
        Cache.ui = this;
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        UITool.initGameCells();

        //时间相关的组件
        Font font = new Font("宋体", Font.BOLD, 30);
        JLabel timeTip = new JLabel("时间");
        timeTip.setIcon(new ImageIcon("img/1.gif"));
        timeTip.setBounds(750, 29, 28, 28);         //这样使得图片的位置固定了，之后用布局器的方法pack()函数
        this.add(timeTip);

        JLabel timeTip2 = new JLabel("时间");
        timeTip2.setForeground(Color.WHITE);//设置前景色
        timeTip2.setFont(font);
        timeTip2.setBounds(830, 25, 60, 50);         //这样使得图片的位置固定了，之后用布局器的方法pack()函数
        this.add(timeTip2);

        time = new JLabel(Const.time + "");
        time.setForeground(Color.WHITE);
        time.setFont(font);
        time.setBounds(830, 25, 60, 50);
        this.add(time);

        timeBarBg = new JLabel();
        timeBarBg.setIcon(new ImageIcon("img/timeBarGg.gif"));
        timeBarBg.setBounds(750, 60, 124, 20);
        timeBar = new JLabel();
        timeBar.setIcon(new ImageIcon("img/timeBar.gif"));
        timeBar.setBounds(752, 62, 120, 16);
        this.add(timeBar);
        this.add(timeBarBg);

        //提示相关的组件
        Font font2 = new Font("宋体", Font.BOLD, 12);
        JLabel hintTip = new JLabel();
        hintTip.setIcon(new ImageIcon("img/0.gif"));
        hintTip.setBounds(750, 180, 28, 28);
        this.add(hintTip);

        hintTip2 = new JLabel("2");
        hintTip2.setForeground(Color.WHITE);
        hintTip2.setBounds(788, 180, 28, 28);
        this.add(hintTip2);

        hint = new JButton("提  示");
        hint.setFont(font2);
        hint.setBounds(750, 211, Const.userBtnWidth, Const.userBtnHeigth);
        this.add(hint);

        //炸弹相关的组件
        JLabel bombTip = new JLabel();
        bombTip.setIcon(new ImageIcon("img/3.gif"));
        bombTip.setBounds(750, 280, 28, 28);
        this.add(bombTip);
        bombTip2 = new JLabel("2");
        bombTip2.setForeground(Color.WHITE);
        bombTip2.setBounds(788, 280, 28, 28);
        this.add(bombTip2);
        bomb = new JButton("炸  弹");
        bomb.setFont(font2);
        bomb.setBounds(750, 311, Const.userBtnWidth, Const.userBtnHeigth);
        this.add(bomb);

        //重新洗牌相关的组件
        JLabel refreshTip = new JLabel();
        refreshTip.setIcon(new ImageIcon("img/2.gif"));
        refreshTip.setBounds(750, 380, 28, 28);
        this.add(refreshTip);
        refreshTip2 = new JLabel("2");
        refreshTip2.setForeground(Color.WHITE);
        refreshTip2.setBounds(788, 380, 28, 28);
        this.add(refreshTip2);
        refresh = new JButton("重新洗牌");
        refresh.setFont(font2);
        refresh.setBounds(750, 411, Const.userBtnWidth, Const.userBtnHeigth);
        this.add(refresh);

        hint.addActionListener(this);
        bomb.addActionListener(this);
        refresh.addActionListener(this);

        this.updateUI();
        Thread t = new TimeThread();
        t.start();
        MusicPlayThread t2 = new MusicPlayThread();
        t2.start();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        for (int i = 0; i < Cache.pointsForPaint.size(); i++)
        {
            g.drawImage(Const.point.getImage(), Cache.pointsForPaint.get(i)[0], Cache.pointsForPaint.get(i)[1], null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {
        String s = evt.getActionCommand();
        if (s.equals("提示"))
        {
            if (this.getHintNum() <= 0)
            {
                return;
            }
            UITool.showHint();
            this.lostHint(1);
        } else if (s.equals("炸  弹"))
        {
            if (this.getBombNum() <= 0)
            {
                return;
            }
            UITool.useBomb();
            this.lostBomb(1);
        } else if (s.equals("重新洗牌"))
        {
            if (this.getRefreshNum() <= 0)
            {
                return;
            }
            UITool.refresh();
            this.lostRefresh(1);
        }
    }

    public void setTimeLabel(double n)
    {
        n = Math.abs(n);
        DecimalFormat df = new DecimalFormat("0");      //格式化函数，只保留整数,并将整数变为字符串
        time.setText(df.format(n));                            //格式化n
        timeBar.setBounds(752, 62, (int) ((n * 120) / Const.time), 16);
        this.add(timeBar);
        this.add(timeBarBg);
        this.updateUI();
    }

    public int getTimeNum()
    {
        return Integer.parseInt(time.getText());    //将字符串转化为整数
    }

    public void addTime(double d)
    {
        double c = Cache.time;
        c = c + d;
        Cache.time = c;
    }

    public void addHintNum(int n)
    {
        Integer m = Integer.parseInt(hintTip2.getText());
        m = m + n;
        hintTip2.setText(m.toString());
    }

    public void addBomb(int n)
    {
        int m = Integer.parseInt(bombTip2.getText());
        m = m + n;
        bombTip2.setText(m + ""); //自动将m转化为字符串，并且与这个空字符串相连接
    }

    public void addRefresh(int n)
    {
        int m = Integer.parseInt(refreshTip2.getText());
        m = m + n;
        refreshTip2.setText(m + "");
    }

    public void lostHint(int n)
    {
        int m = Integer.parseInt(hintTip2.getText());
        m = m - n;
        hintTip2.setText(m + "");
    }

    public void lostBomb(int n)
    {
        int m = Integer.parseInt(bombTip2.getText());
        m = m - n;
        bombTip2.setText(m + "");
    }

    public void lostRefresh(int n)
    {
        int m = Integer.parseInt(refreshTip2.getText());
        m = m - n;
        refreshTip2.setText(m + "");
    }

    public int getHintNum()
    {
        return Integer.parseInt(hintTip2.getText());
    }

    public int getBombNum()
    {
        return Integer.parseInt(bombTip2.getText());
    }

    public int getRefreshNum()
    {
        return Integer.parseInt(refreshTip2.getText());
    }
}
