/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.organization
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.organization.service.impl
 * 3. 파일명 : DeptServiceImpl.java
 * 4. 작성일 : 2019. 10. 1. 오후 2:15:23
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 부서관련 Service 구현
 * </pre>
 */
package com.hrpj.organization.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.StringUtils;
import com.hrpj.core.utils.TreeObjectUtils;
import com.hrpj.organization.service.DeptService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.organization.service.impl
 * 2. 타입명 : DeptServiceImpl.java
 * 3. 작성일 : 2019. 10. 1. 오후 2:15:23
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 부서관련 Service 구현
 * </pre>
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {

	@Autowired
	private CommonDao commonDao;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.organization.service.DeptService#selectDeptHierarchy(java.util.
	 * Map)
	 */
	@Override
	public Map<String, Object> selectDeptHierarchy( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.organization.hrpjdept.selectDeptHierarchy", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "부서리스트 계층형 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		final TreeObjectUtils treeConvert = new TreeObjectUtils( );
		final Map<String, String> fieldMapping = new HashMap<>( );
		fieldMapping.put( "id", "deptid" );
		fieldMapping.put( "name", "deptnm" );
		retMap.put( "selectDeptTree", treeConvert.getTreeObject( retDaoData, "", "deptid", "updeptid", "disporder", fieldMapping, false, false ) );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.DeptService#getDept(java.util.Map)
	 */
	@Override
	public Map<String, Object> getDept( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.organization.hrpjdept.selectDept", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getDept", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.DeptService#insertDept(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertDept( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData = commonDao.insertReturnString( "mapper.organization.hrpjdept.insertDept", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "부서정보 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "deptid", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.DeptService#updateDept(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateDept( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.update( "mapper.organization.hrpjdept.updateDept", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "부서정보 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		final Map<String, Object> tempMap = new HashMap<>( );
		tempMap.put( "compid", paramMap.get( "compid" ) );
		tempMap.put( "deptid", paramMap.get( "deptid" ) );
		if ( !StringUtils.getDefaultString( paramMap.get( "deptnm" ), "" ).equals( "" ) ) {
			tempMap.put( "deptnm", paramMap.get( "deptnm" ) );
		}
		tempMap.put( "deptUpdate", "Y" );
		// 회원정보 수정
		commonDao.update( "mapper.organization.hrpjmember.updateMember", tempMap );
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.DeptService#deleteDept(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteDept( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.delete( "mapper.organization.hrpjdept.deleteDept", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "부서정보 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		// 회원정보 삭제
		commonDao.delete( "mapper.organization.hrpjmember.deleteMember", paramMap );
		// 회원 Role정보 삭제
		commonDao.delete( "mapper.organization.hrpjmember.deleteMemberrole", paramMap );

		retMap.putAll( paramMap );
		return retMap;
	}

}
