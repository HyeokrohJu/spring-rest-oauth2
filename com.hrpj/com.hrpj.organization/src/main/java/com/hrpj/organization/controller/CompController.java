/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.organization
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.organization.controller
 * 3. 파일명 : CompController.java
 * 4. 작성일 : 2019. 10. 1. 오전 11:13:15
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 회사관련 Controller
 * </pre>
 */
package com.hrpj.organization.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;
import com.hrpj.organization.service.CompService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.organization.controller
 * 2. 타입명 : CompController.java
 * 3. 작성일 : 2019. 10. 1. 오전 11:13:15
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 회사관련 Controller
 * </pre>
 */
@RestController
@RequestMapping("/comp")
public class CompController {

	@Autowired
	CompService compService;

	/**
	 * <pre>
	 * 1. 함수명 : selectCompPg
	 * 2. 작성일 : 2019. 10. 1. 오전 11:38:57
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회사정보 리스트 조회(페이징)
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "sgrp" })
	@RequestMapping(value = "/selectCompPg", method = RequestMethod.POST)
	public Object selectCompPg( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( compService.selectCompPg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getComp
	 * 2. 작성일 : 2019. 10. 1. 오전 11:39:40
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회사정보 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/getComp", method = RequestMethod.GET)
	public Object getComp( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( compService.getComp( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertComp
	 * 2. 작성일 : 2019. 10. 1. 오전 11:48:52
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회사정보 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "compnm", "sgrp", "repnm", "domain", "zipcd", "addr1", "addr2", "phone", "fax", "useyn" })
	@RequestMapping(value = "/insertComp", method = RequestMethod.POST)
	public Object insertComp( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( compService.insertComp( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateComp
	 * 2. 작성일 : 2019. 10. 1. 오후 1:48:35
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회사정보 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "compid" })
	@RequestMapping(value = "/updateComp", method = RequestMethod.PUT)
	public Object updateComp( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( compService.updateComp( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteComp
	 * 2. 작성일 : 2019. 10. 1. 오후 1:56:59
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회사정보 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "compid" })
	@RequestMapping(value = "/deleteComp", method = RequestMethod.DELETE)
	public Object deleteComp( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( compService.deleteComp( paramMap ) );

		return resVo;
	}

}
