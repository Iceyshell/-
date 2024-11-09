package com.yanagi.vo;

import com.yanagi.utils.QueryInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-05-06 15:03
 */
@Data
@ApiModel(value = "分页查找用户的vo")
public class UserQueryVo extends QueryInfo {

    private String name;
    private String phoneNumber;
    private Integer status;
    private Integer departmentId;
    private Integer companyId;
}
