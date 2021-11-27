package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DataService;

import java.util.*;

import static java.util.Arrays.copyOfRange;


public class Data
{
    private int[][] data = new int[360][4];

    private TreeMap<Date, Double[]> doubleDataMap;

    private TreeMap<Date, Integer[]> dataMap;
    public Data(int min, int max)
    {
        DataService ds = new DataService();
        data = ds.fillData(data, min, max);
    }

    public Data()
    {
        DataService ds = new DataService();
       // dataMap = ds.dataToIntegerMap("data/USDCB_161125_211125.txt");
        doubleDataMap = ds.dataToDoubleMap("data/USDCB_161125_211125.txt");
    }

    public TreeMap<Date, Integer[]> getDataMap()
    {
        return dataMap;
    }

    public void setDataMap(TreeMap<Date, Integer[]> dataMap)
    {
        this.dataMap = dataMap;
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
