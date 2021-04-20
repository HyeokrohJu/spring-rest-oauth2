/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.reservation
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.reservation.service.impl
 * 3. 파일명 : RevInvoiceServiceImpl.java
 * 4. 작성일 : 2019. 10. 28. 오후 5:04:10
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 예약 Invoice Service 구현
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
import com.hrpj.reservation.service.RevInvoiceService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.reservation.service.impl
 * 2. 타입명 : RevInvoiceServiceImpl.java
 * 3. 작성일 : 2019. 10. 28. 오후 5:04:10
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 예약 Invoice Service 구현
 * </pre>
 */
@Service("revInvoiceService")
public class RevInvoiceServiceImpl implements RevInvoiceService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.reservation.service.RevInvoiceService#selectRevInvoice(java.
	 * util.Map)
	 */
	@Override
	public Map<String, Object> selectRevInvoice( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData =
			commonDao.select( "mapper.reservation.hrpjrevinvoice.selectRevInvoice", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "예약 Invoice 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectRevInvoice", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.reservation.service.RevInvoiceService#insertRevInvoice(java.util
	 * .Map)
	 */
	@Override
	public Map<String, Object> insertRevInvoice( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData = commonDao.insertReturnString( "mapper.reservation.hrpjrevinvoice.insertRevInvoice", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "예약 Invoice 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "revinvoiceid", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.reservation.service.RevInvoiceService#getRevInvoice(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> getRevInvoice( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.reservation.hrpjrevinvoice.selectRevInvoice", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getRevInvoice", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.reservation.service.RevInvoiceService#updateRevInvoice(java.util
	 * .Map)
	 */
	@Override
	public Map<String, Object> updateRevInvoice( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.update( "mapper.reservation.hrpjrevinvoice.updateRevInvoice", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "예약 Invoice 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.reservation.service.RevInvoiceService#deleteRevInvoice(java.util
	 * .Map)
	 */
	@Override
	public Map<String, Object> deleteRevInvoice( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.reservation.hrpjrevinvoice.deleteRevInvoice", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "예약 Invoice 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
