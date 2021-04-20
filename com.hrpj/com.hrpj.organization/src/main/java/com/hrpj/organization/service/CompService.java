/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.organization
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.organization.service
 * 3. 파일명 : CompService.java
 * 4. 작성일 : 2019. 10. 1. 오전 11:14:07
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 회사관련 Service구현
 * </pre>
 */
package com.hrpj.organization.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.organization.service
 * 2. 타입명 : CompService.java
 * 3. 작성일 : 2019. 10. 1. 오전 11:14:07
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 회사관련 Service구현
 * </pre>
 */
public interface CompService {

	/**
	 * <pre>
	 * 1. 함수명 : selectCompPg
	 * 2. 작성일 : 2019. 10. 1. 오전 11:22:19
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회사정보 리스트 조회(페이징)
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectCompPg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getComp
	 * 2. 작성일 : 2019. 10. 1. 오전 11:40:08
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회사정보 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getComp( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertComp
	 * 2. 작성일 : 2019. 10. 1. 오전 11:49:16
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회사정보 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertComp( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateComp
	 * 2. 작성일 : 2019. 10. 1. 오후 1:49:54
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회사정보 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateComp( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteComp
	 * 2. 작성일 : 2019. 10. 1. 오후 1:57:21
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회사정보 물리삭제
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteComp( Map<String, Object> paramMap ) throws BusinessLogicException;

}
