/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.view
 * 3. 파일명 : DownloadView.java
 * 4. 작성일 : 2019. 10. 11. 오후 5:08:47
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 첨부파일 다운로드 View
 * </pre>
 */
package com.hrpj.core.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.hrpj.core.utils.FileUtils;
import com.hrpj.core.utils.StringUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.view
 * 2. 타입명 : DownloadView.java
 * 3. 작성일 : 2019. 10. 11. 오후 5:08:47
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 첨부파일 다운로드 View
 * </pre>
 */
@Component("downloadView")
public class DownloadView extends AbstractView {

	public DownloadView( ) {
		setContentType( "application/download; charset=utf-8" );
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
	 * (java.util.Map, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel( Map<String, Object> model, HttpServletRequest request, HttpServletResponse response ) throws Exception {

		final CaseInsensitiveMap<String, Object> attachMap = (CaseInsensitiveMap<String, Object>) model.get( "attachMap" );
		final File file = new File( StringUtils.getDefaultString( attachMap.get( "fileurl" ), "" ) );

		response.setContentType( getContentType( ) );
		response.setContentLength( (int) file.length( ) );

		String fileNm = StringUtils.getDefaultString( attachMap.get( "filenm" ), "noname" );

		switch(FileUtils.getBrowser( request )) {
			case "MSIE":
			case "Trident": {
				fileNm = URLEncoder.encode( fileNm, "UTF-8" ).replaceAll( "\\+", "%20" );
				break;
			}
			case "Firefox":
			case "Safari": {
				fileNm = new String( fileNm.getBytes( "UTF-8" ), "8859_1" );
				fileNm = URLDecoder.decode( fileNm, "UTF-8" );
				break;
			}
			case "Opera": {
				fileNm = new String( fileNm.getBytes( "UTF-8" ), "8859_1" );
				break;
			}
			case "Chrome": {
				final StringBuffer sb = new StringBuffer( );
				for (int i = 0; i < fileNm.length( ); i++) {
					final char c = fileNm.charAt( i );
					if ( c > '~' ) {
						sb.append( URLEncoder.encode( "" + c, "UTF-8" ) );
					} else {
						sb.append( c );
					}
				}
				fileNm = sb.toString( );
				break;
			}
			default: {
				fileNm = new String( fileNm.getBytes( "UTF-8" ), "8859_1" );
				break;
			}
		}

		response.setHeader( "Content-Length", String.valueOf( file.length( ) ) );
		response.setHeader( "Content-Disposition", "attachment; filename=\"" + fileNm + "\";" );
		response.setHeader( "Content-Transfer-Encoding", "binary" );

		final OutputStream out = response.getOutputStream( );
		FileInputStream fis = null;

		try {
			fis = new FileInputStream( file );
			FileCopyUtils.copy( fis, out );
		} finally {
			if ( fis != null ) {
				try {
					fis.close( );
				} catch (final IOException ioe) {
				}
			}
		}
		out.flush( );
	}

}
