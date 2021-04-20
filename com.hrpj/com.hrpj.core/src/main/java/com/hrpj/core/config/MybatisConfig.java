/**
 * <pre>
 * 1. 프로젝트명 : restful
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.config
 * 3. 파일명 : MybatisConfig.java
 * 4. 작성일 : 2017. 6. 20. 오후 5:35:46
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.core.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.config
 * 2. 타입명 : MybatisConfig.java
 * 3. 작성일 : 2017. 6. 28. 오후 11:21:38
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Mybatis 설정
 * </pre>
 */
@Configuration
// @MapperScan(value = { "com.hrpj.**.dao" })
@EnableTransactionManagement
public class MybatisConfig {

	@Bean(name = "basedatasource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource( ) {
		return DataSourceBuilder.create( ).build( );
	}

	@Bean
	public PlatformTransactionManager transactionManager( ) {
		final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager( dataSource( ) );
		transactionManager.setGlobalRollbackOnParticipationFailure( false );
		return transactionManager;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory( @Qualifier("basedatasource") DataSource datasource ) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean( );
		sqlSessionFactory.setDataSource( datasource );
		sqlSessionFactory.setConfigLocation( new PathMatchingResourcePatternResolver( ).getResource( "classpath:configs/mybatis-config.xml" ) );
		sqlSessionFactory.setMapperLocations( new PathMatchingResourcePatternResolver( ).getResources( "classpath*:mapper/**/*.xml" ) );
		return sqlSessionFactory.getObject( );
	}

	@Bean
	public SqlSessionTemplate sqlSession( SqlSessionFactory sqlSessionFactory ) {
		return new SqlSessionTemplate( sqlSessionFactory );
	}

}
