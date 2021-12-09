package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DataService;

public class DataDouble
{
    private double[][] data;

    public DataDouble(String filename)
    {
        DataService ds = new DataService();
        data = ds.readDoubleData("data/USDCB_161125_211125.txt"); //todo data
    }

    public double[][] getData()
    {
        return data;
    }
}
