/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service.impl
 * 3. 파일명 : BltServiceImpl.java
 * 4. 작성일 : 2019. 10. 7. 오후 4:00:10
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시물 관련 Service 구현
 * </pre>
 */
package com.hrpj.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.board.service.BltService;
import com.hrpj.common.service.AttachService;
import com.hrpj.core.common.CommonThreadLocalBean;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.DaoUtils;
import com.hrpj.core.utils.StringUtils;
import com.hrpj.organization.service.MemberService;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service.impl
 * 2. 타입명 : BltServiceImpl.java
 * 3. 작성일 : 2019. 10. 7. 오후 4:00:10
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시물 관련 Service 구현
 * </pre>
 */
@Service("bltService")
public class BltServiceImpl implements BltService {

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private AttachService attachService;

	@Autowired
	private MemberService memberService;

	/**
	 * <pre>
	 * 1. 함수명 : subBltDelete
	 * 2. 작성일 : 2019. 10. 11. 오전 11:43:23
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 하위 게시물 물리 삭제
	 * </pre>
	 *
	 * @param paramMap
	 */
	protected void subBltDelete( Map<String, Object> paramMap ) {
		final Map<String, Object> seParam = new HashMap<>( );
		seParam.put( "upbltid", paramMap.get( "bltid" ) );
		/**
		 * 하위게시물 리스트 조회
		 */
		final List<CaseInsensitiveMap<String, Object>> retDaoData1 = commonDao.select( "mapper.board.hrpjblt.selectBltHierarchy", seParam );
		if ( retDaoData1 == null ) {
			throw new BusinessLogicException( "하위 게시물 계층형 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), seParam );
		}

		for (final CaseInsensitiveMap<String, Object> item : retDaoData1) {
			item.put( "bltalldel", "Y" );
			/**
			 * 게시물 댓글 삭제
			 */
			commonDao.delete( "mapper.board.hrpjbltcmnt.deleteBltcmnt", item );

			/**
			 * 게시물 열람내역 삭제
			 */
			commonDao.delete( "mapper.board.hrpjbltread.deleteBltread", item );

			/**
			 * 게시물 추천내역 삭제
			 */
			commonDao.delete( "mapper.board.hrpjbltrcmd.deleteBltrcmd", item );

			final int retDaoData2 = commonDao.delete( "mapper.board.hrpjblt.deleteBlt", item );
			if ( retDaoData2 == 0 ) {
				throw new BusinessLogicException( "게시물 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), item );
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltService#selectBltPg(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectBltPg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		paramMap.put( "selectcnt", "Y" );
		final CaseInsensitiveMap<String, Object> retDaoData1 = commonDao.getMap( "mapper.board.hrpjblt.selectBlt", paramMap );
		if ( retDaoData1 == null ) {
			throw new BusinessLogicException( "게시물 리스트 카운트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		paramMap.remove( "selectcnt" );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData2 = commonDao.select( "mapper.board.hrpjblt.selectBlt", paramMap, rb );
		if ( retDaoData2 == null ) {
			throw new BusinessLogicException( "게시물 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectBltCnt", retDaoData1 );
		retMap.put( "selectBltPg", retDaoData2 );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltService#insertBlt(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertBlt( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );

		final Map<String, Object> seMap = new HashMap<>( );
		seMap.put( "bltid", paramMap.get( "currtbltid" ) );
		final CaseInsensitiveMap<String, Object> retDaoData1 = commonDao.getMap( "mapper.board.hrpjblt.selectBlt", seMap );
		/**
		 * 게시물 순번 변경
		 */
		if ( !StringUtils.isNull( retDaoData1 ) ) {
			paramMap.put( "add", "Y" );
			paramMap.put( "upbltid", StringUtils.getParseInt( retDaoData1.get( "bltid" ), "0" ) );
			paramMap.put( "disporder", StringUtils.getParseInt( retDaoData1.get( "disporder" ), "0" ) );
			paramMap.put( "orgbltid", retDaoData1.get( "orgbltid" ) );
			paramMap.put( "lvl", StringUtils.getParseInt( retDaoData1.get( "lvl" ), "0" ) );
			commonDao.update( "mapper.board.hrpjblt.updateBltSort", paramMap );
		}

		final String retDaoData2 = commonDao.insertReturnString( "mapper.board.hrpjblt.insertBlt", paramMap );
		if ( retDaoData2.equals( "" ) ) {
			throw new BusinessLogicException( "게시물 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		if ( !StringUtils.isNull( paramMap.get( "tempkey" ) ) ) {
			final String cont = StringUtils
				.getDefaultString( paramMap.get( "cont" ), "" ).replaceAll( (String) paramMap.get( "tempkey" ), String.valueOf( retDaoData2 ) );
			final Map<String, Object> upmap = new HashMap<>( );
			upmap.put( "bltid", String.valueOf( retDaoData2 ) );
			upmap.put( "cont", cont );
			this.updateBlt( upmap );
		}

		paramMap.put( "tblkey", retDaoData2 );
		paramMap.put( "filethumb", "N" );
		attachService.updateAttach( paramMap );

		if ( !StringUtils.isNull( paramMap.get( "thumbid" ) ) ) {
			final Map<String, Object> thumbUpdParam = new HashMap<>( );
			thumbUpdParam.put( "attachid", paramMap.get( "thumbid" ) );
			thumbUpdParam.put( "filethumb", "Y" );
			attachService.updateAttachDB( thumbUpdParam );
		}

		retMap.put( "bltid", retDaoData2 );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltService#updateBlt(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateBlt( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );

		if ( !( memberService.isAdmin( ) || this.isAuth( paramMap ) ) ) {
			throw new BusinessLogicException( "게시물 수정 권한이 없습니다.", StatusCode.AUTH_ERR_CD.getStatusCd( ), paramMap );
		}

		commonDao.update( "mapper.board.hrpjblt.updateBlt", paramMap );

		if ( !StringUtils.isNull( paramMap.get( "thumbid" ) ) ) {
			paramMap.put( "tblkeyupd", paramMap.get( "bltid" ) );
			paramMap.put( "filethumb", "N" );
			attachService.updateAttachDB( paramMap );

			final Map<String, Object> thumbUpdParam = new HashMap<>( );
			thumbUpdParam.put( "attachid", paramMap.get( "thumbid" ) );
			thumbUpdParam.put( "filethumb", "Y" );
			attachService.updateAttachDB( thumbUpdParam );
		}

		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltService#getBlt(java.util.Map)
	 */
	@Override
	public Map<String, Object> getBlt( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.board.hrpjblt.selectBlt", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getBlt", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltService#deleteBlt(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteBlt( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );

		if ( !( memberService.isAdmin( ) || this.isAuth( paramMap ) ) ) {
			throw new BusinessLogicException( "게시물 삭제 권한이 없습니다.", StatusCode.AUTH_ERR_CD.getStatusCd( ), paramMap );
		}

		paramMap.put( "bltalldel", "Y" );

		/**
		 * 하위 게시물 물리삭제 처리
		 */
		this.subBltDelete( paramMap );
		/**
		 * 게시물 댓글 삭제
		 */
		commonDao.delete( "mapper.board.hrpjbltcmnt.deleteBltcmnt", paramMap );

		/**
		 * 게시물 열람내역 삭제
		 */
		commonDao.delete( "mapper.board.hrpjbltread.deleteBltread", paramMap );

		/**
		 * 게시물 추천내역 삭제
		 */
		commonDao.delete( "mapper.board.hrpjbltrcmd.deleteBltrcmd", paramMap );

		final int retDaoData = commonDao.delete( "mapper.board.hrpjblt.deleteBlt", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "게시물 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		final Map<String, Object> attachDelParam = new HashMap<>( );
		attachDelParam.put( "tblkey", paramMap.get( "bltid" ) );
		attachService.deleteAttach( attachDelParam );

		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BltService#updateCntBlt(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateCntBlt( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		commonDao.update( "mapper.board.hrpjblt.updateCntBlt", paramMap );

		retMap.putAll( paramMap );
		return retMap;
	}

	private Boolean isAuth( Map<String, Object> paramMap ) {
		paramMap.put( "creid", CommonThreadLocalBean.getUserid( ) );
		final CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.board.hrpjblt.selectBlt", paramMap );
		if ( retDaoData == null ) {
			return false;
		}

		return true;
	}

}
