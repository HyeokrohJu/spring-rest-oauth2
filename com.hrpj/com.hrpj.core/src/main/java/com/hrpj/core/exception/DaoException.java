/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.exception
 * 3. 파일명 : DaoException.java
 * 4. 작성일 : 2019. 9. 18. 오전 11:33:29
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : Dao예외 처리
 * </pre>
 */
package com.hrpj.core.exception;

import org.springframework.dao.DataAccessException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.exception
 * 2. 타입명 : DaoException.java
 * 3. 작성일 : 2019. 9. 18. 오전 11:33:29
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Dao예외처리
 * </pre>
 */
public class DaoException extends DataAccessException {

	private static final long serialVersionUID = 2726774781024056495L;

	/**
	 * @param msg
	 */
	public DaoException( String msg ) {
		super( msg );
	}

	/**
	 * @param msg
	 * @param e
	 */
	public DaoException( String msg, Throwable e ) {
		super( msg, e );
	}

}
