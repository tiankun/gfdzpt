/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2013-8-9</p>
 * <p> @author 邹申昶</p>
 */
package com.sysFrams.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.alibaba.fastjson.JSON;


/**
 * 分页信息
 */
public class Page{
	public final static int defaultPageSize=20;
	
	protected Collection elements;//当页输出的对象map列表

	protected int pageSize;//每页显示条数

	protected int pageNumber;//当前页码

	protected int totalElementsCount = 0;//查询结果总记录条数
	 
	public Page(int pageNumber,int pageSize,int totalElementsCount,Collection elements) {
		init(pageNumber,pageSize,totalElementsCount,elements);
	}
	
	protected void init(int pageNumber,int pageSize,int totalElementsCount,Collection elements) {
		this.totalElementsCount=totalElementsCount;
		this.pageSize = pageSize;
		this.pageNumber = getPageNumber(pageNumber,pageSize,totalElementsCount);
		this.elements = elements;
	}
    /**
     * 是否是首页（第一页），第一页页码为1
     *
     * @return 首页标识
     */
	public boolean isFirstPage() {
		return getThisPageNumber() == 1;
	}
    /**
     * 是否是最后一页
     *
     * @return 末页标识
     */
	public boolean isLastPage() {
		return getThisPageNumber() >= getLastPageNumber();
	}
    /**
     * 是否有下一页
     *
     * @return 下一页标识
     */
	public boolean hasNextPage() {
		return getLastPageNumber() > getThisPageNumber();
	}
    /**
     * 是否有上一页
     *
     * @return 上一页标识
     */
	public boolean hasPreviousPage() {
		return getThisPageNumber() > 1;
	}
    /**
     * 获取最后一页页码，也就是总页数
     *
     * @return 最后一页页码
     */
	public int getLastPageNumber() {
		return getLastPageNumber(totalElementsCount, pageSize);
	}
    /**
     * 当前页包含的数据，不同的情况可能返回的数据类型不一样，如Map等，请参考具体的实现
     *
     * @return 当前页数据源
     */
	public Collection getThisPageElements() {
		return elements==null?new ArrayList():elements;
	}
    /**
     * 总的数据条目数量，0表示没有数据
     *
     * @return 总数量
     */
	public int getTotalNumberOfElements() {
		return totalElementsCount;
	}
    /**
     * 获取当前页的首条数据的行编码
     *
     * @return 当前页的首条数据的行编码
     */
	public int getThisPageFirstElementNumber() {
		return (getThisPageNumber() - 1) * getPageSize() + 1;
	}
    /**
     * 获取当前页的末条数据的行编码
     *
     * @return 当前页的末条数据的行编码
     */
	public int getThisPageLastElementNumber() {
		int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
		return getTotalNumberOfElements() < fullPage ? getTotalNumberOfElements() : fullPage;
	}
    /**
     * 获取下一页编码
     *
     * @return 下一页编码
     */
	public int getNextPageNumber() {
		return getThisPageNumber() + 1>getLastPageNumber()?getLastPageNumber():getThisPageNumber() + 1;
	}
    /**
     * 获取上一页编码
     *
     * @return 上一页编码
     */
	public int getPreviousPageNumber() {
		return getThisPageNumber() - 1<1?1: getThisPageNumber() - 1;
	}
    /**
     * 每一页显示的条目数
     *
     * @return 每一页显示的条目数
     */
	public int getPageSize() {
		return pageSize;
	}
    /**
     * 当前页的页码
     *
     * @return 当前页的页码
     */
	public int getThisPageNumber() {
		return pageNumber;
	}
    /**
     * 得到用于多页跳转的页码列表
     * @return
     */
	public List getLinkPageNumbers() {
		return generateLinkPageNumbers(getThisPageNumber(), 10, getLastPageNumber());
	}	
    private int getPageNumber(int pageNumber, int pageSize,int totalElements) {
		if(pageNumber <= 1) {
			return 1;
		}
    	if (Integer.MAX_VALUE == pageNumber
				|| pageNumber > getLastPageNumber(totalElements,pageSize)) { //last page
			return getLastPageNumber(totalElements,pageSize);
		}
		return pageNumber;
    }
    
	private int getLastPageNumber(int totalElements,int pageSize) {
		return (totalElements + pageSize -1) / pageSize ;
	}  
	@SuppressWarnings("unchecked")
	private List generateLinkPageNumbers(int currentPageNumber,int count,int maxPageNumber) {
		int avg = count / 2;
		
		int start = currentPageNumber - avg;
		if(start <= 0) {
			start = 1;
		}
		
		int end = start + count - 1;
		if(end > maxPageNumber) {
			end = maxPageNumber;
		}
		
		if(end - start < count) {
			start = end - count;
			if(start <= 0 ) {
				start = 1;
			}
		}
		
		java.util.List result = new java.util.ArrayList();
		for(int i = start; i <= end; i++) {
			result.add(new Integer(i));
		}
		return result;
	}
	/**
	 * 输出为easyui　datagrid接收的数据格式jsoin
	 */
	public String getJsoin() {
		return "{\"rows\":"+JSON.toJSONString(getThisPageElements())+",\"total\":"+getTotalNumberOfElements()+"}";
	}	
}
