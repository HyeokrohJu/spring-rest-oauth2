/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.utils
 * 3. 파일명 : CryptUtils.java
 * 4. 작성일 : 2018. 2. 20. 오후 3:06:33
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 암호화관련 Utils
 * </pre>
 */
package com.hrpj.core.utils;

import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import com.hrpj.core.security.Base64Utils;
import com.hrpj.core.security.MD4;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.utils
 * 2. 타입명 : CryptUtils.java
 * 3. 작성일 : 2018. 2. 20. 오후 3:06:33
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 암호화관련 Utils
 * </pre>
 */
public class CryptUtils {

	protected static final int BYTE_MASK = 0x0f;

	protected static final char[ ] HEX_DIGITS = new char[ ]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 암호화를 수행합니다.
	 *
	 * @param text
	 * @return
	 */
	public static String encrypt( String text ) {
		String result = null;
		try {
			// RWP-1644 TC 요청에 의한 교체
			if ( text == null || text.trim( ).equals( "" ) ) {
				// 공백처리를 하지 않을 경우, sun.misc 와는 달리, javax.mail api 에선 오류가 발생함으로,
				// 널처리 필수.
				return "";
			}
			final byte[ ] enc = Base64Utils.encode( text.getBytes( "UTF-8" ) );
			result = new String( enc, "UTF-8" );
		} catch (final Exception e) {
			e.printStackTrace( );
		}
		return result;
	}

	/**
	 * 복호화를 수행합니다.
	 *
	 * @param encryptText
	 * @return
	 */
	public static String decrypt( String text ) {
		String result = null;
		try {
			if ( text == null || text.trim( ).equals( "" ) ) {
				return "";
			}
			// RWP-1644 TC 요청에 의한 교체
			final byte[ ] dec = Base64Utils.decode( text.getBytes( "UTF-8" ) );
			result = new String( dec, "UTF-8" );
		} catch (final Exception e) {
			e.printStackTrace( );
		}
		return result;
	}

	/**
	 * 특수한 메시지만을 인코딩하기 위한 함수 디코딩 함수는 아직 안 만들었당. 디코딩할 이유가 없고, 파일만 구분되어 저장되면 됨.
	 *
	 * @param msgId
	 * @return
	 */
	public static String encryptHex( String msgId ) {
		final byte[ ] bytes = msgId.getBytes( );
		final char[ ] buffer = new char[ bytes.length << 1 ];

		for (int i = 0, j = 0; i < bytes.length; i++) {
			final int k = bytes[ i ];
			buffer[ j++ ] = HEX_DIGITS[ ( k >>> 4 ) & BYTE_MASK ];
			buffer[ j++ ] = HEX_DIGITS[ k & BYTE_MASK ];
		}
		return new String( buffer );
	}

	/**
	 * 사용자 패스워드 저장용 차후 컴퍼니 코드를 키로 하여 MAC 구현예정
	 *
	 * @param args
	 */
	public static String md512Nkey( String mdFunction, String dataStream ) {
		MessageDigest md;
		final String message = dataStream;
		String mdResult = "";
		try {
			md = MessageDigest.getInstance( mdFunction );

			md.update( message.getBytes( ) );
			final byte[ ] mb = md.digest( );
			String out = "";
			for (int i = 0; i < mb.length; i++) {
				final byte temp = mb[ i ];
				String s = Integer.toHexString( new Byte( temp ) );
				while (s.length( ) < 2) {
					s = "0" + s;
				}
				s = s.substring( s.length( ) - 2 );
				out += s;
			}
			mdResult = out;
		} catch (final NoSuchAlgorithmException e) {
			System.out.println( "ERROR: " + e.getMessage( ) );
		}
		return mdResult;
	}

	public static String md4key( String inputString ) throws Exception {
		final MessageDigest digester = new MD4( );
		final byte[ ] md4bytes = digester.digest( inputString.getBytes( "UnicodeLittleUnmarked" ) ); // UnicodeLittleUnmarked:
																									 // Sixteen-bit
																									 // Unicode
																									 // Transformation
																									 // Format,
																									 // little-endian
																									 // byte
																									 // order
		final char[ ] encoded = Hex.encodeHex( md4bytes );
		final String genPwd = new String( encoded );
		return genPwd;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getRamdomPassword
	 * 2. 작성일 : 2021. 2. 25. 오전 10:48:40
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 임시 비밀번호 생성
	 * </pre>
	 * 
	 * @param len
	 * @return
	 */
	public static String getRamdomPassword( int len ) {
		final char[ ] charSet = new char[ ]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int idx = 0;
		final StringBuffer sb = new StringBuffer( );

		for (int i = 0; i < len; i++) {
			idx = (int) ( charSet.length * Math.random( ) ); // 36 * 생성된 난수를
															 // Int로 추출 (소숫점제거)
			sb.append( charSet[ idx ] );
		}
		return sb.toString( );
	}

	public static void main( String[ ] args ) {
		// http://59.5.96.56/servlet/MailServiceController.woext?api=8&ssoFlag=true&msgId=%3C2133491498.01269685615903.JavaMail.root%40aspdb2%3E
		// &userId=YWRtaW4
		// %3D
		// &userDomain=
		// aHlueS5jby5rcg%3D%3D&locale=a29fS1I%3D
		// System.out.println(CryptUtils.decrypt("MTc3MjU4MjI"));
		try {
			// System.out.println(URLEncoder.encode(CryptUtils.encrypt("ko_KR"),
			// "UTF-8"));
			// System.out.println(CryptUtils.encrypt("ko_KR"));
			final String inputPwd = "1";
			final String _decStr = URLDecoder.decode( "", "UTF-8" );
			CryptUtils.decrypt( _decStr );
			// System.out.println("_res : " + _res);
			System.out.println( "1:" + encryptHex( inputPwd ) );
			System.out.println( "2:" + encrypt( inputPwd ) );
			System.out.println( "3:" + md512Nkey( "SHA-512", inputPwd ) );
			System.out.println( "4:" + md4key( inputPwd ) );
		} catch (final Exception e) {
			e.printStackTrace( );
		}

	}

}
