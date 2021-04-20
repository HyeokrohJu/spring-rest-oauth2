/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.utils
 * 3. 파일명 : DaoUtils.java
 * 4. 작성일 : 2018. 2. 9. 오전 11:26:29
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : Dao처리 관련 Utils
 * </pre>
 */
package com.hrpj.core.utils;

import org.apache.ibatis.session.RowBounds;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.utils
 * 2. 타입명 : DaoUtils.java
 * 3. 작성일 : 2018. 2. 9. 오전 11:26:29
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : Dao처리 관련 Utils
 * </pre>
 */
public class DaoUtils {

	/**
	 * <pre>
	 * 1. 함수명 : getRowBounds
	 * 2. 작성일 : 2018. 2. 9. 오전 11:26:58
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : Paging 처리를 위한 RowBounds 객체 리턴
	 * </pre>
	 *
	 * @param currentPageNo
	 * @param itemsPerPage
	 * @return
	 */
	public static RowBounds getRowBounds( Object currentPageNo, Object itemsPerPage ) {
		final int defaultItemsPerPage = 20;
		final int cPageNo = StringUtils.getParseInt( currentPageNo, "1" );
		final int iPerPage = StringUtils.getParseInt( itemsPerPage, "20" );

		final int pageNo = ( cPageNo < 1 ) ? 0 : ( cPageNo - 1 );
		final int items = ( iPerPage < 1 ) ? defaultItemsPerPage : iPerPage;

		final int offset = pageNo * items;
		final int limit = items;

		return new RowBounds( offset, limit );
	}

}
