/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.dao
 * 3. 파일명 : CommonDAO.java
 * 4. 작성일 : 2018. 1. 31. 오전 10:39:39
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.ibatis.session.RowBounds;

import com.hrpj.core.exception.DaoException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.dao
 * 2. 타입명 : CommonDAO.java
 * 3. 작성일 : 2018. 1. 31. 오전 10:39:39
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */

public interface CommonDao {

	/**
	 * <pre>
	 * 1. 함수명 : select
	 * 2. 작성일 : 2018. 1. 31. 오후 2:17:38
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 다중 Row반환 Select쿼리 호출
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @return
	 */
	public List<CaseInsensitiveMap<String, Object>> select( String queryid, Map<String, Object> paramMap ) throws DaoException;

	/**
	 * <pre>
	 * 1. 함수명 : select
	 * 2. 작성일 : 2018. 1. 31. 오후 2:18:15
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 페이징 처리를 위한 다중 Row반환 Select쿼리 호출
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @param paramRow
	 * @return
	 */
	public List<CaseInsensitiveMap<String, Object>> select( String queryid, Map<String, Object> paramMap, RowBounds paramRow ) throws DaoException;

	/**
	 * <pre>
	 * 1. 함수명 : getMap
	 * 2. 작성일 : 2018. 1. 31. 오후 2:18:30
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 단일 Row반환 Select쿼리 호출(CaseInsensitiveMap 반환)
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @return
	 */
	public CaseInsensitiveMap<String, Object> getMap( String queryid, Map<String, Object> paramMap ) throws DaoException;

	/**
	 * <pre>
	 * 1. 함수명 : getInt
	 * 2. 작성일 : 2018. 1. 31. 오후 2:18:30
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 단일 Row반환 Select쿼리 호출(int반환)
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @return
	 */
	public int getInt( String queryid, Map<String, Object> paramMap ) throws DaoException;

	/**
	 * <pre>
	 * 1. 함수명 : getString
	 * 2. 작성일 : 2018. 1. 31. 오후 2:18:30
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 단일 Row반환 Select쿼리 호출(String반환)
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @return
	 */
	public String getString( String queryid, Map<String, Object> paramMap ) throws DaoException;

	/**
	 * <pre>
	 * 1. 함수명 : getObject
	 * 2. 작성일 : 2018. 1. 31. 오후 2:18:30
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 단일 Row반환 Select쿼리 호출(Object반환)
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @return
	 */
	public Object getObject( String queryid, Map<String, Object> paramMap ) throws DaoException;

	/**
	 * <pre>
	 * 1. 함수명 : insert
	 * 2. 작성일 : 2018. 1. 31. 오후 2:18:45
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : insert쿼리 호출
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @return
	 */
	public int insert( String queryid, Map<String, Object> paramMap ) throws DaoException;

	/**
	 * <pre>
	 * 1. 함수명 : update
	 * 2. 작성일 : 2018. 1. 31. 오후 2:18:54
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : update쿼리 호출
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @return
	 */
	public int update( String queryid, Map<String, Object> paramMap ) throws DaoException;

	/**
	 * <pre>
	 * 1. 함수명 : delete
	 * 2. 작성일 : 2018. 1. 31. 오후 2:19:02
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : delete쿼리 호출
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @return
	 */
	public int delete( String queryid, Map<String, Object> paramMap ) throws DaoException;

	/**
	 * <pre>
	 * 1. 함수명 : spcall
	 * 2. 작성일 : 2018. 1. 31. 오후 2:19:18
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : procedure호출
	 * 			 output parameter가 있을경우 CaseInsensitiveMap안에 output parameter key값으로  리턴됨
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @return
	 */
	public CaseInsensitiveMap<String, Object> spcall( String queryid, Map<String, Object> paramMap ) throws DaoException;

	/**
	 * <pre>
	 * 1. 함수명 : insertReturnString
	 * 2. 작성일 : 2019. 9. 24. 오후 6:18:14
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : insert쿼리호출 (String으로 key값 리턴)
	 * </pre>
	 *
	 * @param queryid
	 * @param paramMap
	 * @return
	 * @throws DaoException
	 */
	public String insertReturnString( String queryid, Map<String, Object> paramMap ) throws DaoException;

}
