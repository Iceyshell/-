package com.yanagi.mapper;

import com.github.pagehelper.Page;
import com.yanagi.entity.Renter;
import com.yanagi.vo.RenterQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yanagi
 * @description 租户mapper
 * @date 2024-05-01 0:47
 */

public interface RenterMapper {

    /**
     * 添加租户信息
     * @param renter 租户数据
     */
    void insert(Renter renter);

    /**
     * 修改租户信息
     * @param renter 租户数据
     */
    void update(Renter renter);

    /**
     * 删除租户数据
     * @param id
     */
    void delete(Long id);

    /**
     * 分页查询
     * @return
     */
    Page<Renter> findPage (RenterQueryVo renterQueryVo);

}
