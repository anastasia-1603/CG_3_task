package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.Candle;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.CandleDouble;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.ArrayUtil;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.DateUtils;
import java.text.ParseException;
import java.util.*;

import static java.util.Arrays.copyOfRange;

public class DataService {
    Random random = new Random();

    public double[][] readDoubleData(String filename) {
        List<Double[]> dataList = selectListDataFromFile(filename);
        return ArrayUtil.toDouble2DArray(dataList);
    }

    public int[][] readIntData(String filename) {
        List<Integer[]> dataList = ArrayUtil.readListIntegerArraysFromFile(filename);
        return ArrayUtil.toInt2DArray(dataList);
    }

    public Map<GregorianCalendar, CandleDouble> dataToMap(String filename) throws ParseException {
        Map<GregorianCalendar, CandleDouble> dataMap = new TreeMap<>();
        String[][] lines = ArrayUtil.toString2DArray(filename);
        for (String[] s : lines) {
            double[] data = ArrayUtil.toPrimitiveDouble(copyOfRange(s, 1, s.length));
            CandleDouble candle = new CandleDouble(data[0], data[1], data[2], data[3]);
            dataMap.put(DateUtils.readCalendar(s[0]), candle);
        }
        return dataMap;
    }

    /**
     * Читает из файла дату, стоящую в первом столбце каждой строки в заданном формате.
     * @param filename
     * @param format
     * @return List<GregorianCalendar>
     * @throws ParseException
     */
    public List<GregorianCalendar> readListOfDates(String filename, String format) throws ParseException {
        String[][] lines = ArrayUtil.toString2DArray(filename);
        List<GregorianCalendar> list = new ArrayList<>();
        for (String[] s : lines) {
            list.add(DateUtils.readCalendar(s[0]));
        }
        return list;
    }

    /**
     * Читает из файла данные в виде таблицы,
     * где первый столбец - дата, а остальные 4 -
     * цена открытия, максимальная и минимальная цены, цена закрытия.
     * @param filename
     * @return
     */
    public List<Double[]> selectListDataFromFile(String filename)
    {
        List<Double[]> dataList = new ArrayList<>();
        String[][] lines = ArrayUtil.toString2DArray(filename);
        if (lines != null)
        {
            for (String[] s : lines) {
                String[] data = copyOfRange(s, 1, s.length);
                dataList.add(ArrayUtil.arrayStringToDouble(data));
            }
        }
        else {
            throw new NullPointerException("lines must not be null");
        }
        return dataList;
    }

    /*public List<CandleDouble> doubleDataToListCandles(List<Double[]> list)
    {
        List<CandleDouble> candles = new ArrayList<>();
        for (Double[] array : list)
        {
            candles.add(toCandle(array));
        }
        return candles;
    }*/

    public List<CandleDouble> readListCandles(String filename)
    {
        List<Double[]> list = ArrayUtil.readListDoubleArraysFromFile(filename);
        List<CandleDouble> candles = new ArrayList<>();
        for (Double[] array : list)
        {
            candles.add(toCandle(array));
        }
        return candles;
    }

    private CandleDouble toCandle(Double[] data)
    {
        return new CandleDouble(data[0], data[1], data[2], data[3]);
    }

    public Map<Date, Double[]> dataToDoubleMap() {
        try {
            return dataToDoubleMap(Objects.requireNonNull(
                    ArrayUtil.toString2DArray("data/USDCB_161125_211125.txt")));
        } catch (ParseException e) {
            return null;
        }
    }

    public Map<Date, Integer[]> dataToIntegerMap() {
        try {
            return dataToIntegerMap(Objects.requireNonNull(
                    ArrayUtil.toString2DArray("data/USDCB_161125_211125.txt")));
        } catch (ParseException e) {
            return null;
        }
    }

    public Map<Date, int[]> dataToIntMap() {
        try {
            return dataToIntMap(Objects.requireNonNull(
                    ArrayUtil.toString2DArray("data/USDCB_161125_211125.txt")));
        } catch (ParseException e) {
            return null;
        }
    }

    private Map<Date, Integer[]> dataToIntegerMap(String[][] lines) throws ParseException //todo rename
    {
        Map<Date, Integer[]> dataMap = new TreeMap<>();
        for (String[] s : lines) {
            String[] data = copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]), ArrayUtil.toIntegerArray(data));
        }
        return dataMap;
    }

    private Map<Date, int[]> dataToIntMap(String[][] lines) throws ParseException //todo rename и что то сделать с нагромождением методов
    {
        Map<Date, int[]> dataMap = new TreeMap<>();
        for (String[] s : lines) {
            String[] data = copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]), ArrayUtil.castDoubleArrayToInt(ArrayUtil.arrayStringToDouble(data)));
        }
        return dataMap;
    }

    private Map<Date, Double[]> dataToDoubleMap(String[][] lines) throws ParseException //todo rename и сделать метод где в параметрах имя файла
    {
        Map<Date, Double[]> dataMap = new TreeMap<>();
        for (String[] s : lines) {
            String[] data = copyOfRange(s, 1, s.length);
            dataMap.put(DateUtils.readDate(s[0]), ArrayUtil.arrayStringToDouble(data));
        }
        return dataMap;
    }

    public int[][] fillData(int[][] data, int min, int max) {
        fillRandom(data, min, max);
        for (int[] arr : data) {
            Arrays.sort(arr);
            if (random.nextBoolean()) {
                int a = arr[1];
                arr[1] = arr[2];
                arr[2] = a;
            }
        }
        return data;
    }

    public List<Integer[]> fillDataList(List<Integer[]> dataList, String filename) {
        return null;
    }

    private int[][] fillRandom(int[][] array, int min, int max) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = ArrayUtil.random(min, max);
            }
        }
        return array;
    }

    public int[][] selectDataByWeek(int[][] data) {
        int num = data.length / 7;
        int[][] newData = new int[num][4];
        for (int j = 0, i = 0; i < num * 7; i += 7) {
            int[][] slice = copyOfRange(data, i, i + 7);
            newData[j][0] = ArrayUtil.findMin(slice);
            newData[j][1] = slice[0][1];
            newData[j][2] = slice[slice.length - 1][2];
            newData[j][3] = ArrayUtil.findMax(slice);
            j++;
        }
        return newData;
    }

    public int[][] selectDataByMonth(int[][] data) {
        int num = data.length / 30;
        int[][] newData = new int[num][4];
        for (int j = 0, i = 0; i < num * 30; i += 30) {
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
