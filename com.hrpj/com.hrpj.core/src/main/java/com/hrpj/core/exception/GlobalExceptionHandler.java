/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.exception
 * 3. 파일명 : GlobalExceptionHandler.java
 * 4. 작성일 : 2019. 10. 4. 오전 10:31:05
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : Controller 공통 Exception 처리
 * </pre>
 */
package com.hrpj.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.hrpj.core.vo.ResponseErrorVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.exception
 * 2. 타입명 : GlobalExceptionHandler.java
 * 3. 작성일 : 2019. 10. 4. 오전 10:31:05
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Controller 공통 Exception 처리
 * </pre>
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	/**
	 * <pre>
	 * 1. 함수명 : handleException
	 * 2. 작성일 : 2019. 10. 4. 오후 4:48:15
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : BusinessLoginException Global Handler
	 * </pre>
	 *
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = BusinessLogicException.class)
	public Object handleException( HttpServletRequest req, BusinessLogicException e ) {
		e.printStackTrace( );

		final ResponseErrorVo resErrVo = new ResponseErrorVo( );
		resErrVo.setStatusCd( e.getCode( ) );
		resErrVo.setStatusMsg( e.getMessage( ) );
		resErrVo.setReqMap( e.getReqMap( ) );
		resErrVo.setReqUri( req.getRequestURI( ) );
		resErrVo.setReqMethod( req.getMethod( ) );

		final Map<String, Object> errDetail = new HashMap<>( );
		errDetail.put( "errClass", e.getStackTrace( )[ 0 ].getClassName( ) );
		errDetail.put( "errMethod", e.getStackTrace( )[ 0 ].getMethodName( ) );
		errDetail.put( "errLinenum", e.getStackTrace( )[ 0 ].getLineNumber( ) );
		resErrVo.setErrDetail( errDetail );

		return resErrVo;
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	public Object handleException( HttpServletRequest req, AccessDeniedException e ) {
		e.printStackTrace( );

		final ResponseErrorVo resErrVo = new ResponseErrorVo( );
		resErrVo.setStatusCd( "0401" );
		resErrVo.setStatusMsg( e.getMessage( ) );
		resErrVo.setReqUri( req.getRequestURI( ) );
		resErrVo.setReqMethod( req.getMethod( ) );

		final Map<String, Object> errDetail = new HashMap<>( );
		errDetail.put( "errClass", e.getStackTrace( )[ 0 ].getClassName( ) );
		errDetail.put( "errMethod", e.getStackTrace( )[ 0 ].getMethodName( ) );
		errDetail.put( "errLinenum", e.getStackTrace( )[ 0 ].getLineNumber( ) );
		resErrVo.setErrDetail( errDetail );

		return resErrVo;
	}

}
