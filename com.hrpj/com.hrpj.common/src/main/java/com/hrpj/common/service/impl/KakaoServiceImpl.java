/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service.impl
 * 3. 파일명 : KakaoServiceImpl.java
 * 4. 작성일 : 2020. 2. 21. 오전 11:28:56
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : Kakao Service구현
 * </pre>
 */
package com.hrpj.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.common.service.KakaoService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.StringUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service.impl
 * 2. 타입명 : KakaoServiceImpl.java
 * 3. 작성일 : 2020. 2. 21. 오전 11:28:56
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Kakao Service구현
 * </pre>
 */
@Service("kakaoService")
public class KakaoServiceImpl implements KakaoService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.KakaoService#insertKakaoToken(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertKakaoToken( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );

		if ( StringUtils.isNull( paramMap.get( "refreshtoken" ) ) ) {
			final Map<String, Object> preMap = this.getKakaoToken( paramMap );
			if ( !StringUtils.isNull( preMap ) ) {
				final Map<String, Object> getMap = (Map<String, Object>) preMap.get( "getKakaotoken" );
				paramMap.put( "refreshtoken", getMap.get( "refreshtoken" ) );
				paramMap.put( "rtokenexp", getMap.get( "rtokenexp" ) );
			}
		}

		/**
		 * 저장된 토큰 삭제 [S]
		 */
		commonDao.delete( "mapper.common.hrpjkakao.deleteKakaotoken", paramMap );
		/**
		 * 저장된 토큰 삭제 [E]
		 */

		final String retDaoData = commonDao.insertReturnString( "mapper.common.hrpjkakao.insertKakaotoken", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "카카오토큰 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "getKakaotoken", paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.KakaoService#getKakaoToken(java.util.Map)
	 */
	@Override
	public Map<String, Object> getKakaoToken( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.common.hrpjkakao.selectKakaotoken", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getKakaotoken", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.common.service.KakaoService#getKakaoRefreshtoken(java.util.Map)
	 */
	@Override
	public Map<String, Object> getKakaoRefreshtoken( Map<String, Object> paramMap ) throws BusinessLogicException {
		// TODO Auto-generated method stub
		return null;
	}

}
