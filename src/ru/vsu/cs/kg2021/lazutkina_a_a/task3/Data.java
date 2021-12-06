package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DataService;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.ArrayUtil;


import java.util.List;

import static java.util.Arrays.copyOfRange;

public class Data
{
    private int[][] data;

    private List<Integer[]> dataList;

    public List<Integer[]> getDataList()
    {
        return dataList;
    }

    /*public Data(int min, int max)
    {
        DataService ds = new DataService();
        data = ds.fillData(data, min, max);
    }*/

    public Data(String filename)
    {
        DataService ds = new DataService();
        //data = ds.fillData(data, min, max);
        dataList = ds.readIntegerData(filename);
        data = ArrayUtil.toInt2DArray(dataList);
    }


    public int[][] getData()
    {
        return data;
    }

    public int[][] getMonthData()
    {
        return copyOfRange(data, data.length - 31, data.length - 1);
    }

    public int[][] getWeekData()
    {
        return copyOfRange(data, data.length - 8, data.length - 1);
    }

}
