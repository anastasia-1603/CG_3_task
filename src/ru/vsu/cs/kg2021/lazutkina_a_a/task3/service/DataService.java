package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.ArrayUtil;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.DateUtils;

import java.text.ParseException;
import java.util.*;

import static java.util.Arrays.copyOfRange;

public class DataService
{
    Random random = new Random();


    public List<Integer[]> readIntegerData(String filename)
    {
        return ArrayUtil.readListIntegerArraysFromFile(filename);
    }

    public Map<Date, Double[]> dataToMap()
    {
        try
        {
            return dataToMap(Objects.requireNonNull(
                    ArrayUtil.toString2DArray("data/USDCB_161125_211125.txt")));
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    public Map<Date, Integer[]> dataToIntegerMap()
    {
        try
        {
            return dataToIntegerMap(Objects.requireNonNull(
                    ArrayUtil.toString2DArray("data/USDCB_161125_211125.txt")));
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    public Map<Date, int[]> dataToIntMap()
    {
        try
        {
            return dataToIntMap(Objects.requireNonNull(
                    ArrayUtil.toString2DArray("data/USDCB_161125_211125.txt")));
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    private Map<Date, Integer[]> dataToIntegerMap(String[][] lines) throws ParseException //todo rename
    {
        Map<Date, Integer[]> dataMap = new TreeMap<>();
        for (String[] s : lines)
        {
            String[] data = copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]), ArrayUtil.toIntegerArray(data));
        }
        return dataMap;
    }

    private Map<Date, int[]> dataToIntMap(String[][] lines) throws ParseException //todo rename и что то сделать с нагромождением методов
    {
        Map<Date, int[]> dataMap = new TreeMap<>();
        for (String[] s : lines)
        {
            String[] data = copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]), ArrayUtil.castDoubleArrayToInt(ArrayUtil.arrayStringToDouble(data)));
        }
        return dataMap;
    }

    private Map<Date, Double[]> dataToMap(String[][] lines) throws ParseException //todo rename и сделать метод где в параметрах имя файла
    {
        Map<Date, Double[]> dataMap = new TreeMap<>();
        for (String[] s : lines)
        {
            String[] data = copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]), ArrayUtil.arrayStringToDouble(data));
        }
        return dataMap;
    }

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

    public List<Integer[]> fillDataList(List<Integer[]> dataList, String filename)
    {
        return null;
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
            newData[j][2] = slice[slice.length - 1][2];
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
            newData[j][2] = slice[slice.length - 1][2];
            newData[j][3] = ArrayUtil.findMax(slice);
            j++;
        }
        return newData;
    }

}
