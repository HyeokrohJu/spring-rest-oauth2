/**
 * <pre>
 * 1. 프로젝트명 : oauth
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.oauth.configs
 * 3. 파일명 : OAuth2Configuration.java
 * 4. 작성일 : 2017. 6. 19. 오후 6:27:40
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.oauth.configs;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.hrpj.oauth.service.CustomUserDetailsService;
import com.hrpj.oauth.vo.UserInfo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.oauth.configs
 * 2. 타입명 : OAuth2Configuration.java
 * 3. 작성일 : 2017. 6. 29. 오후 9:21:50
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */
@EnableAuthorizationServer
@Configuration
public class OAuth2Configuration {
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Value("${security.oauth2.resource.jwt.key-value}")
	private String jwtKey;

	@Value("${security.oauth2.resource.jwt.access-token-expire}")
	private int accessTokenExp;

	@Value("${security.oauth2.resource.jwt.refresh-token-expire}")
	private int refreshTokenExp;

	@Bean
	public TokenStore tokenStore( ) {
		return new JwtTokenStore( jwtAccessTokenConverter( ) );
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter( ) {
		final JwtAccessTokenConverter converter = new CustomTokenEnhancer( );
		converter.setSigningKey( this.jwtKey );

		final DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter( );
		final DefaultUserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter( );

		userTokenConverter.setUserDetailsService( userDetailsService );
		accessTokenConverter.setUserTokenConverter( userTokenConverter );

		converter.setAccessTokenConverter( accessTokenConverter );

		return converter;
	}

	protected static class CustomTokenEnhancer extends JwtAccessTokenConverter {
		@Value("${security.oauth2.resource.jwt.access-token-expire}")
		private int accessTokenExp;

		@Value("${security.oauth2.resource.jwt.refresh-token-expire}")
		private int refreshTokenExp;

		@Override
		public OAuth2AccessToken enhance( OAuth2AccessToken accessToken, OAuth2Authentication authentication ) {
			final UserInfo user = (UserInfo) authentication.getPrincipal( );

			final Map<String, Object> info = new LinkedHashMap<>( accessToken.getAdditionalInformation( ) );
			info.put( "hrschema", user.getHrschema( ) );
			info.put( "hrtimezone", user.getHrtimezone( ) );
			final Map<String, Object> userinfoMap = new LinkedHashMap<>( );
			userinfoMap.put( "userid", user.getUserid( ) );
			userinfoMap.put( "usernm", user.getUsernm( ) );
			userinfoMap.put( "deptid", user.getDeptid( ) );
			userinfoMap.put( "deptnm", user.getDeptnm( ) );
			userinfoMap.put( "compid", user.getCompid( ) );
			userinfoMap.put( "compnm", user.getCompnm( ) );
			userinfoMap.put( "loginid", user.getLoginid( ) );
			userinfoMap.put( "email", user.getEmail( ) );
			userinfoMap.put( "posnm", user.getPosnm( ) );
			userinfoMap.put( "sgrp", user.getSgrp( ) );
			info.put( "userInfo", userinfoMap );

			final DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken( accessToken );

			final Set<GrantedAuthority> authoritiesSet = new HashSet<>( authentication.getAuthorities( ) );

			final String[ ] authorities = new String[ authoritiesSet.size( ) ];

			int i = 0;
			for (final GrantedAuthority authority : authoritiesSet) {
				authorities[ i++ ] = authority.getAuthority( );
			}

			info.put( "authorities", authorities );
			customAccessToken.setAdditionalInformation( info );
			final long ate =
				new BigDecimal( new Date( ).getTime( ) ).add( new BigDecimal( accessTokenExp ).multiply( new BigDecimal( 3600000 ) ) ).longValue( );
			customAccessToken.setExpiration( new Date( ate ) );
			customAccessToken.setRefreshToken( this.customRefreshTokenExpireDate( accessToken ) );

			return super.enhance( customAccessToken, authentication );
		}

		private OAuth2RefreshToken customRefreshTokenExpireDate( OAuth2AccessToken encodedRefreshToken ) {
			final long rte =
				new BigDecimal( new Date( ).getTime( ) ).add( new BigDecimal( refreshTokenExp ).multiply( new BigDecimal( 3600000 ) ) ).longValue( );
			return new DefaultExpiringOAuth2RefreshToken( encodedRefreshToken.getValue( ), new Date( rte ) );
		}
	}

	@Bean
	@Primary
	public JdbcClientDetailsService jdbcClientDetatilsService( @Qualifier("oauthDatasource") DataSource dataSource ) {
		return new JdbcClientDetailsService( dataSource );
	}

	@Bean
	public FilterRegistrationBean corsFilter( ) {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource( );
		final CorsConfiguration config = new CorsConfiguration( );
		config.setAllowCredentials( true );
		config.addAllowedOrigin( "*" );
		config.addAllowedHeader( "*" );
		config.addAllowedMethod( "*" );
		source.registerCorsConfiguration( "/**", config );
		final FilterRegistrationBean bean = new FilterRegistrationBean( new CorsFilter( source ) );
		bean.setOrder( Ordered.HIGHEST_PRECEDENCE );
		return bean;
	}
}
