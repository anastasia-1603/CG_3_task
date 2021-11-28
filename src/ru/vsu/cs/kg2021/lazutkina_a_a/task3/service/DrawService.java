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

    public void drawCandle(Graphics2D g, ScreenConverter sc, Candlestick candle)
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

    public void drawCandle(Graphics2D g, ScreenConverter sc, Candlestick candle, int x)
    {
       // Date date = candle.getDate();
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

    public void drawDiagram(int[][] data, ScreenConverter sc, Graphics2D g)
    {
        int num = data.length;

        for (int i = 0; i < num; i++)
        {
            Candlestick candlestick = new Candlestick(data[i][0], data[i][1],
                    data[i][2], data[i][3], i+1, 1, sc);
            drawCandle(g, sc, candlestick);
        }
    }

    public void drawCoordinatePlane(Graphics2D g, int zeroX, int zeroY, int width, int height, TreeMap<GregorianCalendar, Integer[]> dataMap)
    {
        BasicStroke oldStroke = (BasicStroke) g.getStroke();
        g.setStroke(new BasicStroke(3));
        g.drawLine(zeroX, zeroY, width, zeroY);
        g.drawLine(zeroX, zeroY, zeroX, -height); //todo понять что за костыль, почему -

        drawDates(g, zeroX, zeroY, width, (SortedSet<GregorianCalendar>) dataMap.keySet());

        g.setStroke(oldStroke);
    }

    private void drawDates(Graphics2D g, int x, int y, int width, SortedSet<GregorianCalendar> dateSet)
    {
        ScreenPoint p1Dash = new ScreenPoint(x, y);
        ScreenPoint p2Dash = new ScreenPoint(p1Dash.getColumn(), y - 3);
        int interval;
        int years = DateUtils.calculateNumberOfYears(dateSet);
        int month = DateUtils.calculateNumberOfMonth(dateSet);
        int weeks = DateUtils.calculateNumberOfWeeks(dateSet);
        List<Integer> calendars = new ArrayList<>();
        if (years > 1)
        {
            interval = width / years;
            calendars = DateUtils.findYears(dateSet);
        }
        else if (month > 6)
        {
            interval = width / month;
        }
        else if (weeks > 2)
        {
            interval = width / weeks;
        }
        else
        {
            interval = width / dateSet.size();
        }
        int i = interval;
       /* for (int k = interval; k < width; k += interval)
        {
            g.drawLine(p1Dash.getColumn() + i, p1Dash.getRow(), p1Dash.getColumn() + i, p2Dash.getRow());
            g.drawString(DateUtils.calendarToString(c), p1Dash.getColumn() + i, p2Dash.getRow());
        }*/
        for (Integer integer : calendars)
        {
            g.drawLine(p1Dash.getColumn() + i, p1Dash.getRow(), p1Dash.getColumn() + i, p2Dash.getRow());
            g.drawString(String.valueOf(integer), p1Dash.getColumn() + i, p2Dash.getRow());
            i += interval;
        }

/*        for (GregorianCalendar c : dateSet)
        {
            g.drawLine(p1Dash.getColumn() + i, p1Dash.getRow(), p1Dash.getColumn() + i, p2Dash.getRow());
            g.drawString(DateUtils.calendarToString(c), p1Dash.getColumn() + i, p2Dash.getRow());
            i += interval;
        }*/
    }

    private void drawDashInScreen(Graphics2D g, int x, int y, boolean vertical)
    {
        ScreenPoint p1Dash = new ScreenPoint(x, y);
        ScreenPoint p2Dash = vertical ?
                new ScreenPoint(p1Dash.getColumn(), y - 3) :
                new ScreenPoint(x + 3, p1Dash.getRow());
        g.drawLine(p1Dash.getColumn(), p1Dash.getRow(), p2Dash.getColumn(), p2Dash.getRow());
    }



    /*public void drawDiagram(TreeMap<Date, Integer[]> dataMap, ScreenConverter sc, Graphics2D g)
    {
        int i = 1;
        for (Date date : dataMap.keySet())
        {
            Candlestick candlestick = new Candlestick(date, dataMap.get(date)[0],
                    dataMap.get(date)[1], dataMap.get(date)[2], dataMap.get(date)[3], 1);
            drawCandle(g, sc, candlestick, i++);
        }
    }*/

    public void drawDiagram(TreeMap<GregorianCalendar, Integer[]> dataMap, ScreenConverter sc, Graphics2D g)
    {
        int i = 1;
        for (Map.Entry<GregorianCalendar, Integer[]> entry : dataMap.entrySet())
        {
            Candlestick candlestick = new Candlestick(entry.getKey(), entry.getValue()[0],
                    entry.getValue()[1], entry.getValue()[2], entry.getValue()[3], 1);
            drawCandle(g, sc, candlestick, i++);
        }
    }

    public void drawString(Graphics2D gr, ScreenConverter sc, String text, RealPoint realPoint)
    {
        ScreenPoint point = sc.realToScreen(realPoint);
        gr.drawString(text, point.getColumn(), point.getRow());
    }
}
