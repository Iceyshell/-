package com.yanagi.vo;

import com.yanagi.utils.QueryInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-05-02 21:23
 */
@Data
@ApiModel(value = "分页查找权限的vo")
public class PermissionQueryVo extends QueryInfo {

    private String label;

    private String code;

    private Integer status;
}
