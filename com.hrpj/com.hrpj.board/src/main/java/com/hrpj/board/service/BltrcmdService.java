/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service
 * 3. 파일명 : BltrcmdService.java
 * 4. 작성일 : 2019. 10. 10. 오후 1:56:34
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 추천 관련 Service
 * </pre>
 */
package com.hrpj.board.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service
 * 2. 타입명 : BltrcmdService.java
 * 3. 작성일 : 2019. 10. 10. 오후 1:56:34
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 추천 관련 Service
 * </pre>
 */
public interface BltrcmdService {

	/**
	 * <pre>
	 * 1. 함수명 : selectBltrcmdPg
	 * 2. 작성일 : 2019. 10. 10. 오후 2:00:19
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 추천 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectBltrcmdPg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertBltrcmd
	 * 2. 작성일 : 2019. 10. 10. 오후 2:00:27
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 추천 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertBltrcmd( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getBltrcmd
	 * 2. 작성일 : 2019. 10. 10. 오후 2:00:47
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 추천 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getBltrcmd( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteBltrcmd
	 * 2. 작성일 : 2019. 10. 10. 오후 2:00:54
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 추천 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteBltrcmd( Map<String, Object> paramMap ) throws BusinessLogicException;

}
