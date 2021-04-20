/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.reservation
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.reservation.service.impl
 * 3. 파일명 : RevServiceImpl.java
 * 4. 작성일 : 2019. 10. 28. 오후 4:46:03
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 예약관련 Service 구현
 * </pre>
 */
package com.hrpj.reservation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.common.service.AttachService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.DaoUtils;
import com.hrpj.core.utils.StringUtils;
import com.hrpj.reservation.service.RevDiverService;
import com.hrpj.reservation.service.RevInvoiceService;
import com.hrpj.reservation.service.RevService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.reservation.service.impl
 * 2. 타입명 : RevServiceImpl.java
 * 3. 작성일 : 2019. 10. 28. 오후 4:46:03
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 예약관련 Service 구현
 * </pre>
 */
@Service("revService")
public class RevServiceImpl implements RevService {

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private AttachService attachService;

	@Autowired
	RevDiverService revDiverService;

	@Autowired
	RevInvoiceService revInvoiceService;

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.reservation.service.RevService#selectRevPg(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectRevPg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		paramMap.put( "selectcnt", "Y" );
		final CaseInsensitiveMap<String, Object> retDaoData1 = commonDao.getMap( "mapper.reservation.hrpjrev.selectRev", paramMap );
		if ( retDaoData1 == null ) {
			throw new BusinessLogicException( "예약 리스트 카운트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		paramMap.remove( "selectcnt" );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData2 = commonDao.select( "mapper.reservation.hrpjrev.selectRev", paramMap, rb );
		if ( retDaoData2 == null ) {
			throw new BusinessLogicException( "예약 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		revDiverService.selectRevDiver( paramMap );
		revInvoiceService.selectRevInvoice( paramMap );

		retMap.put( "selectRevCnt", retDaoData1 );
		retMap.put( "selectRevPg", retDaoData2 );

		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.reservation.service.RevService#insertRev(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertRev( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData =
			commonDao.insertReturnString( "mapper.reservation.hrpjrev.insertRev", (Map<String, Object>) paramMap.get( "revMap" ) );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "예약 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		final List<Map<String, Object>> revDiverList = (List<Map<String, Object>>) paramMap.get( "revDiverMap" );
		final List<String> revDiverId = new ArrayList<>( );
		for (final Map<String, Object> revDiverMap : revDiverList) {
			revDiverMap.put( "revid", String.valueOf( retDaoData ) );
			final Map<String, Object> retRevDiverMap = revDiverService.insertRevDiver( revDiverMap );
			revDiverId.add( StringUtils.getDefaultString( retRevDiverMap.get( "revdiverid" ), "" ) );
		}

		if ( !StringUtils.isNull( paramMap.get( "tempkey" ) ) ) {
			final String cont = StringUtils
				.getDefaultString( paramMap.get( "cont" ), "" ).replaceAll( (String) paramMap.get( "tempkey" ), String.valueOf( retDaoData ) );
			final Map<String, Object> upmap = new HashMap<>( );
			upmap.put( "revid", String.valueOf( retDaoData ) );
			upmap.put( "cont", cont );
			final Map<String, Object> revupmap = new HashMap<>( );
			revupmap.put( "revMap", upmap );
			this.updateRev( revupmap );
		}

		paramMap.put( "tblkey", retDaoData );
		attachService.updateAttach( paramMap );

		retMap.put( "revid", retDaoData );
		retMap.put( "revdiverid", revDiverId );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.reservation.service.RevService#getRev(java.util.Map)
	 */
	@Override
	public Map<String, Object> getRev( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData1 = commonDao.getMap( "mapper.reservation.hrpjrev.selectRev", paramMap );
		if ( retDaoData1 == null ) {
			retDaoData1 = new CaseInsensitiveMap<>( );
		}

		final Map<String, Object> retDaoData2 = revDiverService.selectRevDiver( paramMap );
		final Map<String, Object> retDaoData3 = revInvoiceService.getRevInvoice( paramMap );

		retMap.put( "getRev", retDaoData1 );
		retMap.put( "getRevDiver", retDaoData2.get( "selectRevDiver" ) );
		retMap.put( "getRevInvoice", retDaoData3.get( "getRevInvoice" ) );

		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.reservation.service.RevService#updateRev(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateRev( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData1 = commonDao.update( "mapper.reservation.hrpjrev.updateRev", (Map<String, Object>) paramMap.get( "revMap" ) );
		if ( retDaoData1 == 0 ) {
			throw new BusinessLogicException( "예약 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		if ( !StringUtils.isNull( paramMap.get( "revDiverMap" ) ) ) {
			final List<Map<String, Object>> revDiverList = (List<Map<String, Object>>) paramMap.get( "revDiverMap" );
			for (final Map<String, Object> revDiverMap : revDiverList) {
				revDiverService.updateRevDiver( revDiverMap );
			}
		}
		if ( !StringUtils.isNull( paramMap.get( "revInvoiceMap" ) ) ) {
			revInvoiceService.updateRevInvoice( (Map<String, Object>) paramMap.get( "revInvoiceMap" ) );
		}

		retMap.putAll( paramMap );

		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.reservation.service.RevService#deleteRev(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteRev( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.reservation.hrpjrev.deleteRev", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "예약 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		revDiverService.deleteRevDiver( paramMap );
		revInvoiceService.deleteRevInvoice( paramMap );

		retMap.putAll( paramMap );

		return retMap;
	}

}
