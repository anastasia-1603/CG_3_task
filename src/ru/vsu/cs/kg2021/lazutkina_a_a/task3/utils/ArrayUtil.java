package ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ArrayUtil
{
    private final static Random random = new Random();

    public static Integer findMinValue(Collection<Integer[]> collection)
    {
        Integer min = 0; //todo исправить на array[0]
        for (Integer[] array : collection)
        {
            Integer localMin = findMin(array);
            if (localMin > min)
            {
                min = localMin;
            }
        }
        return min;
    }

    public static Integer findMin(Integer[] array)
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

    public static Integer findMaxValue(Collection<Integer[]> collection)
    {
        Integer max = 0; //todo исправить на array[0]
        for (Integer[] array : collection)
        {
            Integer localMax = findMax(array);
            if (localMax > max)
            {
                max = localMax;
            }
        }
        return max;
    }

    public static Integer findMax(Integer[] array)
    {
        int max = array[0];
        for (int j : array)
        {
            if (j > max)
            {
                max = j;
            }
        }
        return max;
    }

    public static int[][] fillRandom(int[][] array, int min, int max)
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

    public static double[][] readDoubleArray2FromFile(String fileName)
    {
        try
        {
            return toDoubleArray2(readLinesFromFile(fileName));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }
    }

    public static Integer[] castDoubleArrayToInteger(Double[] array)
    {
        Integer[] integerArray = new Integer[array.length];
        double[] doubleArr = toPrimitive(array);
        for (int i = 0; i < array.length; i++)
        {
            integerArray[i] = (int) doubleArr[i];
        }
        return integerArray;
    }

    public static int[] castDoubleArrayToInt(Double[] array)
    {
        double[] doubleArr = toPrimitive(array);
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            intArray[i] = (int) doubleArr[i];
        }
        return intArray;
    }

    public static Double[] arrayStringToDouble(String[] stringArray)
    {
        Double[] doubleArray = new Double[stringArray.length];
        for (int i = 0; i < stringArray.length; i++)
        {
            doubleArray[i] = Double.parseDouble(stringArray[i]);
        }
        return doubleArray;
    }

    public static Integer[] toIntegerArray(String[] array)
    {
        Integer[] newArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
        {
            newArray[i] = Integer.valueOf(array[i]);
        }
        return newArray;
    }

    public static String[][] toString2DArray(String filename) //todo rename
    {
        try
        {
            return splitStringsWithComma(readLinesFromFile(filename));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }
    }

    public static String[][] splitStringsWithComma(String[] lines)
    {
        List<String[]> list = new ArrayList<>();
        for (String s : lines)
        {
            list.add(s.split(","));
        }
        return list.toArray(new String[0][]);
    }
    public static String[] readLinesFromFile(String fileName) throws FileNotFoundException
    {
        List<String> lines;
        try (Scanner scanner = new Scanner(new File(fileName), "UTF-8"))
        {
            lines = new ArrayList<>();
            while (scanner.hasNext())
            {
                lines.add(scanner.nextLine());
            }
        }
        return lines.toArray(new String[0]);
    }

    public static double[][] toDoubleArray2(String[] lines)
    {
        double[][] arr2 = new double[lines.length][];
        for (int r = 0; r < lines.length; r++)
        {
            arr2[r] = toDoubleArray(lines[r]);
        }
        return arr2;
    }

    private static double[] toDoubleArray(String str)
    {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter(",");
        List<Double> list = new ArrayList<>();
        while (scanner.hasNext())
        {
            list.add(scanner.nextDouble());
        }
        Double[] arr = list.toArray(new Double[0]);
        return toPrimitive(arr);
    }

    private static double[] toPrimitive(Double[] arr)
    {
        if (arr == null)
        {
            return null;
        }
        double[] result = new double[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            result[i] = arr[i];
        }
        return result;
    }

    private static int[][] toIntArray2D(String[] lines)
    {
        int[][] arr2 = new int[lines.length][];
        for (int r = 0; r < lines.length; r++)
        {
            arr2[r] = toIntArray(lines[r]);
        }
        return arr2;
    }

    private static int[] toIntArray(String str)
    {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter(",");
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext())
        {
            list.add(scanner.nextInt());
        }
        Integer[] arr = list.toArray(new Integer[0]);
        return toPrimitive(arr);
    }

    public static int[][] toPrimitive(Integer[][] arr) //todo util
    {
        int[][] newArray = new int[arr.length][];
        for (int i = 0; i < arr.length; i++)
        {
            newArray[i] = toPrimitive(arr[i]);
        }
        return newArray;
    }

    public static int[] toPrimitive(Integer[] arr) //todo -> util
    {
        if (arr == null)
        {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            result[i] = arr[i];
        }
        return result;
    }

    public static int findMax(int[][] array)
    {
        int max = array[0][0];
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
        int max = array[0];
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
