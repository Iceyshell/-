package com.yanagi.service.impl;

import com.yanagi.utils.RedisUtils;
import com.github.pagehelper.Page;
import com.yanagi.entity.Record;
import com.yanagi.entity.SysMenu;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.mapper.SysMenuMapper;
import com.yanagi.utils.PageResult;
import com.yanagi.utils.RedisUtils;
import com.yanagi.utils.Result;
import com.yanagi.utils.SecurityUtils;
import com.yanagi.vo.MenuQueryVo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Transactional
@Rollback(true)
public class SysMenuServiceImplTest {

    @Mock
    private SysMenuMapper menuMapper;

    @Mock
    private RecordMapper recordMapper;

    @Mock
    private RedisUtils redisUtils;

    @InjectMocks
    private SysMenuServiceImpl sysMenuService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindPage() {
        // Arrange
        MenuQueryVo queryInfo = new MenuQueryVo();
        queryInfo.setPageNumber(1);
        queryInfo.setPageSize(10);
        Page<SysMenu> page = mock(Page.class);
        when(page.getTotal()).thenReturn(100L);
        when(page.getResult()).thenReturn(List.of());
        when(menuMapper.findPage(queryInfo)).thenReturn(page);

        // Act
        Result result = sysMenuService.findPage(queryInfo);

        // Assert
        assertEquals(PageResult.pageResult(1, 10, 100L, List.of()), result);
    }

    @Test
    public void testInsert() {
        SysMenu menu = new SysMenu();
        menuMapper.insert(menu);

        Record record = new Record();
        record.setType(1);
        recordMapper.insert(record);
        System.out.println(Result.success("添加菜单数据成功"));
    }

}
