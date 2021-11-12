package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.CandleType;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.Candlestick;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Line;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Rectangle;

import java.awt.*;

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

    public void drawCandle(Graphics2D g, ScreenConverter sc, Candlestick candle)
    {
        int time = candle.getTime();
        int high = candle.getHigh();
        int up = candle.getUp();
        int low = candle.getLow();
        int bottom = candle.getBottom();
        int interval = candle.getWidth();

        Line upperShadow = new Line(new RealPoint(time, high), new RealPoint(time, up));
        Rectangle realBody = new Rectangle(new RealPoint(time - interval * 0.4, up),
                new RealPoint(time + interval * 0.4, bottom));
        Line lowerShadow = new Line(new RealPoint(time, bottom), new RealPoint(time, low));

        drawLine(g, sc,upperShadow);
        if (candle.getType() == CandleType.INCREASING)
        {
            drawRect(g, sc, realBody);
        }
        else
        {
            fillRect(g, sc, realBody);
        }
        drawLine(g, sc, lowerShadow);
    }
}
