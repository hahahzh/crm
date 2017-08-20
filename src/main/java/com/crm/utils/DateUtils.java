package com.crm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期管理
 * User: karl
 * Date: 15-06-22 下午10:39
 */
public class DateUtils {

    static Date formatDayMax(String dateStr) {
        Date date = format(dateStr);
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 23) ;
        calendar.add(Calendar.MINUTE, 59) ;
        calendar.add(Calendar.SECOND, 59) ;
        return calendar.getTime();
    }

    static Date formatDayMin(String dateStr) {
        Date date = format(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date) ;
        calendar.add(Calendar.HOUR_OF_DAY, 0);
        calendar.add(Calendar.MINUTE, 0) ;
        calendar.add(Calendar.SECOND, 0) ;
        return calendar.getTime() ;
    }

    static Date formatDayMax(Date date) {
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date) ;
        calendar.set(Calendar.HOUR_OF_DAY, 23)  ;
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999) ;
        return calendar.getTime();
    }

    static Date formatDayMin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date)  ;
        calendar.set(Calendar.HOUR_OF_DAY, 0) ;
        calendar.set(Calendar.MINUTE, 0) ;
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime()  ;
    }

    static Date format(String dateStr) {
        Date str= null;
        if (dateStr==null ||dateStr.equals("")) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd")  ;
            try {
                str = format.parse(dateStr) ;
            } catch (Exception e){
            }
        }
        return str;
    }

    public static Date getBeforeDateByDays(Date date,int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)-days,0,0,0);
        return calendar.getTime();
    }

   public static Date format(String dateStr, String formatStyle){
        if (StringUtils.isEmpty(formatStyle)) {
            formatStyle = "yyyy-MM-dd";
        }
       Date str = null;
        if (dateStr!=null) {
            DateFormat format = new SimpleDateFormat(formatStyle)   ;
             try{
               str =  format.parse(dateStr) ;
             } catch (Exception e){
             }
        }else{
            return null;
        }
       return str;
    }

    static String parse(Date date, String formatStr) {
        String str = "";
        if (date!=null) {
            DateFormat format = new SimpleDateFormat(formatStr);
            str = format.format(date) ;
        }
        return str;
    }

    static String parse(Date date) {
        return parse(date, "yyyy-MM-dd")  ;
    }

    static Date amount(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date) ;
        calendar.add(field, amount)  ;
        return calendar.getTime()  ;
    }

    static final boolean afterNow(Date now, Date needCompare) {
        if (now.getTime() < needCompare.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    static final boolean beforeNow(Date now, Date needCompare) {
        return afterNow(now, needCompare);
    }

    static final int parserDay(Date overdue, Date now)  {
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long diff = formatDayMin(now).getTime() - formatDayMin(overdue).getTime();
        // TODO，多加1天
        int day = (int)((diff / nd) + 1);//计算差多少天
        return day;
    }

    static final int parserLiDay(Date beforeDay, Date endDay) {
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long diff = formatDayMin(endDay).getTime() - formatDayMin(beforeDay).getTime();
        int day =(int )(diff / nd);//计算差多少天
        return day;
    }

    static Date formatDayMaxMonth(Date date) {
        //当月最小的一天
        Date tDate = formatDayMinMonth(date);
        //增加1个月
        Date eDate = amount(tDate, Calendar.MONTH, 1) ;
        //减少1秒
        return amount(eDate, Calendar.SECOND, -1);
    }
// 当月第一天
  public static Date formatDayMinMonth(Date date) {
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1) ;
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0) ;
        return calendar.getTime();
    }
//    计算一年最小的天数（一月份）
    static Date formatYearMin(Date date) {
        Date dateTempMin = formatDayMin(date) ;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTempMin);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime() ;
    }
//计算一年最多的天数（十二月份）
    static Date formatYearMax(Date date) {
        Date dateTempMax = formatDayMin(date) ;
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(dateTempMax) ;
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        Date dateTemp = amount(calendar.getTime(), Calendar.YEAR, 1);
        dateTemp = amount(dateTemp, calendar.SECOND, -1) ;
        return dateTemp ;
    }
    //判断日期是周几
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd") ;
        Calendar c = Calendar.getInstance() ;
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7 ;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1 ;
        }
        return dayForWeek ;
    }

    //根据时间戳，转换为日期时间
    public static String longToDateTime(long time){
        Date date = new Date(time);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(gc.getTime());
    }

    /**
     * 格式化时间
     * @return
     * @throws NumberFormatException
     */
    public static String formatDate(Date date) {
        if(date==null||date.equals("")){
            return "";
        }
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化时间
     *
     * @return
     * @throws NumberFormatException
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);
    }

    /**
     * 判断两个时间是否为同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }
    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static Date getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)+0, 0, 0, 0);
        return c.getTime();
    }


    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static Long getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return c.getTime().getTime();
    }
}
