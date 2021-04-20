/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service
 * 3. 파일명 : ComponentsService.java
 * 4. 작성일 : 2019. 10. 28. 오후 1:30:06
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : React Components 관리 Service
 * </pre>
 */
package com.hrpj.common.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service
 * 2. 타입명 : ComponentsService.java
 * 3. 작성일 : 2019. 10. 28. 오후 1:30:06
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : React Components 관리 Service
 * </pre>
 */
public interface ComponentsService {

	/**
	 * <pre>
	 * 1. 함수명 : selectComponentsPg
	 * 2. 작성일 : 2019. 10. 28. 오후 1:35:57
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : React Components 리스트 조회
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectComponentsPg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertComponents
	 * 2. 작성일 : 2019. 10. 28. 오후 1:36:11
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : React Components 등록
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertComponents( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getComponents
	 * 2. 작성일 : 2019. 10. 28. 오후 1:36:16
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : React Components 상세 조회
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getComponents( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateComponents
	 * 2. 작성일 : 2019. 10. 28. 오후 1:36:23
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : React Components 수정
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateComponents( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteComponents
	 * 2. 작성일 : 2019. 10. 28. 오후 1:36:29
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : React Components  물리삭제
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteComponents( Map<String, Object> paramMap ) throws BusinessLogicException;

}
