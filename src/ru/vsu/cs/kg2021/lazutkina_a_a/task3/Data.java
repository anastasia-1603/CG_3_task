package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import java.util.Arrays;
import java.util.Random;

public class Data
{
    private final int[][] allData = new int[360][4];

    public Data()
    {
        fillRandom(allData);
    }

    public int[][] getAllData()
    {
        return allData;
    }

    private void fillRandom(int[][] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {
                array[i][j] = random(0, 130);
            }
        }
    }

    private int random(int min, int max)
    {
        Random rnd = new Random();
        return rnd.nextInt(max - min + 1) + min;
    }
}
