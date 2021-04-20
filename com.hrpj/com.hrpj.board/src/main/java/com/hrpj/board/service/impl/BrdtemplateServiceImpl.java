/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service.impl
 * 3. 파일명 : BrdtemplateServiceImpl.java
 * 4. 작성일 : 2019. 10. 8. 오후 3:02:42
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시판 템플릿 관리 Service 구현
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

import com.hrpj.board.service.BrdtemplateService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.DaoUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service.impl
 * 2. 타입명 : BrdtemplateServiceImpl.java
 * 3. 작성일 : 2019. 10. 8. 오후 3:02:42
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시판 템플릿 관리 Service 구현
 * </pre>
 */
@Service("brdtemplateService")
public class BrdtemplateServiceImpl implements BrdtemplateService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.board.service.BrdtemplateService#selectBrdtemplatePg(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> selectBrdtemplatePg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData =
			commonDao.select( "mapper.board.hrpjbrdtemplate.selectBrdtemplate", paramMap, rb );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "게시판 템플릿 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectBrdtemplatePg", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.board.service.BrdtemplateService#insertBrdtemplate(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> insertBrdtemplate( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData = commonDao.insertReturnString( "mapper.board.hrpjbrdtemplate.insertBrdtemplate", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "게시판 템플릿 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "templateid", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.board.service.BrdtemplateService#updateBrdtemplate(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> updateBrdtemplate( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.update( "mapper.board.hrpjbrdtemplate.updateBrdtemplate", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "게시판 템플릿 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.board.service.BrdtemplateService#getBrdtemplate(java.util.Map)
	 */
	@Override
	public Map<String, Object> getBrdtemplate( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.board.hrpjbrdtemplate.selectBrdtemplate", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getBrdtemplate", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.board.service.BrdtemplateService#deleteBrdtemplate(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> deleteBrdtemplate( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.board.hrpjbrdtemplate.deleteBrdtemplate", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "게시판 템플릿 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
