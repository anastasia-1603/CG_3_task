package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.Data;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.*;

public class DataService
{
    public int[][] getMonthData(Data allData)
    {
        return copyOfRange(allData.getData(), 330, 360);
    }

}
