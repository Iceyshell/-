package com.yanagi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanagi.entity.Record;
import com.yanagi.entity.SysPermission;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.mapper.SysPermissionMapper;
import com.yanagi.service.SysPermissionService;
import com.yanagi.utils.*;
import com.yanagi.vo.PermissionQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-05-02 18:45
 */

@Service
@Slf4j
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private RedisUtils redisUtil;

    @Autowired
    private RecordMapper recordMapper;



    /**
     * 分页查询
     * @param queryInfo 页码、页数大小、查询内容
     * @return
     */
    @Override
    public Result findPage(PermissionQueryVo queryInfo) {
        log.info("开始权限数据分页-->页码{}, --->{}页数--->查询内容{}", queryInfo.getPageNumber(), queryInfo.getPageSize(), queryInfo.getQueryString());
        // 分页操作
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<SysPermission> page = permissionMapper.findPage(queryInfo);
        long total = page.getTotal();
        List<SysPermission> result = page.getResult();
        log.info("查询的总条数-->{}", total);
        log.info("分页列表-->{}", result);
        Record record = new Record();
        record.setType(4);
        recordMapper.insert(record);
        return PageResult.pageResult(queryInfo.getPageNumber(), queryInfo.getPageSize(), total, result);
    }

    /**
     * 添加权限数据
     * @param permission
     * @return
     */
    @Override
    public Result insert(SysPermission permission) {
        permissionMapper.insert(permission);
        redisUtil.delKey("userInfo_" + SecurityUtils.getUsername());
        Record record = new Record();
        record.setType(1);
        recordMapper.insert(record);
        return Result.success("添加权限数据成功");
    }

    /**
     * 删除权限数据
     * @param id
     * @return
     */
    @Override
    public Result delete(Long id) {
        permissionMapper.delete(id);
        redisUtil.delKey("userInfo_" + SecurityUtils.getUsername());
        Record record = new Record();
        record.setType(2);
        recordMapper.insert(record);
        return Result.success("删除权限数据成功");
    }

    /**
     * 修改权限数据
     * @param permission
     * @return
     */
    @Override
    public Result update(SysPermission permission) {
        permissionMapper.update(permission);
        redisUtil.delKey("userInfo_" + SecurityUtils.getUsername());
        Record record = new Record();
        record.setType(3);
        recordMapper.insert(record);
        return Result.success("修改权限数据成功");
    }

    /**
     * 查询所有的权限数据
     * @return
     */
    @Override
    public Result findAll() {
        return Result.success("查询权限信息成功", permissionMapper.findAll());
    }
}

