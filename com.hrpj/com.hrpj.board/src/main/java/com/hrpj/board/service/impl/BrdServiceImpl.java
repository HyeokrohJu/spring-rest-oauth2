/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.board
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.board.service.impl
 * 3. 파일명 : BrdServiceImpl.java
 * 4. 작성일 : 2019. 10. 4. 오후 3:24:36
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 게시판 Service 구현
 * </pre>
 */
package com.hrpj.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrpj.board.service.BrdService;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.TreeObjectUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.board.service.impl
 * 2. 타입명 : BrdServiceImpl.java
 * 3. 작성일 : 2019. 10. 4. 오후 3:24:36
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 게시판 Service 구현
 * </pre>
 */
@Service("brdService")
public class BrdServiceImpl implements BrdService {

	@Autowired
	private CommonDao commonDao;

	/**
	 * <pre>
	 * 1. 함수명 : subBrdDelete
	 * 2. 작성일 : 2019. 10. 11. 오후 1:10:13
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 하위 게시판 물리 삭제
	 * </pre>
	 *
	 * @param paramMap
	 */
	protected void subBrdDelete( Map<String, Object> paramMap ) {
		final Map<String, Object> seParam = new HashMap<>( );
		seParam.put( "upbrdid", paramMap.get( "brdid" ) );
		/**
		 * 하위게시물 리스트 조회
		 */
		final List<CaseInsensitiveMap<String, Object>> retDaoData1 = commonDao.select( "mapper.board.hrpjbrd.selectBrdHierarchy", seParam );
		if ( retDaoData1 == null ) {
			throw new BusinessLogicException( "하위 게시판 계층형 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), seParam );
		}

		for (final CaseInsensitiveMap<String, Object> item : retDaoData1) {
			item.put( "brdalldel", "Y" );

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

			/**
			 * 게시물 삭제
			 */
			commonDao.delete( "mapper.board.hrpjblt.deleteBlt", item );

			final int retDaoData2 = commonDao.delete( "mapper.board.hrpjbrd.deleteBrd", item );
			if ( retDaoData2 == 0 ) {
				throw new BusinessLogicException( "게시판 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), item );
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BrdService#selectBrdHierarchy(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectBrdHierarchy( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.board.hrpjbrd.selectBrdHierarchy", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "게시판 계층형 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		final TreeObjectUtils treeConvert = new TreeObjectUtils( );
		final Map<String, String> fieldMapping = new HashMap<>( );
		fieldMapping.put( "id", "brdid" );
		fieldMapping.put( "name", "brdnm" );
		retMap.put( "selectBrdTree", treeConvert.getTreeObject( retDaoData, "", "brdid", "upbrdid", "disporder", fieldMapping, false, false ) );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BrdService#insertBrd(java.util.Map)
	 */
	@Override
	public Map<String, Object> insertBrd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final String retDaoData = commonDao.insertReturnString( "mapper.board.hrpjbrd.insertBrd", paramMap );
		if ( retDaoData.equals( "" ) ) {
			throw new BusinessLogicException( "게시판 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.put( "brdid", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BrdService#updateBrd(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateBrd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final int retDaoData = commonDao.update( "mapper.board.hrpjbrd.updateBrd", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "게시판 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BrdService#getBrd(java.util.Map)
	 */
	@Override
	public Map<String, Object> getBrd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.board.hrpjbrd.selectBrd", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getBrd", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.board.service.BrdService#deleteBrd(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteBrd( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		paramMap.put( "brdalldel", "Y" );

		/**
		 * 하위 게시판 물리삭제 처리
		 */
		this.subBrdDelete( paramMap );
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

		/**
		 * 게시물 삭제
		 */
		commonDao.delete( "mapper.board.hrpjblt.deleteBlt", paramMap );

		final int retDaoData = commonDao.delete( "mapper.board.hrpjbrd.deleteBrd", paramMap );
		if ( retDaoData == 0 ) {
			throw new BusinessLogicException( "게시판 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}
		retMap.putAll( paramMap );
		return retMap;
	}

}
