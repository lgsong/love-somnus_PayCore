package com.somnus.pay.core.pojo;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long    total;
	
    private Integer pageNum  = 1;
    
    private Integer pageSize = 10;
    
    private String order;
    
    private boolean autoCount = true;
    
    public Page(){}
    
    public Page(int pageSize){
    	this.pageSize = pageSize;
    }
    
    public Page(int pageNum, int pageSize){
    	this.pageNum = pageNum;
    	this.pageSize = pageSize;
    }
    
    public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public int getFirstResult() {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        return (this.getPageNum() - 1) * this.getPageSize();
    }

}
