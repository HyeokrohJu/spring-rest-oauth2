/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.controller
 * 3. 파일명 : MenuController.java
 * 4. 작성일 : 2019. 9. 18. 오전 11:25:49
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 메뉴관련 Controller
 * </pre>
 */
package com.hrpj.common.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrpj.common.service.MenuService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.controller
 * 2. 타입명 : MenuController.java
 * 3. 작성일 : 2019. 9. 18. 오전 11:25:49
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 메뉴관련 Controller
 * </pre>
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	MenuService menuService;

	/**
	 * <pre>
	 * 1. 함수명 : selectSgrp
	 * 2. 작성일 : 2019. 9. 25. 오전 10:58:00
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트 그룹 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/selectSgrp", method = RequestMethod.GET)
	public Object selectSgrp( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.selectSgrp( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertSgrp
	 * 2. 작성일 : 2019. 9. 25. 오전 10:58:05
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트 그룹 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "stype", "preSgrp", "sgrpnm", "als", "delyn" })
	@RequestMapping(value = "/insertSgrp", method = RequestMethod.POST)
	public Object insertSgrp( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.insertSgrp( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateSgrp
	 * 2. 작성일 : 2019. 9. 27. 오후 12:28:56
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트그룹 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "sgrp" })
	@RequestMapping(value = "/updateSgrp", method = RequestMethod.PUT)
	public Object updateSgrp( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.updateSgrp( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getSgrp
	 * 2. 작성일 : 2019. 9. 27. 오후 12:35:23
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트그룹 상세 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/getSgrp", method = RequestMethod.GET)
	public Object getSgrp( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.getSgrp( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteSgrp
	 * 2. 작성일 : 2019. 9. 30. 오후 6:03:34
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트그룹 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "sgrp" })
	@RequestMapping(value = "/deleteSgrp", method = RequestMethod.DELETE)
	public Object deleteSgrp( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.deleteSgrp( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : selectMenuTree
	 * 2. 작성일 : 2019. 9. 24. 오후 5:54:22
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 계층형 메뉴 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectMenuTree", method = RequestMethod.GET)
	public Object selectMenuTree( @RequestParam Map<String, Object> paramMap, Principal user ) {

		if ( user instanceof OAuth2Authentication ) {
			System.out.println( "having roles: {}" + ( (OAuth2Authentication) user ).getAuthorities( ) );
		}

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.selectMenuHierarchy( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : selectMenu
	 * 2. 작성일 : 2019. 12. 9. 오후 2:16:49
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메뉴 리스트 조회
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectMenu", method = RequestMethod.GET)
	public Object selectMenu( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.selectMenu( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertMenu
	 * 2. 작성일 : 2019. 9. 25. 오전 10:09:01
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메뉴 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "sgrp", "upmenucd", "menunm", "mtype", "wdpyn", "useyn", "expyn" })
	@RequestMapping(value = "/insertMenu", method = RequestMethod.POST)
	public Object insertMenu( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.insertMenu( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateMenu
	 * 2. 작성일 : 2019. 9. 27. 오후 12:28:47
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메뉴 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "sgrp", "menucd" })
	@RequestMapping(value = "/updateMenu", method = RequestMethod.PUT)
	public Object updateMenu( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.updateMenu( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getMenu
	 * 2. 작성일 : 2019. 9. 27. 오후 12:56:34
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메뉴 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	public Object getMenu( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.getMenu( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteMenu
	 * 2. 작성일 : 2019. 9. 30. 오후 6:06:34
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메뉴 물리 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "menucd" })
	@RequestMapping(value = "/deleteMenu", method = RequestMethod.DELETE)
	public Object deleteMenu( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( menuService.deleteMenu( paramMap ) );

		return resVo;
	}

}
