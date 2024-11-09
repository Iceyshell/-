package com.yanagi.service.impl;

import com.github.pagehelper.Page;
import com.yanagi.entity.Record;
import com.yanagi.entity.SysRole;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.mapper.SysRoleMapper;
import com.yanagi.utils.PageResult;
import com.yanagi.utils.RedisUtils;
import com.yanagi.utils.Result;
import com.yanagi.utils.SecurityUtils;
import com.yanagi.vo.RoleQueryVo;
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
public class SysRoleServiceImplTest {
    @Mock
    private SysRoleMapper roleMapper;

    @Mock
    private RecordMapper recordMapper;

    @Mock
    private RedisUtils redisUtils;

    @InjectMocks
    private SysRoleServiceImpl sysRoleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPage() {
        // Arrange
        RoleQueryVo queryInfo = new RoleQueryVo();
        queryInfo.setPageNumber(1);
        queryInfo.setPageSize(10);
        Page<SysRole> page = mock(Page.class);
        when(page.getTotal()).thenReturn(100L);
        when(page.getResult()).thenReturn(List.of());
        when(roleMapper.findPage(queryInfo)).thenReturn(page);

        // Act
        Result result = sysRoleService.findPage(queryInfo);

        // Assert
        assertEquals(PageResult.pageResult(1, 10, 100L, List.of()), result);
    }

    @Test
    void testInsert() {
        SysRole role = new SysRole();
        roleMapper.insert(role);
        Record record = new Record();
        record.setType(1);
        recordMapper.insert(record);
        System.out.println("\"添加角色信息成功！\"");
    }

}