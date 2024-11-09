package com.yanagi.mapper;

import com.yanagi.entity.Department;
import com.yanagi.entity.Record;
import com.yanagi.entity.Renter;

import java.util.List;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-06-25 14:00
 */

public interface RecordMapper {

    /**
     * 获取所有的记录
     * @return
     */
    List<Record> findAll();


    void insert(Record record);
}
