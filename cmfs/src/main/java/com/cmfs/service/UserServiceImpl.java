package com.cmfs.service;

import com.cmfs.dao.ZUserMapper;
import com.cmfs.entity.UserMap;
import com.cmfs.entity.ZUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private ZUserMapper zUserMapper;
    @Override
    public List<UserMap> findBySex(String sex) {
        List<UserMap> users = zUserMapper.findBySex(sex);
        return users;
    }
}
