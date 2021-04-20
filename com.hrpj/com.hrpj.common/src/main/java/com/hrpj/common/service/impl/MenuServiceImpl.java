/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service.impl
 * 3. 파일명 : MenuServiceImpl.java
 * 4. 작성일 : 2019. 9. 18. 오전 11:46:58
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 메뉴관련 Service구현
 * </pre>
 */
package com.hrpj.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.common.service.MenuService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.StringUtils;
import com.hrpj.core.utils.TreeObjectUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service.impl
 * 2. 타입명 : MenuServiceImpl.java
 * 3. 작성일 : 2019. 9. 18. 오전 11:46:58
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 메뉴관련 Service구현
 * </pre>
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.MenuService#selectSgrp(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectSgrp( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.common.hrpjmenu.selectSgrp", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "사이트코드 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "selectSgrp", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.MenuService#insertSgrp(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertSgrp( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData1 = commonDao.insertReturnString( "mapper.common.hrpjmenu.insertSgrp", paramMap );
		if ( retDaoData1.equals( "" ) ) {
			throw new BusinessLogicException( "사이트 그룹 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "sgrp", retDaoData1 );

		paramMap.put( "menuroot", "Y" );
		paramMap.put( "sgrp", retDaoData1 );
		paramMap.put( "upmenucd", "" );
		paramMap.put( "menunm", StringUtils.getDefaultString( paramMap.get( "sgrpnm" ), "" ) );
		paramMap.put( "mtype", "" );
		paramMap.put( "wdpyn", "N" );
		paramMap.put( "useyn", "Y" );
		paramMap.put( "expyn", "Y" );
		final String retDaoData2 = commonDao.insertReturnString( "mapper.common.hrpjmenu.insertMenu", paramMap );
		if ( retDaoData2.equals( "" ) ) {
			throw new BusinessLogicException( "최상위 메뉴 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "menucd", retDaoData2 );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.MenuService#updateSgrp(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateSgrp( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData1 = commonDao.update( "mapper.common.hrpjmenu.updateSgrp", paramMap );
		if ( retDaoData1 == 0 ) {
			throw new BusinessLogicException( "사이트 그룹 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		paramMap.put( "menuroot", retDaoData1 );
		paramMap.put( "upmenucd", "" );
		if ( !StringUtils.getDefaultString( paramMap.get( "sgrpnm" ), "" ).equals( "" ) ) {
			paramMap.put( "menunm", paramMap.get( "sgrpnm" ) );
		}
		final int retDaoData2 = commonDao.update( "mapper.common.hrpjmenu.updateMenu", paramMap );
		if ( retDaoData2 == 0 ) {
			throw new BusinessLogicException( "최상위 메뉴 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.MenuService#getSgrp(java.util.Map)
	 */
	@Override
	public Map<String, Object> getSgrp( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.common.hrpjmenu.selectSgrp", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getSgrp", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.MenuService#deleteSgrp(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteSgrp( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData1 = commonDao.delete( "mapper.common.hrpjmenu.deleteSgrp", paramMap );
		if ( retDaoData1 == 0 ) {
			throw new BusinessLogicException(
				"[MenuServiceImpl.deleteSgrp]:::사이트 그룹 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		final int retDaoData2 = commonDao.delete( "mapper.common.hrpjmenu.deleteMenu", paramMap );
		if ( retDaoData2 == 0 ) {
			throw new BusinessLogicException( "메뉴 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.common.service.MenuService#selectMenuHierarchy(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectMenuHierarchy( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.common.hrpjmenu.selectMenuHierarchy", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "메뉴 계층형 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		final TreeObjectUtils treeConvert = new TreeObjectUtils( );
		final Map<String, String> fieldMapping = new HashMap<>( );
		fieldMapping.put( "id", "menucd" );
		fieldMapping.put( "name", "menunm" );
		retMap.put( "selectMenuTree", treeConvert.getTreeObject( retDaoData, "", "menucd", "upmenucd", "disporder", fieldMapping, false, false ) );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.MenuService#selectMenu(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectMenu( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.common.hrpjmenu.selectMenu", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "메뉴 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectMenu", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.MenuService#insertMenu(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertMenu( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData = commonDao.insertReturnString( "mapper.common.hrpjmenu.insertMenu", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "메뉴 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "menucd", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.MenuService#updateMenu(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateMenu( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.update( "mapper.common.hrpjmenu.updateMenu", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "메뉴 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.MenuService#getMenu(java.util.Map)
	 */
	@Override
	public Map<String, Object> getMenu( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.common.hrpjmenu.selectMenu", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getMenu", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.MenuService#deleteMenu(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteMenu( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.common.hrpjmenu.deleteMenu", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "메뉴 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
