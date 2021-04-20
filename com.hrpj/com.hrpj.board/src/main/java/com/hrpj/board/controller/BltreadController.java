/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.controller
 * 3. 파일명 : BltreadController.java
 * 4. 작성일 : 2019. 10. 11. 오전 10:38:50
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 열람내역 관련 Controller
 * </pre>
 */
package com.hrpj.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrpj.board.service.BltreadService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.controller
 * 2. 타입명 : BltreadController.java
 * 3. 작성일 : 2019. 10. 11. 오전 10:38:50
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 열람내역 관련 Controller
 * </pre>
 */
@RestController
@RequestMapping("/bltread")
public class BltreadController {

	@Autowired
	BltreadService bltreadService;

	/**
	 * <pre>
	 * 1. 함수명 : selectBltreadPg
	 * 2. 작성일 : 2019. 10. 11. 오전 10:56:09
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 열람내역 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectBltreadPg", method = RequestMethod.GET)
	public Object selectBltreadPg( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltreadService.selectBltreadPg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertBltread
	 * 2. 작성일 : 2019. 10. 11. 오전 10:56:11
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 열람내역 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@CustomValid(validField = { "brdid", "bltid" })
	@RequestMapping(value = "/insertBltread", method = RequestMethod.POST)
	public Object insertBltread( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltreadService.insertBltread( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getBltread
	 * 2. 작성일 : 2019. 10. 11. 오전 10:56:13
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 열람내역 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getBltread", method = RequestMethod.GET)
	public Object getBltread( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltreadService.getBltread( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteBltread
	 * 2. 작성일 : 2019. 10. 11. 오전 10:56:15
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 열람내역 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@CustomValid(validField = { "bltid" })
	@RequestMapping(value = "/deleteBltread", method = RequestMethod.DELETE)
	public Object deleteBltread( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( bltreadService.deleteBltread( paramMap ) );

		return resVo;
	}

}
