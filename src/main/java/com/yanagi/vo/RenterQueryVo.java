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
@ApiModel(value = "分页查找租户的vo")
public class RenterQueryVo extends QueryInfo {

    private String name;
    private String phoneNumber;
    private Integer status;
}
