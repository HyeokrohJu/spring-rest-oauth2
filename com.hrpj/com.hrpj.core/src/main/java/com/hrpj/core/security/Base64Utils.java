/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.security
 * 3. 파일명 : Base64Utils.java
 * 4. 작성일 : 2018. 2. 20. 오후 3:10:15
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 암호화 Base64
 * </pre>
 */
package com.hrpj.core.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.mail.internet.MimeUtility;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.security
 * 2. 타입명 : Base64Utils.java
 * 3. 작성일 : 2018. 2. 20. 오후 3:10:15
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 암호화 Base64
 * </pre>
 */
public class Base64Utils {

	/**
	 * <pre>
	 * 1. 함수명 : encode
	 * 2. 작성일 : 2019. 10. 10. 오전 11:05:50
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : Base64암호화
	 * </pre>
	 *
	 * @param b
	 * @return
	 * @throws Exception
	 */
	public static byte[ ] encode( byte[ ] b ) throws Exception {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream( );
		final OutputStream b64os = MimeUtility.encode( baos, "base64" );
		b64os.write( b );
		b64os.close( );
		return baos.toByteArray( );
	}

	/**
	 * <pre>
	 * 1. 함수명 : decode
	 * 2. 작성일 : 2019. 10. 10. 오전 11:05:59
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : Base64복호화
	 * </pre>
	 *
	 * @param b
	 * @return
	 * @throws Exception
	 */
	public static byte[ ] decode( byte[ ] b ) throws Exception {
		final ByteArrayInputStream bais = new ByteArrayInputStream( b );
		final InputStream b64is = MimeUtility.decode( bais, "base64" );
		final byte[ ] tmp = new byte[ b.length ];
		final int n = b64is.read( tmp );
		final byte[ ] res = new byte[ n ];
		System.arraycopy( tmp, 0, res, 0, n );
		return res;
	}

}
