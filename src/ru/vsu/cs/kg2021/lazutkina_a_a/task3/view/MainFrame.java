package ru.vsu.cs.kg2021.lazutkina_a_a.task3.view;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.DrawPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame
{
    private JPanel panelMain;
    private DrawPanel drawPanel;
    private JButton buttonDay;
    private JButton buttonWeek;
    private JButton buttonMonth;

    private JButton buttonWeekPeriod;
    private JButton buttonMonthPeriod;
    private JButton buttonYearPeriod;
    private JPanel panelButtonsTime;
    private JPanel panelButtonsPeriod;


    public MainFrame() throws HeadlessException
    {
        panelMain = new JPanel();
        drawPanel = new DrawPanel();
        initButtons();
        initPanelButtonsTime();
        initPanelButtonsPeriod();

        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setSize(this.getWidth(), this.getHeight());
        addComponentsToPanel();
        addListeners();

        this.setTitle("Candlestick chart");
        this.add(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initButtons()
    {
        buttonDay = new JButton("Day");
        buttonWeek = new JButton("Week");
        buttonMonth = new JButton("Month");

        buttonWeekPeriod = new JButton("Week");
        buttonMonthPeriod = new JButton("Month");
        buttonYearPeriod = new JButton("Year");
    }

    private void addListeners()
    {
        buttonWeekPeriod.addActionListener(e ->
        {
            drawPanel.setCurrData(DrawPanel.WEEK_DATA);
            buttonWeek.setEnabled(false);
            buttonMonth.setEnabled(false);
        });

        buttonMonthPeriod.addActionListener(e ->
        {
            drawPanel.setCurrData(DrawPanel.MONTH_DATA);
            buttonWeek.setEnabled(true);
            buttonMonth.setEnabled(false);
        });

        buttonYearPeriod.addActionListener(e ->
        {
            drawPanel.setCurrData(DrawPanel.YEAR_DATA);
            buttonWeek.setEnabled(true);
            buttonMonth.setEnabled(true);
        });

        buttonWeek.addActionListener(e ->
        {
            drawPanel.setDataForWeek();
        });
    }

    private void initPanelButtonsTime()
    {
        panelButtonsTime = new JPanel();
        panelButtonsTime.setLayout(new BoxLayout(panelButtonsTime, BoxLayout.X_AXIS));
        panelButtonsTime.add(buttonDay);
        panelButtonsTime.add(buttonWeek);
        panelButtonsTime.add(buttonMonth);
    }

    private void initPanelButtonsPeriod()
    {
        panelButtonsPeriod = new JPanel();
        panelButtonsPeriod.setLayout(new BoxLayout(panelButtonsPeriod, BoxLayout.X_AXIS));
        panelButtonsPeriod.add(buttonWeekPeriod);
        panelButtonsPeriod.add(buttonMonthPeriod);
        panelButtonsPeriod.add(buttonYearPeriod);
    }

    private void addComponentsToPanel()
    {
        panelMain.add(panelButtonsTime);
        panelMain.add(drawPanel);
        panelMain.add(panelButtonsPeriod);
    }


}
