/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.reservation
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.reservation.service
 * 3. 파일명 : RevService.java
 * 4. 작성일 : 2019. 10. 28. 오후 4:35:15
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 예약관련 Service
 * </pre>
 */
package com.hrpj.reservation.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.reservation.service
 * 2. 타입명 : RevService.java
 * 3. 작성일 : 2019. 10. 28. 오후 4:35:15
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 예약관련 Service
 * </pre>
 */
public interface RevService {

	/**
	 * <pre>
	 * 1. 함수명 : selectRevPg
	 * 2. 작성일 : 2019. 10. 28. 오후 4:48:56
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 리스트 조회
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectRevPg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertRev
	 * 2. 작성일 : 2019. 10. 28. 오후 4:49:02
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 생성
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertRev( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getRev
	 * 2. 작성일 : 2019. 10. 28. 오후 4:49:08
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 상세조회
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getRev( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateRev
	 * 2. 작성일 : 2019. 10. 28. 오후 4:49:15
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 수정
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateRev( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteRev
	 * 2. 작성일 : 2019. 10. 28. 오후 4:49:21
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 물리삭제
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteRev( Map<String, Object> paramMap ) throws BusinessLogicException;

}
