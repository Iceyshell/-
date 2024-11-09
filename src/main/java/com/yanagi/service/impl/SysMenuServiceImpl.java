package com.yanagi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanagi.entity.Record;
import com.yanagi.entity.SysMenu;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.mapper.SysMenuMapper;
import com.yanagi.service.SysMenuService;
import com.yanagi.utils.*;
import com.yanagi.vo.MenuQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanagi
 * @description 菜单service实现
 * @date 2024-05-01 1:05
 */
@Service
@Slf4j
public class  SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private RedisUtils redisUtil;


    @Override
    public Result findParent(MenuQueryVo queryInfo) {
        log.info("开始数据分页-->页码{}, --->{}页数--->查询内容{}", queryInfo.getPageNumber(), queryInfo.getPageSize(), queryInfo.getQueryString());
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<SysMenu> page = menuMapper.findParent(queryInfo);
        long total = page.getTotal();
        List<SysMenu> result = page.getResult();
        log.info("查询的总条数-->{}", total);
        log.info("分页列表-->{}", result);
        return new PageResult(queryInfo.getPageNumber(), queryInfo.getPageSize(), total, result);
    }

    @Override
    public Result findPage(MenuQueryVo queryInfo) {
        log.info("开始数据分页-->页码{}, --->{}页数--->查询内容{}", queryInfo.getPageNumber(), queryInfo.getPageSize(), queryInfo.getQueryString());
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<SysMenu> page = menuMapper.findPage(queryInfo);
        long total = page.getTotal();
        List<SysMenu> result = page.getResult();
        log.info("查询的总条数-->{}", total);
        log.info("分页列表-->{}", result);

        Record record = new Record();
        record.setType(4);
        recordMapper.insert(record);
        return new PageResult(queryInfo.getPageNumber(), queryInfo.getPageSize(), total, result);
    }

    @Override
    public Result findAll() {
        return Result.success("查询所有菜单成功", menuMapper.findAll());
    }

    @Override
    public Result insert(SysMenu menu) {
        menuMapper.insert(menu);
        redisUtil.delKey("userInfo_" + SecurityUtils.getUsername());
        Record record = new Record();
        record.setType(1);
        recordMapper.insert(record);
        return Result.success("添加菜单数据成功");
    }

    @Override
    public Result delete(Long id) {
        menuMapper.delete(id);
        redisUtil.delKey("userInfo_" + SecurityUtils.getUsername());
        Record record = new Record();
        record.setType(2);
        recordMapper.insert(record);
        return Result.success("删除菜单数据成功");
    }

    @Override
    public Result update(SysMenu menu) {
        menuMapper.update(menu);
        redisUtil.delKey("userInfo_" + SecurityUtils.getUsername());
        Record record = new Record();
        record.setType(3);
        recordMapper.insert(record);
        return Result.success("修改菜单数据成功");
    }
}
