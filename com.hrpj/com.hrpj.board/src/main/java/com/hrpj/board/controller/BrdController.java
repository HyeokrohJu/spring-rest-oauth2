/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.controller
 * 3. 파일명 : BrdController.java
 * 4. 작성일 : 2019. 10. 4. 오후 3:21:19
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시판 관련
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

import com.hrpj.board.service.BrdService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.controller
 * 2. 타입명 : BrdController.java
 * 3. 작성일 : 2019. 10. 4. 오후 3:21:19
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시판 관련
 * </pre>
 */
@RestController
@RequestMapping("/brd")
public class BrdController {

	@Autowired
	BrdService brdService;

	/**
	 * <pre>
	 * 1. 함수명 : selectBrdTree
	 * 2. 작성일 : 2019. 10. 4. 오후 3:23:59
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 계층형 게시판 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectBrdTree", method = RequestMethod.GET)
	public Object selectBrdTree( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( brdService.selectBrdHierarchy( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertBrd
	 * 2. 작성일 : 2019. 10. 4. 오후 6:05:16
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "upbrdid", "sgrp", "brdnm", "brddesc", "useyn", "fldyn", "brdtype", "printyn", "replyyn", "cmntyn", "notiyn",
		"replynotiyn", "cmntnotiyn", "clickdispyn", "rcmdyn", "attachyn", "attachsize", "pwduseyn", "secretyn", "odelyn", "newbltyn", "annyn",
		"bltreuseyn", "reserveyn", "templateid", "disporder" })
	@RequestMapping(value = "/insertBrd", method = RequestMethod.POST)
	public Object insertBrd( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( brdService.insertBrd( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateBrd
	 * 2. 작성일 : 2019. 10. 4. 오후 6:07:33
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "brdid" })
	@RequestMapping(value = "/updateBrd", method = RequestMethod.PUT)
	public Object updateBrd( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( brdService.updateBrd( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getBrd
	 * 2. 작성일 : 2019. 10. 4. 오후 6:09:21
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getBrd", method = RequestMethod.GET)
	public Object getBrd( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( brdService.getBrd( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteBrd
	 * 2. 작성일 : 2019. 10. 4. 오후 6:11:08
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 물리 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "brdid" })
	@RequestMapping(value = "/deleteBrd", method = RequestMethod.DELETE)
	public Object deleteBrd( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( brdService.deleteBrd( paramMap ) );

		return resVo;
	}

}
