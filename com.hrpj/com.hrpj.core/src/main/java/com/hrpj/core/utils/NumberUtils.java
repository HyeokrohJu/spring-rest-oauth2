/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.utils
 * 3. 파일명 : NumberUtils.java
 * 4. 작성일 : 2018. 1. 31. 오후 2:29:32
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : Number 관련 Utils
 * </pre>
 */

package com.hrpj.core.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.utils
 * 2. 타입명 : NumberUtils.java
 * 3. 작성일 : 2018. 1. 31. 오후 2:29:32
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Number 관련 Utils
 * </pre>
 */

public class NumberUtils {

	/**
	 * 패턴 형식의 문자열을 파싱하여 Number 객체를 얻는다.<br>
	 * java.text.NumberFormat을 사용하고 있으니 보다 상세한 내용은 <a href=
	 * "http://java.sun.com/javase/6/docs/api/java/text/NumberFormat.html">java.text.NumberFormat.parse(String)</a>을
	 * 참조하세요.
	 *
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static Number parseNumber( String value, String pattern ) {
		try {
			final NumberFormat numberFormat = new DecimalFormat( pattern );
			return numberFormat.parse( value );
		} catch (final ParseException e) {
			throw new RuntimeException( e );
		}
	}

}
