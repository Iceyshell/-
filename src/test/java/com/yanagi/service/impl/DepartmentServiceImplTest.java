package com.yanagi.service.impl;

import com.yanagi.mapper.DepartmentMapper;
import com.yanagi.utils.Result;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@Transactional
@Rollback(true)
public class DepartmentServiceImplTest {

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        when(departmentMapper.findAll()).thenReturn(null); // 提供你的模拟返回值

        // Act
        Result result = departmentService.findAll();

        // Assert
        assertEquals(Result.success("查询所有部门成功", null), result);
    }

}
