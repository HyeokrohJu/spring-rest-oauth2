/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.organization
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.organization.service
 * 3. 파일명 : MemberService.java
 * 4. 작성일 : 2019. 9. 27. 오후 5:35:14
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 회원 관련 Service
 * </pre>
 */
package com.hrpj.organization.service;

import java.util.Map;

import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.organization.service
 * 2. 타입명 : MemberService.java
 * 3. 작성일 : 2019. 9. 27. 오후 5:35:14
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 회원 관련 Service
 * </pre>
 */
public interface MemberService {

	/**
	 * <pre>
	 * 1. 함수명 : selectMemberPg
	 * 2. 작성일 : 2019. 9. 27. 오후 5:37:30
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 리스트 조회(페이징)
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectMemberPg( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getMember
	 * 2. 작성일 : 2019. 9. 30. 오전 11:06:57
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getMember( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : insertMember
	 * 2. 작성일 : 2019. 9. 30. 오후 2:40:20
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 등록
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> insertMember( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : updateMember
	 * 2. 작성일 : 2019. 9. 30. 오후 5:18:32
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> updateMember( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : deleteMember
	 * 2. 작성일 : 2019. 9. 30. 오후 6:09:00
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 물리 삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> deleteMember( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : selectUserRole
	 * 2. 작성일 : 2019. 10. 2. 오후 1:46:55
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 Role 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> selectUserRole( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getFindId
	 * 2. 작성일 : 2021. 2. 24. 오후 2:20:15
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 아이디 찾기
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getFindId( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : getFindPwd
	 * 2. 작성일 : 2021. 2. 24. 오후 4:15:26
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 회원 비밀번호 찾기
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws BusinessLogicException
	 */
	public Map<String, Object> getFindPwd( Map<String, Object> paramMap ) throws BusinessLogicException;

	/**
	 * <pre>
	 * 1. 함수명 : isAdmin
	 * 2. 작성일 : 2021. 2. 26. 오후 5:56:54
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 어드민 권한 여부
	 * </pre>
	 *
	 * @return
	 * @throws BusinessLogicException
	 */
	public Boolean isAdmin( ) throws BusinessLogicException;

}
