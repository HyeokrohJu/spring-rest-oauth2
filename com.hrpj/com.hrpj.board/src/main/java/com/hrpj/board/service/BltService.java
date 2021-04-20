/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service
 * 3. 파일명 : BltService.java
 * 4. 작성일 : 2019. 10. 7. 오후 3:58:13
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 관련 Service
 * </pre>
 */
package com.hrpj.board.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service
 * 2. 타입명 : BltService.java
 * 3. 작성일 : 2019. 10. 7. 오후 3:58:13
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 관련 Service
 * </pre>
 */
public interface BltService {

	/**
	 * <pre>
	 * 1. 함수명 : selectBltPg
	 * 2. 작성일 : 2019. 10. 7. 오후 4:09:24
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> selectBltPg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertBlt
	 * 2. 작성일 : 2019. 10. 7. 오후 4:10:22
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertBlt( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateBlt
	 * 2. 작성일 : 2019. 10. 7. 오후 4:10:24
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateBlt( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getBlt
	 * 2. 작성일 : 2019. 10. 7. 오후 4:10:27
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getBlt( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteBlt
	 * 2. 작성일 : 2019. 10. 7. 오후 4:10:29
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteBlt( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateCntBlt
	 * 2. 작성일 : 2020. 2. 3. 오후 3:18:16
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시물 조회수 증가
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateCntBlt( Map<String, Object> paramMap ) throws BusinessLogicException;

}
