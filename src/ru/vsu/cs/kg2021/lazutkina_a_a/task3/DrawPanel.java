package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel
{
    private ScreenConverter sc;
    private Line axisX, axisY;

    public DrawPanel()
    {
        sc = new ScreenConverter(-2, 2, 4, 4, 800, 600);
        axisX = new Line(new RealPoint(-1, 0), new RealPoint(1, 0));
        axisY = new Line(new RealPoint(0, -1), new RealPoint(0, 1));

        /*this.addMouseMotionListener(new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {

            }

            @Override
            public void mouseMoved(MouseEvent e)
            {
                x = e.getX();
                y = e.getY();
                repaint();
            }
        });*/
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
        drawLine(g, sc, axisX);
        drawLine(g, sc, axisY);
        origG.drawImage(bi, 0, 0, null);
        g.dispose();
    }

    private static void drawLine(Graphics2D g, ScreenConverter sc, Line line)
    {
        ScreenPoint p1 = sc.realToScreen(line.getPoint1());
        ScreenPoint p2 = sc.realToScreen(line.getPoint2());
        g.drawLine(p1.getColumn(), p1.getRow(), p2.getColumn(), p2.getRow());
    }
}
