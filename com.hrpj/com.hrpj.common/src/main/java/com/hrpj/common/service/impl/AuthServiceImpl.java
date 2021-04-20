/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service.impl
 * 3. 파일명 : AuthServiceImpl.java
 * 4. 작성일 : 2019. 11. 22. 오후 4:30:35
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 권한 관련 Service 구현
 * </pre>
 */
package com.hrpj.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.common.service.AuthService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service.impl
 * 2. 타입명 : AuthServiceImpl.java
 * 3. 작성일 : 2019. 11. 22. 오후 4:30:35
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 권한 관련 Service 구현
 * </pre>
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.AuthService#insertAuth(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertAuth( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData1 = commonDao.insertReturnString( "mapper.common.hrpjauth.insertAuth", paramMap );
		if ( retDaoData1.equals( "" ) ) {
			throw new BusinessLogicException( "권한 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "authid", retDaoData1 );

		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.AuthService#deleteAuth(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteAuth( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData1 = commonDao.delete( "mapper.common.hrpjauth.deleteAuth", paramMap );
		if ( retDaoData1 == 0 ) {
			throw new BusinessLogicException( "권한 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
