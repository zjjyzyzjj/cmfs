package com.cmfs.service;

import com.cmfs.conf.LogAnnotation;
import com.cmfs.dao.ZTurnMapper;
import com.cmfs.entity.ZTurn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class TurnServiceImpl implements TurnService {
    @Resource
    private ZTurnMapper zTurnMapper;
    @Override
    public List<ZTurn> findAll() {
        List<ZTurn> zTurns = zTurnMapper.selectAll();
        return zTurns;
    }
    @LogAnnotation(value = "删除一个轮播图")
    @Override
    public void deleteById(String id) {
        zTurnMapper.deleteByPrimaryKey(id);
    }
    @LogAnnotation("插入一个轮播图")
    @Override
    public void insert(ZTurn zTurn) {
        zTurnMapper.insert(zTurn);
    }
    @LogAnnotation(value = "修改一个轮播图")
    @Override
    public void update(ZTurn zTurn) {
        zTurnMapper.updateByPrimaryKeySelective(zTurn);
    }

    @Override
    public ZTurn queryById(String id) {
        ZTurn zTurn = zTurnMapper.selectByPrimaryKey(id);
        return zTurn;
    }
}
