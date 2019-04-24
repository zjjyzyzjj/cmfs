package com.cmfs.service;

import com.cmfs.entity.ZAdmin;

public interface AdminService {
    /**
     * 验证登录是否正确
     * @param zAdmin
     * @return
     */
    ZAdmin login(ZAdmin zAdmin);
}
