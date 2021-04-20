/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.controller
 * 3. 파일명 : BltController.java
 * 4. 작성일 : 2019. 10. 7. 오후 3:57:01
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 관련 Controller
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

import com.hrpj.board.service.BltService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.controller
 * 2. 타입명 : BltController.java
 * 3. 작성일 : 2019. 10. 7. 오후 3:57:01
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 관련 Controller
 * </pre>
 */
@RestController
@RequestMapping("/blt")
public class BltController {

	@Autowired
	BltService bltService;

	/**
	 * <pre>
	 * 1. 함수명 : selectBltPg
	 * 2. 작성일 : 2019. 10. 7. 오후 4:11:05
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectBltPg", method = RequestMethod.GET)
	public Object selectBltPg( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltService.selectBltPg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertBlt
	 * 2. 작성일 : 2019. 10. 7. 오후 4:11:14
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@CustomValid(validField = { "brdid", "state", "title", "cont" })
	@RequestMapping(value = "/insertBlt", method = RequestMethod.POST)
	public Object insertBlt( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltService.insertBlt( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateBlt
	 * 2. 작성일 : 2019. 10. 7. 오후 4:11:20
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@CustomValid(validField = { "bltid" })
	@RequestMapping(value = "/updateBlt", method = RequestMethod.PUT)
	public Object updateBlt( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltService.updateBlt( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getBlt
	 * 2. 작성일 : 2019. 10. 7. 오후 4:11:26
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getBlt", method = RequestMethod.GET)
	public Object getBlt( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltService.getBlt( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteBlt
	 * 2. 작성일 : 2019. 10. 7. 오후 4:11:32
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@CustomValid(validField = { "bltid" })
	@RequestMapping(value = "/deleteBlt", method = RequestMethod.DELETE)
	public Object deleteBlt( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltService.deleteBlt( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateCntBlt
	 * 2. 작성일 : 2020. 2. 3. 오후 3:17:27
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 조회수 증가
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@CustomValid(validField = { "bltid" })
	@RequestMapping(value = "/updateCntBlt", method = RequestMethod.PUT)
	public Object updateCntBlt( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltService.updateCntBlt( paramMap ) );

		return resVo;
	}

}
