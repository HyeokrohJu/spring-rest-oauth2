/**
 * <pre>
 * 1. 프로젝트명 : com.hrpj.core
 * 2. 패키지명(또는 디렉토리 경로) : com.hrpj.core.utils
 * 3. 파일명 : TreeObjectUtils.java
 * 4. 작성일 : 2018. 7. 20. 오전 9:34:28
 * 5. 작성자 : Ju Hyeokroh
 * 6. 설명   : 계층형 리스트 Hierarchy구조의 Map으로 변환
 * </pre>
 */
package com.hrpj.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <pre>
 * 1. 패키지명 : com.hrpj.core.utils
 * 2. 타입명 : TreeObjectUtils.java
 * 3. 작성일 : 2018. 7. 20. 오전 9:34:28
 * 4. 작성자 : Ju Hyeokroh
 * 5. 설명   : 계층형 리스트 Hierarchy구조의 Map으로 변환
 * </pre>
 */

public class TreeObjectUtils {

	private List<Map<String, Object>> _treeModel = null;
	private int _treeLength = 0;
	private int _loopLength = 0;
	private int _listLength = 0;
	private String _idField = "";
	private String _parentField = "";
	private Map<String, String> _fieldMapping = null;
	private CopyOnWriteArrayList<Map<String, Object>> _list = null;
	private final boolean _sortReverse = false;
	private DescendingObj descending = null;
	private AscendingObj ascending = null;

	public List<Map<String, Object>> getTreeObject( List<? extends Map<String, Object>> _list, String _rootId, String _idField, String _parentField,
		String _sortField, Map<String, String> _fieldMapping, boolean _sortReverse, boolean _hierarchyFlag ) {

		this._treeModel = new ArrayList<>( );
		this._treeLength = 0;
		this._loopLength = 0;
		this._listLength = _list.size( );
		this._idField = _idField;
		this._parentField = _parentField;
		this._fieldMapping = _fieldMapping;
		this._list = new CopyOnWriteArrayList<>( );
		this._list.addAll( _list );

		_sortField = _sortField == null ? _sortField : "disporder";
		this.descending = new DescendingObj( _sortField );
		this.ascending = new AscendingObj( _sortField );

		while (this._treeLength != this._listLength && this._listLength != this._loopLength++) {
			for (final Map<String, Object> item : this._list) {
				if ( String.valueOf( item.get( this._parentField ) ).equals( _rootId ) || _hierarchyFlag ) {
					item.put( "treeid", item.get( this._idField ) );
					item.put( "level", 0 );
					item.put( "children", new ArrayList<Map<String, Object>>( ) );
					item.put( "isOpen", true );

					item.putAll( this.fieldMapping( item, this._fieldMapping ) );

					this._treeModel.add( item );
					Collections.sort( this._treeModel, this._sortReverse ? this.descending : this.ascending );
					this._treeLength++;
					this._list.remove( item );

					break;
				} else {
					this.parentNode( this._treeModel, item );
				}
			}
		}
		return this._treeModel;
	}

	@SuppressWarnings("unchecked")
	protected void parentNode( List<Map<String, Object>> _children, Map<String, Object> item ) {
		for (final Map<String, Object> child : _children) {
			if ( ( String.valueOf( child.get( this._idField ) ) ).equals( String.valueOf( item.get( this._parentField ) ) ) ) {
				item.put( "treeid", item.get( this._idField ) );
				item.put( "level", (int) child.get( "level" ) + 1 );
				item.put( "children", new ArrayList<Map<String, Object>>( ) );

				item.putAll( this.fieldMapping( item, this._fieldMapping ) );

				final List<Map<String, Object>> childList = (List<Map<String, Object>>) child.get( "children" );
				childList.add( item );
				Collections.sort( childList, this._sortReverse ? this.descending : this.ascending );
				child.put( "children", childList );

				this._treeLength++;
				this._list.remove( item );

				break;
			} else {
				if ( ( (List<Map<String, Object>>) child.get( "children" ) ).size( ) > 0 ) {
					this.parentNode( (List<Map<String, Object>>) child.get( "children" ), item );;
				}
			}
		}
	}

	protected Map<String, Object> fieldMapping( Map<String, Object> item, Map<String, String> _fieldMapping ) {
		_fieldMapping.forEach( ( key, value ) -> {
			item.put( key, value );
		} );
		return item;
	}

}

class DescendingObj implements Comparator<Map<String, Object>> {

	private String sortField = "";

	public DescendingObj( String sortField ) {
		this.sortField = sortField;
	}

	@Override
	public int compare( Map<String, Object> o1, Map<String, Object> o2 ) {
		return ( String.valueOf( o2.get( this.sortField ) ) ).compareTo( String.valueOf( o1.get( this.sortField ) ) );
	}

}

class AscendingObj implements Comparator<Map<String, Object>> {

	private String sortField = "";

	public AscendingObj( String sortField ) {
		this.sortField = sortField;
	}

	@Override
	public int compare( Map<String, Object> o1, Map<String, Object> o2 ) {
		return ( String.valueOf( o1.get( this.sortField ) ) ).compareTo( String.valueOf( o2.get( this.sortField ) ) );
	}

}
