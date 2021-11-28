/*package ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DrawService;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Line;

import java.awt.*;
import java.awt.geom.Line2D;

public class CoordinatePlane implements Drawable
{
    private Line axisX;
    private Line axisY;

    private int maxY;
    private int maxX;

    int zeroX;
    int zeroY;
    private int height;
    private int width;

    private final double COF_DASH = 0.005;
    private final DrawService DS = new DrawService();
    private ScreenConverter sc;

*//*    public CoordinatePlane(ScreenConverter sc, int maxY, int maxX)
    {
        this.sc = sc;
        this.maxX = maxX;
        this.maxY = maxY;

        this.axisX = new Line(
                new RealPoint(sc.getCx(), 0),
                new RealPoint(sc.getRealWidth(), 0));
        this.axisY = new Line(
                new RealPoint(sc.getCx(), 0),
                new RealPoint(sc.getCx(), maxY));
    }*//*

    public CoordinatePlane(int zeroX, int zeroY, int width, int height, ScreenConverter sc)
    {
        this.zeroX = zeroX;
        this.zeroY = zeroY;
        this.height = height;
        this.width = width;
        this.sc = sc;

    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void draw(Graphics2D g)
    {
        BasicStroke oldStroke = (BasicStroke) g.getStroke();
        g.setStroke(new BasicStroke(3));
        g.drawLine(zeroX, zeroY, width, zeroY);
        g.drawLine(zeroX, zeroY, zeroX, -height); //todo понять что за костыль, почему -

        int interval = width / 10;
        for (int i = zeroX + interval; i < zeroX + width; i += interval)
        {
            drawDashInScreen(g, zeroX + i, zeroY, true);
        }

        g.setStroke(oldStroke);
    }

    private void drawDashInScreen(Graphics2D g, int x, int y, boolean vertical)
    {
        ScreenPoint p1Dash = new ScreenPoint(x, y);
        ScreenPoint p2Dash = vertical ?
                new ScreenPoint(p1Dash.getColumn(), y - 3) :
                new ScreenPoint(x + 3, p1Dash.getRow());
        g.drawLine(p1Dash.getColumn(), p1Dash.getRow(), p2Dash.getColumn(), p2Dash.getRow());
    }

    public int getZeroX()
    {
        return zeroX;
    }

    public void setZeroX(int zeroX)
    {
        this.zeroX = zeroX;
    }

    public int getZeroY()
    {
        return zeroY;
    }

    public void setZeroY(int zeroY)
    {
        this.zeroY = zeroY;
    }

    private void drawAxisY(Graphics2D g)
    {
        DS.drawLine(g, sc, axisY);
        double startY = axisY.getPoint1().getY();
        double finalY = axisY.getPoint2().getY();
        double interval = (finalY - startY) / maxY;
        for (int i = (int) startY; i <= finalY; i += interval * 10)
        {
            drawDash(g, axisY.getPoint1().getX(), startY + i, false);
            DS.drawString(g, sc, String.valueOf(i), new RealPoint(axisY.getPoint1().getX() + 0.1, i));
        }
    }

    private void drawAxisX(Graphics2D g)
    {
        DS.drawLine(g, sc, axisX);
        double startX = axisX.getPoint1().getX();
        double finalX = axisX.getPoint2().getX();
        double interval = (finalX - startX) / maxX;
        for (int i = (int) startX; i < finalX; i += interval)
        {
            drawDash(g, startX + i, axisX.getPoint1().getY(), true);
            DS.drawString(g, sc, String.valueOf(i), new RealPoint(i, axisX.getPoint1().getY() + 2));
        }
    }

    private void drawDash(Graphics2D g, double x, double y, boolean vertical)
    {
        RealPoint p1Dash = new RealPoint(x, y);
        RealPoint p2Dash = vertical ?
                new RealPoint(p1Dash.getX(), y + COF_DASH * sc.getRealHeight()) :
                new RealPoint(x + COF_DASH * sc.getRealWidth(), p1Dash.getY());
        Line dash = new Line(p1Dash, p2Dash);
        DS.drawLine(g, sc, dash);
    }

    public void setMaxX(int maxX)
    {
        this.maxX = maxX;
    }
}*/
