package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.Data;

import java.util.Arrays;
import java.util.Random;

import static java.util.Arrays.*;

public class DataService
{
    Random random = new Random();

    public int[][] fillData(int[][] data, int min, int max)
    {
        fillRandom(data, min, max);
        for (int[] arr : data)
        {
            Arrays.sort(arr);
            if (random.nextBoolean())
            {
                int a = arr[1];
                arr[1] = arr[2];
                arr[2] = a;
            }
        }
        return data;
    }

    private int[][] fillRandom(int[][] array, int min, int max)
    {
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {
                array[i][j] = random(min, max);
            }
        }
        return array;
    }

    public int[][] selectDataForWeek(int[][] data)
    {
        int num = data.length / 7;
        int[][] newData = new int[num][4];
        for (int j = 0, i = 0; i < num * 7; i += 7)
        {
            int[][] slice = copyOfRange(data, i, i + 7);
            newData[j][0] = findMin(slice);
            newData[j][1] = slice[0][1];
            newData[j][2] = slice[slice.length-1][2];
            newData[j][3] = findMax(slice);
            j++;
        }
        return newData;
    }

    private int findMax(int[][] array)
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

    private int findMax(int[] array)
    {
        int max = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] > max)
            {
                max = array[i];
            }
        }
        return max;
    }

    private int findMin(int[][] array)
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

    private int findMin(int[] array)
    {
        int min = array[0];
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] < min)
            {
                min = array[i];
            }
        }
        return min;
    }

    private int random(int min, int max)
    {
        return random.nextInt(max - min + 1) + min;
    }
}
