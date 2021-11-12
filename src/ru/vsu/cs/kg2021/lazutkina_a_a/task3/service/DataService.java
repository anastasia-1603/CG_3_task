package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.Data;

import java.util.Arrays;

public class DataService
{
    public int[][] getMonthData(Data allData)
    {
        return Arrays.copyOfRange(allData.getAllData(), 330, 360);
    }

}
