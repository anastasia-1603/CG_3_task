package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DrawService;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Line;

import java.awt.*;

public class CoordinatePlane implements Drawable
{
    private Line axisX;
    private Line axisY;
    private int xMax;
    private int yMax;

    private final double COF_DASH = 0.005;
    private final DrawService DS = new DrawService();
    private ScreenConverter sc;

    public CoordinatePlane(Line axisX, Line axisY, ScreenConverter sc)
    {
        this.sc = sc;
        int xZero = (int)(sc.getRealWidth());
        int yZero = (int)(sc.getRealHeight());
        this.axisX = new Line(new RealPoint(xZero, yZero), new RealPoint(1, 0));
        this.axisY = new Line(new RealPoint(xZero, yZero), new RealPoint(0, 1));
    }

    public CoordinatePlane(int xMax, int yMax, ScreenConverter sc)
    {
        this.sc = sc;
        int xZero = (int)(sc.getRealWidth());
        int yZero = -2*(int)(sc.getRealHeight());
        this.axisX = new Line(
                new RealPoint(0, yZero),
                new RealPoint(sc.getRealWidth(), yZero));
        this.axisY = new Line(
                new RealPoint(0, yZero),
                new RealPoint(0, 0));
        this.xMax = xMax;
        this.yMax = yMax;
    }

    @Override
    public void draw(Graphics2D g)
    {
        drawAxisX(g, xMax);
        drawAxisY(g, yMax);
    }

    private void drawAxisY(Graphics2D g, int numberUnits)
    {
       /* g.drawLine(axisY.getsPoint1().getColumn(),axisY.getsPoint1().getRow(),
                axisY.getsPoint1().getColumn(), axisY.getsPoint2().getRow());*/
        DS.drawLine(g, sc, axisY);
        //numberUnits = (int) axisY.getPoint2().getY();
        double startY = axisY.getPoint1().getY();
        double finalY = axisX.getPoint2().getX();
        double interval = (finalY - startY) / numberUnits; // периодичность и ...?
        for (double i = startY + interval; i < finalY; i += interval)
        {
            drawDash(g, axisY.getPoint1().getX(), startY +  (finalY - startY) * i, false);
        }
    }

    private void drawAxisX(Graphics2D g, int numberUnits)
    {
        /*g.drawLine(axisX.getsPoint1().getColumn(),axisX.getsPoint1().getRow(),
                axisX.getsPoint1().getColumn(), axisX.getsPoint2().getRow());*/
        DS.drawLine(g, sc, axisX);
        //numberUnits = (int) axisX.getPoint2().getX();
        double startX = axisX.getPoint1().getX();
        double finalX = axisX.getPoint2().getX();
        double interval = (finalX - startX) / numberUnits; // периодичность и ...?
        for (double i = startX; i < finalX; i += interval)
        {
            drawDash(g, startX +  (finalX - startX) * i, axisX.getPoint1().getY(), true);
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
}
