package com.wupao.log.pojo;
/**
 * 
 * @项目名称：lyd-admin
 * @类名称：BorrowLoanRegion
 * @类描述：极速借地址实体类
 * @创建人：樊诚
 * @创建时间：2018年10月10日 下午4:50:21 
 * @version：
 */
public class BorrowLoanRegion {

	private Integer id;
	
	private String name;
	
	private Integer pid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "BorrowLoanRegion [id=" + id + ", name=" + name + ", pid=" + pid
				+ "]";
	}


}
