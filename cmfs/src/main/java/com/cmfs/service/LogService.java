package com.cmfs.service;

import com.cmfs.entity.ZLog;

import java.util.List;

public interface LogService {
    void insert(ZLog zLog);
    List<ZLog> findAll();
}
