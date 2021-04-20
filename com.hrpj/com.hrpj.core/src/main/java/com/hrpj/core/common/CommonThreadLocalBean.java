package com.hrpj.core.common;

import java.util.Map;

import com.hrpj.core.exception.JWTInvalidException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.common
 * 2. 타입명 : CommonThreadLocalBean.java
 * 3. 작성일 : 2017. 6. 28. 오후 11:03:06
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : CommonThreadLocalBean
 * </pre>
 */
public class CommonThreadLocalBean {

	private static ThreadLocal<Map<String, Object>> jwtInfoThread = new ThreadLocal<>( );

	protected static Map<String, Object> getJwtInfo( ) {
		return jwtInfoThread.get( );
	}

	/**
	 * <pre>
	 * 1. 함수명 : setJwtInfo
	 * 2. 작성일 : 2017. 6. 27. 오후 6:11:26
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : filter before method에 추가
	 * </pre>
	 *
	 * @param jwtInfo
	 */
	public static void setJwtInfo( Map<String, Object> jwtInfo ) {
		jwtInfoThread.set( jwtInfo );
	}

	/**
	 * <pre>
	 * 1. 함수명 : removeJwtInfo
	 * 2. 작성일 : 2017. 6. 27. 오후 6:11:20
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : filter after method에 추가
	 * </pre>
	 */
	public static void removeJwtInfo( ) {
		jwtInfoThread.remove( );
	}

	/**
	 * <pre>
	 * 1. 함수명 : getUserInfo
	 * 2. 작성일 : 2017. 6. 27. 오후 6:10:21
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : JWT UserInfo 정보 반환
	 * </pre>
	 *
	 * @return
	 * @throws JWTInvalidException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getUserInfo( ) throws JWTInvalidException {
		final Map<String, Object> ui = (Map<String, Object>) getJwtInfo( ).get( "userInfo" );
		if ( ui == null ) {
			throw new JWTInvalidException( "JWT Token 정보가 존재하지 않습니다." );
		}

		return ui;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getUserid
	 * 2. 작성일 : 2017. 6. 27. 오후 6:10:10
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 로그인 사용자 인덱스를 반환합니다.
	 * </pre>
	 *
	 * @return
	 * @throws JWTInvalidException
	 */
	public static String getUserid( ) throws JWTInvalidException {
		return String.valueOf( getUserInfo( ).get( "userid" ) );
	}

	/**
	 * <pre>
	 * 1. 함수명 : getUsernm
	 * 2. 작성일 : 2019. 10. 7. 오후 5:44:04
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 로그인 사용자 이름을 반환합니다.
	 * </pre>
	 *
	 * @return
	 * @throws JWTInvalidException
	 */
	public static String getUsernm( ) throws JWTInvalidException {
		return String.valueOf( getUserInfo( ).get( "usernm" ) );
	}

	/**
	 * <pre>
	 * 1. 함수명 : getCwschema
	 * 2. 작성일 : 2017. 6. 27. 오후 6:09:56
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 로그인 사용자 스키마 정보를 반환합니다.
	 * </pre>
	 *
	 * @return
	 * @throws JWTInvalidException
	 */
	public static String getHrschema( ) throws JWTInvalidException {
		return (String) getJwtInfo( ).get( "hrschema" );
	}

	/**
	 * <pre>
	 * 1. 함수명 : getSgrp
	 * 2. 작성일 : 2019. 10. 11. 오후 3:47:54
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트그룹 정보
	 * </pre>
	 *
	 * @return
	 * @throws JWTInvalidException
	 */
	public static String getSgrp( ) throws JWTInvalidException {
		return (String) getJwtInfo( ).get( "sgrp" );
	}

	/**
	 * <pre>
	 * 1. 함수명 : getTimezone
	 * 2. 작성일 : 2017. 6. 27. 오후 6:09:50
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 타임존을 반환합니다.
	 * </pre>
	 *
	 * @return
	 * @throws JWTInvalidException
	 */
	public static String getTimezone( ) throws JWTInvalidException {
		String retStr = "";

		try {
			final Object tz = getJwtInfo( ).get( "hrtimezone" );
			if ( tz == null ) {
				retStr = "GMT+9:00";
			} else {
				retStr = (String) tz;
			}
		} catch (final Exception e) {
			retStr = "GMT+9:00";
		}

		return retStr;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getTimezonelong
	 * 2. 작성일 : 2017. 6. 27. 오후 6:09:41
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 타임존[숫자]을 반환합니다.
	 * </pre>
	 *
	 * @return
	 * @throws JWTInvalidException
	 */
	public static long getTimezonelong( ) throws JWTInvalidException {
		long retLong = 0;

		try {
			final Object tz = getJwtInfo( ).get( "hrtimezonelong" );
			if ( tz == null ) {
				retLong = 9;
			} else {
				retLong = (Long) tz;
			}
		} catch (final Exception e) {
			retLong = 9;
		}

		return retLong;
	}

	/**
	 * <pre>
	 * 1. 함수명 : existUserInfo
	 * 2. 작성일 : 2017. 6. 27. 오후 6:09:16
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : User Info 정보 유무
	 * </pre>
	 *
	 * @return
	 * @throws JWTInvalidException
	 */
	public static boolean existUserInfo( ) throws JWTInvalidException {
		try {
			final Object ui = getUserInfo( );
			if ( ui == null ) {
				return false;
			}
		} catch (final Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getClientIp
	 * 2. 작성일 : 2017. 6. 27. 오후 6:09:08
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : ClientIp 조회
	 * </pre>
	 *
	 * @return
	 */
	public static String getClientIp( ) {
		return (String) getJwtInfo( ).get( "remoteaddr" );
	}

	/**
	 * <pre>
	 * 1. 함수명 : getClientHost
	 * 2. 작성일 : 2019. 10. 4. 오후 3:59:08
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : ClinetHost 조회
	 * </pre>
	 *
	 * @return
	 */
	public static String getClientHost( ) {
		return (String) getJwtInfo( ).get( "remotehost" );
	}

	/**
	 * <pre>
	 * 1. 함수명 : getBrowser
	 * 2. 작성일 : 2017. 6. 27. 오후 6:08:56
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : Client Browser 정보 조회
	 * </pre>
	 *
	 * @return
	 */
	public static String getBrowser( ) {
		final String userAgent = (String) getJwtInfo( ).get( "User-Agent" );
		String browser = "";
		if ( userAgent.indexOf( "Trident" ) > 0 || userAgent.indexOf( "MSIE" ) > 0 ) {
			browser = "IE";
		} else if ( userAgent.indexOf( "Opera" ) > 0 || userAgent.indexOf( "OPR" ) > 0 ) {
			browser = "Opera";
		} else if ( userAgent.indexOf( "Firefox" ) > 0 ) {
			browser = "Firefox";
		} else if ( userAgent.indexOf( "Safari" ) > 0 ) {
			if ( userAgent.indexOf( "Chrome" ) > 0 ) {
				browser = "Chrome";
			} else {
				browser = "Safari";
			}
		}

		return browser;
	}

}
