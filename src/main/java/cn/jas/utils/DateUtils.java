package cn.jas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static String DATE_FORMAT = "yyyy-MM-dd";
    private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date) {
        if (null == date) {
            return "";
        }
        String strDate = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            strDate = simpleDateFormat.format(date);
        }catch (Exception e){

        }
        return strDate;
    }

}
