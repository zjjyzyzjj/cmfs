package com.cmfs.service;

import com.cmfs.dao.ZAdminMapper;
import com.cmfs.entity.ZAdmin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

            @Service
            @Transactional
            public class AdminServiceImpl implements AdminService {
                @Resource
                private ZAdminMapper zAdminMapper;
                @Override
                public ZAdmin login(ZAdmin zAdmin) {
                    List<ZAdmin> list = zAdminMapper.select(zAdmin);
                    String password = zAdmin.getPassword();
                    if(list!=null){
                        for (ZAdmin admin : list) {
                            if(password.equals(admin.getPassword())){
                                return admin;
                            }
                            throw new RuntimeException("密码错误");
                        }

        }

        throw new RuntimeException("用户名不存在");
    }
}
