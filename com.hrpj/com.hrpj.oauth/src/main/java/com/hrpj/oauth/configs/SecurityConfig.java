/**
 * <pre>
 * 1. 프로젝트명 : oauth
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.oauth.configs
 * 3. 파일명 : SecurityConfig.java
 * 4. 작성일 : 2017. 6. 19. 오후 9:52:50
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.oauth.configs;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hrpj.oauth.service.CustomUserDetailsService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.oauth.configs
 * 2. 타입명 : SecurityConfig.java
 * 3. 작성일 : 2017. 6. 29. 오후 9:22:04
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserInfoAuthenticationProvider userInfoAuthenticationProvider;

	@Override
	@Autowired
	public void configure( AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService( userDetailsService );
		auth.authenticationProvider( userInfoAuthenticationProvider );
	}

	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		http
			.csrf( ).disable( ).exceptionHandling( )
			.authenticationEntryPoint( ( request, response, authException ) -> response.sendError( HttpServletResponse.SC_UNAUTHORIZED ) ).and( )
			.authorizeRequests( ).antMatchers( HttpMethod.OPTIONS ).permitAll( ).antMatchers( "/**" ).authenticated( ).and( ).httpBasic( );
	}

	@Bean
	public PasswordEncoder passwordEncoder( ) {
		return new BCryptPasswordEncoder( );
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean( ) throws Exception {
		return super.authenticationManagerBean( );
	}
}
