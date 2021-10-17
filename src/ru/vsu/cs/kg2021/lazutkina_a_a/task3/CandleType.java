package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import java.awt.*;

public enum CandleType
{
    BLACK(1, Color.BLACK),
    WHITE(2, Color.WHITE);

    public final int type;
    public final Color color;

    private CandleType(int type, Color color)
    {
        this.type = type;
        this.color = color;
    }
}
