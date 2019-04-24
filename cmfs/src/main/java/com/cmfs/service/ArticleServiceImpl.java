package com.cmfs.service;

import com.cmfs.dao.ZArticleMapper;
import com.cmfs.entity.ZArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ZArticleMapper zArticleMapper;
    @Override
    public ZArticle showArticle(String id, String uid) {
        ZArticle zArticle = zArticleMapper.selectByPrimaryKey(id);

        return zArticle;
    }
}
