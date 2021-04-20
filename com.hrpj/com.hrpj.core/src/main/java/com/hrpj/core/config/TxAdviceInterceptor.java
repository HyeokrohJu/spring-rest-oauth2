/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.config
 * 3. 파일명 : TxAdviceInterceptor.java
 * 4. 작성일 : 2019. 9. 25. 오후 5:18:06
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : DB트랜잭션 처리
 * </pre>
 */
package com.hrpj.core.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.config
 * 2. 타입명 : TxAdviceInterceptor.java
 * 3. 작성일 : 2019. 9. 25. 오후 5:18:06
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : DB트랜잭션 처리
 * </pre>
 */
@Aspect
@Configuration
public class TxAdviceInterceptor {

	private final Log logger = LogFactory.getLog( this.getClass( ) );
	private static final int TX_METHOD_TIMEOUT = 180;
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.hrpj..service..*ServiceImpl.*(..))";

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Bean
	public TransactionInterceptor txAdvice( ) {
		final TransactionInterceptor txAdvice = new TransactionInterceptor( );
		final Properties txAttributes = new Properties( );

		final List<RollbackRuleAttribute> rollbackRules = new ArrayList<>( );
		rollbackRules.add( new RollbackRuleAttribute( Exception.class ) );

		/** If need to add additionall exceptio, add here **/
		final DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute( TransactionDefinition.PROPAGATION_REQUIRED );
		readOnlyAttribute.setReadOnly( true );
		readOnlyAttribute.setTimeout( TX_METHOD_TIMEOUT );

		final RuleBasedTransactionAttribute writeAttribute =
			new RuleBasedTransactionAttribute( TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules );
		writeAttribute.setTimeout( TX_METHOD_TIMEOUT );

		final String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString( );
		final String writeTransactionAttributesDefinition = writeAttribute.toString( );
		logger.info( "Read Only Attributes :: {}" + readOnlyTransactionAttributesDefinition );
		logger.info( "Write Attributes :: {}" + writeTransactionAttributesDefinition );

		// read-only
		txAttributes.setProperty( "retrieve*", readOnlyTransactionAttributesDefinition );
		txAttributes.setProperty( "select*", readOnlyTransactionAttributesDefinition );
		txAttributes.setProperty( "get*", readOnlyTransactionAttributesDefinition );
		txAttributes.setProperty( "list*", readOnlyTransactionAttributesDefinition );
		txAttributes.setProperty( "search*", readOnlyTransactionAttributesDefinition );
		txAttributes.setProperty( "find*", readOnlyTransactionAttributesDefinition );
		txAttributes.setProperty( "count*", readOnlyTransactionAttributesDefinition );

		// write rollback-rule
		txAttributes.setProperty( "*", writeTransactionAttributesDefinition );

		txAdvice.setTransactionAttributes( txAttributes );
		txAdvice.setTransactionManager( transactionManager );

		return txAdvice;
	}

	@Bean
	public Advisor txAdviceAdvisor( ) {
		final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut( );
		pointcut.setExpression( AOP_POINTCUT_EXPRESSION );
		return new DefaultPointcutAdvisor( pointcut, txAdvice( ) );
	}

}
