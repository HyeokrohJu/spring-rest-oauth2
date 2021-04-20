/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.service.impl
 * 3. 파일명 : AttachServiceImpl.java
 * 4. 작성일 : 2019. 10. 11. 오후 1:30:58
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 첨부파일 관련 Service 구현
 * </pre>
 */
package com.hrpj.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrpj.common.service.AttachService;
import com.hrpj.core.common.CommonThreadLocalBean;
import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.dao.CommonDao;
import com.hrpj.core.exception.BusinessLogicException;
import com.hrpj.core.utils.DaoUtils;
import com.hrpj.core.utils.FileUtils;
import com.hrpj.core.utils.StringUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.service.impl
 * 2. 타입명 : AttachServiceImpl.java
 * 3. 작성일 : 2019. 10. 11. 오후 1:30:58
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 첨부파일 관련 Service 구현
 * </pre>
 */
@Service("attachService")
public class AttachServiceImpl implements AttachService {

	@Autowired
	private CommonDao commonDao;

	@Value("${server.attach.src}")
	private String attachSrc;

	@Value("${server.attach.domain}")
	private String attachDomain;

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.AttachService#selectAttachPg(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectAttachPg( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final RowBounds rb = DaoUtils.getRowBounds( paramMap.get( "currentPageNo" ), paramMap.get( "itemsPerPage" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.common.hrpjattach.selectAttach", paramMap, rb );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "첨부파일 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectAttachPg", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.AttachService#selectAttach(java.util.Map)
	 */
	@Override
	public Map<String, Object> selectAttach( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		final List<CaseInsensitiveMap<String, Object>> retDaoData = commonDao.select( "mapper.common.hrpjattach.selectAttach", paramMap );
		if ( retDaoData == null ) {
			throw new BusinessLogicException( "첨부파일 전체 리스트 조회에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "selectAttach", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.AttachService#uploadAttach(java.util.Map,
	 * org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public Map<String, Object> uploadAttach( Map<String, Object> paramMap, MultipartFile file ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );

		/**
		 * 첨부파일 관련 Parameter세팅
		 */
		if ( file.getOriginalFilename( ).equals( "blob" ) ) {
			paramMap.put( "filenm", paramMap.get( "name" ) );
			paramMap.put( "filetype", FileUtils.getFileExt( StringUtils.getDefaultString( paramMap.get( "name" ), "" ) ) );
		} else {
			paramMap.put( "filenm", file.getOriginalFilename( ) );
			paramMap.put( "filetype", FileUtils.getFileExt( file.getOriginalFilename( ) ) );
		}
		paramMap.put( "filesnm", FileUtils.getUniqueFileName( file.getOriginalFilename( ) ) );
		paramMap.put( "filesize", file.getSize( ) );

		final String basePath = attachSrc + CommonThreadLocalBean.getHrschema( ) + File.separator + paramMap.get( "tblnm" ) + File.separator;
		final String baseUrl = attachDomain + CommonThreadLocalBean.getHrschema( ) + "/" + paramMap.get( "tblnm" ) + "/";
		if ( !StringUtils.isNull( paramMap.get( "tblkey" ) ) ) {
			paramMap.put( "filepath", basePath + paramMap.get( "tblkey" ) + File.separator );
			paramMap.put( "fileurl", baseUrl + paramMap.get( "tblkey" ) + "/" + paramMap.get( "filesnm" ) );
		} else {
			paramMap.put( "filepath", basePath + paramMap.get( "tempkey" ) + File.separator );
			paramMap.put( "fileurl", baseUrl + paramMap.get( "tempkey" ) + "/" + paramMap.get( "filesnm" ) );
		}

		/**
		 * 첨부파일 업로드 처리
		 */
		try {
			final File folderObj = new File( StringUtils.getDefaultString( paramMap.get( "filepath" ), "" ) );
			if ( !folderObj.exists( ) ) {
				folderObj.mkdirs( );
			}

			final File fileObj = new File(
				StringUtils.getDefaultString( paramMap.get( "filepath" ), "" )
					+ StringUtils.getDefaultString( paramMap.get( "filesnm" ), "noname" ) );
			file.transferTo( fileObj );
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace( );
			throw new BusinessLogicException( StatusCode.UPLOAD_ERR_CD.getStatusMsg( ), StatusCode.UPLOAD_ERR_CD.getStatusCd( ), paramMap, e );
		}

		final String retDaoData = commonDao.insertReturnString( "mapper.common.hrpjattach.insertAttach", paramMap );
		if ( retDaoData.equals( "" ) ) {
			FileUtils
				.fileDelete(
					StringUtils.getDefaultString( paramMap.get( "filepath" ), "" )
						+ StringUtils.getDefaultString( paramMap.get( "filesnm" ), "noname" ) );

			throw new BusinessLogicException( "첨부파일 등록에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
		}

		retMap.put( "attachid", retDaoData );
		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.AttachService#updateAttach(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateAttach( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		/**
		 * 이동할 첨부파일 정보 조회
		 */
		final Map<String, Object> seParam = new HashMap<>( );
		seParam.put( "tempkey", paramMap.get( "tempkey" ) );
		seParam.put( "tblnm", paramMap.get( "tblnm" ) );
		final List<CaseInsensitiveMap<String, Object>> retDaoData1 = commonDao.select( "mapper.common.hrpjattach.selectAttach", seParam );

		if ( retDaoData1.size( ) > 0 ) {

			/**
			 * 첨부파일 물리경로 이동
			 */
			String tempFilePath = "";
			for (final CaseInsensitiveMap<String, Object> item : retDaoData1) {
				/**
				 * 첨부파일 이동 Path 설정
				 */
				final String basePath = attachSrc + CommonThreadLocalBean.getHrschema( ) + File.separator + paramMap.get( "tblnm" ) + File.separator;
				final String baseUrl = attachDomain + CommonThreadLocalBean.getHrschema( ) + "/" + paramMap.get( "tblnm" ) + "/";
				paramMap.put( "filepath", basePath + paramMap.get( "tblkey" ) + File.separator );
				paramMap.put( "fileurl", baseUrl + paramMap.get( "tblkey" ) + "/" + item.get( "filesnm" ) );
				paramMap.put( "attachid", item.get( "attachid" ) );

				/**
				 * 첨부파일 DB수정
				 */
				final int retDaoData2 = commonDao.update( "mapper.common.hrpjattach.updateAttach", paramMap );
				if ( retDaoData2 == 0 ) {
					throw new BusinessLogicException( "첨부파일 수정에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
				}

				final File folderObj = new File( StringUtils.getDefaultString( paramMap.get( "filepath" ), "" ) );
				if ( !folderObj.exists( ) ) {
					folderObj.mkdirs( );
				}

				tempFilePath = StringUtils.getDefaultString( item.get( "filepath" ), "" );
				final boolean status = FileUtils
					.fileMove(
						StringUtils.getDefaultString( item.get( "filepath" ), "" ) + StringUtils.getDefaultString( item.get( "filesnm" ), "noname" ),
						StringUtils.getDefaultString( paramMap.get( "filepath" ), "" )
							+ StringUtils.getDefaultString( item.get( "filesnm" ), "noname" ) );

				if ( !status ) {
					throw new BusinessLogicException( "물리경로 첨부파일 이동에 실패하였습니다.", StatusCode.UPLOAD_ERR_CD.getStatusCd( ), paramMap );
				}
			}

			/**
			 * 첨부파일 Temp폴더 삭제
			 */
			if ( !StringUtils.isNull( tempFilePath ) ) {
				FileUtils.deleteDirFileList( tempFilePath );
			}
		}

		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.AttachService#getAttach(java.util.Map)
	 */
	@Override
	public Map<String, Object> getAttach( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.common.hrpjattach.selectAttach", paramMap );
		if ( retDaoData == null ) {
			retDaoData = new CaseInsensitiveMap<>( );
		}
		retMap.put( "getAttach", retDaoData );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.AttachService#deleteAttach(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteAttach( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );

		/**
		 * 삭제할 첨부파일 정보 조회
		 */
		final List<CaseInsensitiveMap<String, Object>> retDaoData1 = commonDao.select( "mapper.common.hrpjattach.selectAttach", paramMap );

		if ( retDaoData1 == null || retDaoData1.size( ) == 0 ) {
			retMap.putAll( paramMap );
			return retMap;
		}

		for (final CaseInsensitiveMap<String, Object> item : retDaoData1) {
			/**
			 * 첨부파일 DB삭제
			 */
			final int retDaoData2 = commonDao.delete( "mapper.common.hrpjattach.deleteAttach", item );
			if ( retDaoData2 == 0 ) {
				throw new BusinessLogicException( "첨부파일 물리 삭제에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ), paramMap );
			}

			/**
			 * 물리경로 첨부파일 삭제
			 */
			FileUtils
				.fileDelete(
					StringUtils.getDefaultString( item.get( "filepath" ), "" ) + StringUtils.getDefaultString( item.get( "filesnm" ), "noname" ) );
		}

		retMap.putAll( paramMap );
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.AttachService#attachDownload(java.util.Map)
	 */
	@Override
	public Map<String, Object> attachDownload( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		if ( !StringUtils.isNull( paramMap.get( "attachid" ) ) ) {
			CaseInsensitiveMap<String, Object> retDaoData = commonDao.getMap( "mapper.common.hrpjattach.selectAttach", paramMap );
			if ( retDaoData == null ) {
				retDaoData = new CaseInsensitiveMap<>( );
			}
			retMap.putAll( retDaoData );
		} else {
			retMap.putAll( paramMap );
		}
		return retMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hrpj.common.service.AttachService#updateAttachDB(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateAttachDB( Map<String, Object> paramMap ) throws BusinessLogicException {
		final Map<String, Object> retMap = new HashMap<>( );
		commonDao.update( "mapper.common.hrpjattach.updateAttach", paramMap );
		retMap.putAll( paramMap );
		return retMap;
	}

}
