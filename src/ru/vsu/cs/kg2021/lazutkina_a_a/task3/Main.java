package ru.vsu.cs.kg2021.lazutkina_a_a.task3;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.view.MainFrame;
import javax.swing.*;
import java.text.ParseException;


public class Main
{
    public static void main(String[] args) throws ParseException {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        /*DataService ds = new DataService();
        List<GregorianCalendar> dates = ds.readListOfDates("data/USDCB_161125_211125.txt", "yyyyMMdd");

        List<CandleDouble> candles = ds.readListCandles("data/USDCB_161125_211125.txt");

        for (int i = 0; i < dates.size(); i++)
        {
            CandleDouble c = candles.get(i);
            System.out.println(DateUtils.toString(dates.get(i), "yyyy.MM.dd") + " " + c.getHigh() + " " + c.getLow());

        }
        System.out.println("weeks");

        TreeMap<GregorianCalendar, CandleDouble> map = ds.groupByWeek(dates, candles);
        for (Map.Entry<GregorianCalendar, CandleDouble> e : map.entrySet())
        {
            System.out.println(DateUtils.toString(e.getKey(), "yyyy.MM.dd") + " " + e.getValue().getHigh()
                    + " " + e.getValue().getLow() + " " + e.getValue().getClose() + " " +  e.getValue().getOpen());
        }
*/
    }
}
