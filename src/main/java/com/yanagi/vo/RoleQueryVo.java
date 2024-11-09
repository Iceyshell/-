package com.yanagi.vo;

import com.yanagi.utils.QueryInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-05-05 8:29
 */
@Data
@ApiModel(value = "分页查找角色的vo")
public class RoleQueryVo extends QueryInfo {

    private String label;

    private String code;

    private Integer status;
}
