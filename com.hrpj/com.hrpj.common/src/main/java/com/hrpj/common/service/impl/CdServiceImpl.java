/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service.impl
 * 3. 파일명 : CdServiceImpl.java
 * 4. 작성일 : 2019. 9. 27. 오후 3:12:09
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 코드관련 Service구현
 * </pre>
 */
package com.hrpj.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.common.service.CdService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.TreeObjectUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service.impl
 * 2. 타입명 : CdServiceImpl.java
 * 3. 작성일 : 2019. 9. 27. 오후 3:12:09
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 코드관련 Service 구현
 * </pre>
 */
@Service("cdService")
public class CdServiceImpl implements CdService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.CdService#selectCdHierarchy(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectCdHierarchy( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.common.hrpjcd.selectCdHierarchy", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "코드 계층형 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		final TreeObjectUtils treeConvert = new TreeObjectUtils( );
		final Map<String, String> fieldMapping = new HashMap<>( );
		fieldMapping.put( "id", "cd" );
		fieldMapping.put( "name", "cdnm" );
		retMap.put( "selectCd", treeConvert.getTreeObject( retDaoData, "", "cd", "upcd", "disporder", fieldMapping, false, false ) );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.CdService#insertCd(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertCd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData = commonDao.insertReturnString( "mapper.common.hrpjcd.insertCd", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "코드 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "cd", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.CdService#getCd(java.util.Map)
	 */
	@Override
	public Map<String, Object> getCd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.common.hrpjcd.selectCd", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getCd", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.CdService#updateCd(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateCd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.update( "mapper.common.hrpjcd.updateCd", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "코드 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.CdService#deleteCd(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteCd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.common.hrpjcd.deleteCd", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "코드 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
