/**
 * <pre>
 * 1. 프로젝트명 : oauth
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.oauth.vo
 * 3. 파일명 : Role.java
 * 4. 작성일 : 2017. 6. 26. 오후 6:30:13
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   :
 * </pre>
 */

package com.hrpj.oauth.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.oauth.vo
 * 2. 타입명 : Role.java
 * 3. 작성일 : 2017. 6. 29. 오후 9:23:50
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   :
 * </pre>
 */
@Getter
@Setter
@NoArgsConstructor
public class Role {
	
	private Long roleid;

	private String code;

	private String label;

}
