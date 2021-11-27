package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.ArrayUtil;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.DateUtils;
import java.text.ParseException;
import java.util.*;

import static java.util.Arrays.copyOfRange;

public class DataService
{
    Random random = new Random();

    public TreeMap<Date, Double[]> dataToDoubleMap(String filename)
    {
        try
        {
            return textToDoubleMap(filename);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    public TreeMap<Date, Integer[]> dataToIntegerMap(String filename)
    {
        try
        {
            return dataToIntegerMap(Objects.requireNonNull(
                    ArrayUtil.toString2DArray(filename)));
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

    private TreeMap<Date, Integer[]> dataToIntegerMap(String[][] lines) throws ParseException //todo rename
    {
        TreeMap<Date, Integer[]> dataMap = new TreeMap<>();
        for (String[] s : lines)
        {
            String[] data = copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]),ArrayUtil.castDoubleArrayToInteger(ArrayUtil.arrayStringToDouble(data)));
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

    private TreeMap<Date, Double[]> textToDoubleMap(String filename) throws ParseException //todo rename и сделать метод где в параметрах имя файла
    {
        String[][] lines = ArrayUtil.toString2DArray(filename);
        TreeMap<Date, Double[]> dataMap = new TreeMap<>();
        assert lines != null;
        for (String[] s : lines)
        {
            String[] data = copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]), ArrayUtil.arrayStringToDouble(data));
        }
        return dataMap;
    }

    public int[][] fillData(int[][] data, int min, int max)
    {
        ArrayUtil.fillRandom(data, min, max);
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

    public TreeMap<GregorianCalendar, Integer[]> selectDataByWeek(TreeMap<GregorianCalendar, Integer[]> dataMap)
    {
        Iterator<Map.Entry<GregorianCalendar, Integer[]>> itr = dataMap.entrySet().iterator();
        TreeMap<GregorianCalendar, Integer[]> newMap = new TreeMap<>();
        int week = dataMap.firstKey().get(Calendar.WEEK_OF_MONTH);
        List<Integer[]> slice = new ArrayList<>();
        while (itr.hasNext())
        {
            Map.Entry<GregorianCalendar, Integer[]> entry = itr.next();
            GregorianCalendar c = new GregorianCalendar();
            c.set(Calendar.YEAR, entry.getKey().get(Calendar.YEAR));
            c.set(Calendar.MONTH, entry.getKey().get(Calendar.MONTH));
            c.set(Calendar.WEEK_OF_MONTH, entry.getKey().get(Calendar.WEEK_OF_MONTH));
            while (itr.hasNext() && entry.getKey().get(Calendar.WEEK_OF_MONTH) == week)
            {
                slice.add(entry.getValue());
                entry = itr.next();
            }
            if (slice.size() != 0)
            {
                Integer[] newData = new Integer[slice.get(0).length];
                newData[0] = slice.get(0)[0];
                newData[1] = ArrayUtil.findMaxValue(slice);
                newData[2] = ArrayUtil.findMinValue(slice);
                newData[3] = slice.get(slice.size()-1)[slice.get(0).length-1];

                newMap.put(c, newData);
            }

            slice.clear();
            week = entry.getKey().get(Calendar.WEEK_OF_MONTH);
        }

        return newMap;
    }

    public static void main(String[] args)
    {
        DataService ds = new DataService();
        TreeMap<Date, Integer[]> oldMap = ds.dataToIntegerMap("data/USDCB_161125_211125.txt");
        TreeMap<GregorianCalendar, Integer[]> dataMap = ds.dateMapToCalendar(oldMap);
        dataMap = ds.selectDataByWeek(dataMap);

        for (GregorianCalendar c : dataMap.keySet())
        {
            System.out.print(c.get(Calendar.YEAR) + " " + c.get(Calendar.MONTH) + " "
                    + c.get(Calendar.WEEK_OF_MONTH) + " " + c.get(Calendar.DAY_OF_MONTH));
            System.out.print(Arrays.toString(dataMap.get(c)));
            System.out.println();
        }
    }

    public TreeMap<GregorianCalendar, Integer[]> dateMapToCalendar(TreeMap<Date, Integer[]> map)
    {
        TreeMap<GregorianCalendar, Integer[]> dataMap = new TreeMap<>();
        for (Date d : map.keySet())
        {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(d);
            dataMap.put(c, map.get(d));
        }
        return dataMap;
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
