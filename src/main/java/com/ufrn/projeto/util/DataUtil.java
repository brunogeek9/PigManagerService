package com.ufrn.projeto.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DataUtil {
    public static Date currentDate(){
        TimeZone timeZone = TimeZone.getTimeZone("America/Recife");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(timeZone);
        cal.setTime(data);
        return cal.getTime();
    }
}
