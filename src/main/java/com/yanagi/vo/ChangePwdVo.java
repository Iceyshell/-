package com.yanagi.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-05-08 9:30
 */

@Data
@ApiModel(value = "修改密码参数")
public class ChangePwdVo {
    String username;
    String oldPwd;
    String newPwd;
}
