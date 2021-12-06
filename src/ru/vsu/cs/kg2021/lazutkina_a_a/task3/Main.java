package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.service.DataService;
import ru.vsu.cs.kg2021.lazutkina_a_a.task3.view.MainFrame;
import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        /*DataService ds = new DataService();
        double[][] arr = ds.readData();
        String[][] strings = ds.readDataInString();
        for (String[] s : strings)
        {
            System.out.println(Arrays.toString(s));
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Map<Date, int[]> dataMap = ds.dataToIntMap();
        for (Date date : dataMap.keySet())
        {
            System.out.print(format.format(date));
            System.out.print(Arrays.toString(dataMap.get(date)));
            System.out.println();
        }
        System.out.println(dataMap);*/
    }
}
