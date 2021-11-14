package ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram;

import java.awt.*;

public enum CandleType
{
    DECREASING (Color.BLACK),
    INCREASING (Color.WHITE);

    public final Color color;

    CandleType(Color color)
    {
        this.color = color;
    }
}
