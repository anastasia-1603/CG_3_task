package ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils;

import java.util.Random;

public class ArrayUtil
{
    private final static Random random = new Random();
    public static int findMax(int[][] array)
    {
        int max = 0;
        for (int[] data : array)
        {
            int localMax = findMax(data);
            if (localMax > max)
            {
                max = localMax;
            }
        }
        return max;
    }

    public static int findMax(int[] array)
    {
        int max = 0;
        for (int j : array)
        {
            if (j > max)
            {
                max = j;
            }
        }
        return max;
    }

    public static int findMin(int[][] array)
    {
        int min = array[0][0];
        for (int[] data : array)
        {
            int localMin = findMin(data);
            if (localMin < min)
            {
                min = localMin;
            }
        }
        return min;
    }

    public static int findMin(int[] array)
    {
        int min = array[0];
        for (int j : array)
        {
            if (j < min)
            {
                min = j;
            }
        }
        return min;
    }

    public static int random(int min, int max)
    {
        return random.nextInt(max - min + 1) + min;
    }
}
