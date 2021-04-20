/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service
 * 3. 파일명 : MenuService.java
 * 4. 작성일 : 2019. 9. 18. 오전 11:45:08
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 메뉴 관련 Service
 * </pre>
 */
package com.hrpj.common.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service
 * 2. 타입명 : MenuService.java
 * 3. 작성일 : 2019. 9. 18. 오전 11:45:08
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 메뉴관련 Service
 * </pre>
 */
public interface MenuService {

	/**
	 * <pre>
	 * 1. 함수명 : selectSgrp
	 * 2. 작성일 : 2019. 9. 25. 오전 10:57:46
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트그룹 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> selectSgrp( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertSgrp
	 * 2. 작성일 : 2019. 9. 25. 오전 10:55:56
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트그룹 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> insertSgrp( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateSgrp
	 * 2. 작성일 : 2019. 9. 27. 오전 9:29:18
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트그룹 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateSgrp( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getSgrp
	 * 2. 작성일 : 2019. 9. 27. 오후 12:31:28
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트그룹 상세 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getSgrp( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteSgrp
	 * 2. 작성일 : 2019. 9. 27. 오후 12:58:42
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 사이트그룹 물리 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteSgrp( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : selectMenuHierarchy
	 * 2. 작성일 : 2019. 9. 18. 오후 3:36:01
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 계층형 메뉴 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> selectMenuHierarchy( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : selectMenu
	 * 2. 작성일 : 2019. 12. 9. 오후 2:17:14
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메뉴 리스트 조회
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectMenu( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertMenu
	 * 2. 작성일 : 2019. 9. 24. 오후 5:56:39
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메뉴 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> insertMenu( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateMenu
	 * 2. 작성일 : 2019. 9. 27. 오전 10:46:42
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메뉴 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateMenu( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getMenu
	 * 2. 작성일 : 2019. 9. 27. 오후 12:40:00
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메뉴 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getMenu( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteMenu
	 * 2. 작성일 : 2019. 9. 30. 오후 6:04:43
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메뉴 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteMenu( Map<String, Object> paramMap ) throws BusinessLogicException;

}
