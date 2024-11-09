package com.yanagi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanagi.entity.Record;
import com.yanagi.entity.Renter;
import com.yanagi.entity.SysUser;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.mapper.RenterMapper;
import com.yanagi.service.RenterService;
import com.yanagi.utils.PageResult;
import com.yanagi.utils.Result;
import com.yanagi.vo.RenterQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-06-25 22:51
 */
@Service
@Slf4j
public class RenterServiceImpl implements RenterService {

    @Autowired
    private RenterMapper renterMapper;

    @Autowired
    private RecordMapper recordMapper;


    @Override
    public Result findPage(RenterQueryVo queryInfo) {
        log.info("分页查询--> 页码==>{}, 页数大小==>{}", queryInfo.getPageNumber(), queryInfo.getPageSize());
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<Renter> page = renterMapper.findPage(queryInfo);
        long total = page.getTotal();
        List<Renter> result = page.getResult();
//        result.forEach(item -> {
//            item.setRoles(userMapper.findRoles(item.getId()));
//            item.setName(item.getUsername());
//            item.setPassword(null);
//        });
        Record record = new Record();
        record.setType(4);
        recordMapper.insert(record);
        return PageResult.pageResult(queryInfo.getPageNumber(), queryInfo.getPageSize(), total, result);
    }

    @Override
    public Result insert(Renter renter) {
        renter.setStatus(true);
        renterMapper.insert(renter);
        Record record = new Record();
        record.setType(1);
        recordMapper.insert(record);
        return Result.success("新增租户数据成功");
    }

    @Override
    public Result delete(Long id) {
        renterMapper.delete(id);
        Record record = new Record();
        record.setType(2);
        recordMapper.insert(record);
        return Result.success("删除租户数据成功");
    }

    @Override
    public Result update(Renter renter) {
        renterMapper.update(renter);
        Record record = new Record();
        record.setType(3);
        recordMapper.insert(record);
        return Result.success("修改租户数据成功");
    }
}
