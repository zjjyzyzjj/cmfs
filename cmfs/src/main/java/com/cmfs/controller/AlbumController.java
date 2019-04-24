package com.cmfs.controller;

import com.cmfs.entity.PageBean;
import com.cmfs.entity.ZAlbum;
import com.cmfs.service.AlbumService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Resource
    private AlbumService albumService;
    @RequestMapping("findAll")
    public Map<String, Object> findAll(Integer page, Integer rows){
        if(rows==null ||rows.equals("") ){
            rows=5;
        }
        PageBean pageBean = albumService.findAll(page, rows);

        Map<String, Object> map = new HashMap<>();
        map.put("page",pageBean.getPage());
        map.put("total",pageBean.getTotal());
        map.put("records",pageBean.getRecords());
        map.put("rows",pageBean.getRows());
        return map;
    }
    @RequestMapping("update")
    public String update(ZAlbum zAlbum, MultipartFile image, HttpServletRequest request) throws IOException {
        if(zAlbum.getId()==null||"".equals(zAlbum.getId())){
            if(image!=null){
                String originalFilename = image.getOriginalFilename();
                String realPath = request.getSession().getServletContext().getRealPath("imag");
                zAlbum.setImgPath("/imag/"+originalFilename);
                image.transferTo(new File(realPath,originalFilename));
                zAlbum.setId(UUID.randomUUID().toString());
                albumService.insert(zAlbum);
            }
            return "insertOk";
        }else{
            albumService.update(zAlbum);
            return "updateOk";
        }
    }
    @RequestMapping("queryById")
    public ZAlbum queryById(String id){
        ZAlbum album = albumService.findById(id);
        return album;
    }
    @RequestMapping("deleteById")
    public String deleteById(String id){
        albumService.deleteById(id);
        return "success";
    }
    @RequestMapping("allname")
    public List<ZAlbum> allName(){
        List<ZAlbum> zAlbums = albumService.allName();
        return zAlbums;
    }
}
