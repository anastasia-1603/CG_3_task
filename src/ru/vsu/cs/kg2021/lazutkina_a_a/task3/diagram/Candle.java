package ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram;

public class Candle {
    private double close;
    private double high;
    private double low;
    private double bottom;
    private double up;
    private double open;
    private int index;
    private CandleType type;

    public Candle(double open, double high, double low, double close, int index) {

        if (close > open) {
            type = CandleType.INCREASING;
            up = close;
            bottom = open;
        } else {
            type = CandleType.DECREASING;
            up = open;
            bottom = close;
        }

        this.open = open;
        this.close = close;
        this.index = index;
        this.high = high;
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getBottom() {
        return bottom;
    }

    public double getUp() {
        return up;
    }

    public CandleType getType() {
        return type;
    }
}
