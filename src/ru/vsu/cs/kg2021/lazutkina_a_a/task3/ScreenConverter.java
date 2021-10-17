package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

public class ScreenConverter
{
    private double cx, cy, realWidth, realHeight;
    private int screenWidth, screenHeight;

    public ScreenConverter(double cx, double cy, double realWidth, double realHeight, int screenWidth, int screenHeight)
    {
        this.cx = cx;
        this.cy = cy;
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public ScreenPoint realToScreen(RealPoint point)
    {
        double x = (point.getX() - cx) / realWidth * screenWidth;
        double y = (cy - point.getY()) / realHeight * screenHeight;
        return new ScreenPoint((int)x, (int)y);
    }

    public double getCx()
    {
        return cx;
    }

    public void setCx(double cx)
    {
        this.cx = cx;
    }

    public double getCy()
    {
        return cy;
    }

    public void setCy(double cy)
    {
        this.cy = cy;
    }

    public double getRealWidth()
    {
        return realWidth;
    }

    public void setRealWidth(double realWidth)
    {
        this.realWidth = realWidth;
    }

    public double getRealHeight()
    {
        return realHeight;
    }

    public void setRealHeight(double realHeight)
    {
        this.realHeight = realHeight;
    }

    public int getScreenWidth()
    {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth)
    {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight()
    {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight)
    {
        this.screenHeight = screenHeight;
    }
}
