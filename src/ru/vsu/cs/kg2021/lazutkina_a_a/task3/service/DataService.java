package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.ArrayUtil;

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
                array[i][j] = ArrayUtil.random(min, max);
            }
        }
        return array;
    }

    public int[][] selectDataByWeek(int[][] data)
    {
        int num = data.length / 7;
        int[][] newData = new int[num][4];
        for (int j = 0, i = 0; i < num * 7; i += 7)
        {
            int[][] slice = copyOfRange(data, i, i + 7);
            newData[j][0] = ArrayUtil.findMin(slice);
            newData[j][1] = slice[0][1];
            newData[j][2] = slice[slice.length-1][2];
            newData[j][3] = ArrayUtil.findMax(slice);
            j++;
        }
        return newData;
    }

    public int[][] selectDataByMonth(int[][] data)
    {
        int num = data.length / 30;
        int[][] newData = new int[num][4];
        for (int j = 0, i = 0; i < num * 30; i += 30)
        {
            int[][] slice = copyOfRange(data, i, i + 30);
            newData[j][0] = ArrayUtil.findMin(slice);
            newData[j][1] = slice[0][1];
            newData[j][2] = slice[slice.length-1][2];
            newData[j][3] = ArrayUtil.findMax(slice);
            j++;
        }
        return newData;
    }

}
