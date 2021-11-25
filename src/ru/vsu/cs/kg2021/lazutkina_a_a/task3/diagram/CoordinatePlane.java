package ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DrawService;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Line;

import java.awt.*;

public class CoordinatePlane implements Drawable
{
    private Line axisX;
    private Line axisY;

    private int maxY;
    private int maxX;

    private final double COF_DASH = 0.005;
    private final DrawService DS = new DrawService();
    private ScreenConverter sc;

    public CoordinatePlane(ScreenConverter sc, int maxY, int maxX)
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
    }

    @Override
    public void draw(Graphics2D g)
    {
        drawAxisX(g);
        drawAxisY(g);
    }

    public void setWidth(int width)
    {
        this.axisX.getPoint2().setX(width);
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
}
