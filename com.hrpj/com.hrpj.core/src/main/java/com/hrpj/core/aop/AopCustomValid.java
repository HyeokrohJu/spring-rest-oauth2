/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.aop
 * 3. 파일명 : AopCustomValid.java
 * 4. 작성일 : 2019. 10. 1. 오후 9:41:58
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : Validation AOP
 * </pre>
 */
package com.hrpj.core.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.exception.CustomValidException;
import com.hrpj.core.vo.ResponseErrorVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.aop
 * 2. 타입명 : AopCustomValid.java
 * 3. 작성일 : 2019. 10. 1. 오후 9:41:58
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Validation AOP
 * </pre>
 */
@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
public class AopCustomValid {

	private final Log logger = LogFactory.getLog( this.getClass( ) );

	/**
	 * <pre>
	 * 1. 함수명 : getAnnotatedArg
	 * 2. 작성일 : 2019. 10. 1. 오후 11:08:37
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : @CustomValidParam arguments 추출
	 * </pre>
	 *
	 * @param joinPoint
	 * @return
	 */
	private List<Object> getAnnotatedArg( ProceedingJoinPoint joinPoint ) {
		final List<Object> result = new ArrayList<>( );
		final MethodSignature signature = (MethodSignature) joinPoint.getSignature( );
		final Method method = signature.getMethod( );

		final Object[ ] objs = joinPoint.getArgs( );

		final Parameter[ ] parameters = method.getParameters( );
		for (int i = 0; i < parameters.length; i++) {
			final Parameter parameter = parameters[ i ];
			final Annotation[ ] annotations = parameter.getAnnotations( );
			for (final Annotation annotation : annotations) {
				if ( annotation.annotationType( ) == CustomValidParam.class ) {
					result.add( objs[ i ] );
					break;
				}
			}
		}

		return result;
	}

	/**
	 * <pre>
	 * 1. 함수명 : customValidAop
	 * 2. 작성일 : 2019. 10. 1. 오후 11:09:26
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : @CustomValid 체크필드 Validation
	 * </pre>
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	@Around("@annotation(com.hrpj.core.annotation.CustomValid)")
	public Object customValidAop( ProceedingJoinPoint joinPoint ) throws Throwable {
		final HttpServletRequest req = ( (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes( ) ).getRequest( );
		Map<String, Object> paramMap = null;
		final String signatureString = joinPoint.getSignature( ).toShortString( );
		logger.debug( "Aspect:" + signatureString + "start.customValidAop" );
		Object result = null;
		final long start = System.currentTimeMillis( );
		try {
			final MethodSignature signature = (MethodSignature) joinPoint.getSignature( );
			final Method method = signature.getMethod( );
			final CustomValid customValid = method.getAnnotation( CustomValid.class );

			final String[ ] validField = customValid.validField( );

			final List<Object> AnnoArg = this.getAnnotatedArg( joinPoint );

			for (final Object obj : AnnoArg) {
				paramMap = (Map<String, Object>) obj;
				for (final String field : validField) {
					if ( !paramMap.containsKey( field ) ) {
						throw new CustomValidException(
							"[" + signatureString + "]:::" + StatusCode.VALID_ERR_CD.getStatusMsg( ), StatusCode.VALID_ERR_CD.getStatusCd( ),
							validField );
					}
				}
			}

			result = joinPoint.proceed( );

		} catch (final CustomValidException e) {
			e.printStackTrace( );
			final ResponseErrorVo resVo = new ResponseErrorVo( );
			resVo.setStatusCd( e.getCode( ) );
			resVo.setStatusMsg( e.getMessage( ) );
			resVo.setReqMap( paramMap );
			resVo.setReqUri( req.getRequestURI( ) );
			resVo.setReqMethod( req.getMethod( ) );

			final Map<String, Object> errDetail = new HashMap<>( );
			errDetail.put( "errClass", e.getStackTrace( )[ 0 ].getClassName( ) );
			errDetail.put( "errMethod", e.getStackTrace( )[ 0 ].getMethodName( ) );
			errDetail.put( "errLinenum", e.getStackTrace( )[ 0 ].getLineNumber( ) );
			errDetail.put( "validField", e.getValidField( ) );
			resVo.setErrDetail( errDetail );

			result = resVo;
		} finally {
			final long finish = System.currentTimeMillis( );
			logger.debug( signatureString + "end.customValidAop" );
			logger.debug( signatureString + ":duration:" + ( finish - start ) + "ms" );
		}
		return result;
	}

}
