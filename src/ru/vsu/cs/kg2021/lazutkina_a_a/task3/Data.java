package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import java.util.Random;

public class Data
{
    private final int[][] data = new int[360][4];

    public Data(int min, int max)
    {
        fillRandom(data, min, max);
    }

    public int[][] getData()
    {
        return data;
    }

    private void fillRandom(int[][] array, int min, int max)
    {
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {
                array[i][j] = random(min, max);
            }
        }
    }

    private int random(int min, int max)
    {
        Random rnd = new Random();
        return rnd.nextInt(max - min + 1) + min;
    }
}
