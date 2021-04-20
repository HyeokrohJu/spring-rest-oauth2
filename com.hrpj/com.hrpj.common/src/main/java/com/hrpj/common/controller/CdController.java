/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.controller
 * 3. 파일명 : CdController.java
 * 4. 작성일 : 2019. 9. 27. 오후 3:10:58
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 코드관리 Controller
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

import com.hrpj.common.service.CdService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.controller
 * 2. 타입명 : CdController.java
 * 3. 작성일 : 2019. 9. 27. 오후 3:10:58
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 코드 Controller
 * </pre>
 */
@RestController
@RequestMapping("/cd")
public class CdController {

	@Autowired
	CdService cdService;

	/**
	 * <pre>
	 * 1. 함수명 : selectCdTree
	 * 2. 작성일 : 2019. 9. 27. 오후 3:13:50
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 계층형 코드 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectCdTree", method = RequestMethod.GET)
	public Object selectCdTree( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( cdService.selectCdHierarchy( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertCd
	 * 2. 작성일 : 2019. 9. 27. 오후 3:55:55
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 코드 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "upcd", "cdnm", "useyn" })
	@RequestMapping(value = "/insertCd", method = RequestMethod.POST)
	public Object insertCd( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( cdService.insertCd( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getCd
	 * 2. 작성일 : 2019. 9. 27. 오후 5:18:59
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 코드 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getCd", method = RequestMethod.GET)
	public Object getCd( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( cdService.getCd( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateCd
	 * 2. 작성일 : 2019. 9. 27. 오후 5:19:49
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 코드 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "cd" })
	@RequestMapping(value = "/updateCd", method = RequestMethod.PUT)
	public Object updateCd( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( cdService.updateCd( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteCd
	 * 2. 작성일 : 2019. 10. 1. 오후 3:37:51
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 코드 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "cd" })
	@RequestMapping(value = "/deleteCd", method = RequestMethod.DELETE)
	public Object deleteCd( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( cdService.deleteCd( paramMap ) );

		return resVo;
	}

}
