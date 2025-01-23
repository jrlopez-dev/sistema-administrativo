package com.sistemaadministrativo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

public class DateUtil {
    private static final Logger LOG = Logger.getLogger(DateUtil.class.getName());

    private DateUtil() {
    }
    public static final String STANDAR_RFC3339 = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String DDMMYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
    public static final String DDMM_YYY_HHMMSS = "dd-MM-yyyy HH:mm:ss";
    public static final String DDMMYYYY = "dd/MM/yyyy";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String MMYYY = "MM/yyyy";

    public static Date date(String f, String p) {
        Date date = null;
        if (Objects.isNull(f) || Objects.isNull(p)) {
            return date;
        }
        try {
            date = new SimpleDateFormat(p).parse(f);
        } catch (ParseException e) {
            LOG.severe(e.getMessage());
        }
        return date;
    }

    public static Date restarDias(Date fecha, Integer dias){
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DATE, -dias);
        Date nowMinus = c.getTime();
        return nowMinus;
    }

    public static String date(Date f, String p) {
        if (f == null || p == null) {
            return "";
        }
        return new SimpleDateFormat(p).format(f);
    }

    public static Date first(String f) {
        if (Objects.isNull(f)) {
            return null;
        }
        return DateUtil.date(String.format("%s 00:00:00", f), DateUtil.DDMMYYY_HHMMSS);
    }

    public static Date yyyyddmm(String f) {
        if (Objects.isNull(f)) {
            return null;
        }
        return DateUtil.date(f,DateUtil.YYYY_MM_DD);
    }
    public static Date last(String f) {
        if (Objects.isNull(f)) {
            return null;
        }
        return DateUtil.date(String.format("%s 23:59:59", f), DateUtil.DDMMYYY_HHMMSS);
    }
    public static Date first(Date f) {
        if (Objects.isNull(f)) {
            return null;
        }
        return DateUtil.date(String.format("%s 00:00:00", DateUtil.date(f, DateUtil.DDMMYYYY)), DateUtil.DDMMYYY_HHMMSS);
    }

    public static Date last(Date f) {
        if (Objects.isNull(f)) {
            return null;
        }
        return DateUtil.date(String.format("%s 23:59:59", DateUtil.date(f, DateUtil.DDMMYYYY)), DateUtil.DDMMYYY_HHMMSS);
    }

    public static Date addHour(Date f, Integer h) {
        if (Objects.isNull(f)) {
            return null;
        }
        if (Objects.isNull(h)) {
            h = Integer.parseInt("0");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(f);
        calendar.add(Calendar.HOUR, h);
        return calendar.getTime();
    }

}
