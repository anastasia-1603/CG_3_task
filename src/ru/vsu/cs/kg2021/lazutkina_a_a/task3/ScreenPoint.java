package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

public class ScreenPoint
{
    private final int column;
    private final int row;

    public ScreenPoint(int column, int row)
    {
        this.column = column;
        this.row = row;
    }

    public int getColumn()
    {
        return column;
    }

    public int getRow()
    {
        return row;
    }
}
