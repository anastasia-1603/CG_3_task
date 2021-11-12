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
    private JButton buttonHour;
    private JButton buttonDay;
    private JPanel panelButtons1;
    private JPanel panelButtons2;


    public MainFrame() throws HeadlessException
    {
        panelMain = new JPanel();
        drawPanel = new DrawPanel();
        buttonHour = new JButton("Hour");
        buttonDay = new JButton("Day");
        panelButtons1 = new JPanel();
        panelButtons2 = new JPanel();
        panelButtons1.setLayout(new BoxLayout(panelButtons1, BoxLayout.X_AXIS));
        panelButtons2.setLayout(new BoxLayout(panelButtons2, BoxLayout.X_AXIS));
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelButtons1.add(buttonHour);
        panelButtons2.add(buttonDay);
        panelMain.setSize(this.getWidth(), this.getHeight());
        addComponentsToPanel();

        this.setTitle("Candlestick chart");
        this.add(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void addComponentsToPanel()
    {
        panelMain.add(panelButtons1);
        panelMain.add(drawPanel);
        panelMain.add(panelButtons2);
    }


}
