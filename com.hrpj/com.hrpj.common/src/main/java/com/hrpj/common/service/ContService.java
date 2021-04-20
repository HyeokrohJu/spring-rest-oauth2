/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service
 * 3. 파일명 : ContService.java
 * 4. 작성일 : 2019. 10. 14. 오전 9:43:31
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 컨텐츠 Service
 * </pre>
 */
package com.hrpj.common.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service
 * 2. 타입명 : ContService.java
 * 3. 작성일 : 2019. 10. 14. 오전 9:43:31
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 컨텐츠 Service
 * </pre>
 */
public interface ContService {

	/**
	 * <pre>
	 * 1. 함수명 : selectContPg
	 * 2. 작성일 : 2019. 10. 14. 오전 9:44:26
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 컨텐츠 리스트 조회
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectContPg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertCont
	 * 2. 작성일 : 2019. 10. 14. 오전 9:44:34
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 컨텐츠 등록
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertCont( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getCont
	 * 2. 작성일 : 2019. 10. 14. 오전 9:44:40
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 컨텐츠 상세조회
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getCont( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteCont
	 * 2. 작성일 : 2019. 10. 14. 오전 9:44:47
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 컨텐츠 물리삭제
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteCont( Map<String, Object> paramMap ) throws BusinessLogicException;

}
