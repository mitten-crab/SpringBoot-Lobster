package com.lobster.common.entity;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据对象
 */
public class PageDataInfo implements Serializable {

    /**
     * Returns the page content
     */
    private List<?> content;

    /**
     * Returns the total amount of elements.
     */
    private long totalCount;

    /**
     * Returns the number of total pages.
     */
    private int totalPages;

    /**
     * Returns the pageSize
     */
    private int pageSize;

    /**
     * Returns the pageNumber of the current . Is always non-negative.
     */
    private int pageNumber;


    /**
     * 分页数据对象
     */
    public PageDataInfo() {
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageDataInfo(List<?> list, int total) {
        this.content = content;
        this.totalCount = totalCount;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public static PageRequest toPageParam(int pageNumber, int pageSize, Sort sort) {
        pageNumber = ObjectUtils.isEmpty(pageNumber) ? 0 : pageNumber;
        pageSize = ObjectUtils.isEmpty(pageSize) ? 10 : pageSize;
        return PageRequest.of(pageNumber, pageSize, sort);
    }

    public static PageDataInfo toPageData(Page<?> page) {
        PageDataInfo pageDataInfo = new PageDataInfo();
        pageDataInfo.setTotalCount(page.getTotalElements());
        pageDataInfo.setTotalPages(page.getTotalPages());
        pageDataInfo.setPageNumber(page.getNumber());
        pageDataInfo.setPageSize(page.getSize());
        pageDataInfo.setContent(page.getContent());
        return pageDataInfo;
    }

    public static PageDataInfo toPageData(List<?> list, Page<?> page) {
        PageDataInfo pageDataInfo = new PageDataInfo();
        pageDataInfo.setTotalCount(page.getTotalElements());
        pageDataInfo.setTotalPages(page.getTotalPages());
        pageDataInfo.setPageNumber(page.getNumber());
        pageDataInfo.setPageSize(page.getSize());
        pageDataInfo.setContent(list);
        return pageDataInfo;
    }

}
