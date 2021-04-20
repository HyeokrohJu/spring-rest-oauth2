/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.controller
 * 3. 파일명 : ContController.java
 * 4. 작성일 : 2019. 10. 14. 오전 9:33:56
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 컨텐츠 Controller
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

import com.hrpj.common.service.ContService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.controller
 * 2. 타입명 : ContController.java
 * 3. 작성일 : 2019. 10. 14. 오전 9:33:56
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 컨텐츠 Controller
 * </pre>
 */
@RestController
@RequestMapping("/cont")
public class ContController {

	@Autowired
	ContService contService;

	/**
	 * <pre>
	 * 1. 함수명 : selectContPg
	 * 2. 작성일 : 2019. 10. 14. 오전 9:59:25
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 컨텐츠 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/selectContPg", method = RequestMethod.GET)
	public Object selectContPg( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( contService.selectContPg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertCont
	 * 2. 작성일 : 2019. 10. 14. 오전 9:59:17
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 컨텐츠 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "menucd", "cont" })
	@RequestMapping(value = "/insertCont", method = RequestMethod.POST)
	public Object insertCont( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( contService.insertCont( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getCont
	 * 2. 작성일 : 2019. 10. 14. 오전 9:58:54
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 컨텐츠 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getCont", method = RequestMethod.GET)
	public Object getCont( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( contService.getCont( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteCont
	 * 2. 작성일 : 2019. 10. 14. 오전 9:58:36
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 컨텐츠 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "menucd", "contid" })
	@RequestMapping(value = "/deleteCont", method = RequestMethod.DELETE)
	public Object deleteCont( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( contService.deleteCont( paramMap ) );

		return resVo;
	}

}
