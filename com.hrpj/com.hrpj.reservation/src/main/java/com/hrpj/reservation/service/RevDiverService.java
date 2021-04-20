/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.reservation
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.reservation.service
 * 3. 파일명 : RevDiverService.java
 * 4. 작성일 : 2019. 10. 28. 오후 4:56:17
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 예약 다이버 정보 Service
 * </pre>
 */
package com.hrpj.reservation.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.reservation.service
 * 2. 타입명 : RevDiverService.java
 * 3. 작성일 : 2019. 10. 28. 오후 4:56:17
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 예약 다이버 정보 Service
 * </pre>
 */
public interface RevDiverService {

	/**
	 * <pre>
	 * 1. 함수명 : selectRevDiver
	 * 2. 작성일 : 2019. 10. 28. 오후 4:58:36
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 다이버 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectRevDiver( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertRevDiver
	 * 2. 작성일 : 2019. 10. 28. 오후 4:58:42
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 다이버 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertRevDiver( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getRevDiver
	 * 2. 작성일 : 2019. 10. 28. 오후 4:59:04
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 다이버 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getRevDiver( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateRevDiver
	 * 2. 작성일 : 2019. 10. 28. 오후 4:59:10
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 다이버 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateRevDiver( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteRevDiver
	 * 2. 작성일 : 2019. 10. 28. 오후 4:59:16
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 다이버 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteRevDiver( Map<String, Object> paramMap ) throws BusinessLogicException;

}
