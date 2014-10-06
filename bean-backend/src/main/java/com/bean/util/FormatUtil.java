package com.bean.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Daniel on 14-8-20.
 */
public class FormatUtil {
    private static final String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatTimeAsStringFromTimestamp(Timestamp st){
        if(st == null){
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(DATA_FORMAT);
        String str = df.format(st);

        return str;
    }

    public static Timestamp formatTimeAsTimestampFromString(String time){
        if(time == null){
            return null;
        }
        Timestamp ts =Timestamp.valueOf(time);
        return ts;
    }
}
