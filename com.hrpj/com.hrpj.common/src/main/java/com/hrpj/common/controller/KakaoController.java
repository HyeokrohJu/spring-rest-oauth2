/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.controller
 * 3. 파일명 : KakaoController.java
 * 4. 작성일 : 2020. 2. 21. 오전 11:24:56
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 카카오 관련 처리 Controller
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

import com.hrpj.common.service.KakaoService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.controller
 * 2. 타입명 : KakaoController.java
 * 3. 작성일 : 2020. 2. 21. 오전 11:24:56
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 카카오 관련 처리
 * </pre>
 */
@RestController
@RequestMapping("/kakao")
public class KakaoController {

	@Autowired
	KakaoService kakaoService;

	/**
	 * <pre>
	 * 1. 함수명 : insertCont
	 * 2. 작성일 : 2020. 2. 26. 오후 2:51:59
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 카카오 토큰 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "accesstoken" })
	@RequestMapping(value = "/insertKakaotoken", method = RequestMethod.POST)
	public Object insertKakaotoken( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( kakaoService.insertKakaoToken( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getCont
	 * 2. 작성일 : 2020. 2. 26. 오후 2:52:07
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 카카오 토큰 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getKakaotoken", method = RequestMethod.GET)
	public Object getKakaotoken( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( kakaoService.getKakaoToken( paramMap ) );

		return resVo;
	}

	@RequestMapping(value = "/getKakaoRefreshtoken", method = RequestMethod.GET)
	public Object getKakaoRefreshtoken( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( kakaoService.getKakaoRefreshtoken( paramMap ) );

		return resVo;
	}
}
