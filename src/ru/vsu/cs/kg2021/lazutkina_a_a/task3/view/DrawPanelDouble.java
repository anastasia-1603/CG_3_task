package ru.vsu.cs.kg2021.lazutkina_a_a.task3.view;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.*;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.CandleDouble;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DataService;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DrawService;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.ArrayUtil;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.view.status.Period;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.view.status.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.GregorianCalendar;
import java.util.List;

public class DrawPanelDouble extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private static final String FILENAME = "data/data.txt";
    private List<GregorianCalendar> dates;
    private List<CandleDouble> candles;
    private static final DrawService DRAW_SERVICE = new DrawService();
    private static final DataService DATA_SERVICE = new DataService();

    //private Period period;
    private Time time;

    private ScreenConverter sc;

    private List<CandleDouble> currData;

    private double max;
    private double min;

    public DrawPanelDouble(int width, int height)
    {
        this.setSize(width, height);
        dates = DATA_SERVICE.readListOfDates(FILENAME, "dd.MM.yyyy", 5);
        candles = DATA_SERVICE.readListCandles(FILENAME, 0, 4);

        setDefaultView();

        max = DATA_SERVICE.findMaxPrice(currData);
        min = DATA_SERVICE.findMinPrice(currData);
        sc = new ScreenConverter(0, max + 1,
                currData.size() + 1, max - min + 2,
                this.getWidth(), this.getHeight());

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }

    @Override
    protected void paintComponent(Graphics origG)
    {
        sc.setScreenWidth(this.getWidth());
        sc.setScreenHeight(this.getHeight());
        //sc.setCy(max);
        //sc.setRealHeight(max - min);

        setData();

        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));

        DRAW_SERVICE.drawDiagram(currData, sc, g);
        DRAW_SERVICE.drawCoordinatePlane(g, 3, this.getHeight()-3, this.getWidth(), this.getHeight());

        origG.drawImage(bi, 0, 0, null);
        g.dispose();
    }

    private void setDefaultView()
    {
        currData = DATA_SERVICE.groupByWeek(dates, candles);
        time = Time.WEEK;
    }

    public Time getTime()
    {
        return time;
    }

    public void setTime(Time time)
    {
        this.time = time;
        setData();
        sc.setCx(0);
        sc.setRealWidth(currData.size() + 1);
        repaint();
    }

    public void setCurrView()
    {
        double realRightestX = sc.screenXtoRealX(sc.getScreenWidth());
        double realLeftX = sc.screenXtoRealX(0);
        if (realRightestX < currData.size() && realLeftX > 0)
        {
            currData = currData.subList((int)realLeftX, (int)realRightestX-1);
            max = DATA_SERVICE.findMaxPrice(currData);
            //min = DATA_SERVICE.findMinPrice(currData);
        }
    }


    public void setData()
    {
        switch (time)
        {
            case DAY -> groupDataByDay();
            case WEEK -> groupDataByWeek();
            case MONTH -> groupDataByMonth();
        }
    }


    private void groupDataByDay()
    {
        currData = candles;
    }

    private void groupDataByWeek()
    {
        currData = DATA_SERVICE.groupByWeek(dates, candles);
    }

    private void groupDataByMonth()
    {
        currData = DATA_SERVICE.groupByMonth(dates, candles);
    }
  /*  public void setDataByDay()
    {
        switch (period)
        {
            case WEEK -> setCurrData(data.getWeekData());
            case MONTH -> setCurrData(data.getMonthData());
            case YEAR -> setCurrData(data.getData());
        }
    }

    public void setDataByWeek()
    {
        switch (period)
        {
            case MONTH -> setCurrData(DATA_SERVICE.selectDataByWeek(data.getMonthData()));
            case YEAR -> setCurrData(DATA_SERVICE.selectDataByWeek(data.getData()));
        }
    }

    public void setDataByMonth()
    {
        if (period == Period.YEAR)
        {
            setCurrData(DATA_SERVICE.selectDataByMonth(data.getData()));
        }
    }
*/

    private void setDefaultScreenConverter()
    {
        sc.setCx(0);
        sc.setCy(max + 1);
        sc.setRealWidth(currData.size() + 1);
        sc.setRealHeight(max - min + 2);
        sc.setScreenWidth(this.getWidth());
        sc.setScreenHeight(this.getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (e.getClickCount() == 2 && !e.isConsumed() && SwingUtilities.isLeftMouseButton(e)) {
            e.consume();
            setDefaultScreenConverter();
            repaint();
        }
    }

    private ScreenPoint prevPoint = null;

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
            if (sc.getCx() + delta.getX() >= 0)
            {
                sc.moveCornerX(delta);
            }
            prevPoint = currPoint;
            //setCurrView();
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
        sc.changeScaleX(scale);
        //setCurrView();
        repaint();
    }
}
