package com.wmc.novel.common;

import java.io.Serializable;

/**
 * 
 * @className: PageInfo
 * @description: 分页信息
 * @uthor money
 * @date 2020年11月16日
 */
public class PageInfo implements Serializable {

	private static final long serialVersionUID = 7841703198546957104L;
	/**
	 * 总记录数
	 */
	private Long count;
	/**
	 * 总页数
	 */
	private Long totalPages;
	/**
	 * 当前页
	 */
	private Integer page;
	/**
	 * 页记录数
	 */
	private Integer limit;

	public PageInfo(Long count) {
		this.count = count;
	}

	public PageInfo(Long count, Integer page, Integer limit) {
		this.count = count;
		this.totalPages = (count - 1) / limit + 1;
		this.page = page;
		this.limit = limit;
	}

	public static PageInfo page(Long count) {
		return new PageInfo(count);
	}

	public static PageInfo page(Long count, Integer page, Integer limit) {
		return new PageInfo(count, page, limit);
	}

	public Long getTotal() {
		return count;
	}

	public void setTotal(Long count) {
		this.count = count;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
