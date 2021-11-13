package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DataService;

import static java.util.Arrays.copyOfRange;


public class Data
{
    private final int[][] data = new int[360][4];
    private final DataService DATA_SERVICE = new DataService();

    public Data(int min, int max)
    {
        DATA_SERVICE.fillData(data, min, max);
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
