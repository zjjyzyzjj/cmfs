package com.cmfs.dao;

import com.cmfs.entity.ZChapter;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZChapterMapper extends Mapper<ZChapter> {
    List<ZChapter> findAll();
}