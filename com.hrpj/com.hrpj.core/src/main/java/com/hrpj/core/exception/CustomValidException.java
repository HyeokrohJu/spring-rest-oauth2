/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.exception
 * 3. 파일명 : CustomVaildException.java
 * 4. 작성일 : 2019. 10. 1. 오후 10:23:47
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : CustomValid Exception 처리
 * </pre>
 */
package com.hrpj.core.exception;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.exception
 * 2. 타입명 : CustomVaildException.java
 * 3. 작성일 : 2019. 10. 1. 오후 10:23:47
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : CustomValid Exception 처리
 * </pre>
 */
public class CustomValidException extends RuntimeException {

	private static final long serialVersionUID = 2755186000818091764L;

	private String code;

	private String[ ] validField;

	public CustomValidException( ) {
		super( );
	}

	public CustomValidException( String msg ) {
		super( msg );
	}

	public CustomValidException( String msg, Throwable cause ) {
		super( msg, cause );
	}

	public CustomValidException( String msg, String code ) {
		super( msg );
		this.code = code;
	}

	public CustomValidException( String msg, String code, String[ ] validField ) {
		super( msg );
		this.code = code;
		this.validField = validField;
	}

	public CustomValidException( String msg, String code, Throwable cause ) {
		super( msg, cause );
		this.code = code;
	}

	public String getCode( ) {
		return this.code;
	}

	public String[ ] getValidField( ) {
		return this.validField;
	}

}
