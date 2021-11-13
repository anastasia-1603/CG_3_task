package ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.Drawable;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.RealPoint;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.ScreenConverter;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DrawService;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Line;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Rectangle;

import java.awt.*;

public class Candlestick
{
    //private int open;
    //private int close;
    private int high;
    private int low;
    private int time;
    private int bottom;
    private int up;

    private int width;
    private CandleType type;
    private ScreenConverter sc;

    public Candlestick(int low, int open, int close, int high, int time, int width, ScreenConverter sc)
    {
        if (close > open)
        {
            type = CandleType.INCREASING;
            up = close;
            bottom = open;
        }
        else
        {
            type = CandleType.DECREASING;
            up = open;
            bottom = close;
        }
        //this.open = open;
        //this.close = close;
        this.high = high;
        this.low = low;
        this.time = time;
        this.sc = sc;
        this.width = width;

        //type = (close > open) ? CandleType.INCREASING : CandleType.DECREASING;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHigh()
    {
        return high;
    }

    public void setHigh(int high)
    {
        this.high = high;
    }

    public int getLow()
    {
        return low;
    }

    public void setLow(int low)
    {
        this.low = low;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public int getBottom()
    {
        return bottom;
    }

    public void setBottom(int bottom)
    {
        this.bottom = bottom;
    }

    public int getUp()
    {
        return up;
    }

    public void setUp(int up)
    {
        this.up = up;
    }

    public CandleType getType()
    {
        return type;
    }
}
