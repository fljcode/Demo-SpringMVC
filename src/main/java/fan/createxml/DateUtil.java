package fan.createxml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 时间工具类，所有方法均为线程安全
 * 
 * @author wangzhimin
 * @since 2015年12月15日
 */
public class DateUtil {
	
	/**yyyy-MM-dd HH:mm:ss*/
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 格式化日期为字符串
	 * 
	 * @param pattern 字符串格式
	 * @param date 日期
	 * @return
	 * 
	 */
	public static String format(String pattern, Date date) {
		return format(pattern, date, null);
	}
	
	/**
	 * 格式化日期为字符串
	 * 
	 * @param pattern 字符串格式
	 * @param date 日期
	 * @param zone 时区
	 * @return
	 */
	public static String format(String pattern, Date date, TimeZone zone) {
		SimpleDateFormat formator = new SimpleDateFormat();
		if(zone != null)
			formator.setTimeZone(zone);
		
		formator.applyPattern(pattern);
		return formator.format(date);
	}
	

	/**
	 * 以默认格式（yyyy-MM-dd），格式化日期为字符串
	 * 
	 * @param date 日期
	 * @return
	 * 
	 */
	public static String format(Date date) {
		return format(date, null);
	}
	
	/**
	 * 以默认格式（yyyy-MM-dd），格式化日期为字符串
	 * 
	 * @param date 日期
	 * @param zone 时区
	 * @return
	 */
	public static String format(Date date, TimeZone zone) {
		SimpleDateFormat formator = new SimpleDateFormat("yyyy-MM-dd");
		if(zone != null)
			formator.setTimeZone(zone);
		
		return formator.format(date);
	}
	
	

	/**
	 * 返回指定日期的后一天
	 * 
	 * @param date 日期
	 * @return
	 * 
	 */
	public static Date next(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 返回指定日期的后一天
	 * 
	 * @param dateStr yyyyMMdd 日期
	 * @return
	 * 
	 */
	public static String next(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 返回指定日期的后几天
	 * 
	 * @param dateStr yyyyMMdd 日期
	 * @return
	 * 
	 */
	public static String next(String dateStr, int interval) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, interval);
		return sdf.format(cal.getTime());
	}

	public static String nextXDays(String pattern, String strDate, int x) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(pattern, strDate));
		cal.add(Calendar.DAY_OF_MONTH, x);
		return format(pattern, cal.getTime());
	}

	/**
	 * 返回指定日期的前一天
	 * 
	 * @param date 日期
	 * @return
	 * 
	 */
	public static Date previous(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 返回指定日期的前一天
	 * 
	 * @param date 日期
	 * @return
	 * 
	 */
	public static Date previous(TimeZone zone, Date date) {
		Calendar cal = Calendar.getInstance(zone);
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	
	
	/**
	 * 返回指定日期的前几天的日期
	 * 
	 * @param date
	 * @param interval 指定几天前
	 * @return
	 * 
	 */
	public static Date previous(Date date, int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -Math.abs(interval));
		return cal.getTime();
	}

	/**
	 * 返回指定日期的前一天
	 * 
	 * @param date yyyyMMdd格式
	 * @return
	 * 
	 */
	public static String previous(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return sdf.format(cal.getTime());
	}

	/**
	 * 返回指定日期的前几天
	 * 
	 * @param date yyyyMMdd格式
	 * @return
	 * 
	 */
	public static String previous(String dateStr, int interval) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -Math.abs(interval));
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 两日期相隔天数
	 * @param beginDate 
	 * @param endDate
	 * @return
	 */
	public static int getDaysBetween(Date beginDate, Date endDate) {
		Calendar d1 = new GregorianCalendar();
		d1.setTime(beginDate);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(endDate);
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days + 1;
	}

	/**
	 * 两日期相隔天数
	 * 
	 * @param beginDateStr yyyyMMdd
	 * @param endDateStr yyyyMMdd
	 * @return
	 * 
	 */
	public static int getDaysBetween(String beginDate, String endDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			Date bDate = format.parse(beginDate);
			Date eDate = format.parse(endDate);
			Calendar d1 = new GregorianCalendar();
			d1.setTime(bDate);
			Calendar d2 = new GregorianCalendar();
			d2.setTime(eDate);
			int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
			int y2 = d2.get(Calendar.YEAR);
			if (d1.get(Calendar.YEAR) != y2) {
				d1 = (Calendar) d1.clone();
				do {
					days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数

					d1.add(Calendar.YEAR, 1);
				} while (d1.get(Calendar.YEAR) != y2);
			}
			return days + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 返回指定日期的前几小时的日期
	 * 
	 * @param date 日期
	 * @param num 推前小时数
	 * @return
	 * 
	 */
	public static Date previousHour(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -Math.abs(num));
		return cal.getTime();
	}
	
	/**
	 * 获取指定时区和时间的前几分钟
	 * @param zone
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date previousMin(TimeZone zone, Date date, int num) {
		Calendar cal = Calendar.getInstance(zone);
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -Math.abs(num));
		return cal.getTime();
	}
	
	
	
	
	/**
	 * 获取指定时区的当前时间
	 * @param zone
	 * @return
	 */
	public static Date getCurrentTime(TimeZone zone) {
		Calendar cal = Calendar.getInstance(zone);
		return cal.getTime();
	}

	/**
	 * 清除日期中的时分秒为0
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static Date toZeroTime(TimeZone zone, Date date) {
		Calendar cal = Calendar.getInstance(zone);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 返回主定月份的所有日期
	 * 
	 * @param year 年
	 * @param month 月
	 * @return 日期列表
	 * 
	 */
	public static List<Date> getDays(int year, int month) {
		List<Date> r = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(toZeroTime(TimeZone.getDefault(),new Date()));
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		while (cal.get(Calendar.MONTH) == month) {
			r.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}

		return r;
	}

	/**
	 * 转换字符串为日期
	 * 
	 * @param pattern 字符串格式
	 * @param date 日期字符串
	 * @return
	 * @throws ParseException
	 * 
	 */
	public static Date parseDate(String pattern, String date) throws ParseException {
		SimpleDateFormat formator = new SimpleDateFormat(pattern);
		return formator.parse(date);
	}
	
	
	public static Date parseDate(String pattern, String date, TimeZone zone) throws ParseException {
		SimpleDateFormat formator = new SimpleDateFormat();
		formator.setTimeZone(zone);
		formator.applyPattern(pattern);
		return formator.parse(date);
	}

	/**
	 * 返回日期中的指定域
	 * 
	 * @param date
	 * @param field
	 * @return
	 * 
	 */
	public static int get(Date date, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(field);
	}

	/**
	 * 获取当前日期所在月的第一天
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 * 
	 */
	public static String getFirstDay(String strDate, String pattern) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		cal.setTime(sdf.parse(strDate));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		String strFirstDay = sdf.format(firstDate);
		return strFirstDay;
	}

	/**
	 * 获取当前日期所在月的第一天
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 * 
	 */
	public static String getFirstDay(Date strDate, String pattern) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		cal.setTime(strDate);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		String strFirstDay = sdf.format(firstDate);
		return strFirstDay;
	}

	/**
	 * 获得某一年中指定节气的开始日
	 * 
	 * @param year 指定的年份
	 * @param num 节气在一年中的序号（如，小寒为1,大寒为2 ，冬至为24）
	 * @return yyyy-MM-dd
	 * 
	 */
	public static String getStartDate(String year, int num) {
		long[] DifferenceInMonth = new long[] { 1272060, 1275495, 1281180, 1289445, 1299225, 1310355, 1321560, 1333035,
				1342770, 1350855, 1356420, 1359045, 1358580, 1355055, 1348695, 1340040, 1329630, 1318455, 1306935,
				1297380, 1286865, 1277730, 1274550, 1271556 };
		long DifferenceInYear = 31556926;
		Calendar c = Calendar.getInstance();
		c.set(1901, 1, 1);
		c.setTimeInMillis(947120460000l);
		for (; Integer.parseInt(year) < getYear(c);) {
			c.setTimeInMillis(c.getTimeInMillis() - DifferenceInYear * 1000);
		}
		for (; Integer.parseInt(year) > getYear(c);) {
			c.setTimeInMillis(c.getTimeInMillis() + DifferenceInYear * 1000);
		}
		for (int m = 0; m < num - 1; m++) {
			c.setTimeInMillis(c.getTimeInMillis() + DifferenceInMonth[m] * 1000);
		}
		return getDate(c);
	}

	/**
	 * 获得某一年中指定节气的终止日
	 * 
	 * @param year 指定年份
	 * @param num 节气在一年中的序号（如，小寒为1,大寒为2，冬至为24）
	 * @return yyyy-MM-dd
	 * 
	 */
	public static String getEndDate(String year, int num) {
		// 放置的是每个节气对应的秒数

		long[] DifferenceInMonth = new long[] { 1272060, 1275495, 1281180, 1289445, 1299225, 1310355, 1321560, 1333035,
				1342770, 1350855, 1356420, 1359045, 1358580, 1355055, 1348695, 1340040, 1329630, 1318455, 1306935,
				1297380, 1286865, 1277730, 1274550, 1271556 };
		// 一年的平均秒数

		long DifferenceInYear = 31556926;
		Calendar c = Calendar.getInstance();
		c.set(1901, 1, 1);
		c.setTimeInMillis(947120460000l);
		for (; Integer.parseInt(year) < getYear(c);) {
			c.setTimeInMillis(c.getTimeInMillis() - DifferenceInYear * 1000);
		}
		for (; Integer.parseInt(year) > getYear(c);) {
			c.setTimeInMillis(c.getTimeInMillis() + DifferenceInYear * 1000);
		}
		for (int m = 0; m < num; m++) {
			c.setTimeInMillis(c.getTimeInMillis() + DifferenceInMonth[m] * 1000);
		}
		return getDate(c);
	}

	private static int getYear(Calendar c) {
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(date);
		return Integer.parseInt(year);
	}

	private static String getDate(Calendar c) {
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = sdf.format(date);
		return strdate;
	}

	public static int getHour(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static Date[] getYearInterval(int year) {
		Calendar cal = Calendar.getInstance();
		Date curDate = toZeroTime(TimeZone.getDefault(), new Date());
		cal.setTime(curDate);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date d1 = cal.getTime();

		cal.set(Calendar.MONTH, 11);
		Date d2 = cal.getTime();

		return new Date[] { d1, d2 };
	}

	
	/**
	 * <p> 获取某一天00:00:00的date </p>
	 * <p> 比如当前时间2016-01-13 09:36:05 090
	 * 转化后的时间为 2016-01-13 09:36:00 000 </p>
	 * @param timezone
	 * @param date
	 * @return
	 */
	public static Date getMinRounding(TimeZone timezone, Date date){
		Calendar cal = Calendar.getInstance(timezone);
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		
		return cal.getTime();
		
	}
	
	/**
	 * <p> 获取某一天00:00:00时刻的date </p>
	 * <p> 比如当前时间2016-01-13 09:36:05 090
	 * 转化后的时间为 2016-01-13 00:00:00 000 </p>
	 * @param timezone
	 * @param date
	 * @return
	 */
	public static Date toZeroDay(TimeZone timezone, Date date){
		Calendar cal = Calendar.getInstance(timezone);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
		
	}
	
	/**
	 * <p> 获取某一天23:59:59 999时刻的date </p>
	 * <p> 比如当前时间2016-01-13 09:36:05 090
	 * 转化后的时间为 2016-01-13 23:59:59 999 </p>
	 * @param timezone
	 * @param date
	 * @return
	 */
	public static Date toLastTimeOfDay(TimeZone timezone, Date date){
		Calendar cal = Calendar.getInstance(timezone);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	/**
	 * 清除日期中的秒为0
	 * 
	 * @param date
	 * @return
	 * @author yushikai1
	 * 
	 */
	public static Date toZeroSecond(TimeZone timezone, Date date) {
		Calendar cal = Calendar.getInstance(timezone);
		cal.setTime(date);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	
	
	/**
	 * 返回周一的日期
	 * @param timezone
	 * @param date
	 * @return
	 */
	public static Date getMonday(TimeZone timezone, Date date){
		Calendar cal = Calendar.getInstance(timezone);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}
	
	/**
	 * 返回日期所在月份的第一天
	 * @param timezone
	 * @param date
	 * @return
	 */
	public static Date getfirstMonthDay(TimeZone timezone, Date date){
		Calendar cal = Calendar.getInstance(timezone);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 当前时间所在年的周数
	 *
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static int getWeekOfYear(Date date, TimeZone timeZone) {
		Calendar c = new GregorianCalendar(timeZone);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 当前时间所在年的最大周数
	 *
	 * @param year
	 * @param timeZone
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year, TimeZone timeZone) {
		Calendar c = new GregorianCalendar(timeZone);
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

		return getWeekOfYear(c.getTime(), timeZone);
	}

	/**
	 * 某年的第几周的开始日期
	 *
	 * @param year
	 * @param week
	 * @param timeZone
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week, TimeZone timeZone) {
		Calendar c = new GregorianCalendar(timeZone);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime(), timeZone);
	}

	/**
	 * 某年的第几周的结束日期
	 *
	 * @param year
	 * @param week
	 * @param timeZone
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week, TimeZone timeZone) {
		Calendar c = new GregorianCalendar(timeZone);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime(), timeZone);
	}

	/**
	 * 当前时间所在周的开始日期
	 *
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date, TimeZone timeZone) {
		Calendar c = new GregorianCalendar(timeZone);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 当前时间所在周的结束日期
	 *
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date, TimeZone timeZone) {
		Calendar c = new GregorianCalendar(timeZone);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}
	

	/**
	 * 当前时间所在月第一天
	 *
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date, TimeZone timeZone) {
		Calendar cal = Calendar.getInstance(timeZone);
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 当前时间所在月最后一天
	 *
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date, TimeZone timeZone) {
		Calendar cal = Calendar.getInstance(timeZone);
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
	
	/**
	 * 判断now和date  b，在某个时区下，是否属于同一周
	 * @param now
	 * @param b
	 * @param timeZone
	 * @return
	 */
	public static boolean isSameWeekWithB(Date now,Date b,TimeZone timeZone){
		long begin = DateUtil.toZeroDay(timeZone, DateUtil.getFirstDayOfWeek(b, timeZone)).getTime();
		long end = DateUtil.toLastTimeOfDay(timeZone, DateUtil.getLastDayOfWeek(b, timeZone)).getTime();
		long nowTime = now.getTime();
		if(nowTime>=begin&&nowTime<=end)
			return true;
		return false;
	}
	
	/**
	 * 判断now和date  b，在某个时区下，是否属于同一月
	 * @param now
	 * @param b
	 * @param timeZone
	 * @return
	 */
	public static boolean isSameMonthWithB(Date now,Date b,TimeZone timeZone){
		long begin = DateUtil.toZeroDay(timeZone, DateUtil.getFirstDayOfMonth(b, timeZone)).getTime();
		long end = DateUtil.toLastTimeOfDay(timeZone, DateUtil.getLastDayOfMonth(b, timeZone)).getTime();
		long nowTime = now.getTime();
		if(nowTime>=begin&&nowTime<=end)
			return true;
		return false;
	}
	
	/**
	 * 判断now和date  b，在某个时区下，是否属于同一天
	 * @param now
	 * @param b
	 * @param timeZone
	 * @return
	 */
	public static boolean isSameDayWithB(Date now,Date b,TimeZone timeZone){
		Date dayBegin = DateUtil.toZeroDay(timeZone, DateUtil.toZeroDay(timeZone,b));
		long begin = dayBegin.getTime();
		Calendar cal = Calendar.getInstance(timeZone);
		cal.setTime(dayBegin);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		long end = cal.getTime().getTime();
		long nowTime = now.getTime();
		if(nowTime>=begin&&nowTime<=end)
			return true;
		return false;
	}
}
