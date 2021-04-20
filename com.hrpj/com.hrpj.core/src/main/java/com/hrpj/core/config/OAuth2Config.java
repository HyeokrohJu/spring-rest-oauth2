/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.config
 * 3. 파일명 : OAuth2Config.java
 * 4. 작성일 : 2019. 10. 23. 오후 9:13:14
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : oAuth2 Config
 * </pre>
 */
package com.hrpj.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.config
 * 2. 타입명 : OAuth2Config.java
 * 3. 작성일 : 2019. 10. 23. 오후 9:13:14
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : oAuth2 Config
 * </pre>
 */
@Configuration
public class OAuth2Config extends ResourceServerConfigurerAdapter {

	@Override
	public void configure( HttpSecurity http ) throws Exception {
		http.authorizeRequests( ).antMatchers( "/**" ).permitAll( );
	}

}
