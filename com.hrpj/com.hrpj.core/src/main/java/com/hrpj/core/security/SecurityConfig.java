/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.security
 * 3. 파일명 : SecurityConfig.java
 * 4. 작성일 : 2019. 9. 30. 오후 4:08:20
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : SpringSecurity 암호화
 * </pre>
 */
package com.hrpj.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.security
 * 2. 타입명 : SecurityConfig.java
 * 3. 작성일 : 2019. 9. 30. 오후 4:08:20
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : SpringSecurity 암호화
 * </pre>
 */
@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder( ) {
		return new BCryptPasswordEncoder( );
	}

}
