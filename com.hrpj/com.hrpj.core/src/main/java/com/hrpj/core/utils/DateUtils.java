/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.utils
 * 3. 파일명 : DateUtils.java
 * 4. 작성일 : 2018. 1. 31. 오후 2:28:08
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 날짜 관련 Utils
 * </pre>
 */

package com.hrpj.core.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.utils
 * 2. 타입명 : DateUtils.java
 * 3. 작성일 : 2018. 1. 31. 오후 2:28:08
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 날짜 관련 Utils
 * </pre>
 */

public class DateUtils {

	/**
	 * 패턴 형식으로 일시를 포맷하여 문자열을 얻는다.<br>
	 * java.text.DateFormat을 사용하고 있으니 보다 상세한 내용은 <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/text/DateFormat.html">java.text.DateFormat.format(java.util.Date)</a>을
	 * 참조하세요.
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate( Date date, String pattern ) {
		final DateFormat dateFormat = new SimpleDateFormat( pattern );
		return dateFormat.format( date );
	}

	/**
	 * 패턴 형식으로 일시를 포맷하여 문자열을 얻는다.<br>
	 * java.text.DateFormat을 사용하고 있으니 보다 상세한 내용은 <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/text/DateFormat.html">java.text.DateFormat.format(java.util.Date)</a>을
	 * 참조하세요.
	 *
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String formatDate( long time, String pattern ) {
		return formatDate( new Date( time ), pattern );
	}

	/**
	 * 문자열의 일시를 패턴 형식으로 파싱하여 java.util.Date 객체를 얻는다.<br>
	 * java.text.DateFormat을 사용하고 있으니 보다 상세한 내용은 <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/text/DateFormat.html">java.text.DateFormat.parse(java.lang.String)</a>을
	 * 참조하세요.
	 *
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static Date parseDate( String value, String pattern ) {
		try {
			final DateFormat dateFormat = new SimpleDateFormat( pattern );
			return dateFormat.parse( value );
		} catch (final ParseException e) {
			throw new RuntimeException( e );
		}
	}

	/**
	 * 문자열의 일시를 패턴 형식으로 파싱하여 java.sql.Timestamp 객체를 얻는다.<br>
	 *
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static Timestamp parseTimestamp( String value, String pattern ) {
		final Date date = parseDate( value, pattern );
		return new Timestamp( date.getTime( ) );
	}

	/**
	 * 현재 시간의 java.sql.Timestamp를 얻는다.
	 *
	 * @return
	 */
	public static Timestamp getCurrentTimestamp( ) {
		return new Timestamp( System.currentTimeMillis( ) );
	}

	/**
	 * 현재 시간의 java.util.Date 를 얻는다.
	 *
	 * @return
	 */
	public static Date getCurrentDate( ) {
		return new Date( System.currentTimeMillis( ) );
	}

	/**
	 * 계산할 일자값으로 date를 얻는다. 일자값이 음수이면 과거 date를 얻고, 일자값이 양수이면 미래값을 얻는다.
	 *
	 * @param value
	 *            계산할 일자값.
	 * @return
	 */
	public static Date getDate( long value ) {
		final long dates = value * 24L * 60L * 60L * 1000L;
		return new Date( System.currentTimeMillis( ) + dates );
	}

	/**
	 * 날짜 형식 변경
	 *
	 * @param dt
	 * @param defPattern
	 * @param nwPattern
	 * @return
	 */
	public static String dateStyle( String dt, String defPattern, String nwPattern ) {
		String retVal = "";
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat( defPattern );
			final SimpleDateFormat refSdf = new SimpleDateFormat( nwPattern );
			final Date d = sdf.parse( dt );
			final Calendar ca = Calendar.getInstance( );
			ca.setTime( d );

			retVal = refSdf.format( ca.getTime( ) );
		} catch (final Exception e) {
			e.printStackTrace( );
		}

		return retVal;
	}

	/**
	 * Date -> Timestamp
	 *
	 * @return
	 */
	public static Timestamp getDateToTimestamp( Date dt ) {
		final Timestamp tTime = new Timestamp( dt.getTime( ) );

		return tTime;
	}

	/**
	 * String -> Timestamp 2009-03-20 10:20:30.0; // 형식을 지켜야 함
	 *
	 * @return
	 */
	public static Timestamp getStringToTimestamp( String dtStr ) {
		final Timestamp tTime = Timestamp.valueOf( dtStr + ".0" );

		return tTime;
	}

}
