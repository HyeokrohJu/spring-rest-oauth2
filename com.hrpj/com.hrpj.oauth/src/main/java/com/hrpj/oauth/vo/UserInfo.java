/**
 * <pre>
 * 1. 프로젝트명 : oauth
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.oauth.vo
 * 3. 파일명 : UserInfo.java
 * 4. 작성일 : 2017. 6. 26. 오후 6:25:45
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 유저정보
 * </pre>
 */

package com.hrpj.oauth.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.oauth.vo
 * 2. 타입명 : UserInfo.java
 * 3. 작성일 : 2017. 6. 29. 오후 9:24:02
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 유저정보
 * </pre>
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInfo implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String id;

	private String username;

	private String password;

	private boolean enabled = true;

	private boolean credentialsexpired = false;

	private boolean expired = false;

	private boolean locked = false;

	private String userid;

	private String usernm;

	private String deptid;

	private String deptnm;

	private String compid;

	private String compnm;

	private String loginid;

	private String passwd;

	private String email;

	private String posnm;

	private String hrschema;

	private String hrtimezone;

	private Set<Role> roles;

	private String sgrp;

	public UserInfo( UserInfo userinfo ) {
		this.id = userinfo.getUserid( );
		this.username = userinfo.getUsernm( );
		this.password = userinfo.getPasswd( );
		this.enabled = userinfo.isEnabled( );
		this.credentialsexpired = userinfo.isCredentialsexpired( );
		this.locked = userinfo.isExpired( );
		this.roles = userinfo.getRoles( );
		this.userid = userinfo.getUserid( );
		this.usernm = userinfo.getUsernm( );
		this.deptid = userinfo.getDeptid( );
		this.deptnm = userinfo.getDeptnm( );
		this.compid = userinfo.getCompid( );
		this.compnm = userinfo.getCompnm( );
		this.loginid = userinfo.getLoginid( );
		this.passwd = userinfo.getPasswd( );
		this.email = userinfo.getEmail( );
		this.posnm = userinfo.getPosnm( );
		this.hrschema = userinfo.getHrschema( );
		this.hrtimezone = userinfo.getHrtimezone( );
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities(
	 * )
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities( ) {
		final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>( );
		for (final Role role : getRoles( )) {
			grantedAuthorities.add( new SimpleGrantedAuthority( role.getCode( ) ) );
		}
		return grantedAuthorities;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired( ) {
		return !expired;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked( ) {
		return !locked;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired( ) {
		return !credentialsexpired;
	}

}
