package demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author： xinjingjie
 * @Date：2021/5/12 14:24
 **/
public class DateAdd {
    static SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static List<String> addMinute(Date beginTime, Date endTime, int interval) throws ParseException {
        List<String> list = new ArrayList<>();
        while (beginTime.before(endTime)) {
            beginTime.setTime(beginTime.getTime() + interval * 60 * 1000);
            list.add(s.format(beginTime));
        }
        return list;
    }

    public static void main(String[] args) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date s = c.getTime();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date e = c.getTime();
        System.out.println(s);
        System.out.println(e);
        System.out.println(addMinute(s, e, 5));

    }


}
