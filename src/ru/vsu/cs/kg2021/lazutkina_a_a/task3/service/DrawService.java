package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Line;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Rectangle;

import java.awt.*;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

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

        Color oldC = g.getColor();
        if (candle.getType().color == Color.WHITE)
        {
            g.setColor(Color.BLACK);
            drawLine(g, sc, upperShadow);
            drawRect(g, sc, realBody);
        }
        else
        {
            g.setColor(candle.getType().color);
            drawLine(g, sc, upperShadow);
            fillRect(g, sc, realBody);
        }
        drawLine(g, sc, lowerShadow);
        g.setColor(oldC);

    }

    public void drawCandle(Graphics2D g, ScreenConverter sc, CandleDouble candle, int width, int place)
    {
        double high = candle.getHigh();
        double up = candle.getUp();
        double low = candle.getLow();
        double bottom = candle.getBottom();


        Line upperShadow = new Line(new RealPoint(place, high), new RealPoint(place, up));
        Rectangle realBody = new Rectangle(new RealPoint(place - width * 0.4, up),
                new RealPoint(place + width * 0.4, bottom));
        Line lowerShadow = new Line(new RealPoint(place, bottom), new RealPoint(place, low));

        Color oldC = g.getColor();
        if (candle.getType().color == Color.WHITE)
        {
            g.setColor(Color.BLACK);
            drawLine(g, sc, upperShadow);
            drawRect(g, sc, realBody);
        }
        else
        {
            g.setColor(candle.getType().color);
            drawLine(g, sc, upperShadow);
            fillRect(g, sc, realBody);
        }
        drawLine(g, sc, lowerShadow);
        g.setColor(oldC);

    }

    public void drawDiagram(int[][] data, ScreenConverter sc, Graphics2D g)
    {
        int num = data.length;
        for (int i = 0; i < num; i++)
        {
            Candle candle = new Candle(data[i][0], data[i][1],
                    data[i][2], data[i][3], i+1, 1);
            drawCandle(g, sc, candle);
            drawDashX(g, sc, 3, candle.getTime(), 0);
            drawString(g, sc, candle.getTime()-1, 5,  String.valueOf(candle.getTime() - 1));

            //drawDashY(g, sc, 4, 2, sc.getScreenHeight()-candle.getLow());
        }
    }

    public void drawDiagram(List<CandleDouble> candles, ScreenConverter sc, Graphics2D g)
    {
        int num = candles.size();
        int width = 1;
        int i = 0;
        for (CandleDouble candle : candles)
        {
            drawCandle(g, sc, candle, width, i++ + width);
        }
    }

   public void drawCoordinatePlane(Graphics2D g, int x, int y, int width, int height)
    {
        BasicStroke oldStroke = (BasicStroke) g.getStroke();
        g.setStroke(new BasicStroke(3));
        g.drawLine(x, y, width, y);
        g.drawLine(x, y, x, -height);
        g.setStroke(oldStroke);
    }

    private void drawDashX(Graphics2D g, ScreenConverter sc, int height, double x, double y)
    {
        RealPoint p1Dash = new RealPoint(x, y);
        RealPoint p2Dash = new RealPoint(p1Dash.getX(), y + height);
        Line dash = new Line(p1Dash, p2Dash);
        drawLine(g, sc, dash);
    }

  /*  private void drawDashY(Graphics2D g, ScreenConverter sc, int height, int x, int y)
    {
        RealPoint p = new RealPoint(x, y);
        ScreenPoint sP = sc.realToScreen(p);
        g.drawLine(sP.getColumn(), sP.getRow(), sP.getColumn() + height, sP.getRow());
    }*/

    private void drawString(Graphics2D g, ScreenConverter sc, double x, double y, String text)
    {
        RealPoint p = new RealPoint(x, y);
        ScreenPoint sP = sc.realToScreen(p);
        g.drawString(text, sP.getColumn(), sP.getRow());
    }
}
