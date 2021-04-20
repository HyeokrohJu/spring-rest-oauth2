/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service.impl
 * 3. 파일명 : BltreadServiceImpl.java
 * 4. 작성일 : 2019. 10. 11. 오전 10:42:18
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 열람내역 관련 Service구현
 * </pre>
 */
package com.hrpj.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.board.service.BltreadService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.DaoUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service.impl
 * 2. 타입명 : BltreadServiceImpl.java
 * 3. 작성일 : 2019. 10. 11. 오전 10:42:18
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 열람내역 관련 Service구현
 * </pre>
 */
@Service("bltreadService")
public class BltreadServiceImpl implements BltreadService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltreadService#selectBltreadPg(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectBltreadPg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.board.hrpjbltread.selectBltread", paramMap, rb );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "게시물 열람내역 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectBltreadPg", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltreadService#insertBltread(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertBltread( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData = commonDao.insertReturnString( "mapper.board.hrpjbltread.insertBltread", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "게시물 열람내역 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltreadService#getBltread(java.util.Map)
	 */
	@Override
	public Map<String, Object> getBltread( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.board.hrpjbltread.selectBltread", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getBltread", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltreadService#deleteBltread(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteBltread( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.board.hrpjbltread.deleteBltread", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "게시물 열람내역 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
