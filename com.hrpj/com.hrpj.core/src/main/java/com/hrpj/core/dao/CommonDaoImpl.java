/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.dao
 * 3. 파일명 : CommonDaoImpl.java
 * 4. 작성일 : 2018. 1. 31. 오전 10:41:12
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrpj.core.exception.DaoException;
import com.hrpj.core.utils.StringUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.dao
 * 2. 타입명 : CommonDaoImpl.java
 * 3. 작성일 : 2018. 1. 31. 오전 10:41:12
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */

@Repository("commonDao")
public class CommonDaoImpl implements CommonDao {

	private final Log logger = LogFactory.getLog( this.getClass( ) );

	@Autowired
	private SqlSessionTemplate sqlSession;

	protected void logQueryId( String queryid ) {
		if ( logger.isDebugEnabled( ) ) {
			logger.debug( "\t SqlQueryId \t: " + queryid );
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#select(java.lang.String, java.util.Map)
	 */
	@Override
	public List<CaseInsensitiveMap<String, Object>> select( String queryid, Map<String, Object> paramMap ) throws DaoException {
		this.logQueryId( queryid );
		return sqlSession.selectList( queryid, paramMap );
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#select(java.lang.String, java.util.Map,
	 * org.apache.ibatis.session.RowBounds)
	 */
	@Override
	public List<CaseInsensitiveMap<String, Object>> select( String queryid, Map<String, Object> paramMap, RowBounds paramRow ) throws DaoException {
		this.logQueryId( queryid );
		return sqlSession.selectList( queryid, paramMap, paramRow );
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#get(java.lang.String, java.util.Map)
	 */
	@Override
	public CaseInsensitiveMap<String, Object> getMap( String queryid, Map<String, Object> paramMap ) throws DaoException {
		this.logQueryId( queryid );
		return sqlSession.selectOne( queryid, paramMap );
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#getInt(java.lang.String, java.util.Map)
	 */
	@Override
	public int getInt( String queryid, Map<String, Object> paramMap ) throws DaoException {
		this.logQueryId( queryid );
		return sqlSession.selectOne( queryid, paramMap );
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#getString(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public String getString( String queryid, Map<String, Object> paramMap ) throws DaoException {
		this.logQueryId( queryid );
		return sqlSession.selectOne( queryid, paramMap );
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#getObject(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public Object getObject( String queryid, Map<String, Object> paramMap ) throws DaoException {
		this.logQueryId( queryid );
		return sqlSession.selectOne( queryid, paramMap );
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#insert(java.lang.String, java.util.Map)
	 */
	@Override
	public int insert( String queryid, Map<String, Object> paramMap ) throws DaoException {
		this.logQueryId( queryid );
		return sqlSession.insert( queryid, paramMap );
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#update(java.lang.String, java.util.Map)
	 */
	@Override
	public int update( String queryid, Map<String, Object> paramMap ) throws DaoException {
		this.logQueryId( queryid );
		return sqlSession.update( queryid, paramMap );
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#delete(java.lang.String, java.util.Map)
	 */
	@Override
	public int delete( String queryid, Map<String, Object> paramMap ) throws DaoException {
		this.logQueryId( queryid );
		return sqlSession.delete( queryid, paramMap );
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#spcall(java.lang.String, java.util.Map)
	 */
	@Override
	public CaseInsensitiveMap<String, Object> spcall( String queryid, Map<String, Object> paramMap ) throws DaoException {
		this.logQueryId( queryid );
		return sqlSession.selectOne( queryid, paramMap );
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.core.dao.CommonDao#insertReturnString(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public String insertReturnString( String queryid, Map<String, Object> paramMap ) throws DaoException {
		this.logQueryId( queryid );
		sqlSession.insert( queryid, paramMap );
		return StringUtils.getDefaultString( paramMap.get( "returnKey" ), "" );
	}

}
