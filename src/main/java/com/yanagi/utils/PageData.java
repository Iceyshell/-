package com.yanagi.utils;

import lombok.Data;

import java.util.List;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-05-02 21:04
 */
@Data
public class PageData {
    private Integer pageNum;
    private Integer pageSize;
    private long total;
    private List<?> list;

    public PageData(Integer pageNum, Integer pageSize, long total, List<?> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

    public PageData() {
    }
}
