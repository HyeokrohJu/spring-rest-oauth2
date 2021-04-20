/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service
 * 3. 파일명 : KakaoService.java
 * 4. 작성일 : 2020. 2. 21. 오전 11:28:56
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : Kakao Service
 * </pre>
 */
package com.hrpj.common.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service
 * 2. 타입명 : KakaoService.java
 * 3. 작성일 : 2020. 2. 21. 오전 11:28:56
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Kakao Service
 * </pre>
 */
public interface KakaoService {

	/**
	 * <pre>
	 * 1. 함수명 : insertKakaoToken
	 * 2. 작성일 : 2020. 2. 21. 오전 11:28:56
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : Kakao Token 저장
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertKakaoToken( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getKakaoToken
	 * 2. 작성일 : 2020. 2. 21. 오전 11:29:05
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : Kakao Token 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getKakaoToken( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getKakaoRefreshtoken
	 * 2. 작성일 : 2020. 2. 26. 오후 3:37:48
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : kakao refresh token 생성
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getKakaoRefreshtoken( Map<String, Object> paramMap ) throws BusinessLogicException;

}
