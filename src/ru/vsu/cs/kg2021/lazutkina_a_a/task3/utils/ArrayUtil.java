package ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ArrayUtil
{
    private final static Random random = new Random();

    public static double[][] toDouble2DArray(List<Double[]> list)
    {
        return toPrimitive(list.toArray(new Double[0][]));
    }

    public static int[][] toInt2DArray(List<Integer[]> list)
    {
        return toPrimitive(list.toArray(new Integer[0][]));
    }

/*    public static List<Integer[]> readListIntegerArraysFromFile(String filename)
    {
        List<Integer[]> dataList = new ArrayList<>();
        try
        {
            String[] strings = readLinesFromFile(filename);
            for (String s : strings)
            {
                dataList.add(toIntegerArray(s));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return dataList;
    }*/



    public static List<Double[]> readListDoubleArraysFromFile(String filename)
    {
        List<Double[]> dataList = new ArrayList<>();
        String[][] lines = ArrayUtil.toString2DArray(filename);
        if (lines != null)
        {
            for (String[] s : lines)
            {
                String[] data = Arrays.copyOfRange(s, 1, s.length);
                dataList.add(arrayStringToDouble(data));
            }
        }
        else
        {
            throw new NullPointerException("lines is null");
        }
        return dataList;
    }


/*    public static double[][] readDoubleArray2FromFile(String fileName)
    {
        try
        {
            return toDoubleArray2(readLinesFromFile(fileName));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }
    }*/

/*    public static int[] castDoubleArrayToInt(Double[] array)
    {
        double[] doubleArr = toPrimitive(array);
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            intArray[i] = (int) doubleArr[i];
        }
        return intArray;
    }*/

    public static Double[] arrayStringToDouble(String[] stringArray)
    {
        Double[] doubleArray = new Double[stringArray.length];
        for (int i = 0; i < stringArray.length; i++)
        {
            doubleArray[i] = Double.parseDouble(stringArray[i]);
        }
        return doubleArray;
    }

    public static double[] toPrimitiveDouble(String[] stringArray)
    {
        Double[] doubleArray = new Double[stringArray.length];
        for (int i = 0; i < stringArray.length; i++)
        {
            doubleArray[i] = Double.parseDouble(stringArray[i]);
        }
        return toPrimitive(doubleArray);
    }

/*    public static Integer[] toIntegerArray(String[] lines)
    {
        Integer[] newArray = new Integer[lines.length];
        for (int i = 0; i < lines.length; i++)
        {
            newArray[i] = Integer.valueOf(lines[i]);
        }
        return newArray;
    }*/

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

/*    public static double[][] toDoubleArray2(String[] lines)
    {
        double[][] arr2 = new double[lines.length][];
        for (int r = 0; r < lines.length; r++)
        {
            arr2[r] = toPrimitiveDoubleArray(lines[r]);
        }
        return arr2;
    }*/

    private static Double[] toDoubleArray(String str)
    {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter(",");
        List<Double> list = new ArrayList<>();
        while (scanner.hasNext())
        {
            list.add(scanner.nextDouble());
        }
        return list.toArray(new Double[0]);
    }

/*    private static double[] toPrimitiveDoubleArray(String str)
    {
        return toPrimitive(toDoubleArray(str));
    }*/

    private static double[][] toPrimitive(Double[][] array)
    {
        double[][] array2D = new double[array.length][];
        for (int i = 0; i < array.length; i++)
        {
            array2D[i] = toPrimitive(array[i]);
        }
        return array2D;
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

/*    private static int[][] toIntArray2D(String[] lines)
    {
        int[][] arr2 = new int[lines.length][];
        for (int r = 0; r < lines.length; r++)
        {
            arr2[r] = toIntArray(lines[r]);
        }
        return arr2;
    }*/

/*    private static int[] toIntArray(String str)
    {
        return toPrimitive(toIntegerArray(str));
    }*/

/*    private static Integer[] toIntegerArray(String str)
    {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext())
        {
            list.add(scanner.nextInt());
        }
        return list.toArray(new Integer[0]);
    }*/

    public static int[][] toPrimitive(Integer[][] arr)
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

   /* public static double findMax(double[][] array)
    {
        double max = 0;
        for (double[] data : array)
        {
            double localMax = findMax(data);
            if (localMax > max)
            {
                max = localMax;
            }
        }
        return max;
    }

    public static double findMax(double[] array)
    {
        double max = 0;
        for (double j : array)
        {
            if (j > max)
            {
                max = j;
            }
        }
        return max;
    }

    public static double findMin(double[] array)
    {
        double min = array[0];
        for (double j : array)
        {
            if (j < min)
            {
                min = j;
            }
        }
        return min;
    }

    public static double findMin(double[][] array)
    {
        double min = array[0][0];
        for (double[] data : array)
        {
            double localMin = findMin(data);
            if (localMin < min)
            {
                min = localMin;
            }
        }
        return min;
    }

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
    }*/

    public static int random(int min, int max)
    {
        return random.nextInt(max - min + 1) + min;
    }
}
