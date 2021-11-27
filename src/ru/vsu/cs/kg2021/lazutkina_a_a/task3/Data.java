package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DataService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.copyOfRange;


public class Data
{
    private int[][] data = new int[360][4];
   // private final Map<Date, Double>

    private Map<Date, Integer[]> dataMap;
    public Data(int min, int max)
    {
        DataService ds = new DataService();
        //dataMap = ds.dataToIntegerMap();
        data = ds.fillData(data, min, max);
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
