package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Line;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Rectangle;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.DateUtils;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DrawService implements LineDrawer {
    public void drawLine(Graphics2D g, ScreenConverter sc, Line line) {
        ScreenPoint p1 = sc.realToScreen(line.getPoint1());
        ScreenPoint p2 = sc.realToScreen(line.getPoint2());
        g.drawLine(p1.getColumn(), p1.getRow(), p2.getColumn(), p2.getRow());
    }

    public void drawRect(Graphics2D g, ScreenConverter sc, Rectangle rect) {
        ScreenPoint p1 = sc.realToScreen(rect.getLeftUpperPoint());
        ScreenPoint p2 = sc.realToScreen(rect.getRightLowerPoint());
        g.drawRect(p1.getColumn(), p1.getRow(),
                Math.abs(p2.getColumn() - p1.getColumn()), Math.abs(p2.getRow() - p1.getRow()));
    }


    public void fillRect(Graphics2D g, ScreenConverter sc, Rectangle rect) {
        ScreenPoint p1 = sc.realToScreen(rect.getLeftUpperPoint());
        ScreenPoint p2 = sc.realToScreen(rect.getRightLowerPoint());
        g.fillRect(p1.getColumn(), p1.getRow(),
                Math.abs(p2.getColumn() - p1.getColumn()), Math.abs(p2.getRow() - p1.getRow()));
    }

    public void drawCandle(Graphics2D g, ScreenConverter sc, Candle candle, int width, int place) {
        double high = candle.getHigh();
        double up = candle.getUp();
        double low = candle.getLow();
        double bottom = candle.getBottom();


        Line upperShadow = new Line(new RealPoint(place, high), new RealPoint(place, up));
        Rectangle realBody = new Rectangle(new RealPoint(place - width * 0.4, up),
                new RealPoint(place + width * 0.4, bottom));
        Line lowerShadow = new Line(new RealPoint(place, bottom), new RealPoint(place, low));

        Color oldC = g.getColor();
        if (candle.getType().color == Color.WHITE) {
            g.setColor(Color.BLACK);
            drawLine(g, sc, upperShadow);
            drawRect(g, sc, realBody);
        } else {
            g.setColor(candle.getType().color);
            drawLine(g, sc, upperShadow);
            fillRect(g, sc, realBody);
        }
        drawLine(g, sc, lowerShadow);
        g.setColor(oldC);

    }

    public void drawDiagram(Data data, ScreenConverter sc, Graphics2D g) {
        int i = 0;
        for (Map.Entry<GregorianCalendar, Candle> entry: data.getMap().entrySet()) {
            i++;
            drawCandle(g, sc, entry.getValue(), 1, i);
            drawDashX(g, sc, 4, i, sc.getScreenHeight());
            drawString(g, sc, i, sc.getScreenHeight() - 4, DateUtils.toString(entry.getKey(), "dd.MM.yyyy"));
        }

        DataService ds = new DataService();
        double min = ds.findMinPrice(data.getCandles());
        double max = ds.findMaxPrice(data.getCandles());
        double interval = (max - min) / sc.getRealHeight();

        for (double d = min; d <= max; d+=interval)
        {
            drawDashY(g, sc, 4, 4, d);
            drawPrice(g, sc, 4, d, d);
        }
    }

    public void drawCoordinatePlane(Graphics2D g, int x, int y, int width, int height) {
        BasicStroke oldStroke = (BasicStroke) g.getStroke();
        g.setStroke(new BasicStroke(3));
        g.drawLine(x, y, width, y);
        g.drawLine(x, y, x, -height);
        g.setStroke(oldStroke);
    }

    private void drawDashX(Graphics2D g, ScreenConverter sc, int height, double realX, int screenY) {
        int screenX = sc.realXtoScreenX(realX);
        g.drawLine(screenX, screenY, screenX, screenY - height);
    }

    private void drawDashY(Graphics2D g, ScreenConverter sc, int height, int screenX, double realY) {
        int screenY = sc.realYtoScreenY(realY);
        g.drawLine(screenX, screenY, screenX + height, screenY);
    }

    private void drawString(Graphics2D g, ScreenConverter sc, double realX, int screenY, String text) {
        int screenX = sc.realXtoScreenX(realX);
        g.drawString(text, screenX, screenY);
    }

    private void drawPrice(Graphics2D g, ScreenConverter sc, int screenX, double realY, double price) {
        int screenY = sc.realYtoScreenY(realY);
        String text = String.format("%.2f", price);
        g.drawString(text, screenX, screenY);
    }

}
