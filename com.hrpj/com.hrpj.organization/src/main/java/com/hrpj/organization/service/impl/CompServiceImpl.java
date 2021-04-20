/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.organization
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.organization.service.impl
 * 3. 파일명 : CompServiceImpl.java
 * 4. 작성일 : 2019. 10. 1. 오전 11:14:26
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 회사 관련 Service구현
 * </pre>
 */
package com.hrpj.organization.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.DaoUtils;
import com.hrpj.core.utils.StringUtils;
import com.hrpj.organization.service.CompService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.organization.service.impl
 * 2. 타입명 : CompServiceImpl.java
 * 3. 작성일 : 2019. 10. 1. 오전 11:14:26
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 회사관련 Service 구현
 * </pre>
 */
@Service("compService")
public class CompServiceImpl implements CompService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.organization.service.CompService#selectCompPg(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectCompPg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.organization.hrpjcomp.selectComp", paramMap, rb );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "회사정보 조회(페이징) 에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectCompPg", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.CompService#getComp(java.util.Map)
	 */
	@Override
	public Map<String, Object> getComp( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.organization.hrpjcomp.selectComp", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}

		retMap.put( "getComp", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.CompService#insertComp(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertComp( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData1 = commonDao.insertReturnString( "mapper.organization.hrpjcomp.insertComp", paramMap );
		if ( retDaoData1.equals( "" ) ) {
			throw new BusinessLogicException( "회사정보 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "compid", retDaoData1 );

		paramMap.put( "deptnm", StringUtils.getDefaultString( paramMap.get( "compnm" ), "" ) );
		paramMap.put( "updeptid", "" );
		paramMap.put( "compid", retDaoData1 );
		paramMap.put( "state", "1" );
		final String retDaoData2 = commonDao.insertReturnString( "mapper.organization.hrpjdept.insertDept", paramMap );
		if ( retDaoData2.equals( "" ) ) {
			throw new BusinessLogicException( "부서정보 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "deptid", retDaoData2 );

		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.CompService#updateComp(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateComp( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData1 = commonDao.update( "mapper.organization.hrpjcomp.updateComp", paramMap );
		if ( retDaoData1 == 0 ) {
			throw new BusinessLogicException( "회사정보 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		final Map<String, Object> tempMap = new HashMap<>( );
		tempMap.put( "compid", paramMap.get( "compid" ) );
		if ( !StringUtils.getDefaultString( paramMap.get( "compnm" ), "" ).equals( "" ) ) {
			tempMap.put( "compnm", paramMap.get( "compnm" ) );
		}
		tempMap.put( "compUpdate", "Y" );
		// 부서정보 수정
		commonDao.update( "mapper.organization.hrpjdept.updateDept", tempMap );
		// 회원정보 수정
		commonDao.update( "mapper.organization.hrpjmember.updateMember", tempMap );

		if ( !StringUtils.getDefaultString( paramMap.get( "compnm" ), "" ).equals( "" ) ) {
			tempMap.put( "deptnm", paramMap.get( "compnm" ) );
		}
		tempMap.put( "compRootUpdate", "Y" );
		tempMap.remove( "compUpdate" );
		// 최상위 부서정보 수정
		commonDao.update( "mapper.organization.hrpjdept.updateDept", tempMap );

		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.CompService#deleteComp(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteComp( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.organization.hrpjcomp.deleteComp", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "회사정보 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		// 부서정보 삭제
		commonDao.delete( "mapper.organization.hrpjdept.deleteDept", paramMap );
		// 회원정보 삭제
		commonDao.delete( "mapper.organization.hrpjmember.deleteMember", paramMap );
		// 멤버Role정보 삭제
		commonDao.delete( "mapper.organization.hrpjmember.deleteMemberrole", paramMap );

		retMap.putAll( paramMap );
		return retMap;
	}

}
