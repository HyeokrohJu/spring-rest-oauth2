/**
 * <pre>
 * 1. 프로젝트명 : oauth
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.oauth.service
 * 3. 파일명 : CustomUserDetailsService.java
 * 4. 작성일 : 2017. 6. 19. 오후 10:08:48
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.oauth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hrpj.oauth.dao.UserInfoDao;
import com.hrpj.oauth.exception.UserNotFoundException;
import com.hrpj.oauth.vo.Role;
import com.hrpj.oauth.vo.UserInfo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.oauth.service
 * 2. 타입명 : CustomUserDetailsService.java
 * 3. 작성일 : 2017. 6. 29. 오후 9:23:06
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private HttpServletRequest request;

	private final UserInfoDao userInfoRepository;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker( );

	@Autowired
	public CustomUserDetailsService( UserInfoDao userInfoRepository ) {
		this.userInfoRepository = userInfoRepository;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername( String loginid ) throws UsernameNotFoundException {
		final Map<String, String> paramMap = new HashMap<>( );
		paramMap.put( "loginid", loginid );
		paramMap.put( "hrschema", request.getParameter( "hrschema" ) );
		final UserInfo userInfo = userInfoRepository.findByLoginId( paramMap );
		if ( userInfo == null || userInfo.getUserid( ) == null ) {
			throw new UserNotFoundException( "UserNotFoundException : User " + loginid + " not found." );
		}
		userInfo.setHrschema( request.getParameter( "hrschema" ) );
		userInfo.setHrtimezone( request.getParameter( "hrtimezone" ) );

		paramMap.put( "userid", userInfo.getUserid( ) );
		final Set<Role> roleInfo = userInfoRepository.findRole( paramMap );
		userInfo.setRoles( roleInfo );

		if ( userInfo.getRoles( ) == null || userInfo.getRoles( ).isEmpty( ) ) {
			throw new UsernameNotFoundException( "UserroleNotFoundException : User not authorized." );
		}

		final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>( );
		for (final Role role : userInfo.getRoles( )) {
			grantedAuthorities.add( new SimpleGrantedAuthority( role.getCode( ) ) );
		}

		detailsChecker.check( userInfo );

		return userInfo;
	}

}
