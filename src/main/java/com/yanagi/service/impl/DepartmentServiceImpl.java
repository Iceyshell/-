package com.yanagi.service.impl;

import com.yanagi.mapper.DepartmentMapper;
import com.yanagi.mapper.SysPermissionMapper;
import com.yanagi.service.DepartmentService;
import com.yanagi.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-06-25 14:04
 */
@Service
@Slf4j
public class DepartmentServiceImpl  implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Result findAll() {
        return Result.success("查询所有部门成功", departmentMapper.findAll());
    }
}
