/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.oauth
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.oauth.exception
 * 3. 파일명 : UserNotFountException.java
 * 4. 작성일 : 2018. 2. 1. 오후 5:33:57
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */
package com.hrpj.oauth.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.oauth.exception
 * 2. 타입명 : UserNotFountException.java
 * 3. 작성일 : 2018. 2. 1. 오후 5:33:57
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends AuthenticationException implements Serializable {
	private static final long serialVersionUID = -4382298772955402633L;

	private String errMsg = null;

	public UserNotFoundException( String msg ) {
		super( msg );
		this.errMsg = msg;
	}

	@Override
	public String toString( ) {
		return errMsg != null ? errMsg : "UserNotFoundException";
	}
}
