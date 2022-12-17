package java2.utils;

import com.alibaba.fastjson2.util.DateUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class DateUtil {
    public static Timestamp ISOToTimestamp(String iso) {
        if (iso == null) {return null;}
        return Timestamp.valueOf(iso.replace("T", " ").replace("Z", " "));
    }
}
