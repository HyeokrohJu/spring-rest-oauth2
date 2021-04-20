/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.controller
 * 3. 파일명 : BltcmntController.java
 * 4. 작성일 : 2019. 10. 10. 오전 10:47:45
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 댓글관련 Controller
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

import com.hrpj.board.service.BltcmntService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.controller
 * 2. 타입명 : BltcmntController.java
 * 3. 작성일 : 2019. 10. 10. 오전 10:47:45
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 댓글관련 Controller
 * </pre>
 */
@RestController
@RequestMapping("/bltcmnt")
public class BltcmntController {

	@Autowired
	BltcmntService bltcmntService;

	/**
	 * <pre>
	 * 1. 함수명 : selectBltcmntPg
	 * 2. 작성일 : 2019. 10. 10. 오후 2:02:28
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 댓글 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectBltcmntPg", method = RequestMethod.GET)
	public Object selectBltcmntPg( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltcmntService.selectBltcmntPg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertBltcmnt
	 * 2. 작성일 : 2019. 10. 10. 오후 2:02:39
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 댓글 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@CustomValid(validField = { "brdid", "bltid", "cmnt", "state", "rcmdcnt" })
	@RequestMapping(value = "/insertBltcmnt", method = RequestMethod.POST)
	public Object insertBltcmnt( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltcmntService.insertBltcmnt( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateBltcmnt
	 * 2. 작성일 : 2019. 10. 10. 오후 2:02:47
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 댓글 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@CustomValid(validField = { "cmntid" })
	@RequestMapping(value = "/updateBltcmnt", method = RequestMethod.PUT)
	public Object updateBltcmnt( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltcmntService.updateBltcmnt( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getBltcmnt
	 * 2. 작성일 : 2019. 10. 10. 오후 2:02:53
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 댓글 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getBltcmnt", method = RequestMethod.GET)
	public Object getBltcmnt( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltcmntService.getBltcmnt( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteBltcmnt
	 * 2. 작성일 : 2019. 10. 10. 오후 2:03:00
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 댓글 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@CustomValid(validField = { "cmntid" })
	@RequestMapping(value = "/deleteBltcmnt", method = RequestMethod.DELETE)
	public Object deleteBltcmnt( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltcmntService.deleteBltcmntProc( paramMap ) );

		return resVo;
	}

}
