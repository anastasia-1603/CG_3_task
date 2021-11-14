package ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.Drawable;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.RealPoint;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.ScreenConverter;
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
                new RealPoint(maxX, 0));
        this.axisY = new Line(
                new RealPoint(sc.getCx(), maxY),
                new RealPoint(0, 0));
    }

    @Override
    public void draw(Graphics2D g)
    {
        drawAxisX(g);
        drawAxisY(g);
    }

    private void drawAxisY(Graphics2D g)
    {
        DS.drawLine(g, sc, axisY);
        double finalY = axisY.getPoint1().getY();
        double startY = axisY.getPoint2().getY();
        double interval = (finalY - startY) / maxY;
        for (double i = startY; i < finalY; i += interval)
        {
            drawDash(g, axisY.getPoint1().getX(), startY + i, false);
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
            DS.drawString(g, sc, String.valueOf(i), new RealPoint(i, axisX.getPoint1().getY()* 1.5));
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

    public void setSc(ScreenConverter sc)
    {
        this.sc = sc;
    }

    public Line getAxisX()
    {
        return axisX;
    }

    public void setAxisX(Line axisX)
    {
        this.axisX = axisX;
    }

    public Line getAxisY()
    {
        return axisY;
    }

    public void setAxisY(Line axisY)
    {
        this.axisY = axisY;
    }

    public int getMaxY()
    {
        return maxY;
    }

    public void setMaxY(int maxY)
    {
        this.maxY = maxY;
    }

    public int getMaxX()
    {
        return maxX;
    }

    public void setMaxX(int maxX)
    {
        this.maxX = maxX;
    }
    /*private void drawCoordinateGrid(Graphics2D gr, ScreenConverter screenConverter, Color color) {
        gr.setColor(color);
        drawLine(gr, screenConverter, oX);
        drawLine(gr, screenConverter, oY);
        drawLine(gr, screenConverter, oYCursor1);
        drawLine(gr, screenConverter, oYCursor2);
        drawLine(gr, screenConverter, oXCursor1);
        drawLine(gr, screenConverter, oXCursor2);

        drawWithFont(gr, new Font("Serif", Font.BOLD, (int) (0.015 * this.getWidth())), () -> {
            drawString(gr, screenConverter, STRING_X, new RealPoint(4.93, -0.18));
            drawString(gr, screenConverter, STRING_Y, new RealPoint(-0.1, 4.89));
        });

        int i = 1;
        double plusCoordinate = 0;
        double minusCoordinate = 0;
        for (double coordinate = 0; plusCoordinate < oX.getPoint2().getRealX() - SINGLE_SEGMENT; i++) {
            plusCoordinate = coordinate + (SINGLE_SEGMENT*i);
            minusCoordinate = coordinate - (SINGLE_SEGMENT*i);

            Line currentSegmentPlusX = new Line(new RealPoint(plusCoordinate, 0.02), new RealPoint(plusCoordinate, -0.02));
            Line currentSegmentPlusY = new Line(new RealPoint(0.02, plusCoordinate), new RealPoint(-0.02, plusCoordinate));

            Line currentSegmentMinusX = new Line(new RealPoint(minusCoordinate, 0.02), new RealPoint(minusCoordinate, -0.02));
            Line currentSegmentMinusY = new Line(new RealPoint(0.02, minusCoordinate), new RealPoint(-0.02, minusCoordinate));

            drawLine(gr, screenConverter, currentSegmentPlusY);
            drawLine(gr, screenConverter, currentSegmentPlusX);
            drawLine(gr, screenConverter, currentSegmentMinusY);
            drawLine(gr, screenConverter, currentSegmentMinusX);


            int finalI = i;
            double finalPlusCoordinate = plusCoordinate;
            double finalMinusCoordinate = minusCoordinate;
            drawWithFont(gr, new Font("Serif", Font.BOLD, (int) (0.01 * this.getWidth())), () -> {
                drawString(gr, screenConverter, Integer.toString(finalI), new RealPoint(finalPlusCoordinate - 0.02, -0.18));
                drawString(gr, screenConverter, Integer.toString(finalI), new RealPoint(-0.12, finalPlusCoordinate - 0.09));
                drawString(gr, screenConverter, "-" + finalI, new RealPoint(finalMinusCoordinate - 0.05, -0.18));
                drawString(gr, screenConverter, "-" + finalI, new RealPoint(-0.15, finalMinusCoordinate - 0.09));
            });
        }
    }*/
}
