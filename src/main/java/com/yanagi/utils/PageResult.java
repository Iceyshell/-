package com.yanagi.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author yanagi
 * @description 分页返回结果
 * @date 2024-04-19 13:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult  extends Result implements Serializable {

    public PageResult(Integer pageNum, Integer pageSize, long total , List<?> list) {
        this.setCode(200);
        this.setMsg("查询成功");
        this.setData(new PageData(pageNum, pageSize, total, list));
    }

    /**
     * 直接返回分页数据
     * @param total 分页总条数
     * @param list 分页数据列表
     * @return
     */
    public static Result pageResult(Integer pageNum, Integer pageSize, long total , List<?> list) {
        return new PageResult(pageNum, pageSize, total, list);
    }
}
