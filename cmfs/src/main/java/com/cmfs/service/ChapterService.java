package com.cmfs.service;

import com.cmfs.entity.PageBean;
import com.cmfs.entity.ZChapter;


public interface ChapterService {
    /**
     * 分页查询所有章节
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageBean findAll(Integer pageNum, Integer pageSize);

    /**
     * 根据id删除一条记录
     * @param id
     */
    void deleteById(String id);

    /**
     * 增加一条记录
     * @param zChapter
     */
    void insert(ZChapter zChapter);
    ZChapter selectById(String id);
    void updatePlayCount(String id);
}
