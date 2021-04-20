/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.vo
 * 3. 파일명 : ResponseVo.java
 * 4. 작성일 : 2019. 10. 4. 오전 10:44:28
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : Rest Response VO
 * </pre>
 */
package com.hrpj.core.vo;

import java.util.HashMap;
import java.util.Map;

import com.hrpj.core.constants.StatusCode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.vo
 * 2. 타입명 : ResponseVo.java
 * 3. 작성일 : 2019. 10. 4. 오전 10:44:28
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Rest Response VO
 * </pre>
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseVo {

	/**
	 * Response 상태 코드
	 */
	private String statusCd = StatusCode.SUCCESS_CD.getStatusCd( );

	/**
	 * Response 상태 메세지
	 */
	private String statusMsg = StatusCode.SUCCESS_CD.getStatusMsg( );

	/**
	 * Request Parameter Map
	 */
	private Map<String, Object> reqMap = new HashMap<>( );

	/**
	 * Response Data Map
	 */
	private Map<String, Object> resMap = new HashMap<>( );

}
