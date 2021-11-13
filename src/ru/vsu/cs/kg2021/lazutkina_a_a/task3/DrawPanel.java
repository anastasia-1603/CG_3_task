package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

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
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 200;
    private static final Data DATA = new Data(MIN_VALUE, MAX_VALUE);
    private static final DrawService DRAW_SERVICE = new DrawService();
    private static final DataService DATA_SERVICE = new DataService();

    public static final int[][] WEEK_DATA = DATA.getWeekData();
    public static final int[][] MONTH_DATA = DATA.getMonthData();
    public static final int[][] YEAR_DATA = DATA.getData();

    private ScreenConverter sc;
    private CoordinatePlane coordinatePlane;
    private int[][] currData;

    public DrawPanel()
    {
        this.currData = DATA.getMonthData();
        sc = new ScreenConverter(0, MAX_VALUE, currData.length + 1, MAX_VALUE,
                this.getWidth(), this.getHeight());
        coordinatePlane = new CoordinatePlane(sc, MAX_VALUE, currData.length + 1);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }

    public int[][] getCurrData()
    {
        return currData;
    }

    public void setCurrData(int[][] currData)
    {
        this.currData = currData;
        repaint();
    }

    public void setDataForWeek()
    {
        setCurrData(DATA_SERVICE.selectDataForWeek(currData));
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

        DRAW_SERVICE.drawDiagram(currData, sc, g);
        origG.drawImage(bi, 0, 0, null);
        g.dispose();
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
        if (e.getClickCount() == 2 && !e.isConsumed() && SwingUtilities.isLeftMouseButton(e)) {
            e.consume();
            sc.setCx(0);
            sc.setCy(MAX_VALUE);
            sc.setRealWidth(currData.length + 1);
            sc.setRealHeight(MAX_VALUE);
            sc.setScreenWidth(this.getWidth());
            sc.setScreenHeight(this.getHeight());
            repaint();
        }
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
