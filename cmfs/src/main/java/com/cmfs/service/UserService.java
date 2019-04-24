package com.cmfs.service;


import com.cmfs.entity.UserMap;

import java.util.List;

public interface UserService {
    /**
     * 根据性别查询所有用户
     * @param sex
     * @return
     */
    List<UserMap> findBySex(String sex);
}
