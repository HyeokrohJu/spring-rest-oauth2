/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service.impl
 * 3. 파일명 : ComponentsServiceImpl.java
 * 4. 작성일 : 2019. 10. 28. 오후 1:30:52
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : React Components 관리 Service 구현
 * </pre>
 */
package com.hrpj.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.common.service.ComponentsService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.DaoUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service.impl
 * 2. 타입명 : ComponentsServiceImpl.java
 * 3. 작성일 : 2019. 10. 28. 오후 1:30:52
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : React Components 관리 Service 구현
 * </pre>
 */
@Service("componentsService")
public class ComponentsServiceImpl implements ComponentsService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.common.service.ComponentsService#selectComponentsPg(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> selectComponentsPg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.common.hrpjcomponents.selectComponents", paramMap, rb );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "React Components 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectContPg", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.common.service.ComponentsService#insertComponents(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertComponents( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData = commonDao.insertReturnString( "mapper.common.hrpjcomponents.insertComponents", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "React Components 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "contid", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.common.service.ComponentsService#getComponents(java.util.Map)
	 */
	@Override
	public Map<String, Object> getComponents( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.common.hrpjcomponents.selectComponents", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getCont", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.common.service.ComponentsService#updateComponents(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateComponents( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.update( "mapper.common.hrpjcomponents.updateComponents", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "React Components 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.common.service.ComponentsService#deleteComponents(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteComponents( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.common.hrpjcomponents.deleteComponents", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "React Components 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
