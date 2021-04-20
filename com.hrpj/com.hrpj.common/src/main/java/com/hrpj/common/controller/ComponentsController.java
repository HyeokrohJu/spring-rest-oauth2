/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.controller
 * 3. 파일명 : ComponentsController.java
 * 4. 작성일 : 2019. 10. 28. 오후 1:23:06
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : React Components 관리
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

import com.hrpj.common.service.ComponentsService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.controller
 * 2. 타입명 : ComponentsController.java
 * 3. 작성일 : 2019. 10. 28. 오후 1:23:06
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : React Components 관리
 * </pre>
 */
@RestController
@RequestMapping("/components")
public class ComponentsController {

	@Autowired
	ComponentsService componentsService;

	/**
	 * <pre>
	 * 1. 함수명 : selectComponentsPg
	 * 2. 작성일 : 2019. 10. 28. 오후 1:34:45
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : React Components 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/selectComponentsPg", method = RequestMethod.GET)
	public Object selectComponentsPg( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( componentsService.selectComponentsPg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertComponents
	 * 2. 작성일 : 2019. 10. 28. 오후 1:35:00
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : React Components 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "menucd", "upath", "cpath", "useyn" })
	@RequestMapping(value = "/insertComponents", method = RequestMethod.POST)
	public Object insertComponents( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( componentsService.insertComponents( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getComponents
	 * 2. 작성일 : 2019. 10. 28. 오후 1:35:12
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : React Components 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/getComponents", method = RequestMethod.GET)
	public Object getComponents( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( componentsService.getComponents( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateComponents
	 * 2. 작성일 : 2019. 10. 28. 오후 1:35:27
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : React Components 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "upath", "cpath" })
	@RequestMapping(value = "/updateComponents", method = RequestMethod.PUT)
	public Object updateComponents( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( componentsService.updateComponents( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteComponents
	 * 2. 작성일 : 2019. 10. 28. 오후 1:35:38
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : React Components 물리 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/deleteComponents", method = RequestMethod.DELETE)
	public Object deleteComponents( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( componentsService.deleteComponents( paramMap ) );

		return resVo;
	}

}
