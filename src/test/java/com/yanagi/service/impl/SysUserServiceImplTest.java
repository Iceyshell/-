package com.yanagi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanagi.entity.Record;
import com.yanagi.entity.SysUser;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.mapper.SysUserMapper;
import com.yanagi.utils.PageResult;
import com.yanagi.utils.RedisUtils;
import com.yanagi.utils.Result;
import com.yanagi.vo.UserQueryVo;
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
public class SysUserServiceImplTest {

    @Mock
    private SysUserMapper userMapper;

    @Mock
    private RecordMapper recordMapper;

    @Mock
    private RedisUtils redisUtils;

    @InjectMocks
    private SysUserServiceImpl sysUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPage() {
        // Arrange
        UserQueryVo queryInfo = new UserQueryVo();
        queryInfo.setPageNumber(1);
        queryInfo.setPageSize(10);
        Page<SysUser> page = mock(Page.class);
        when(page.getTotal()).thenReturn(100L);
        when(page.getResult()).thenReturn(List.of());
        when(userMapper.findPage(queryInfo)).thenReturn(page);

        // Act
        Result result = sysUserService.findPage(queryInfo);

        // Assert
        assertEquals(PageResult.pageResult(1, 10, 100L, List.of()), result);
    }

    @Test
    void testInsert() {
        SysUser sysUser = new SysUser();
        userMapper.insert(sysUser);
        Record record = new Record();
        record.setType(1);
        recordMapper.insert(record);
        System.out.println("用户添加成功！");
    }

}