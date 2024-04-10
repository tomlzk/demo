package com.example.game;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton
{
    private static final long serialVersionUID = 2558491969593969627L;
    int xPoint;
    int yPoint;
    Color cellColor;
    public Cell(int x, int y)
    {
        this.xPoint = x;
        this.yPoint = y;

        this.cellColor = Const.cellClr;

        this.setSize(Const.btnWidth, Const.btnHeigth);
    }

    public int getxPoint()
    {
        return xPoint;
    }

    public void setxPoint(int xPoint)
    {
        this.xPoint = xPoint;
    }

    public int getyPoint()
    {
        return yPoint;
    }

    public void setyPoint(int yPoint)
    {
        this.yPoint = yPoint;
    }

    public Color getCellColor()
    {
        return cellColor;
    }


    public void setCellColor(Color cellColor)
    {
        this.cellColor = cellColor;
    }


}

