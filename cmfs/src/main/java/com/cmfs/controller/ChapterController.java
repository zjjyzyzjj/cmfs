package com.cmfs.controller;

import com.cmfs.entity.PageBean;
import com.cmfs.entity.ZChapter;
import com.cmfs.service.ChapterService;
import com.cmfs.utils.AudioUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String, Object> findAll(Integer page,Integer rows){
        if(rows==null ||rows.equals("") ){
            rows=5;
        }
        PageBean pageBean = chapterService.findAll(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("page",pageBean.getPage());
        map.put("total",pageBean.getTotal());
        map.put("records",pageBean.getRecords());
        map.put("rows",pageBean.getRows());
        return map;
    }
    @RequestMapping("insert")
    @ResponseBody
    public String insert(ZChapter zChapter, HttpServletRequest request, MultipartFile file) throws IOException {
        zChapter.setId(UUID.randomUUID().toString());
        String originalFilename = file.getOriginalFilename();
        zChapter.setAudioPath("/audio/"+originalFilename);
        String realPath = request.getSession().getServletContext().getRealPath("audio");
        File file1 = new File(realPath, originalFilename);
        file.transferTo(file1);
        long length = AudioUtil.getAudioLength(file1);
        zChapter.setAudiotime(length);
        Date date = new Date();
        zChapter.setUptime(date);
        zChapter.setPlays(0);
        long size = file.getSize();
        zChapter.setAudioSize(size/1024/1024);
        chapterService.insert(zChapter);
        return "success";
    }

    // 下载音频
    @RequestMapping("/download")
    public void download(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("audio");
        String[] strings = name.split("/");
        String str = strings[strings.length-1];
        //文件后缀
        String extension = FilenameUtils.getExtension(str);
        //设置响应类型
        response.setContentType(request.getSession().getServletContext().getMimeType("."+extension));
        FileInputStream inputStream = new FileInputStream(new File(path, str));
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(str,"UTF-8").replace("+","%20"));
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream,outputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }
    @RequestMapping("/updateDowloadCount")
    public void updateDownloadCount(String id){
        chapterService.updatePlayCount(id);

    }
    //删除章节（连带删除服务器中存在的音频）
    @RequestMapping("/delete")
    @ResponseBody
    public void delete(String id, HttpSession session){
        ZChapter chapter = chapterService.selectById(id);
        //从服务器中删除
        String realPath = session.getServletContext().getRealPath("/audio");
        String filePath = realPath+"\\"+chapter.getAudioPath();
        File file = new File(filePath);
        file.delete();
        //删除数据库
        chapterService.deleteById(id);
    }
    @RequestMapping("/updatePlayCount")
    @ResponseBody
    public String updatePlayCount(String id){
        chapterService.updatePlayCount(id);
        return "success";
    }
}
