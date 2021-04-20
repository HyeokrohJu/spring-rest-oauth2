/**
 * <pre>
 * 1. 프로젝트명 : oauth
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.oauth.configs
 * 3. 파일명 : UserInfoAuthenticationProvider.java
 * 4. 작성일 : 2017. 6. 19. 오후 10:50:51
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.oauth.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hrpj.oauth.service.CustomUserDetailsService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.oauth.configs
 * 2. 타입명 : UserInfoAuthenticationProvider.java
 * 3. 작성일 : 2017. 6. 29. 오후 9:22:18
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */
@Component
public class UserInfoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.dao.
	 * AbstractUserDetailsAuthenticationProvider#additionalAuthenticationChecks(
	 * org.springframework.security.core.userdetails.UserDetails,
	 * org.springframework.security.authentication.
	 * UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
		logger.debug("> additionalAuthenticationChecks");

		if (token.getCredentials() == null || userDetails.getPassword() == null) {
			throw new BadCredentialsException("Credentials may not be null.");
		}

		if (!passwordEncoder.matches((String) token.getCredentials(), userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid credentials.");
		}

		logger.debug("< additionalAuthenticationChecks");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.dao.
	 * AbstractUserDetailsAuthenticationProvider#retrieveUser(java.lang.String,
	 * org.springframework.security.authentication.
	 * UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
		logger.debug("> retrieveUser");

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		logger.debug("< retrieveUser");
		return userDetails;
	}

}
