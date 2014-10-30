package cn.sp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

	private static Logger _log = LoggerFactory.getLogger(DateUtils.class);
	public final static String DATE_FORMAT_A = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_FORMAT_B = "yyyy-MM-dd";
	
	/**
	 * 将日期格式化成指定格式的Date
	 * @param _date 日期字符串
	 * @param _format 日期格式
	 * @return 指定格式的Date
	 * @throws {@link java.text.ParseException} 
	 */
	public static Date format(String _date, String _format) throws ParseException{
		SimpleDateFormat _formater = new SimpleDateFormat(_format);
		Date _destDate = _formater.parse(_date);
		return _destDate;
	}

	/**
	 * 获取当前日期字符串
	 * 
	 * @param _format
	 *            日期格式
	 * @return
	 */
	public static String getCurrentDateStr(String _format) {
		SimpleDateFormat _formater = new SimpleDateFormat(_format);
		String _currentDateStr = _formater.format(new Date());
		return _currentDateStr;
	}

	public static String parse(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String temp = "";
		if (date != null) {
			temp = sdf.format(date);
		}
		return temp;
	}

	public static Date parseCurrentDate(String _format) {
		Date _date = null;
		SimpleDateFormat _formater = new SimpleDateFormat(_format);
		String _currentDateStr = _formater.format(new Date());
		try {
			_date = _formater.parse(_currentDateStr);
		} catch (ParseException e) {
			_log.error("[解析日期格式发生异常]", e);
		}
		return _date;
	}

	public static String parseDefault(Date date) {

		return parse(date, DATE_FORMAT_A);
	}
	
	public  Date convertToDate(XMLGregorianCalendar cal) throws Exception{
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }
}