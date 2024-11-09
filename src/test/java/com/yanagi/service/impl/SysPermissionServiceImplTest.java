package com.yanagi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanagi.entity.Record;
import com.yanagi.entity.SysPermission;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.mapper.SysPermissionMapper;
import com.yanagi.utils.PageResult;
import com.yanagi.utils.RedisUtils;
import com.yanagi.utils.Result;
import com.yanagi.utils.SecurityUtils;
import com.yanagi.vo.PermissionQueryVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@Transactional
@Rollback(true)
public class SysPermissionServiceImplTest {
    @Mock
    private SysPermissionMapper permissionMapper;

    @Mock
    private RecordMapper recordMapper;

    @Mock
    private RedisUtils redisUtils;

    @InjectMocks
    private SysPermissionServiceImpl sysPermissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPage() {
        // Arrange
        PermissionQueryVo queryInfo = new PermissionQueryVo();
        queryInfo.setPageNumber(1);
        queryInfo.setPageSize(10);
        Page<SysPermission> page = mock(Page.class);
        when(page.getTotal()).thenReturn(100L);
        when(page.getResult()).thenReturn(List.of());
        when(permissionMapper.findPage(queryInfo)).thenReturn(page);

        // Act
        Result result = sysPermissionService.findPage(queryInfo);

        // Assert
        assertEquals(PageResult.pageResult(1, 10, 100L, List.of()), result);
    }

    @Test
    void testInsert() {
        SysPermission permission = new SysPermission();
        permissionMapper.insert(permission);
        Record record = new Record();
        record.setType(1);
        recordMapper.insert(record);
        System.out.println(Result.success("添加权限数据成功"));
    }

}