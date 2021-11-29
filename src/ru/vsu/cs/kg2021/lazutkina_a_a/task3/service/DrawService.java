package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Line;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Rectangle;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.DateUtils;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DrawService implements LineDrawer
{
    public void drawLine(Graphics2D g, ScreenConverter sc, Line line)
    {
        ScreenPoint p1 = sc.realToScreen(line.getPoint1());
        ScreenPoint p2 = sc.realToScreen(line.getPoint2());
        g.drawLine(p1.getColumn(), p1.getRow(), p2.getColumn(), p2.getRow());
    }

    public void drawRect(Graphics2D g, ScreenConverter sc, Rectangle rect)
    {
        ScreenPoint p1 = sc.realToScreen(rect.getLeftUpperPoint());
        ScreenPoint p2 = sc.realToScreen(rect.getRightLowerPoint());
        g.drawRect(p1.getColumn(), p1.getRow(),
                Math.abs(p2.getColumn() - p1.getColumn()), Math.abs(p2.getRow() - p1.getRow()));
    }

    public void fillRect(Graphics2D g, ScreenConverter sc, Rectangle rect)
    {
        ScreenPoint p1 = sc.realToScreen(rect.getLeftUpperPoint());
        ScreenPoint p2 = sc.realToScreen(rect.getRightLowerPoint());
        g.fillRect(p1.getColumn(), p1.getRow(),
                Math.abs(p2.getColumn() - p1.getColumn()), Math.abs(p2.getRow() - p1.getRow()));
    }

    public void drawCandle(Graphics2D g, ScreenConverter sc, Candle candle)
    {
        // Date date = candle.getDate();
        int high = candle.getHigh();
        int up = candle.getUp();
        int low = candle.getLow();
        int bottom = candle.getBottom();
        int interval = candle.getWidth();
        int time = candle.getTime();

        Line upperShadow = new Line(new RealPoint(time, high), new RealPoint(time, up));
        Rectangle realBody = new Rectangle(new RealPoint(time - interval * 0.4, up),
                new RealPoint(time + interval * 0.4, bottom));
        Line lowerShadow = new Line(new RealPoint(time, bottom), new RealPoint(time, low));

        drawLine(g, sc,upperShadow);
        Color oldC = g.getColor();
        g.setColor(candle.getType().color);
        fillRect(g, sc, realBody);
        g.setColor(Color.BLACK);
        drawRect(g, sc, realBody);
        g.setColor(oldC);
        drawLine(g, sc, lowerShadow);
    }

    /*public void drawCandle(Graphics2D g, ScreenConverter sc, Candle candle, int x)
    {
        int high = candle.getHigh();
        int up = candle.getUp();
        int low = candle.getLow();
        int bottom = candle.getBottom();
        int interval = candle.getWidth();

        Line upperShadow = new Line(new RealPoint(x, high), new RealPoint(x, up));
        Rectangle realBody = new Rectangle(new RealPoint(x - interval * 0.4, up),
                new RealPoint(x + interval * 0.4, bottom));
        Line lowerShadow = new Line(new RealPoint(x, bottom), new RealPoint(x, low));

        drawLine(g, sc,upperShadow);
        Color oldC = g.getColor();
        g.setColor(candle.getType().color);
        fillRect(g, sc, realBody);
        g.setColor(Color.BLACK);
        drawRect(g, sc, realBody);
        g.setColor(oldC);
        drawLine(g, sc, lowerShadow);
    }*/

    public void drawDiagram(int[][] data, ScreenConverter sc, Graphics2D g)
    {
        int num = data.length;
        for (int i = 0; i < num; i++)
        {
            Candle candle = new Candle(data[i][0], data[i][1],
                    data[i][2], data[i][3], i+1, 1);
            drawCandle(g, sc, candle);

            //drawDashInScreen(g, sc, new RealPoint(candle.getTime(), 0), 3, true);
        }
    }

   public void drawCoordinatePlane(Graphics2D g, int zeroX, int zeroY, int width, int height)
    {
        BasicStroke oldStroke = (BasicStroke) g.getStroke();
        g.setStroke(new BasicStroke(3));
        g.drawLine(zeroX, zeroY, width, zeroY);
        g.drawLine(zeroX, zeroY, zeroX, -height);
        g.setStroke(oldStroke);
    }

    private void drawDashInScreen(Graphics2D g,
                                  ScreenConverter sc,
                                  RealPoint point, int height, boolean vertical)
    {
        ScreenPoint zeroPoint = sc.realToScreen(point);
        zeroPoint.setColumn((int)sc.getCx());
        ScreenPoint p2Dash = vertical ?
                new ScreenPoint(zeroPoint.getColumn(), zeroPoint.getRow()+ height) :
                new ScreenPoint(zeroPoint.getColumn() + height, zeroPoint.getRow());
        g.drawLine(zeroPoint.getColumn(), zeroPoint.getRow(), p2Dash.getColumn(), p2Dash.getRow());

    }

      private void drawDash(Graphics2D g, ScreenConverter sc, int height, double x, double y, boolean vertical)
    {
        RealPoint p1Dash = new RealPoint(x, y);
        RealPoint p2Dash = vertical ?
                new RealPoint(p1Dash.getX(), y + height) :
                new RealPoint(x + height, p1Dash.getY());
        Line dash = new Line(p1Dash, p2Dash);
        drawLine(g, sc, dash);
    }

    /*for (int i = (int) startX; i < finalX; i += interval)
        {
            drawDash(g, startX + i, axisX.getPoint1().getY(), true);
            DS.drawString(g, sc, String.valueOf(i), new RealPoint(i, axisX.getPoint1().getY() + 2));
        }*/
}
