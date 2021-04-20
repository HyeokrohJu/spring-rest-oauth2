/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.annotation
 * 3. 파일명 : CustomValid.java
 * 4. 작성일 : 2019. 10. 1. 오후 10:49:04
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : Validation Check Annotation
 * </pre>
 */
package com.hrpj.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.annotation
 * 2. 타입명 : CustomValid.java
 * 3. 작성일 : 2019. 10. 1. 오후 10:49:17
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Validation Check Annotation
 * </pre>
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValid {

	String[ ] validField() default {};

}
