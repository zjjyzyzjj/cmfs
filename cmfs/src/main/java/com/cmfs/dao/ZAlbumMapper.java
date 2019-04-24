package com.cmfs.dao;

import com.cmfs.entity.ZAlbum;
import tk.mybatis.mapper.common.Mapper;

public interface ZAlbumMapper extends Mapper<ZAlbum> {
    ZAlbum findById(String id);
}