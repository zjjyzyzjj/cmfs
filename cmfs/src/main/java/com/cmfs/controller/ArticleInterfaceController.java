package com.cmfs.controller;

import com.cmfs.entity.ZArticle;
import com.cmfs.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleInterfaceController {
    @Resource
    private ArticleService articleService;
    @RequestMapping("testInteface")
    public Map showArticle(String id,String uid){
        ZArticle zArticle = articleService.showArticle(id, uid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("link",zArticle.getContent());
        map.put("id",zArticle.getId());
        map.put("ext",null);
        return map;
    }
}
