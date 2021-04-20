/**
 * <pre>
 * 1. 프로젝트명 : oauth
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.oauth.configs
 * 3. 파일명 : JwtOAuth2AuthorizationServerConfiguration.java
 * 4. 작성일 : 2017. 6. 19. 오후 6:31:04
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.oauth.configs;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.hrpj.oauth.service.CustomUserDetailsService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.oauth.configs
 * 2. 타입명 : JwtOAuth2AuthorizationServerConfiguration.java
 * 3. 작성일 : 2017. 6. 29. 오후 9:21:19
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */
@Configuration
public class JwtOAuth2AuthorizationServerConfiguration extends OAuth2AuthorizationServerConfiguration {

	private final JwtAccessTokenConverter jwtAccessTokenConverter;
	private final ClientDetailsService clientDetailsService;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	CustomUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder( ) {
		return new BCryptPasswordEncoder( );
	}

	/**
	 * @param details
	 * @param authenticationManager
	 * @param tokenStore
	 * @param tokenConverter
	 * @param properties
	 */
	public JwtOAuth2AuthorizationServerConfiguration( BaseClientDetails details, AuthenticationManager authenticationManager,
		ObjectProvider<TokenStore> tokenStore, ObjectProvider<AccessTokenConverter> tokenConverter, AuthorizationServerProperties properties,
		JwtAccessTokenConverter jwtAccessTokenConverter, ClientDetailsService clientDetailsService ) {

		super( details, authenticationManager, tokenStore, tokenConverter, properties );

		this.jwtAccessTokenConverter = jwtAccessTokenConverter;
		this.clientDetailsService = clientDetailsService;
	}

	@Override
	public void configure( AuthorizationServerEndpointsConfigurer endpoints ) throws Exception {
		super.configure( endpoints );
		endpoints
			.authenticationManager( authenticationManager ).accessTokenConverter( jwtAccessTokenConverter ).userDetailsService( userDetailsService );
	}

	@Override
	public void configure( ClientDetailsServiceConfigurer clients ) throws Exception {
		clients.withClientDetails( clientDetailsService );
	}
}
