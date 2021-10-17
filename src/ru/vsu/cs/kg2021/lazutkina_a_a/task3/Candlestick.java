package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import java.awt.*;

public class Candlestick implements Drawable
{
    private int open;
    private int close;
    private int high;
    private int low;
    private CandleType type;

    public Candlestick(int open, int close, int high, int low)
    {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        type = (close < open) ? CandleType.BLACK : CandleType.WHITE;
    }


}
