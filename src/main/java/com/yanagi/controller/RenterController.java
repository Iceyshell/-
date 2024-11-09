package com.yanagi.controller;

import com.yanagi.entity.Renter;
import com.yanagi.service.RenterService;
import com.yanagi.utils.Result;
import com.yanagi.vo.RenterQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-06-25 22:56
 */
@RestController
@Api(tags = "租户")
@RequestMapping("/renter")
public class RenterController {

    @Autowired
    private RenterService renterService;

    @ApiOperation(value = "分页查询")
    @PostMapping("/list")
    public Result findPage(@RequestBody RenterQueryVo queryInfo) {
        return renterService.findPage(queryInfo);
    }

    @ApiOperation(value = "添加权限")
    @PostMapping("/add")
    public Result insert(@RequestBody Renter renter) {
        return renterService.insert(renter);
    }

    @ApiOperation(value = "修改权限")
    @PostMapping("/edit")
    public Result update(@RequestBody Renter renter) {
        return renterService.update(renter);
    }

    @ApiOperation(value = "删除权限")
    @PostMapping("/delete")
    public Result delete(@RequestBody Renter renter) {
        return renterService.delete(renter.getId());
    }

}
