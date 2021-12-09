package ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram;

public class CandleDouble
{
    private double high;
    private double low;
    private double bottom;
    private double up;

    private CandleType type;

    public CandleDouble(double low, double open, double close, double high)
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
    }

    public double getHigh()
    {
        return high;
    }

    public double getLow()
    {
        return low;
    }

    public double getBottom()
    {
        return bottom;
    }

    public double getUp()
    {
        return up;
    }

    public CandleType getType()
    {
        return type;
    }
}
