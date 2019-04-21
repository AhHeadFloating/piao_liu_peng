package com.miaoyunhan.piao_liu_peng.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getFistTimeByDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        Date time = c.getTime();
        return time;
    }
}
