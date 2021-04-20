/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.utils
 * 3. 파일명 : FileUtils.java
 * 4. 작성일 : 2019. 10. 11. 오후 3:16:40
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 파일관련 Utils
 * </pre>
 */
package com.hrpj.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.utils
 * 2. 타입명 : FileUtils.java
 * 3. 작성일 : 2019. 10. 11. 오후 3:16:40
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 파일관련 Utils
 * </pre>
 */
public class FileUtils {

	/**
	 * <pre>
	 * 1. 함수명 : getUniqueFileName
	 * 2. 작성일 : 2019. 10. 11. 오후 4:44:33
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 고유 파일명 생성
	 * </pre>
	 *
	 * @param fileName
	 * @return
	 */
	public static String getUniqueFileName( String fileName ) {

		String uniqueFilename = "";
		final String uuid = StringUtils.remove( UUID.randomUUID( ).toString( ), '-' ).toUpperCase( );
		final int fileIndex = StringUtils.lastIndexOf( fileName, '.' );

		/**
		 * 파일명과 확장자 분리
		 */
		if ( fileIndex != -1 ) {
			final String extension = StringUtils.substring( fileName, fileIndex + 1 );
			uniqueFilename = uuid + "." + extension;
		} else {
			uniqueFilename = uuid;
		}

		return uniqueFilename;

	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteDirFileList
	 * 2. 작성일 : 2019. 10. 11. 오후 4:37:26
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 디렉토리의 파일 리스트를 삭제하는 메소드
	 * </pre>
	 *
	 * @param dirPath
	 */
	public static void deleteDirFileList( String dirPath ) {
		// 파일 목록을 요청한 디렉토리를 가지고 파일 객체를 생성함
		final File dir = new File( dirPath );

		// 디렉토리가 존재한다면
		while (dir.exists( )) {
			// 파일 목록을 구함
			final File[ ] files = dir.listFiles( );

			// 파일 배열을 삭제
			for (final File f : files) {
				f.delete( );
			}

			if ( files.length == 0 && dir.isDirectory( ) ) {
				dir.delete( );
			}
		}

	}

	/**
	 * <pre>
	 * 1. 함수명 : fileCopy
	 * 2. 작성일 : 2019. 10. 11. 오후 4:37:56
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 파일을 복사하는 메소드
	 * </pre>
	 *
	 * @param inFileName
	 * @param outFileName
	 */
	public static void fileCopy( String inFileName, String outFileName ) {
		try {
			final byte[ ] buf = new byte[ 1024 ];
			final FileInputStream fin = new FileInputStream( inFileName );
			final FileOutputStream fout = new FileOutputStream( outFileName );

			int read = 0;
			while (( read = fin.read( buf, 0, buf.length ) ) != -1) {
				fout.write( buf, 0, read );
			}

			fin.close( );
			fout.close( );

		} catch (final IOException e) {
			e.printStackTrace( );
		}
	}

	/**
	 * <pre>
	 * 1. 함수명 : fileMove
	 * 2. 작성일 : 2019. 10. 11. 오후 4:46:45
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 파일을 이동하는 메소드
	 * </pre>
	 *
	 * @param inFileName
	 * @param outFileName
	 * @return
	 */
	public static boolean fileMove( String inFileName, String outFileName ) {
		boolean status = true;

		final File beforeFile = new File( inFileName );
		final File afterFile = new File( outFileName );
		if ( beforeFile.renameTo( afterFile ) ) {
			status = true;

			// 복사한뒤 원본파일을 삭제함
			fileDelete( inFileName );
		} else {
			try {
				final byte[ ] buf = new byte[ 1024 ];
				final FileInputStream fin = new FileInputStream( inFileName );
				final FileOutputStream fout = new FileOutputStream( outFileName );
				int read = 0;
				while (( read = fin.read( buf, 0, buf.length ) ) != -1) {
					fout.write( buf, 0, read );
				}

				fin.close( );
				fout.close( );

				// 복사한뒤 원본파일을 삭제함
				fileDelete( inFileName );

				status = true;
			} catch (final IOException e) {
				e.printStackTrace( );
				status = false;
			}
		}

		return status;
	}

	/**
	 * <pre>
	 * 1. 함수명 : fileDelete
	 * 2. 작성일 : 2019. 10. 11. 오후 4:47:04
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 파일을 삭제하는 메소드
	 * </pre>
	 *
	 * @param deleteFileName
	 * @return
	 */
	public static boolean fileDelete( String deleteFileName ) {
		final File I = new File( deleteFileName );
		final File pFile = I.getParentFile( );

		if ( pFile.isDirectory( ) ) {
			I.delete( );
			if ( pFile.listFiles( ).length == 0 ) {
				return pFile.delete( );
			}

			return true;

		} else {
			return I.delete( );
		}
	}

	/**
	 * <pre>
	 * 1. 함수명 : fileMake
	 * 2. 작성일 : 2019. 10. 11. 오후 4:38:27
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 파일을 생성하는 메소드
	 * </pre>
	 *
	 * @param makeFileName
	 */
	public static void fileMake( String makeFileName ) {
		final File f1 = new File( makeFileName );
		try {
			f1.createNewFile( );
		} catch (final IOException e) {
			e.printStackTrace( );
		}
	}

	/**
	 * <pre>
	 * 1. 함수명 : fileIsLive
	 * 2. 작성일 : 2019. 10. 11. 오후 4:38:39
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 파일을 존재여부를 확인하는 메소드
	 * </pre>
	 *
	 * @param isLivefile
	 * @return
	 */
	public static Boolean fileIsLive( String isLivefile ) {
		final File f1 = new File( isLivefile );

		if ( f1.exists( ) ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <pre>
	 * 1. 함수명 : getBrowser
	 * 2. 작성일 : 2019. 10. 11. 오후 4:38:45
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 브라우저 정보 조회
	 * </pre>
	 *
	 * @param request
	 * @return
	 */
	public static String getBrowser( HttpServletRequest request ) {

		final String header = request.getHeader( "User-Agent" );

		if ( header.indexOf( "MSIE" ) > -1 ) {
			return "MSIE";
		} else if ( header.indexOf( "Trident" ) > -1 ) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if ( header.indexOf( "Chrome" ) > -1 ) {
			return "Chrome";
		} else if ( header.indexOf( "Opera" ) > -1 ) {
			return "Opera";
		} else if ( header.indexOf( "Safari" ) > -1 ) {
			return "Safari";
		}

		return "Firefox";

	}

	/**
	 * <pre>
	 * 1. 함수명 : getFileExt
	 * 2. 작성일 : 2019. 11. 28. 오후 11:33:27
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 파일 확장자 추출
	 * </pre>
	 *
	 * @param filenm
	 * @return
	 */
	public static String getFileExt( String filenm ) {
		return FilenameUtils.getExtension( filenm );
	}

}
