/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.controller
 * 3. 파일명 : BltrcmdController.java
 * 4. 작성일 : 2019. 10. 10. 오후 1:56:02
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 추천 관련 Controller
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

import com.hrpj.board.service.BltrcmdService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.controller
 * 2. 타입명 : BltrcmdController.java
 * 3. 작성일 : 2019. 10. 10. 오후 1:56:02
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 추천 관련 Controller
 * </pre>
 */
@RestController
@RequestMapping("/bltrcmd")
public class BltrcmdController {

	@Autowired
	BltrcmdService bltrcmdService;

	/**
	 * <pre>
	 * 1. 함수명 : selectBltrcmdPg
	 * 2. 작성일 : 2019. 10. 11. 오전 10:03:30
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 추천 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectBltrcmdPg", method = RequestMethod.GET)
	public Object selectBltrcmdPg( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltrcmdService.selectBltrcmdPg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertBltrcmd
	 * 2. 작성일 : 2019. 10. 11. 오전 10:03:42
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 추천 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@CustomValid(validField = { "brdid", "bltid", "rcmdtype" })
	@RequestMapping(value = "/insertBltrcmd", method = RequestMethod.POST)
	public Object insertBltrcmd( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltrcmdService.insertBltrcmd( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getBltrcmd
	 * 2. 작성일 : 2019. 10. 11. 오전 10:03:50
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 추천 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getBltrcmd", method = RequestMethod.GET)
	public Object getBltrcmd( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltrcmdService.getBltrcmd( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteBltrcmd
	 * 2. 작성일 : 2019. 10. 11. 오전 10:03:57
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 추천 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@CustomValid(validField = { "bltid" })
	@RequestMapping(value = "/deleteBltrcmd", method = RequestMethod.DELETE)
	public Object deleteBltrcmd( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltrcmdService.deleteBltrcmd( paramMap ) );

		return resVo;
	}

}
