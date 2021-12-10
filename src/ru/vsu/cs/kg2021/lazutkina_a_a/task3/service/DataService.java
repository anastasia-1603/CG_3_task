package ru.vsu.cs.kg2021.lazutkina_a_a.task3.service;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.Data;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.Candle;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.ArrayUtil;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils.DateUtils;

import java.text.ParseException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataService {

    Random random = new Random();

    public double findMaxPrice(List<Candle> candles) {
        double max = -1;
        for (Candle c: candles) {
            double high = c.getHigh();
            if (high > max) {
                max = high;
            }
        }
        return max;
    }

    public double findMinPrice(List<Candle> candles) {
        double min = candles.get(0).getLow();
        for (Candle c: candles) {
            double low = c.getLow();
            if (low < min) {
                min = low;
            }
        }
        return min;
    }

    public Data groupByWeek(Data data) {

        List<GregorianCalendar> dates = data.getDates();
        TreeMap<GregorianCalendar, Candle> map = data.getMap();

        List<GregorianCalendar> newDates = new ArrayList<>();
        List<Candle> newCandles = new ArrayList<>();

        int week = dates.get(0).get(Calendar.WEEK_OF_MONTH);
        newDates.add(dates.get(0));
        List<Candle> slice = new ArrayList<>();

        int i = 1;
        for (GregorianCalendar c: dates) {
            int currWeek = c.get(Calendar.WEEK_OF_MONTH);

            if (currWeek != week) {
                week = currWeek;
                newDates.add(c);
                newCandles.add(groupCandles(slice, i++));
                slice.clear();
            }
            slice.add(map.get(c));
        }

        return new Data(newDates, newCandles);
    }

    public Data groupByMonth(Data data) {
        List<GregorianCalendar> dates = data.getDates();
        TreeMap<GregorianCalendar, Candle> map = data.getMap();
        List<GregorianCalendar> newDates = new ArrayList<>();
        List<Candle> newCandles = new ArrayList<>();

        int month = dates.get(0).get(Calendar.MONTH);
        newDates.add(dates.get(0));
        List<Candle> slice = new ArrayList<>();

        int i = 1;
        for (GregorianCalendar c: dates) {
            int currMonth = c.get(Calendar.MONTH);

            if (currMonth != month) {
                month = currMonth;
                newDates.add(c);
                newCandles.add(groupCandles(slice, i++));
                slice.clear();
            }
            slice.add(map.get(c));
        }
        return new Data(newDates, newCandles);
    }

    public Data groupBy(Data data, int calendarConst) {
        List<GregorianCalendar> dates = data.getDates();
        TreeMap<GregorianCalendar, Candle> map = data.getMap();
        List<GregorianCalendar> newDates = new ArrayList<>();
        List<Candle> newCandles = new ArrayList<>();

        int date = dates.get(0).get(calendarConst);
        newDates.add(dates.get(0));
        List<Candle> slice = new ArrayList<>();

        int i = 1;
        for (GregorianCalendar c: dates) {
            int currDate = c.get(calendarConst);

            if (currDate != date) {
                date = currDate;
                newDates.add(c);
                newCandles.add(groupCandles(slice, i++));
                slice.clear();
            }
            slice.add(map.get(c));
        }
        return new Data(newDates, newCandles);
    }

    public Candle groupCandles(List<Candle> candles, int index) {
        double high = findMaxPrice(candles);
        double low = findMinPrice(candles);
        double open = candles.get(0).getOpen();
        double close = candles.get(candles.size() - 1).getClose();
        return new Candle(open, high, low, close, index);
    }

    public <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        return IntStream
                .range(0, keys.size() - 1)
                .boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }

    /**
     * Читает из файла дату, стоящую в первом столбце каждой строки в заданном формате.
     *
     * @param filename
     * @param format
     * @return List<GregorianCalendar>
     * @throws ParseException
     */
    public List<GregorianCalendar> readListOfDates(String filename, String format, int column) {

        List<GregorianCalendar> list = new ArrayList<>();
        try {
            String[][] lines = ArrayUtil.toString2DArray(filename);
            if (lines != null) {
                for (String[] s: lines) {
                    list.add(DateUtils.readCalendar(s[column - 1], format));
                }
            } else {
                throw new NullPointerException("lines is null");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Candle> readListCandles(String filename, int from, int to) {
        List<Double[]> list = ArrayUtil.readListDoubleArraysFromFile(filename, from, to);
        List<Candle> candles = new ArrayList<>();
        int index = 1;
        for (Double[] array: list) {
            candles.add(toCandle(array, index++));
        }
        return candles;
    }

    private Candle toCandle(Double[] data, int index) {
        return new Candle(data[0], data[1], data[2], data[3], index);
    }

/*    public int[][] fillData(int[][] data, int min, int max) {
        fillRandom(data, min, max);
        for (int[] arr: data) {
            Arrays.sort(arr);
            if (random.nextBoolean()) {
                int a = arr[1];
                arr[1] = arr[2];
                arr[2] = a;
            }
        }
        return data;
    }*/


/*    private int[][] fillRandom(int[][] array, int min, int max) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = ArrayUtil.random(min, max);
            }
        }
        return array;
    }*/

}
