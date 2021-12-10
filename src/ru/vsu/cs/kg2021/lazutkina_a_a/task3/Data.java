package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.diagram.Candle;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DataService;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeMap;

public class Data
{
    private static final DataService DATA_SERVICE = new DataService();
    private List<Candle> candles;
    private List<GregorianCalendar> dates;
    private TreeMap<GregorianCalendar, Candle> map = new TreeMap<>();

    public Data(String filename, String dateFormat, int columnDate, int fromData, int toData)
    {
        dates = DATA_SERVICE.readListOfDates(filename, dateFormat, columnDate);
        candles = DATA_SERVICE.readListCandles(filename, fromData, toData);
        map.putAll(DATA_SERVICE.zipToMap(dates, candles));
    }


    public Data(List<GregorianCalendar> dates, List<Candle> candles) {
        this.candles = candles;
        this.dates = dates;
        map.putAll(DATA_SERVICE.zipToMap(dates, candles));
    }

    public TreeMap<GregorianCalendar, Candle> getMap() {
        return map;
    }

    public void setMap(TreeMap<GregorianCalendar, Candle> map) {
        this.map = map;
    }

    public List<Candle> getCandles() {
        return candles;
    }

    /*public void setCandles(List<Candle> candles) {
        this.candles = candles;
    }*/

    public List<GregorianCalendar> getDates() {
        return dates;
    }

    /*public void setDates(List<GregorianCalendar> dates) {
        this.dates = dates;
    }*/

    public void setData(List<GregorianCalendar> dates, List<Candle> candles)
    {
        this.candles = candles;
        this.dates = dates;
        map.clear();
        map.putAll(DATA_SERVICE.zipToMap(dates, candles));
    }
}
