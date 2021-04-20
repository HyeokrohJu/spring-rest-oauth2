/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.utils
 * 3. 파일명 : StringUtils.java
 * 4. 작성일 : 2018. 1. 31. 오후 2:27:57
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : String 관련 Utils
 * </pre>
 */

package com.hrpj.core.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.utils
 * 2. 타입명 : StringUtils.java
 * 3. 작성일 : 2018. 1. 31. 오후 2:27:57
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : String 관련 Utils
 * </pre>
 */

public class StringUtils {

	private static final String MONEY_PATTERN = "#,##0"; // 금액 포맷.

	/**
	 * 패턴 형식으로 숫자를 포맷하여 문자열을 얻는다.<br>
	 * java.text.NumberFormat을 사용하고 있으니 보다 상세한 내용은 <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/text/NumberFormat.html">java.text.NumberFormat.format(long)</a>을
	 * 참조하세요.
	 *
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static String formatNumber( long value, String pattern ) {
		final NumberFormat numberFormat = new DecimalFormat( pattern );
		return numberFormat.format( value );
	}

	/**
	 * 패턴 형식으로 숫자를 포맷하여 문자열을 얻는다.<br>
	 * java.text.NumberFormat을 사용하고 있으니 보다 상세한 내용은 <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/text/NumberFormat.html">java.text.NumberFormat.format(double)</a>을
	 * 참조하세요.
	 *
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static String formatNumber( double value, String pattern ) {
		final NumberFormat numberFormat = new DecimalFormat( pattern );
		return numberFormat.format( value );
	}

	/**
	 * 패턴 형식으로 숫자를 포맷하여 문자열을 얻는다.<br>
	 * java.text.NumberFormat을 사용하고 있으니 보다 상세한 내용은 <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/text/NumberFormat.html">java.text.NumberFormat.format(Object)</a>을
	 * 참조하세요.
	 *
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static String formatNumber( Number value, String pattern ) {
		final NumberFormat numberFormat = new DecimalFormat( pattern );
		return numberFormat.format( value );
	}

	/**
	 * 주어진 숫자를 금액 포맷하여 문자열을 얻는다.
	 *
	 * @param value
	 * @return
	 */
	public static String formatMoney( long value ) {
		return formatNumber( value, MONEY_PATTERN );
	}

	/**
	 * 주어진 숫자를 금액 포맷하여 문자열을 얻는다.
	 *
	 * @param value
	 * @return
	 */
	public static String formatMoney( int value ) {
		return formatNumber( value, MONEY_PATTERN );
	}

	/**
	 * 주어진 숫자를 금액 포맷하여 문자열을 얻는다.
	 *
	 * @param value
	 * @return
	 */
	public static String formatMoney( Number value ) {
		return formatNumber( value, MONEY_PATTERN );
	}

	/**
	 * Null 체크
	 *
	 * @return
	 */
	public static boolean isNull( String obj ) {
		if ( obj == null || obj.equals( "" ) || obj.equals( "null" ) || obj.equals( "undefined" ) ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Null 체크
	 *
	 * @return
	 */
	public static boolean isNull( Object obj ) {
		if ( obj == null || obj.equals( "" ) || obj.equals( "null" ) || obj.equals( "undefined" ) ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Map에 대한 초기값 설정
	 *
	 * @return
	 */
	public static String getDefaultString( Object obj, String defaultValue ) {
		Object resultValue = obj;
		if ( isNull( obj ) ) {
			resultValue = defaultValue;
		}
		return resultValue.toString( );
	}

	/**
	 * <pre>
	 * 1. 함수명 : getParseInt
	 * 2. 작성일 : 2019. 10. 8. 오후 2:21:48
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : Object to Integer
	 * </pre>
	 *
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static int getParseInt( Object obj, String defaultValue ) {
		return Integer.parseInt( getDefaultString( obj, defaultValue ) );
	}

	/**
	 * <pre>
	 * 1. 함수명 : toUTF8
	 * 2. 작성일 : 2016. 11. 9. 오전 11:08:25
	 * 3. 작성자 : Goodcen R&D
	 * 4. 설명   : String UTF-8 변환
	 * </pre>
	 *
	 * @param s
	 * @return
	 */
	public static String toUTF8( String s ) {
		String result = "";
		try {
			result = new String( s.getBytes( "8859_1" ), "UTF-8" ); // UTF8로 변환
		} catch (final Exception e) {
			result = "";
		}
		return result;
	}

}
