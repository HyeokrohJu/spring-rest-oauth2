/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service.impl
 * 3. 파일명 : BltrcmdServiceImpl.java
 * 4. 작성일 : 2019. 10. 10. 오후 1:57:05
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 추천 관련 Service 구현
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

import com.hrpj.board.service.BltrcmdService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.DaoUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service.impl
 * 2. 타입명 : BltrcmdServiceImpl.java
 * 3. 작성일 : 2019. 10. 10. 오후 1:57:05
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 추천 관련 Service 구현
 * </pre>
 */
@Service("bltrcmdService")
public class BltrcmdServiceImpl implements BltrcmdService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltrcmdService#selectBltrcmdPg(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectBltrcmdPg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.board.hrpjbltrcmd.selectBltrcmd", paramMap, rb );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "게시물 추천 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectBltrcmdPg", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltrcmdService#insertBltrcmd(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertBltrcmd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );

		/**
		 * 등록된 게시물 추천정보 삭제
		 */
		commonDao.update( "mapper.board.hrpjbltrcmd.deleteBltrcmd", paramMap );

		final String retDaoData = commonDao.insertReturnString( "mapper.board.hrpjbltrcmd.insertBltrcmd", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "게시물 추천 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltrcmdService#getBltrcmd(java.util.Map)
	 */
	@Override
	public Map<String, Object> getBltrcmd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.board.hrpjbltrcmd.selectBltrcmd", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getBltrcmd", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltrcmdService#deleteBltrcmd(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteBltrcmd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.board.hrpjbltrcmd.deleteBltrcmd", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "게시물 추천 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
