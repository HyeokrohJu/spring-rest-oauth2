/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.controller
 * 3. 파일명 : BrdtemplateController.java
 * 4. 작성일 : 2019. 10. 8. 오후 3:01:57
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시판 템플릿 관리 Controller
 * </pre>
 */
package com.hrpj.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrpj.board.service.BrdtemplateService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.controller
 * 2. 타입명 : BrdtemplateController.java
 * 3. 작성일 : 2019. 10. 8. 오후 3:01:57
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시판 템플릿 관리 Controller
 * </pre>
 */
@RestController
@RequestMapping("/brdtemplate")
public class BrdtemplateController {

	@Autowired
	BrdtemplateService brdtemplateService;

	/**
	 * <pre>
	 * 1. 함수명 : selectBrdtemplatePg
	 * 2. 작성일 : 2019. 10. 8. 오후 3:40:33
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 템플릿 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectBrdtemplatePg", method = RequestMethod.GET)
	public Object selectBrdtemplatePg( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( brdtemplateService.selectBrdtemplatePg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertBrdtemplate
	 * 2. 작성일 : 2019. 10. 8. 오후 3:40:42
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 템플릿 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "sgrp", "templatenm", "cont", "useyn" })
	@RequestMapping(value = "/insertBrdtemplate", method = RequestMethod.POST)
	public Object insertBrdtemplate( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( brdtemplateService.insertBrdtemplate( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateBrdtemplate
	 * 2. 작성일 : 2019. 10. 8. 오후 3:40:50
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 템플릿 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "templateid" })
	@RequestMapping(value = "/updateBrdtemplate", method = RequestMethod.PUT)
	public Object updateBrdtemplate( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( brdtemplateService.updateBrdtemplate( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getBrdtemplate
	 * 2. 작성일 : 2019. 10. 8. 오후 3:40:57
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 템플릿 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getBrdtemplate", method = RequestMethod.GET)
	public Object getBrdtemplate( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( brdtemplateService.getBrdtemplate( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteBrdtemplate
	 * 2. 작성일 : 2019. 10. 8. 오후 3:41:05
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 템플릿 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "templateid" })
	@RequestMapping(value = "/deleteBrdtemplate", method = RequestMethod.DELETE)
	public Object deleteBrdtemplate( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( brdtemplateService.deleteBrdtemplate( paramMap ) );

		return resVo;
	}

}
