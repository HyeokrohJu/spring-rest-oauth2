/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.oauth
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.oauth.configs
 * 3. 파일명 : DataSourceConfig.java
 * 4. 작성일 : 2018. 1. 31. 오후 4:10:08
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.oauth.configs;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.oauth.configs
 * 2. 타입명 : DataSourceConfig.java
 * 3. 작성일 : 2018. 1. 31. 오후 4:10:08
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */

@Configuration
public class DataSourceConfig {
	@Bean(name = "oauthDatasource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.oauth")
	public DataSource datasource( ) {
		return DataSourceBuilder.create( ).build( );
	}

	@Bean(name = "restDatasource")
	@ConfigurationProperties(prefix = "spring.datasource.rest")
	public DataSource restDatasource( ) {
		return DataSourceBuilder.create( ).build( );
	}
}
