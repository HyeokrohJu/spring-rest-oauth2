/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.reservation
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.reservation.service.impl
 * 3. 파일명 : RevDiverServiceImpl.java
 * 4. 작성일 : 2019. 10. 28. 오후 4:56:39
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 예약 다이버 정보 Service 구현
 * </pre>
 */
package com.hrpj.reservation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.reservation.service.RevDiverService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.reservation.service.impl
 * 2. 타입명 : RevDiverServiceImpl.java
 * 3. 작성일 : 2019. 10. 28. 오후 4:56:39
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 예약 다이버 정보 Service 구현
 * </pre>
 */
@Service("revDiverService")
public class RevDiverServiceImpl implements RevDiverService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.reservation.service.RevDiverService#selectRevDiver(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> selectRevDiver( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.reservation.hrpjrevdiver.selectRevDiver", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "예약 다이버 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectRevDiver", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.reservation.service.RevDiverService#insertRevDiver(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> insertRevDiver( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData = commonDao.insertReturnString( "mapper.reservation.hrpjrevdiver.insertRevDiver", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "예약 다이버 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "revdiverid", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.reservation.service.RevDiverService#getRevDiver(java.util.Map)
	 */
	@Override
	public Map<String, Object> getRevDiver( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.reservation.hrpjrevdiver.selectRevDiver", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getRevDiver", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.reservation.service.RevDiverService#updateRevDiver(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> updateRevDiver( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.update( "mapper.reservation.hrpjrevdiver.updateRevDiver", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "예약 다이버 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.reservation.service.RevDiverService#deleteRevDiver(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> deleteRevDiver( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.reservation.hrpjrevdiver.deleteRevDiver", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "예약 다이버 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
