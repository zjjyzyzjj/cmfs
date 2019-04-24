package com.cmfs.service;

import com.cmfs.entity.PageBean;
import com.cmfs.entity.ZAlbum;

import java.util.List;

public interface AlbumService {
    /**
     * 查询所有专辑
     * @return
     */
    PageBean findAll(Integer pageNum, Integer pageSize);

    /**
     * 根据id删除一条
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据id查找一条
     * @param id
     * @return
     */
    ZAlbum findById(String id);

    /**
     * 插入一条数据
     * @param zAlbum
     */
    void insert(ZAlbum zAlbum);

    /**
     * 更新一条数据
     * @param zAlbum
     */
    void update(ZAlbum zAlbum);
    List<ZAlbum> allName();
}
