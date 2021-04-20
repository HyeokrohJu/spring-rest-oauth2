/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.controller
 * 3. 파일명 : AuthController.java
 * 4. 작성일 : 2019. 11. 22. 오후 4:29:11
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 권한 관련 Controller
 * </pre>
 */
package com.hrpj.common.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrpj.common.service.AuthService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.controller
 * 2. 타입명 : AuthController.java
 * 3. 작성일 : 2019. 11. 22. 오후 4:29:11
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 권한 관련 Controller
 * </pre>
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;

	/**
	 * <pre>
	 * 1. 함수명 : insertAuth
	 * 2. 작성일 : 2019. 11. 22. 오후 4:45:04
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 권한 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "authid", "menucd", "rolecd" })
	@RequestMapping(value = "/insertSgrp", method = RequestMethod.POST)
	public Object insertAuth( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( authService.insertAuth( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteAuth
	 * 2. 작성일 : 2019. 11. 22. 오후 4:46:20
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 권한 삭제
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "authid, menucd" })
	@RequestMapping(value = "/deleteAuth", method = RequestMethod.DELETE)
	public Object deleteAuth( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( authService.deleteAuth( paramMap ) );

		return resVo;
	}
}
