package com.cmfs.service;

import com.cmfs.dao.ZLogMapper;
import com.cmfs.entity.ZLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Resource
    private ZLogMapper zLogMapper;
    @Override
    public void insert(ZLog zLog) {
        zLogMapper.insert(zLog);
}

    @Override
    public List<ZLog> findAll() {
        List<ZLog> all = zLogMapper.selectAll();
        return all;
    }
}
