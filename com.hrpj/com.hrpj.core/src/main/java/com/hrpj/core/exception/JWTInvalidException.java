package com.hrpj.core.exception;

import java.io.Serializable;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.exception
 * 2. 타입명 : JWTInvalidException.java
 * 3. 작성일 : 2017. 6. 28. 오후 11:05:25
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : JWTInvalidException
 * </pre>
 */
public class JWTInvalidException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -4382298772955402633L;

	private String errMsg = null;

	public JWTInvalidException( ) {
		super( );
	}

	public JWTInvalidException( String msg ) {
		super( msg );
		this.errMsg = msg;
	}

	@Override
	public String toString( ) {
		return errMsg != null ? errMsg : "system.jwttokenalert";
	}

}
