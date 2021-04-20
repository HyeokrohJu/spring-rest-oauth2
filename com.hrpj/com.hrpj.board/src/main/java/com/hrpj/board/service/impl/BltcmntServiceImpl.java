/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service.impl
 * 3. 파일명 : BltcmntServiceImpl.java
 * 4. 작성일 : 2019. 10. 10. 오전 10:48:10
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 댓글관련 Service 구현
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

import com.hrpj.board.service.BltcmntService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.DaoUtils;
import com.hrpj.core.utils.StringUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service.impl
 * 2. 타입명 : BltcmntServiceImpl.java
 * 3. 작성일 : 2019. 10. 10. 오전 10:48:10
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 댓글관련 Service 구현
 * </pre>
 */
@Service("bltcmntServcie")
public class BltcmntServiceImpl implements BltcmntService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltcmntService#selectBltcmntPg(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectBltcmntPg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		paramMap.put( "selectcnt", "Y" );
		final CaseInsensitiveMap<String, Object> retDaoData1 = commonDao.getMap( "mapper.board.hrpjbltcmnt.selectBltcmnt", paramMap );
		if ( retDaoData1 == null ) {
			throw new BusinessLogicException( "댓글 리스트 카운트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		paramMap.remove( "selectcnt" );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData2 = commonDao.select( "mapper.board.hrpjbltcmnt.selectBltcmnt", paramMap, rb );
		if ( retDaoData2 == null ) {
			throw new BusinessLogicException( "댓글 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectBltcmntCnt", retDaoData1 );
		retMap.put( "selectBltcmntPg", retDaoData2 );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltcmntService#insertBltcmnt(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertBltcmnt( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );

		if ( !StringUtils.isNull( paramMap.get( "currtcmntid" ) ) ) {
			final Map<String, Object> seMap = new HashMap<>( );
			seMap.put( "cmntid", paramMap.get( "currtcmntid" ) );
			final CaseInsensitiveMap<String, Object> retDaoData1 = commonDao.getMap( "mapper.board.hrpjbltcmnt.selectBltcmnt", seMap );

			if ( retDaoData1 == null ) {
				paramMap.put( "upcmntid", "0" );
				paramMap.put( "disporder", "0" );
				paramMap.put( "lvl", "0" );
			} else {
				/**
				 * 댓글 순번 변경
				 */
				paramMap.put( "upcmntid", StringUtils.getParseInt( retDaoData1.get( "cmntid" ), "0" ) );
				paramMap.put( "disporder", StringUtils.getParseInt( retDaoData1.get( "disporder" ), "0" ) );
				paramMap.put( "orgcmntid", retDaoData1.get( "orgcmntid" ) );
				paramMap.put( "lvl", StringUtils.getParseInt( retDaoData1.get( "lvl" ), "0" ) );
				commonDao.update( "mapper.board.hrpjbltcmnt.updateBltcmntSort", paramMap );
			}
		}

		final String retDaoData2 = commonDao.insertReturnString( "mapper.board.hrpjbltcmnt.insertBltcmnt", paramMap );
		if ( retDaoData2.equals( "" ) ) {
			throw new BusinessLogicException( "댓글 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "cmntid", retDaoData2 );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltcmntService#updateBltcmnt(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateBltcmnt( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.update( "mapper.board.hrpjbltcmnt.updateBltcmnt", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "댓글 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltcmntService#getBltcmnt(java.util.Map)
	 */
	@Override
	public Map<String, Object> getBltcmnt( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.board.hrpjbltcmnt.selectBltcmnt", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getBltcmnt", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltcmntService#deleteBltcmnt(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteBltcmnt( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.board.hrpjbltcmnt.deleteBltcmnt", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "댓글 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	@Override
	public Map<String, Object> deleteBltcmntProc( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.board.hrpjbltcmnt.deleteBltCmntProc", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "댓글 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
