/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service
 * 3. 파일명 : BltreadService.java
 * 4. 작성일 : 2019. 10. 11. 오전 10:40:11
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 열람내역 관련 Service
 * </pre>
 */
package com.hrpj.board.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service
 * 2. 타입명 : BltreadService.java
 * 3. 작성일 : 2019. 10. 11. 오전 10:40:11
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 열람내역 관련 Service
 * </pre>
 */
public interface BltreadService {

	/**
	 * <pre>
	 * 1. 함수명 : selectBltreadPg
	 * 2. 작성일 : 2019. 10. 11. 오전 10:55:35
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 열람내역 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectBltreadPg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertBltread
	 * 2. 작성일 : 2019. 10. 11. 오전 10:55:33
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 열람내역 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertBltread( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getBltread
	 * 2. 작성일 : 2019. 10. 11. 오전 10:55:31
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 열람내역 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getBltread( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteBltread
	 * 2. 작성일 : 2019. 10. 11. 오전 10:55:29
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 열람내역 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteBltread( Map<String, Object> paramMap ) throws BusinessLogicException;

}
