/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.reservation
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.reservation.service
 * 3. 파일명 : RevInvoiceService.java
 * 4. 작성일 : 2019. 10. 28. 오후 5:03:57
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 예약 Invoice Service
 * </pre>
 */
package com.hrpj.reservation.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.reservation.service
 * 2. 타입명 : RevInvoiceService.java
 * 3. 작성일 : 2019. 10. 28. 오후 5:03:57
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 예약 Invoice Service
 * </pre>
 */
public interface RevInvoiceService {

	/**
	 * <pre>
	 * 1. 함수명 : selectRevInvoice
	 * 2. 작성일 : 2019. 10. 28. 오후 5:06:08
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 Invoice 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectRevInvoice( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertRevInvoice
	 * 2. 작성일 : 2019. 10. 28. 오후 5:06:22
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 Invoice 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertRevInvoice( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getRevInvoice
	 * 2. 작성일 : 2019. 10. 28. 오후 5:06:29
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 Invoice 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getRevInvoice( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateRevInvoice
	 * 2. 작성일 : 2019. 10. 28. 오후 5:06:36
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 Invoice 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateRevInvoice( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteRevInvoice
	 * 2. 작성일 : 2019. 10. 28. 오후 5:06:41
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 예약 Invoice 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteRevInvoice( Map<String, Object> paramMap ) throws BusinessLogicException;

}
