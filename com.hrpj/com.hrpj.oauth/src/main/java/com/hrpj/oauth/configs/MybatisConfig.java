/**
 * <pre>
 * 1. 프로젝트명 : restful
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.oauth.configs
 * 3. 파일명 : MybatisConfig.java
 * 4. 작성일 : 2017. 6. 20. 오후 5:35:46
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.oauth.configs;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.oauth.configs
 * 2. 타입명 : MybatisConfig.java
 * 3. 작성일 : 2017. 6. 29. 오후 9:21:34
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */
@Configuration
@MapperScan(basePackages = { "com.hrpj.**.dao" }, sqlSessionFactoryRef = "oracleSqlSessionFactory")
@EnableTransactionManagement
public class MybatisConfig {

	@Bean(name = "oracleSqlSessionFactory")
	public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("restDatasource") DataSource oracleDataSource,
			ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(oracleDataSource);
		sqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:configs/mybatis-config.xml"));
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}

	@Bean(name = "oracleSqlSessionTemplate")
	public SqlSessionTemplate sqlSession(SqlSessionFactory oracleSqlSessionFactory) {
		return new SqlSessionTemplate(oracleSqlSessionFactory);
	}
}
