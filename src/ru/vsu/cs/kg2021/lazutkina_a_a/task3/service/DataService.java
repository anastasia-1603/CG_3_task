package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.ArrayUtil;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.DateUtils;
import java.text.ParseException;
import java.util.*;


public class DataService
{
    Random random = new Random();

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
            String[] data = Arrays.copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]), ArrayUtil.toIntegerArray(data));
        }
        return dataMap;
    }

    private Map<Date, int[]> dataToIntMap(String[][] lines) throws ParseException //todo rename и что то сделать с нагромождением методов
    {
        Map<Date, int[]> dataMap = new TreeMap<>();
        for (String[] s : lines)
        {
            String[] data = Arrays.copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]), ArrayUtil.castDoubleArrayToInt(ArrayUtil.arrayStringToDouble(data)));
        }
        return dataMap;
    }

    private Map<Date, Double[]> dataToMap(String[][] lines) throws ParseException //todo rename и сделать метод где в параметрах имя файла
    {
        Map<Date, Double[]> dataMap = new TreeMap<>();
        for (String[] s : lines)
        {
            String[] data = Arrays.copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]), ArrayUtil.arrayStringToDouble(data));
        }
        return dataMap;
    }

    /*private int[] castDoubleArrayToInt(Double[] array)
    {
        double[] doubleArr = toPrimitive(array);
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            intArray[i] = (int) doubleArr[i];
        }
        return intArray;
    }

    private static Integer[] toIntegerArray(String[] array)
    {
        Integer[] newArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
        {
            newArray[i] = Integer.valueOf(array[i]);
        }
        return newArray;
    }

    private Double[] arrayStringToDouble(String[] stringArray)
    {
        Double[] doubleArray = new Double[stringArray.length];
        for (int i = 0; i < stringArray.length; i++)
        {
            doubleArray[i] = Double.parseDouble(stringArray[i]);
        }
        return doubleArray;
    }

    private Date readDate(String date) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.parse(date);
    }

    public String[][] toString2DArray(String filename) //todo rename
    {
        try
        {
            return splitStringArray(readLinesFromFile(filename));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }
    }

    public String[][] splitStringArray(String[] lines)
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


    public static double[][] toDoubleArray2(String[] lines)
    {
        double[][] arr2 = new double[lines.length][];
        for (int r = 0; r < lines.length; r++)
        {
            arr2[r] = toDoubleArray(lines[r]);
        }
        return arr2;
    }

    public static double[] toDoubleArray(String str)
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



    public static double[] toPrimitive(Double[] arr)
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

    public int[][] toPrimitive(Integer[][] arr) //todo util
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
*/
}
