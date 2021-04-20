/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.utils
 * 3. 파일명 : UserAgent.java
 * 4. 작성일 : 2018. 1. 31. 오후 2:43:36
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : UserAgent 관련
 * </pre>
 */

package com.hrpj.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.utils
 * 2. 타입명 : UserAgent.java
 * 3. 작성일 : 2018. 1. 31. 오후 2:43:36
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : UserAgent 관련
 * </pre>
 */

public class UserAgent {

	/**
	 * <pre>
	 * 1. 함수명 : getHostName
	 * 2. 작성일 : 2019. 10. 4. 오후 5:49:38
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : Local 호스트명 조회
	 * </pre>
	 *
	 * @return
	 */
	public static String getLocalHostName( ) {
		try {
			return InetAddress.getLocalHost( ).getHostName( );
		} catch (final UnknownHostException e) {
			throw new RuntimeException( e.getMessage( ), e );
		}
	}

	/**
	 * <pre>
	 * 1. 함수명 : getAddrName
	 * 2. 작성일 : 2019. 10. 4. 오후 5:49:18
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : Local IP주소 조회
	 * </pre>
	 *
	 * @return
	 */
	public static String getLocalAddrName( ) {
		try {
			return InetAddress.getLocalHost( ).getHostAddress( );
		} catch (final UnknownHostException e) {
			throw new RuntimeException( e.getMessage( ), e );
		}
	}

	/**
	 * <pre>
	 * 1. 함수명 : getClientIp
	 * 2. 작성일 : 2019. 10. 4. 오후 6:00:02
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : Client IP 조회
	 * </pre>
	 *
	 * @param request
	 * @return
	 */
	public static String getClientIp( HttpServletRequest request ) {
		String ip = null;
		ip = request.getHeader( "X-Forwarded-For" );
		if ( ip == null || ip.length( ) == 0 || "unknown".equalsIgnoreCase( ip ) ) {
			ip = request.getHeader( "Proxy-Client-IP" );
		}
		if ( ip == null || ip.length( ) == 0 || "unknown".equalsIgnoreCase( ip ) ) {
			ip = request.getHeader( "WL-Proxy-Client-IP" );
		}
		if ( ip == null || ip.length( ) == 0 || "unknown".equalsIgnoreCase( ip ) ) {
			ip = request.getHeader( "HTTP_CLIENT_IP" );
		}
		if ( ip == null || ip.length( ) == 0 || "unknown".equalsIgnoreCase( ip ) ) {
			ip = request.getHeader( "X-FORWARDED-FOR" );
		}
		if ( ip == null || ip.length( ) == 0 || "unknown".equalsIgnoreCase( ip ) ) {
			ip = request.getHeader( "HTTP_X_FORWARDED_FOR" );
		}
		if ( ip == null || ip.length( ) == 0 || "unknown".equalsIgnoreCase( ip ) ) {
			ip = request.getHeader( "X-Real-IP" );
		}
		if ( ip == null || ip.length( ) == 0 || "unknown".equalsIgnoreCase( ip ) ) {
			ip = request.getHeader( "X-RealIP" );
		}
		if ( ip == null || ip.length( ) == 0 || "unknown".equalsIgnoreCase( ip ) ) {
			ip = request.getHeader( "REMOTE_ADDR" );
		}
		if ( ip == null || ip.length( ) == 0 || "unknown".equalsIgnoreCase( ip ) ) {
			ip = request.getRemoteAddr( );
		}
		return ip;
	}

	/**
	 * <pre>
	 * 1. 함수명 : toUTF8
	 * 2. 작성일 : 2017. 1. 18. 오후 7:05:12
	 * 3. 작성자 : Goodcen R&D
	 * 4. 설명   : userAgent 체크
	 * </pre>
	 *
	 * @param s
	 * @return Y : 모바일, N : 웹
	 */
	public static String mobileCheck( String userAgent ) {
		String result = "";

		final String[ ] mobileos = { "Mobile", "iPhone", "iPod", "Android", "BlackBerry", "Windows CE", "Nokia", "Webos", "Opera Mini",
			"SonyEricsson", "Opera Mobi", "IEMobile" };

		int j = -1;
		int check = 0;
		if ( userAgent != null && !userAgent.equals( "" ) ) {
			for (int i = 0; i < mobileos.length; i++) {
				j = userAgent.indexOf( mobileos[ i ] );
				if ( j > -1 ) {
					check++;
				}
			}
		}

		if ( check > 0 ) {
			result = "Y";
		} else {
			result = "N";
		}
		return result;
	}

}
