package com.cmfs.service;

import com.cmfs.conf.LogAnnotation;
import com.cmfs.dao.ZAlbumMapper;
import com.cmfs.dao.ZChapterMapper;
import com.cmfs.entity.PageBean;
import com.cmfs.entity.ZAlbum;
import com.cmfs.entity.ZChapter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService{
    @Resource
    private ZAlbumMapper zAlbumMapper;
    @Resource
    private ZChapterMapper zChapterMapper;
    @Override
    public PageBean findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ZAlbum> albums = zAlbumMapper.selectAll();
        PageInfo pageInfo=new PageInfo(albums);
        return new PageBean(pageInfo.getPageNum(),pageInfo.getPages(),pageInfo.getSize(),pageInfo.getList());
    }

    @LogAnnotation(value = "删除一个专辑")
    @Override
    public void deleteById(String id) {

        ZAlbum album = zAlbumMapper.findById(id);
        List<ZChapter> chapters=album.getChapters();
        if(chapters!=null){
            for (ZChapter chapter : chapters) {
                zChapterMapper.delete(chapter);
            }
        }
        zAlbumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ZAlbum findById(String id) {
        ZAlbum zAlbum = zAlbumMapper.selectByPrimaryKey(id);
        return zAlbum;
    }
    @LogAnnotation(value = "插入一个专辑")
    @Override
    public void insert(ZAlbum zAlbum) {
         zAlbumMapper.insertSelective(zAlbum);
    }
    @LogAnnotation(value = "修改一个专辑")
    @Override
    public void update(ZAlbum zAlbum) {
        zAlbumMapper.updateByPrimaryKeySelective(zAlbum);
    }

    @Override
    public List<ZAlbum> allName() {
        List<ZAlbum> albums = zAlbumMapper.selectAll();
        return albums;
    }
}
