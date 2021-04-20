/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service
 * 3. 파일명 : AttachService.java
 * 4. 작성일 : 2019. 10. 11. 오후 1:30:38
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 첨부파일 관련 Service
 * </pre>
 */
package com.hrpj.common.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service
 * 2. 타입명 : AttachService.java
 * 3. 작성일 : 2019. 10. 11. 오후 1:30:38
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :  첨부파일 관련 Service
 * </pre>
 */
public interface AttachService {

	/**
	 * <pre>
	 * 1. 함수명 : selectAttachPg
	 * 2. 작성일 : 2019. 10. 11. 오후 6:01:58
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectAttachPg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : selectAttach
	 * 2. 작성일 : 2019. 10. 11. 오후 6:02:10
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 전체 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectAttach( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : uploadAttach
	 * 2. 작성일 : 2019. 10. 11. 오후 6:02:19
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 업로드
	 * </pre>
	 *
	 * @param paramMap
	 * @param file
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> uploadAttach( Map<String, Object> paramMap, MultipartFile file ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateAttach
	 * 2. 작성일 : 2019. 10. 11. 오후 6:02:29
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateAttach( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getAttach
	 * 2. 작성일 : 2019. 10. 11. 오후 6:02:35
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getAttach( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteAttach
	 * 2. 작성일 : 2019. 10. 11. 오후 6:02:41
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 물리 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteAttach( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : attachDownload
	 * 2. 작성일 : 2019. 10. 11. 오후 6:02:52
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 다운로드
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> attachDownload( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateAttachDB
	 * 2. 작성일 : 2019. 11. 28. 오후 10:06:08
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 DB 수정
	 * </pre>
	 * 
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateAttachDB( Map<String, Object> paramMap ) throws BusinessLogicException;

}
