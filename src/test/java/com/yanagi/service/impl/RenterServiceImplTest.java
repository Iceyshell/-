package com.yanagi.service.impl;

import com.github.pagehelper.Page;
import com.yanagi.entity.Renter;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.mapper.RenterMapper;
import com.yanagi.utils.PageResult;
import com.yanagi.utils.Result;
import com.yanagi.vo.RenterQueryVo;
import org.junit.Before;
import org.junit.Test;
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
public class RenterServiceImplTest {

    @Mock
    private RenterMapper renterMapper;

    @Mock
    private RecordMapper recordMapper;

    @InjectMocks
    private RenterServiceImpl renterService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindPage() {
        // Arrange
        RenterQueryVo queryInfo = new RenterQueryVo();
        queryInfo.setPageNumber(1);
        queryInfo.setPageSize(10);
        Page<Renter> page = mock(Page.class);
        when(page.getTotal()).thenReturn(100L);
        when(page.getResult()).thenReturn(List.of());
        when(renterMapper.findPage(queryInfo)).thenReturn(page);

        // Act
        Result result = renterService.findPage(queryInfo);

        // Assert
        assertEquals(PageResult.pageResult(1, 10, 100L, List.of()), result);
    }

    @Test
    public void testInsert() {
        // Arrange
        Renter renter = new Renter();

        // Act
        Result result = renterService.insert(renter);

        // Assert
        assertEquals(Result.success("新增租户数据成功"), result);
    }
}
