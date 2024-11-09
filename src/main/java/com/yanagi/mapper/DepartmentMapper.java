package com.yanagi.mapper;

import com.yanagi.entity.Department;

import java.util.List;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-06-25 14:00
 */

public interface DepartmentMapper {

    /**
     * 获取所有的分类
     * @return
     */
    List<Department> findAll();
}
