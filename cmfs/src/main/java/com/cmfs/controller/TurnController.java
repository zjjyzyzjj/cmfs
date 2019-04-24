package com.cmfs.controller;

import com.cmfs.entity.ZTurn;
import com.cmfs.service.TurnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("turn")
public class TurnController {
    @Resource
    private TurnService turnService;
    @RequestMapping("findAll")
    public @ResponseBody List<ZTurn> findAll(){
        List<ZTurn> list = turnService.findAll();
        return list;
    }
    @RequestMapping("delete")
    public @ResponseBody String deleteById(String id){
      turnService.deleteById(id);
      return "success";
    }
    @RequestMapping("insert")
    public @ResponseBody void insert(ZTurn zTurn, MultipartFile image, HttpServletRequest request) throws IOException {
            if(zTurn.getId()==null||"".equals(zTurn.getId())){
                //如果id为空,则执行插入
                if(image.getOriginalFilename()!=null){
                    String originalFilename = image.getOriginalFilename();
                    String realPath = request.getSession().getServletContext().getRealPath("imag");
                    String imgPath="/imag/"+originalFilename;
                    zTurn.setImgPath(imgPath);
                    image.transferTo(new File(realPath,originalFilename));
                }
                Date date = new Date();
                zTurn.setUptime(date);
                zTurn.setId(UUID.randomUUID().toString());
                turnService.insert(zTurn);
            }else{
                //如果id不为空,则执行更改
              /*  if(image.getOriginalFilename()!=null){
                    String originalFilename = image.getOriginalFilename();
                    String realPath = request.getSession().getServletContext().getRealPath("imag");
                    String imgPath="/imag/"+originalFilename;
                    zTurn.setImgPath(imgPath);
                    image.transferTo(new File(realPath,originalFilename));
                }*/

                turnService.update(zTurn);
            }
    }
    @RequestMapping("queryById")
    public @ResponseBody ZTurn queryById(String id){
        ZTurn zTurn = turnService.queryById(id);
        return zTurn;
    }

}
