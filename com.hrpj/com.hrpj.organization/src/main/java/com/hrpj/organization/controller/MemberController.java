/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.organization
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.organization.controller
 * 3. 파일명 : MemberController.java
 * 4. 작성일 : 2019. 9. 27. 오후 5:30:49
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */
package com.hrpj.organization.controller;

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
import com.hrpj.organization.service.MemberService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.organization.controller
 * 2. 타입명 : MemberController.java
 * 3. 작성일 : 2019. 9. 27. 오후 5:30:49
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */
@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	/**
	 * <pre>
	 * 1. 함수명 : selectMemberPg
	 * 2. 작성일 : 2019. 9. 30. 오전 10:40:38
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 조회 (페이징)
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/selectMemberPg", method = RequestMethod.POST)
	public Object selectMemberPg( @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( memberService.selectMemberPg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getMember
	 * 2. 작성일 : 2019. 9. 30. 오후 2:21:59
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getMember", method = RequestMethod.GET)
	public Object getMember( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( memberService.getMember( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertMember
	 * 2. 작성일 : 2019. 9. 30. 오후 2:40:00
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@CustomValid(validField = { "usernm", "loginid", "state", "passwd", "email", "useyn", "mphone", "sex", "roleid" })
	@RequestMapping(value = "/insertMember", method = RequestMethod.POST)
	public Object insertMember( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( memberService.insertMember( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateMember
	 * 2. 작성일 : 2019. 9. 30. 오후 5:13:12
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin') or hasAuthority('role_user')")
	@CustomValid(validField = { "userid" })
	@RequestMapping(value = "/updateMember", method = RequestMethod.PUT)
	public Object updateMember( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( memberService.updateMember( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteMember
	 * 2. 작성일 : 2019. 9. 30. 오후 6:09:12
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 물리 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "userid" })
	@RequestMapping(value = "/deleteMember", method = RequestMethod.DELETE)
	public Object deleteMember( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( memberService.deleteMember( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getUserRole
	 * 2. 작성일 : 2019. 10. 2. 오후 1:46:28
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 Role 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/selectUserRole", method = RequestMethod.GET)
	public Object getUserRole( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( memberService.selectUserRole( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getFindId
	 * 2. 작성일 : 2021. 2. 24. 오후 2:19:51
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 아이디 찾기
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getFindId", method = RequestMethod.GET)
	public Object getFindId( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( memberService.getFindId( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getFindPwd
	 * 2. 작성일 : 2021. 2. 25. 오전 11:18:25
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 비밀번호 찾기
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getFindPwd", method = RequestMethod.GET)
	public Object getFindPwd( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( memberService.getFindPwd( paramMap ) );

		return resVo;
	}

}
