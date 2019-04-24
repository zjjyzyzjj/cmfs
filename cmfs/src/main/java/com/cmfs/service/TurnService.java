package com.cmfs.service;

import com.cmfs.entity.ZTurn;

import java.util.List;

public interface TurnService {
    /**
     * 查询所有轮播图
     * @return
     */
    List<ZTurn> findAll();

    /**
     * 根据id删除一条记录
     * @param
     */
    void deleteById(String id);

    /**
     * 插入一条数据
     * @param zTurn
     */
    void insert(ZTurn zTurn);

    /**
     * 修改一条数据
     * @param zTurn
     */
    void update(ZTurn zTurn);
    ZTurn queryById(String id);
}
