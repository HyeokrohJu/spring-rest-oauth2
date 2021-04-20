package com.hrpj.core.exception;

import java.util.Map;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.exception
 * 2. 타입명 : BusinessLogicException.java
 * 3. 작성일 : 2017. 6. 28. 오후 11:05:12
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : BusinessLogicException
 * </pre>
 */
public class BusinessLogicException extends RuntimeException {

	private static final long serialVersionUID = 2919912205062866373L;
	private String code;
	private Map<String, Object> reqMap;

	public BusinessLogicException( ) {
		super( );
	}

	public BusinessLogicException( String msg ) {
		super( msg );
	}

	public BusinessLogicException( String msg, Throwable cause ) {
		super( msg, cause );
	}

	public BusinessLogicException( String msg, String code ) {
		super( msg );
		this.code = code;
	}

	public BusinessLogicException( String msg, String code, Map<String, Object> reqMap ) {
		super( msg );
		this.code = code;
		this.reqMap = reqMap;
	}

	public BusinessLogicException( String msg, String code, Throwable cause ) {
		super( msg, cause );
		this.code = code;
	}

	public BusinessLogicException( String msg, String code, Map<String, Object> reqMap, Throwable cause ) {
		super( msg, cause );
		this.code = code;
		this.reqMap = reqMap;
	}

	public String getCode( ) {
		return this.code;
	}

	public Map<String, Object> getReqMap( ) {
		return this.reqMap;
	}

}
