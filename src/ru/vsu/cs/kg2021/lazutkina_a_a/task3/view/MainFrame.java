package ru.vsu.cs.kg2021.lazutkina_a_a.task3.view;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.view.status.Period;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.view.status.Time;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private JPanel panelMain;
    private DrawPanelDouble drawPanel;
    private JButton buttonDay;
    private JButton buttonWeek;
    private JButton buttonMonth;

    /*private JButton buttonWeekPeriod;
    private JButton buttonMonthPeriod;
    private JButton buttonYearPeriod;*/
    private JPanel panelButtonsTime;
    //private JPanel panelButtonsPeriod;

    public MainFrame() throws HeadlessException
    {
        panelMain = new JPanel();
        drawPanel = new DrawPanelDouble();
        drawPanel.setSize(800, 600);
        //initButtons();
        //initPanelButtonsTime();
        //initPanelButtonsPeriod();

        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setSize(this.getWidth(), this.getHeight());
        addComponentsToPanel();
        //addListeners();
        //changeButtons();

        this.setTitle("Candlestick chart");
        this.add(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initButtons()
    {
        buttonDay = new JButton("Day");
        buttonWeek = new JButton("Week");
        buttonMonth = new JButton("Month");

       /* buttonWeekPeriod = new JButton("Week");
        buttonMonthPeriod = new JButton("Month");
        buttonYearPeriod = new JButton("Year");*/
    }

    private void addListeners()
    {
        /*buttonWeekPeriod.addActionListener(e ->
        {
            drawPanel.setPeriod(Period.WEEK);
            changeView();
        });

        buttonMonthPeriod.addActionListener(e ->
        {
            drawPanel.setPeriod(Period.MONTH);
            changeView();
        });

        buttonYearPeriod.addActionListener(e ->
        {
            drawPanel.setPeriod(Period.YEAR);
            changeView();
        });

        buttonDay.addActionListener(e ->
        {
            drawPanel.setTime(Time.DAY);
            changeView();
        });

        buttonWeek.addActionListener(e ->
        {
            drawPanel.setTime(Time.WEEK);
            changeView();
        });

        buttonMonth.addActionListener(e ->
        {
            drawPanel.setTime(Time.MONTH);
            changeView();
        });*/
    }

    /*private void changeView()
    {
        changeButtons();
        //drawPanel.changeCoordinatePlaneWidth();
    }

    private void changeButtons()
    {
        changeStatusOnSwitching();
        changeEnabledButtons();
        //setColorPressedButtonsPeriod();
        setColorPressedButtonsTime();
        changeEnabledButtonsTime();
    }

    private void changeEnabledButtonsTime()
    {
        switch (drawPanel.getTime())
        {
            case DAY -> buttonDay.setEnabled(false);
            case WEEK -> buttonWeek.setEnabled(false);
            case MONTH -> buttonMonth.setEnabled(false);
        }
    }*/

    /*private void changeStatusOnSwitching()
    {
        if ((drawPanel.getPeriod() == Period.WEEK && drawPanel.getTime() != Time.DAY) ||
                (drawPanel.getPeriod() == Period.MONTH && drawPanel.getTime() == Time.MONTH))
        {
            drawPanel.setTime(Time.DAY);
        }
    }*/

    /*private void changeEnabledButtons()
    {
        switch (drawPanel.getPeriod())
        {
            case YEAR ->
                    {
                        buttonDay.setEnabled(true);
                        buttonWeek.setEnabled(true);
                        buttonMonth.setEnabled(true);
                        buttonWeekPeriod.setEnabled(true);
                        buttonMonthPeriod.setEnabled(true);
                        buttonYearPeriod.setEnabled(false);
                    }
            case MONTH ->
                    {
                        buttonDay.setEnabled(true);
                        buttonWeek.setEnabled(true);
                        buttonMonth.setEnabled(false);
                        buttonWeekPeriod.setEnabled(true);
                        buttonMonthPeriod.setEnabled(false);
                        buttonYearPeriod.setEnabled(true);
                    }
            case WEEK ->
                    {
                        buttonDay.setEnabled(true);
                        buttonWeek.setEnabled(false);
                        buttonMonth.setEnabled(false);
                        buttonWeekPeriod.setEnabled(false);
                        buttonMonthPeriod.setEnabled(true);
                        buttonYearPeriod.setEnabled(true);
                    }
        }
    }*/

   /* private void setColorPressedButtonsPeriod()
    {
        switch (drawPanel.getPeriod())
        {
            case WEEK ->
                    {
                        buttonWeekPeriod.setBackground(Color.BLUE);
                        buttonMonthPeriod.setBackground(null);
                        buttonYearPeriod.setBackground(null);
                    }
            case MONTH ->
                    {
                        buttonWeekPeriod.setBackground(null);
                        buttonMonthPeriod.setBackground(Color.BLUE);
                        buttonYearPeriod.setBackground(null);
                    }
            case YEAR ->
                    {
                        buttonWeekPeriod.setBackground(null);
                        buttonMonthPeriod.setBackground(null);
                        buttonYearPeriod.setBackground(Color.BLUE);
                    }
        }
    }*/

    /*private void setColorPressedButtonsTime()
    {
        switch (drawPanel.getTime())
        {
            case DAY ->
                    {
                        buttonDay.setBackground(Color.BLUE);
                        buttonWeek.setBackground(null);
                        buttonMonth.setBackground(null);
                    }
            case WEEK ->
                    {
                        buttonDay.setBackground(null);
                        buttonWeek.setBackground(Color.BLUE);
                        buttonMonth.setBackground(null);
                    }
            case MONTH ->
                    {
                        buttonDay.setBackground(null);
                        buttonWeek.setBackground(null);
                        buttonMonth.setBackground(Color.BLUE);
                    }
        }
    }
*/
    private void initPanelButtonsTime()
    {
        panelButtonsTime = new JPanel();
        panelButtonsTime.setLayout(new BoxLayout(panelButtonsTime, BoxLayout.X_AXIS));
        panelButtonsTime.add(buttonDay);
        panelButtonsTime.add(buttonWeek);
        panelButtonsTime.add(buttonMonth);
    }

   /* private void initPanelButtonsPeriod()
    {
        panelButtonsPeriod = new JPanel();
        panelButtonsPeriod.setLayout(new BoxLayout(panelButtonsPeriod, BoxLayout.X_AXIS));
        panelButtonsPeriod.add(buttonWeekPeriod);
        panelButtonsPeriod.add(buttonMonthPeriod);
        panelButtonsPeriod.add(buttonYearPeriod);
    }*/

    private void addComponentsToPanel()
    {
        //panelMain.add(panelButtonsTime);
        panelMain.add(drawPanel);
        //panelMain.add(panelButtonsPeriod);
    }
}
