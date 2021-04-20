/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service
 * 3. 파일명 : CdService.java
 * 4. 작성일 : 2019. 9. 27. 오후 3:12:00
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 코드관련 Service
 * </pre>
 */
package com.hrpj.common.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service
 * 2. 타입명 : CdService.java
 * 3. 작성일 : 2019. 9. 27. 오후 3:12:00
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 코드관련 Service
 * </pre>
 */
public interface CdService {

	/**
	 * <pre>
	 * 1. 함수명 : selectCdHierarchy
	 * 2. 작성일 : 2019. 9. 27. 오후 3:15:24
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 계층형 코드 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectCdHierarchy( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertCd
	 * 2. 작성일 : 2019. 9. 27. 오후 3:47:15
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 코드 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertCd( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getCd
	 * 2. 작성일 : 2019. 9. 27. 오후 5:07:12
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 코드 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getCd( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateCd
	 * 2. 작성일 : 2019. 9. 27. 오후 5:17:05
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 코드 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateCd( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteCd
	 * 2. 작성일 : 2019. 10. 1. 오후 3:38:36
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 코드 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteCd( Map<String, Object> paramMap ) throws BusinessLogicException;

}
