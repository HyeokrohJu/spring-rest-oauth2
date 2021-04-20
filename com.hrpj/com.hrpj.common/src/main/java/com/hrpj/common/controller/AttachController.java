/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.common
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.common.controller
 * 3. 파일명 : AttachController.java
 * 4. 작성일 : 2019. 10. 11. 오후 1:30:00
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 첨부파일 관련 Controller
 * </pre>
 */
package com.hrpj.common.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hrpj.common.service.AttachService;
import com.hrpj.core.annotation.CustomValid;
import com.hrpj.core.annotation.CustomValidParam;
import com.hrpj.core.utils.FileUtils;
import com.hrpj.core.utils.StringUtils;
import com.hrpj.core.vo.ResponseVo;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.common.controller
 * 2. 타입명 : AttachController.java
 * 3. 작성일 : 2019. 10. 11. 오후 1:30:00
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 첨부파일 관련 Controller
 * </pre>
 */
@RestController
@RequestMapping("/attach")
public class AttachController {

	@Autowired
	AttachService attachService;

	/**
	 * <pre>
	 * 1. 함수명 : selectAttachPg
	 * 2. 작성일 : 2019. 10. 11. 오후 2:58:27
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectAttachPg", method = RequestMethod.GET)
	public Object selectAttachPg( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( attachService.selectAttachPg( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : selectAttach
	 * 2. 작성일 : 2019. 10. 11. 오후 2:58:36
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 전체 리스트 조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/selectAttach", method = RequestMethod.GET)
	public Object selectAttach( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( attachService.selectAttach( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : uploadAttach
	 * 2. 작성일 : 2019. 10. 11. 오후 5:28:21
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 업로드
	 * </pre>
	 *
	 * @param file
	 * @param paramMap
	 * @return
	 */
	@CustomValid(validField = { "tblnm" })
	@RequestMapping(value = "/uploadAttach", method = RequestMethod.POST)
	public Object uploadAttach( @RequestParam MultipartFile file, @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( attachService.uploadAttach( paramMap, file ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : updateAttach
	 * 2. 작성일 : 2019. 10. 11. 오후 2:58:55
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 수정
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@CustomValid(validField = { "tempkey" })
	@RequestMapping(value = "/updateAttach", method = RequestMethod.PUT)
	public Object updateAttach( @CustomValidParam @RequestBody Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( attachService.updateAttach( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : getAttach
	 * 2. 작성일 : 2019. 10. 11. 오후 2:59:01
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 상세조회
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getAttach", method = RequestMethod.GET)
	public Object getAttach( @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( attachService.getAttach( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : deleteAttach
	 * 2. 작성일 : 2019. 10. 11. 오후 2:59:08
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 첨부파일 물리삭제
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 */
	@CustomValid(validField = { "attachid" })
	@RequestMapping(value = "/deleteAttach", method = RequestMethod.DELETE)
	public Object deleteAttach( @CustomValidParam @RequestParam Map<String, Object> paramMap ) {

		final ResponseVo resVo = new ResponseVo( );
		resVo.setReqMap( paramMap );
		resVo.setResMap( attachService.deleteAttach( paramMap ) );

		return resVo;
	}

	/**
	 * <pre>
	 * 1. 함수명 : attachDownload
	 * 2. 작성일 : 2019. 10. 11. 오후 5:33:10
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 파일 다운로드
	 * </pre>
	 *
	 * @param paramMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/attachDownload", method = RequestMethod.GET)
	public Object attachDownload( @RequestParam Map<String, Object> paramMap, HttpServletRequest request ) throws IOException {

		final Map<String, Object> attachMap = attachService.attachDownload( paramMap );
		final File file = new File(
			StringUtils.getDefaultString( attachMap.get( "filepath" ), "" ) + File.separator
				+ StringUtils.getDefaultString( attachMap.get( "filesnm" ), "" ) );

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

		final HttpHeaders headers = new HttpHeaders( );
		headers.add( "Cache-Control", "no-cache, no-store, must-revalidate" );
		headers.add( "Pragma", "no-cache" );
		headers.add( "Expires", "0" );
		headers.add( "Content-Length", String.valueOf( file.length( ) ) );
		headers.add( "Content-Disposition", "attachment; filename=\"" + fileNm + "\";" );
		headers.add( "Content-Transfer-Encoding", "binary" );

		final Path path = Paths.get( file.getAbsolutePath( ) );
		final ByteArrayResource resource = new ByteArrayResource( Files.readAllBytes( path ) );

		return ResponseEntity
			.ok( ).headers( headers ).contentLength( (int) file.length( ) ).contentType( MediaType.parseMediaType( "application/octet-stream" ) )
			.body( resource );
	}

}
