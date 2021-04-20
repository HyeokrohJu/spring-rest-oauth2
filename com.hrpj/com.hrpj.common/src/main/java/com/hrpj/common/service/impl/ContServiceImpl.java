/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service.impl
 * 3. 파일명 : ContServiceImpl.java
 * 4. 작성일 : 2019. 10. 14. 오전 9:45:17
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 컨텐츠 Service구현
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

import com.hrpj.common.service.ContService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.DaoUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service.impl
 * 2. 타입명 : ContServiceImpl.java
 * 3. 작성일 : 2019. 10. 14. 오전 9:45:17
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 컨텐츠 Service구현
 * </pre>
 */
@Service("contService")
public class ContServiceImpl implements ContService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.ContService#selectContPg(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectContPg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.common.hrpjcont.selectCont", paramMap, rb );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "컨텐츠 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectContPg", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.ContService#insertCont(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertCont( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData = commonDao.insertReturnString( "mapper.common.hrpjcont.insertCont", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "컨텐츠 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "contid", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.ContService#getCont(java.util.Map)
	 */
	@Override
	public Map<String, Object> getCont( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.common.hrpjcont.selectCont", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getCont", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.ContService#deleteCont(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteCont( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.common.hrpjcont.deleteCont", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "컨텐츠 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
