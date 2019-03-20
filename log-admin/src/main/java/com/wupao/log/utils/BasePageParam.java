package com.wupao.log.utils;

public class BasePageParam {
	protected Integer page = 1;

	protected Integer rows = 10;

	/**排序字段**/
	protected String field;
	/**排序类型 asc desc**/
	protected String order;
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	
	
}
