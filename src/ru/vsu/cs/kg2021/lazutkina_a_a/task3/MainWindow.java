package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame
{
    private final DrawPanel dp;

    public MainWindow() throws HeadlessException
    {
        this.dp = new DrawPanel();
        this.add(dp);

    }
}
