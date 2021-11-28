package ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils
{
    public static Date readDate(String date) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.parse(date);
    }

    public static TreeMap<GregorianCalendar, Integer[]> dateMapToCalendar(TreeMap<Date, Integer[]> map)
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

    public static int calculateNumberOfWeeks(SortedSet<GregorianCalendar> dateSet)
    {
        return calculateNumberOfMonth(dateSet) * 4;
    }

    public static int calculateNumberOfMonth(SortedSet<GregorianCalendar> dateSet)
    {
        return calculateNumberOfYears(dateSet) * 12;
    }

    public static int calculateNumberOfYears(SortedSet<GregorianCalendar> dateSet)
    {
        int year = dateSet.first().get(Calendar.YEAR);
        int number = 0;
        for (GregorianCalendar c : dateSet)
        {
            if (c.get(Calendar.YEAR) != year)
            {
                number++;
                year = c.get(Calendar.YEAR);
            }
        }
        return number;
    }

    public static List<Integer> findYears(SortedSet<GregorianCalendar> dateSet)
    {
        int year = dateSet.first().get(Calendar.YEAR);
        List<Integer> calendars = new ArrayList<>();
        calendars.add(year);
        for (GregorianCalendar c : dateSet)
        {
            if (c.get(Calendar.YEAR) != year)
            {
                year = c.get(Calendar.YEAR);
                calendars.add(year);
            }
        }
        return calendars;
    }

    public static String calendarToString(Calendar c)
    {
        Date date = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        return formatter.format(date);
    }
}
