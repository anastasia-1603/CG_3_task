package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.Candlestick;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DataService;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DrawService;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener
{
    private static final Data DATA = new Data();
    private static final DrawService DRAW_SERVICE = new DrawService();
    public static final DataService DATA_SERVICE = new DataService();

    private ScreenConverter sc;
    private CoordinatePlane coordinatePlane;
    private Data currData;

    public DrawPanel()
    {
        
        sc = new ScreenConverter(-0.02, 1, 30, 130, this.getWidth(), this.getHeight());
        coordinatePlane = new CoordinatePlane(30, 130, sc);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }

    public Data getCurrData()
    {
        return currData;
    }

    public void setCurrData(Data currData)
    {
        this.currData = currData;
    }

    @Override
    protected void paintComponent(Graphics origG)
    {
        sc.setScreenWidth(getWidth());
        sc.setScreenHeight(getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        coordinatePlane.draw(g);
        int[][] data = DATA_SERVICE.getMonthData(DATA);
        drawDiagram(data, g);
        origG.drawImage(bi, 0, 0, null);
        g.dispose();
    }


    private void drawDiagram(int[][] data, Graphics2D g) // num весь промежуток
    {
        int num = data.length;

        for (int i = 0; i < num; i++)
        {
            Candlestick candlestick = new Candlestick(data[i][0], data[i][1],
                    data[i][2], data[i][3], i+1, 1, sc);
            DRAW_SERVICE.drawCandle(g, sc, candlestick);
        }
    }

    private static boolean closeToLine(ScreenConverter sc, Line l, ScreenPoint p, int eps)
    {
        return distanceToLine(sc.realToScreen(l.getPoint1()), sc.realToScreen(l.getPoint2()), p) < eps;
    }

    private static Line findLine(ScreenConverter sc, List<Line> lines, ScreenPoint searchPoint, int eps)
    {
        for (Line l : lines)
        {
            if (closeToLine(sc, l, searchPoint, eps))
            {
                return l;
            }
        }
        return null;
    }

    private static double distanceToLine(ScreenPoint lp1, ScreenPoint lp2, ScreenPoint cp)
    {
        double a = lp2.getRow() - lp1.getRow();
        double b = -(lp2.getColumn() - lp1.getColumn());
        double e = - cp.getColumn() * b + cp.getRow();
        double f = a * lp1.getColumn() - b * lp1.getRow();
        double y = (a * e - b * f) / (a * a + b * b);
        double x = (a * y - e) / b;
        return Math.sqrt((cp.getColumn() - x) * (cp.getColumn() - x)
                + (cp.getRow() - y) * (cp.getRow() - y));
        
    }


    @Override
    public void mouseClicked(MouseEvent e)
    {
        //System.out.println(sc.getCx() + " " + sc.getCy());
    }

    private ScreenPoint prevPoint = null;
    private RealPoint p1 = null;
    @Override
    public void mousePressed(MouseEvent e)
    {
        if (SwingUtilities.isLeftMouseButton(e))
        {
            prevPoint = new ScreenPoint(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (SwingUtilities.isLeftMouseButton(e))
        {
            prevPoint = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (SwingUtilities.isLeftMouseButton(e))
        {
            ScreenPoint currPoint = new ScreenPoint(e.getX(), e.getY());
            RealPoint p1 = sc.screenToReal(currPoint);
            RealPoint p2 = sc.screenToReal(prevPoint);
            RealPoint delta = p2.minus(p1);
            sc.moveCorner(delta);
            prevPoint = currPoint;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

    private static final double SCALE_STEP = 0.1;
    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        int clicks = e.getWheelRotation();
        double scale = 1;
        double coef = 1 + SCALE_STEP * (clicks < 0 ? -1 : 1);
        for (int i = Math.abs(clicks); i > 0; i--)
        {
            scale *= coef;
        }
        sc.changeScale(scale);
        repaint();
    }
}
