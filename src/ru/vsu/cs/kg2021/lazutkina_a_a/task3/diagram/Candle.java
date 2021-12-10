/*
package ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram;

public class Candle
{
    private int high;
    private int low;
    private int time;
    private int bottom;
    private int up;

    private int width;
    private CandleType type;

    public Candle(int low, int open, int close, int high, int time, int width)
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

        this.high = high;
        this.low = low;
        this.time = time;
        this.width = width;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHigh()
    {
        return high;
    }

    public int getLow()
    {
        return low;
    }

    public int getTime()
    {
        return time;
    }

    public int getBottom()
    {
        return bottom;
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
*/
