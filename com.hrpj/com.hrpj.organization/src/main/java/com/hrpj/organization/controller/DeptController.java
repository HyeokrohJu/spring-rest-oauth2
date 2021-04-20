/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.organization
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.organization.controller
 * 3. 파일명 : DeptController.java
 * 4. 작성일 : 2019. 10. 1. 오후 2:14:03
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 부서 관련 Controller
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
import com.hrpj.organization.service.DeptService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.organization.controller
 * 2. 타입명 : DeptController.java
 * 3. 작성일 : 2019. 10. 1. 오후 2:14:03
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 부서 관련 Controller
 * </pre>
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	DeptService deptService;

	/**
	 * <pre>
	 * 1. 함수명 : selectDeptTree
	 * 2. 작성일 : 2019. 10. 1. 오후 2:17:15
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 부서리스트 계층형 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/selectDeptTree", method = RequestMethod.GET)
	public Object selectDeptTree( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( deptService.selectDeptHierarchy( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getDept
	 * 2. 작성일 : 2019. 10. 1. 오후 2:41:26
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 부서정보 상세 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/getDept", method = RequestMethod.GET)
	public Object getDept( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( deptService.getDept( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : insertDept
	 * 2. 작성일 : 2019. 10. 1. 오후 2:42:06
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 부서정보 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "deptnm", "updeptid", "compid", "compnm", "state", "useyn" })
	@RequestMapping(value = "/insertDept", method = RequestMethod.POST)
	public Object insertDept( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( deptService.insertDept( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateDept
	 * 2. 작성일 : 2019. 10. 1. 오후 2:42:36
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 부서정보 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "compid", "deptid" })
	@RequestMapping(value = "/updateDept", method = RequestMethod.PUT)
	public Object updateDept( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( deptService.updateDept( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteDept
	 * 2. 작성일 : 2019. 10. 1. 오후 2:43:04
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 부서정보 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@CustomValid(validField = { "deptid" })
	@RequestMapping(value = "/deleteDept", method = RequestMethod.DELETE)
	public Object deleteDept( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( deptService.deleteDept( paramMap ) );

		return resVo;
	}

}
