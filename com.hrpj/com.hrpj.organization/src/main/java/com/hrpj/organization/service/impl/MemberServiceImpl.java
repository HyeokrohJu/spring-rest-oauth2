/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.organization
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.organization.service.impl
 * 3. 파일명 : MemberServiceImpl.java
 * 4. 작성일 : 2019. 9. 27. 오후 5:35:23
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 회원 관련 Service 구현
 * </pre>
 */
package com.hrpj.organization.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hrpj.core.common.CommonThreadLocalBean;
import com.hrpj.core.common.SendMail;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.CryptUtils;
import com.hrpj.core.utils.DaoUtils;
import com.hrpj.core.utils.StringUtils;
import com.hrpj.organization.service.MemberService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.organization.service.impl
 * 2. 타입명 : MemberServiceImpl.java
 * 3. 작성일 : 2019. 9. 27. 오후 5:35:23
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 회원 관련 Service 구현
 * </pre>
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SendMail sendMail;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.organization.service.MemberService#selectMemberPg(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectMemberPg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		paramMap.put( "selectcnt", "Y" );
		final CaseInsensitiveMap<String, Object> retDaoData1 = commonDao.getMap( "mapper.organization.hrpjmember.selectMember", paramMap );
		if ( retDaoData1 == null ) {
			throw new BusinessLogicException( "회원 카운트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		paramMap.remove( "selectcnt" );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData2 = commonDao.select( "mapper.organization.hrpjmember.selectMember", paramMap, rb );
		if ( retDaoData2 == null ) {
			throw new BusinessLogicException( "회원 조회(페이징) 에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ) );
		}

		retMap.put( "selectMemberCnt", retDaoData1 );
		retMap.put( "selectMemberPg", retDaoData2 );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.MemberService#getMember(java.util.Map)
	 */
	@Override
	public Map<String, Object> getMember( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.organization.hrpjmember.selectMember", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}

		retMap.put( "getMember", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.organization.service.MemberService#insertMember(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertMember( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		paramMap.put( "passwd", passwordEncoder.encode( StringUtils.getDefaultString( paramMap.get( "passwd" ), "" ) ) );
		final String retDaoData1 = commonDao.insertReturnString( "mapper.organization.hrpjmember.insertMember", paramMap );
		if ( retDaoData1.equals( "" ) ) {
			throw new BusinessLogicException( "회원 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "userid", retDaoData1 );

		paramMap.put( "userid", retDaoData1 );
		final int retDaoData2 = commonDao.insert( "mapper.organization.hrpjmember.insertMemberrole", paramMap );
		if ( retDaoData2 == 0 ) {
			throw new BusinessLogicException( "회원 Role 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.organization.service.MemberService#updateMember(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateMember( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		if ( !StringUtils.getDefaultString( paramMap.get( "passwd" ), "" ).equals( "" ) ) {
			paramMap.put( "passwd", passwordEncoder.encode( StringUtils.getDefaultString( paramMap.get( "passwd" ), "" ) ) );
		}
		commonDao.update( "mapper.organization.hrpjmember.updateMember", paramMap );
		// 회원 Role정보 수정
		if ( !StringUtils.getDefaultString( paramMap.get( "roleid" ), "" ).equals( "" ) ) {
			commonDao.update( "mapper.organization.hrpjmember.updateMemberrole", paramMap );
		}

		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.organization.service.MemberService#deleteMember(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteMember( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData1 = commonDao.delete( "mapper.organization.hrpjmember.deleteMember", paramMap );
		if ( retDaoData1 == 0 ) {
			throw new BusinessLogicException( "회원 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		final int retDaoData2 = commonDao.delete( "mapper.organization.hrpjmember.deleteMemberrole", paramMap );
		if ( retDaoData2 == 0 ) {
			throw new BusinessLogicException( "회원 Role 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.organization.service.MemberService#selectUserRole(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectUserRole( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.organization.hrpjmember.selectUserRole", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "회원 Role 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "selectUserRole", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.MemberService#getFindId(java.util.Map)
	 */
	@Override
	public Map<String, Object> getFindId( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.organization.hrpjmember.getFindId", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "회원 ID찾기 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "getFindId", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.hrpj.organization.service.MemberService#getFindPwd(java.util.Map)
	 */
	@Override
	public Map<String, Object> getFindPwd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );

		final String tmpPwd = CryptUtils.getRamdomPassword( 10 );
		paramMap.put( "tmpPwd", tmpPwd );
		paramMap.put( "templateFile", "email_findpwd.vm" );

		final CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.organization.hrpjmember.selectMember", paramMap );

		if ( retDaoData == null ) {
			retMap.put( "isChangePwd", false );
		} else {
			paramMap.put( "compid", retDaoData.get( "compid" ) );
			paramMap.put( "deptid", retDaoData.get( "deptid" ) );
			paramMap.put( "userid", retDaoData.get( "userid" ) );
			paramMap.put( "passwd", passwordEncoder.encode( StringUtils.getDefaultString( paramMap.get( "tmpPwd" ), "" ) ) );
			commonDao.update( "mapper.organization.hrpjmember.updateMember", paramMap );

			final String cont = this.sendMail.getContentFromTemplate( paramMap );

			final Map<String, String> mailMap = new HashMap<>( );
			mailMap.put( "toAddress", (String) retDaoData.get( "email" ) );
			mailMap.put( "title", "[BaronDive] 임시 비밀번호가 발급 되었습니다." );
			mailMap.put( "cont", cont );

			this.sendMail.mailSend( mailMap );

			retMap.put( "isChangePwd", true );
		}

		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.organization.service.MemberService#isAdmin()
	 */
	@Override
	public Boolean isAdmin( ) throws BusinessLogicException {
		final Map<String, Object> paramMap = new HashMap<>( );
		paramMap.put( "userid", CommonThreadLocalBean.getUserid( ) );
		final CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.organization.hrpjmember.isAdmin", paramMap );

		if ( Integer.parseInt( String.valueOf( retDaoData.get( "admincnt" ) ) ) > 0 ) {
			return true;
		}
		return false;
	}

}
