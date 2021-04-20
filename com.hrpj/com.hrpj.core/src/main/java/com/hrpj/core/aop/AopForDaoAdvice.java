package com.hrpj.core.aop;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hrpj.core.common.CommonThreadLocalBean;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.aop
 * 2. 타입명 : AopForDaoAdvice.java
 * 3. 작성일 : 2017. 6. 28. 오후 11:03:57
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Dao before 처리를 위한 AOP pointcut설정
 * </pre>
 */
@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
public class AopForDaoAdvice {

	private final Log logger = LogFactory.getLog( this.getClass( ) );

	@Pointcut("execution(public * com.hrpj..dao..get*(..))")
	public void aspectTargetGetForDao( ) {

	}

	@Pointcut("execution(public * com.hrpj..dao..select*(..))")
	public void aspectTargetSelectForDao( ) {

	}

	@Pointcut("execution(public * com.hrpj..dao..insert*(..))")
	public void aspectTargetInsertForDao( ) {

	}

	@Pointcut("execution(public * com.hrpj..dao..update*(..))")
	public void aspectTargetUpdateForDao( ) {

	}

	@Pointcut("execution(public * com.hrpj..dao..delete*(..))")
	public void aspectTargetDeleteForDao( ) {

	}

	@Pointcut("execution(public * com.hrpj..dao..sp*(..))")
	public void aspectTargetProcedureForDao( ) {

	}

	@SuppressWarnings("unchecked")
	@Around(
		value = "aspectTargetGetForDao() || aspectTargetSelectForDao() || aspectTargetInsertForDao() || aspectTargetUpdateForDao() || aspectTargetDeleteForDao() || aspectTargetProcedureForDao()")
	public Object setDaoGlobalInfo( ProceedingJoinPoint joinPoint ) throws Throwable {
		final String signatureString = joinPoint.getSignature( ).toShortString( );
		logger.debug( "Aspect:" + signatureString + "start.setDaoGlobalInfo" );
		Object result = null;
		final long start = System.currentTimeMillis( );
		Map<String, Object> paramMap = null;
		try {
			final Object[ ] objs = joinPoint.getArgs( );
			for (final Object obj : objs) {
				if ( obj instanceof Map<?, ?> ) {
					( (Map<String, Object>) obj ).put( "hrschema", CommonThreadLocalBean.getHrschema( ) );
					( (Map<String, Object>) obj ).put( "hrtime", CommonThreadLocalBean.getTimezonelong( ) );
					( (Map<String, Object>) obj ).put( "hruserid", CommonThreadLocalBean.getUserid( ) );
					( (Map<String, Object>) obj ).put( "hrusernm", CommonThreadLocalBean.getUsernm( ) );
					( (Map<String, Object>) obj ).put( "hrdeptid", CommonThreadLocalBean.getUserInfo( ).get( "deptid" ) );
					( (Map<String, Object>) obj ).put( "hrcompid", CommonThreadLocalBean.getUserInfo( ).get( "compid" ) );
					( (Map<String, Object>) obj ).put( "hrcip", CommonThreadLocalBean.getClientIp( ) );

					paramMap = (Map<String, Object>) obj;
				}
			}
			result = joinPoint.proceed( );

		} catch (final Exception e) {
			e.printStackTrace( );
			throw new BusinessLogicException(
				"[" + signatureString + "]:::" + StatusCode.DB_ERR_CD.getStatusMsg( ), StatusCode.DB_ERR_CD.getStatusCd( ), paramMap, e );
		} finally {
			final long finish = System.currentTimeMillis( );
			logger.debug( signatureString + "end.setDaoGlobalInfo" );
			logger.debug( signatureString + ":duration:" + ( finish - start ) + "ms" );
		}
		return result;
	}

}
