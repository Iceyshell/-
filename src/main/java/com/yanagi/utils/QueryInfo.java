package com.yanagi.utils;


import lombok.Data;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-04-19 13:39
 */
@Data
public class QueryInfo {
    private Integer pageNum;
    private Integer pageSize;

    /**
     * 查询的内容
     */
    private String  queryString;

    public Integer getPageNumber() {
        return pageNum;
    }

    public void setPageNumber(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
