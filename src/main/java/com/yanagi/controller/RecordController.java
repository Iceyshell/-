package com.yanagi.controller;

import com.yanagi.mapper.DepartmentMapper;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.service.DepartmentService;
import com.yanagi.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-06-25 14:07
 */
@RestController
@Slf4j
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordMapper recordMapper;

    @ApiOperation(value = "查询所有的权限")
    @GetMapping("/all")
    public Result findAll() {
        return Result.success("查询所有部门成功", recordMapper.findAll());
    }

}
