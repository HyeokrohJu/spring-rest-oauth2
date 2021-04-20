/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service
 * 3. 파일명 : BrdtemplateService.java
 * 4. 작성일 : 2019. 10. 8. 오후 3:02:34
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시판 템플릿 관리 Service
 * </pre>
 */
package com.hrpj.board.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service
 * 2. 타입명 : BrdtemplateService.java
 * 3. 작성일 : 2019. 10. 8. 오후 3:02:34
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시판 템플릿 관리 Service
 * </pre>
 */
public interface BrdtemplateService {

	/**
	 * <pre>
	 * 1. 함수명 : selectBrdtemplatePg
	 * 2. 작성일 : 2019. 10. 8. 오후 3:41:18
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 템플릿 리스트조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectBrdtemplatePg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertBrdtemplate
	 * 2. 작성일 : 2019. 10. 8. 오후 3:41:29
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 템플릿 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertBrdtemplate( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateBrdtemplate
	 * 2. 작성일 : 2019. 10. 8. 오후 3:41:37
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 템플릿 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateBrdtemplate( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getBrdtemplate
	 * 2. 작성일 : 2019. 10. 8. 오후 3:41:44
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 템플릿 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getBrdtemplate( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteBrdtemplate
	 * 2. 작성일 : 2019. 10. 8. 오후 3:41:52
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 게시판 템플릿 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteBrdtemplate( Map<String, Object> paramMap ) throws BusinessLogicException;

}
