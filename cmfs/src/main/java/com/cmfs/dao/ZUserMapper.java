package com.cmfs.dao;

import com.cmfs.entity.UserMap;
import com.cmfs.entity.ZUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZUserMapper extends Mapper<ZUser> {
    /**
     * 根据性别查询
     * @param sex
     * @return
     */
    List<UserMap> findBySex(String sex);
}