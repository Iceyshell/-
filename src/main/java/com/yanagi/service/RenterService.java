package com.yanagi.service;

import com.yanagi.entity.Renter;
import com.yanagi.utils.Result;
import com.yanagi.vo.RenterQueryVo;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-06-25 22:50
 */

public interface RenterService {


    /**
     * 分页查询
     * @param queryInfo 页码、页数大小、查询内容
     * @return
     */
    Result findPage(RenterQueryVo queryInfo);

    /**
     * 添加租户数据
     * @param renter
     * @return
     */
    Result insert(Renter renter);

    /**
     * 删除租户数据
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 修改租户数据
     * @param renter
     * @return
     */
    Result update(Renter renter);


}
