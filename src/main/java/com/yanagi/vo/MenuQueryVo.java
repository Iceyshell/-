package com.yanagi.vo;

import com.yanagi.utils.QueryInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-05-04 18:16
 */
@Data
@ApiModel(value = "分页查找菜单的vo")
public class MenuQueryVo extends QueryInfo {

    private String name;

    private String title;

    private boolean status;
}
