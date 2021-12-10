package ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static String toString(GregorianCalendar c, String pattern)
    {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = c.getTime();
        return format.format(date);
    }

    public static Date readDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.parse(date);
    }

  /*  public static GregorianCalendar readCalendar(String date, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return dateToCalendar(format.parse(date));
    }*/

    public static GregorianCalendar readCalendar(String date, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return dateToCalendar(format.parse(date));
    }

    public static GregorianCalendar dateToCalendar(Date date) {
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
