/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.constants
 * 3. 파일명 : ErrorCode.java
 * 4. 작성일 : 2019. 9. 18. 오후 1:52:55
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 상태코드 정의
 * </pre>
 */
package com.hrpj.core.constants;

import lombok.Getter;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.constants
 * 2. 타입명 : ErrorCode.java
 * 3. 작성일 : 2019. 9. 18. 오후 1:52:55
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 상태코드 정의
 * </pre>
 */
@Getter
public enum StatusCode {

	SUCCESS_CD("0200", "요청이 정상적으로 처리되었습니다."), SERVER_ERR_CD("0500", "서버에서 에러가 발생하였습니다."), DB_ERR_CD("0600", "DB호출에서 실패하였습니다."),
	VALID_ERR_CD("0400", "Validation 체크중 실패하였습니다."), UPLOAD_ERR_CD("0700", "첨부파일 업로드중 에러가 발생하였습니다."), AUTH_ERR_CD("0401", "권한이 불충분합니다.");

	private String statusCd;
	private String statusMsg;

	private StatusCode( ) {
	}

	private StatusCode( String statusCd, String statusMsg ) {
		this.statusCd = statusCd;
		this.statusMsg = statusMsg;
	}

}
