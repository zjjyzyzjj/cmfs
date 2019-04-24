package com.cmfs.service;

import com.cmfs.conf.LogAnnotation;
import com.cmfs.dao.ZChapterMapper;
import com.cmfs.entity.PageBean;
import com.cmfs.entity.ZChapter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService{
    @Resource
    private ZChapterMapper chapterMapper;

    @Override
    public PageBean findAll(Integer page, Integer rows) {
        //开启分页
        PageHelper.startPage(page,rows);
        List<ZChapter> chapters = chapterMapper.findAll();
        //把查到的结果放到pageinfo对象   里面存放的是所有分页信息，从里面取前台需要的数据
        PageInfo pageInfo = new PageInfo(chapters);
        return new PageBean(pageInfo.getPageNum(),pageInfo.getPages(),pageInfo.getSize(),pageInfo.getList());
    }

    @Override
    @LogAnnotation(value = "删除一个章节")
    public void deleteById(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }

    @Override
    @LogAnnotation(value = "增加一个章节")
    public void insert(ZChapter zChapter) {
        chapterMapper.insert(zChapter);

    }

    @Override
    public ZChapter selectById(String id) {
        ZChapter chapter = chapterMapper.selectByPrimaryKey(id);
        return chapter;
    }

    @Override
    @LogAnnotation(value = "更改章节的播放数")
    public void updatePlayCount(String id) {
        ZChapter chapter = chapterMapper.selectByPrimaryKey(id);
        chapter.setPlays(chapter.getPlays()+1);
        chapterMapper.updateByPrimaryKeySelective(chapter);
    }
}
