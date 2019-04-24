package com.zjj.cmfs;

import com.cmfs.CmfsApplication;
import com.cmfs.dao.ZArticleMapper;
import com.cmfs.dao.ZChapterMapper;
import com.cmfs.entity.ZArticle;
import com.cmfs.entity.ZChapter;
import com.cmfs.service.AlbumService;
import com.cmfs.service.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfsApplication.class)
public class CmfsApplicationTests {
    @Resource
    private ZChapterMapper c ;
    @Resource
    private AlbumService albumService;
    @Resource
    private LogService logService;
    @Autowired
    private ZArticleMapper zArticleMapper;
    @Test
    public void contextLoads() {
        ZChapter t = new ZChapter();
        t.setAudioSize((long)0);
    List<ZChapter> select = c.select(t);
        for (ZChapter chapter : select) {
        System.out.println(chapter);
    }
}
    @Test
    public void testAudioTime(){
        ZArticle article = zArticleMapper.selectByPrimaryKey("1");
        System.out.println(article);
    }
}
