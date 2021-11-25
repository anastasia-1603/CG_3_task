package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DataService;

import java.util.Date;

import static java.util.Arrays.copyOfRange;


public class Data
{
    private final int[][] data = new int[360][4];
   // private final Map<Date, Double>

    public Data(int min, int max)
    {
        DataService ds = new DataService();

        //ds.fillData(data, min, max);
    }

    public int[][] getData()
    {
        return data;
    }

    public int[][] getMonthData()
    {
        return copyOfRange(data, 330, 360);
    }

    public int[][] getWeekData()
    {
        return copyOfRange(data, 353, 360);
    }

}
