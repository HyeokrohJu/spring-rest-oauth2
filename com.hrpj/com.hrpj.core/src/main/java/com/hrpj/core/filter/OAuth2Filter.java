package com.hrpj.core.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import com.hrpj.core.common.CommonThreadLocalBean;
import com.hrpj.core.exception.JWTInvalidException;
import com.hrpj.core.utils.StringUtils;
import com.hrpj.core.utils.UserAgent;

/**
 * <pre>
 * 1. 패키지명 : com.roh.restful.filter
 * 2. 타입명 : OAuth2Filter.java
 * 3. 작성일 : 2017. 6. 27. 오후 5:09:27
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : OAuth2 Filter
 * </pre>
 */
@Component
public class OAuth2Filter implements Filter {

	private final Log logger = LogFactory.getLog( this.getClass( ) );

	@Autowired
	private TokenStore tokenStore;

	@Override
	public void destroy( ) {
		CommonThreadLocalBean.removeJwtInfo( );
	}

	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) {
		try {
			final HttpServletRequest request = (HttpServletRequest) servletRequest;
			String jwtToken = request.getHeader( "Authorization" );
			Map<String, Object> jwtInfo = new HashMap<>( );
			OAuth2AccessToken accessToken = null;
			if ( !StringUtils.isNull( jwtToken ) ) {
				jwtToken = jwtToken.replaceAll( "Bearer ", "" );
				accessToken = tokenStore.readAccessToken( jwtToken );
				jwtInfo = accessToken.getAdditionalInformation( );
			} else {
				final String domain = request.getServerName( );
				String schema = domain.substring( domain.indexOf( "." ) + 1 );
				if ( schema.indexOf( "." ) > -1 ) {
					schema = schema.substring( 0, schema.indexOf( "." ) );
				} else {
					schema = "hrpj";
				}
				jwtInfo.put( "hrschema", schema );
				jwtInfo.put( "hrtimezone", "GMT 9:00" );
				jwtInfo.put( "sgrp", "SS0" );

				final Map<String, Object> userInfo = new HashMap<>( );
				userInfo.put( "userid", "nomember" );
				userInfo.put( "usernm", "nomember" );
				userInfo.put( "deptid", "" );
				userInfo.put( "compid", "" );

				jwtInfo.put( "userInfo", userInfo );
			}

			jwtInfo.put( "User-Agent", request.getHeader( "User-Agent" ) );
			jwtInfo.put( "remotehost", request.getRemoteHost( ) );
			jwtInfo.put( "remoteaddr", UserAgent.getClientIp( request ) );

			CommonThreadLocalBean.setJwtInfo( jwtInfo );

			try {

				final Map<String, Object> userInfo = CommonThreadLocalBean.getUserInfo( );

				logger.debug( "### CURRENT JWT INFO 0.: " + accessToken );
				logger.debug( "### CURRENT JWT INFO 1.: " + userInfo.get( "usernm" ) );
				logger.debug( "### CURRENT JWT INFO 2.: " + userInfo.get( "userid" ) );
				logger.debug( "### CURRENT JWT INFO 3.: " + userInfo.get( "loginid" ) );

			} catch (final JWTInvalidException e) {
				logger.debug( "### JWT IS EMPTY ###" + e.getMessage( ) );
				logger.debug( "### requestUrI : " + request.getRequestURI( ) );
			}

			filterChain.doFilter( servletRequest, servletResponse );
		} catch (final IOException e) {
			e.printStackTrace( );
		} catch (final ServletException e) {
			e.printStackTrace( );
		} finally {
			CommonThreadLocalBean.removeJwtInfo( );
		}
	}

	@Override
	public void init( FilterConfig filterConfig ) throws ServletException {
	}

}
