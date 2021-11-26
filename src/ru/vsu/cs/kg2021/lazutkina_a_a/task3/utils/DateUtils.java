package ru.vsu.cs.kg2021.lazutkina_a_a.task3.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils
{
    public static Date readDate(String date) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.parse(date);
    }
}
