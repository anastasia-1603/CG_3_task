package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Line;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Rectangle;

import java.awt.*;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

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

    public void drawCandle(Graphics2D g, ScreenConverter sc, Candlestick candle, int x)
    {
       // Date date = candle.getDate();
       // long time = date.getTime();
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
    }

    /*public void drawDiagram(int[][] data, ScreenConverter sc, Graphics2D g)
    {
        int num = data.length;

        for (int i = 0; i < num; i++)
        {
            Candlestick candlestick = new Candlestick(data[i][0], data[i][1],
                    data[i][2], data[i][3], i+1, 1, sc);
            drawCandle(g, sc, candlestick);
        }
    }*/

    public void drawDiagram(TreeMap<Date, Integer[]> dataMap, ScreenConverter sc, Graphics2D g)
    {
        int i = 1;
        for (Date date : dataMap.keySet())
        {
            Candlestick candlestick = new Candlestick(date, dataMap.get(date)[0],
                    dataMap.get(date)[1], dataMap.get(date)[2], dataMap.get(date)[3], 1);
            drawCandle(g, sc, candlestick, i++);
        }
    }

    public void drawString(Graphics2D gr, ScreenConverter sc, String text, RealPoint realPoint)
    {
        ScreenPoint point = sc.realToScreen(realPoint);
        gr.drawString(text, point.getColumn(), point.getRow());
    }
}
