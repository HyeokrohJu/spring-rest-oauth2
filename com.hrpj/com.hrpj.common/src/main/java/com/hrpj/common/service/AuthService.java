/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service
 * 3. 파일명 : AuthService.java
 * 4. 작성일 : 2019. 11. 22. 오후 4:30:01
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 권한 관련 Service
 * </pre>
 */
package com.hrpj.common.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service
 * 2. 타입명 : AuthService.java
 * 3. 작성일 : 2019. 11. 22. 오후 4:30:01
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 권한 관련 Service
 * </pre>
 */
public interface AuthService {

	/**
	 * <pre>
	 * 1. 함수명 : insertAuth
	 * 2. 작성일 : 2019. 11. 22. 오후 4:44:55
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 권한 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertAuth( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteAuth
	 * 2. 작성일 : 2019. 11. 22. 오후 4:46:38
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 권한 삭제
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteAuth( Map<String, Object> paramMap ) throws BusinessLogicException;

}
