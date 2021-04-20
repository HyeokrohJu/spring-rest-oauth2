/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.organization
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.organization.service
 * 3. 파일명 : DeptService.java
 * 4. 작성일 : 2019. 10. 1. 오후 2:14:54
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 부서 관련 Service
 * </pre>
 */
package com.hrpj.organization.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.organization.service
 * 2. 타입명 : DeptService.java
 * 3. 작성일 : 2019. 10. 1. 오후 2:14:54
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 부서관련 Service
 * </pre>
 */
public interface DeptService {

	/**
	 * <pre>
	 * 1. 함수명 : selectDeptHierarchy
	 * 2. 작성일 : 2019. 10. 1. 오후 2:17:57
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 부서리스트 계층형 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectDeptHierarchy( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getDept
	 * 2. 작성일 : 2019. 10. 1. 오후 2:43:44
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 부서정보 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getDept( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertDept
	 * 2. 작성일 : 2019. 10. 1. 오후 2:43:51
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 부서정보 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertDept( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateDept
	 * 2. 작성일 : 2019. 10. 1. 오후 2:43:58
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 부서정보 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateDept( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteDept
	 * 2. 작성일 : 2019. 10. 1. 오후 2:44:04
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 부서정보 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteDept( Map<String, Object> paramMap ) throws BusinessLogicException;

}
