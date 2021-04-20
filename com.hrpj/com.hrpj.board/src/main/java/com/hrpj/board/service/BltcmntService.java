/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service
 * 3. 파일명 : BltcmntService.java
 * 4. 작성일 : 2019. 10. 10. 오전 10:48:00
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 댓글관련 Service
 * </pre>
 */
package com.hrpj.board.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service
 * 2. 타입명 : BltcmntService.java
 * 3. 작성일 : 2019. 10. 10. 오전 10:48:00
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 댓글관련 Service
 * </pre>
 */
public interface BltcmntService {

	/**
	 * <pre>
	 * 1. 함수명 : selectBltcmntPg
	 * 2. 작성일 : 2019. 10. 10. 오전 10:52:18
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 댓글 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectBltcmntPg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertBltcmnt
	 * 2. 작성일 : 2019. 10. 10. 오전 10:52:27
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 댓글 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertBltcmnt( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateBltcmnt
	 * 2. 작성일 : 2019. 10. 10. 오전 10:52:33
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 댓글 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateBltcmnt( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getBltcmnt
	 * 2. 작성일 : 2019. 10. 10. 오전 10:52:39
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 댓글 상세 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getBltcmnt( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteBltcmnt
	 * 2. 작성일 : 2019. 10. 10. 오전 10:52:56
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 댓글 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteBltcmnt( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteBltcmntProc
	 * 2. 작성일 : 2021. 2. 16. 오후 1:53:36
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 댓글 삭제처리
	 *   - 자식이 있을경우 state변경
	 *   - 자식이 없을경우 물리삭제 후 부모 state가 D이고 부모에 자식이 삭제 컬럼 외 다른자식이 없을경우 부모도 물리삭제
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteBltcmntProc( Map<String, Object> paramMap ) throws BusinessLogicException;

}
