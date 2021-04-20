/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.reservation
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.reservation.controller
 * 3. 파일명 : RevController.java
 * 4. 작성일 : 2019. 10. 28. 오후 4:30:43
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 에약관련
 * </pre>
 */
package com.hrpj.reservation.controller;

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
import com.hrpj.reservation.service.RevService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.reservation.controller
 * 2. 타입명 : RevController.java
 * 3. 작성일 : 2019. 10. 28. 오후 4:30:43
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 에약관련
 * </pre>
 */
@RestController
@RequestMapping("/rev")
public class RevController {

	@Autowired
	RevService revService;

	/**
	 * <pre>
	 * 1. 함수명 : selectRevPg
	 * 2. 작성일 : 2019. 10. 28. 오후 4:48:14
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/selectRevPg", method = RequestMethod.GET)
	public Object selectRevPg( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( revService.selectRevPg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertRev
	 * 2. 작성일 : 2019. 10. 28. 오후 4:48:23
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@CustomValid(validField = { "revMap", "revDiverMap" })
	@RequestMapping(value = "/insertRev", method = RequestMethod.POST)
	public Object insertRev( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( revService.insertRev( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getRev
	 * 2. 작성일 : 2019. 10. 28. 오후 4:48:32
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@RequestMapping(value = "/getRev", method = RequestMethod.GET)
	public Object getRev( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( revService.getRev( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateRev
	 * 2. 작성일 : 2019. 10. 28. 오후 4:48:38
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@CustomValid(validField = { "revMap" })
	@RequestMapping(value = "/updateRev", method = RequestMethod.PUT)
	public Object updateRev( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( revService.updateRev( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteRev
	 * 2. 작성일 : 2019. 10. 28. 오후 4:48:46
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 물리 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "revid" })
	@RequestMapping(value = "/deleteRev", method = RequestMethod.DELETE)
	public Object deleteRev( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( revService.deleteRev( paramMap ) );

		return resVo;
	}

}
