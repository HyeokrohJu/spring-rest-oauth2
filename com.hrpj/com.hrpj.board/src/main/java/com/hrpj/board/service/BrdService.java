/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service
 * 3. 파일명 : BrdService.java
 * 4. 작성일 : 2019. 10. 4. 오후 3:24:26
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시판 Service
 * </pre>
 */
package com.hrpj.board.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service
 * 2. 타입명 : BrdService.java
 * 3. 작성일 : 2019. 10. 4. 오후 3:24:26
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시판 Service
 * </pre>
 */
public interface BrdService {

	/**
	 * <pre>
	 * 1. 함수명 : selectBrdHierarchy
	 * 2. 작성일 : 2019. 10. 4. 오후 3:26:36
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 계층형 게시판 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectBrdHierarchy( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertBrd
	 * 2. 작성일 : 2019. 10. 4. 오후 6:05:34
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertBrd( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateBrd
	 * 2. 작성일 : 2019. 10. 4. 오후 6:07:46
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateBrd( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getBrd
	 * 2. 작성일 : 2019. 10. 4. 오후 6:09:36
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getBrd( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteBrd
	 * 2. 작성일 : 2019. 10. 4. 오후 6:11:26
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteBrd( Map<String, Object> paramMap ) throws BusinessLogicException;

}
