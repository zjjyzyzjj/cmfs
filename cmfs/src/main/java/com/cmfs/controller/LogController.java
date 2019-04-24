package com.cmfs.controller;

import com.cmfs.entity.ZLog;
import com.cmfs.service.LogService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Controller
@RequestMapping("log")
public class LogController {
    @Resource
    private LogService logService;
    @RequestMapping("findAll")
    public @ResponseBody List<ZLog> findAll(){
        List<ZLog> logs = logService.findAll();
        return logs;
    }
    //导出数据
    @ResponseBody
    @RequestMapping("export")
    public String export(){
        //创建excel工作簿对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建工作表
        HSSFSheet sheet = hssfWorkbook.createSheet();
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        String[] title={"id","name","method","operate_time","yesornot"};
        //创建单元格对象
        HSSFCell cell=null;
        for(int i=0;i<title.length;i++){
            //i标识列索引
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //处理日期格式
        //创建样式对象
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        //创建日期格式
        HSSFDataFormat dataFormat = hssfWorkbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日"));
        List<ZLog> logs = logService.findAll();
        //处理数据行
        for(int i=1;i<=logs.size();i++){
            row=sheet.createRow(i);
            row.createCell(0).setCellValue(logs.get(i-1).getId());
            row.createCell(1).setCellValue(logs.get(i-1).getName());
            row.createCell(2).setCellValue(logs.get(i-1).getMethod());
            row.createCell(3).setCellValue(logs.get(i-1).getOperateTime());
            row.createCell(4).setCellValue(logs.get(i-1).getYesornot());
        }
        try{
            hssfWorkbook.write(new File("D:\\学习资料\\上课资料\\framework\\后期项目\\日志.xls"));
            hssfWorkbook.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "导出success";
    }
    @RequestMapping("import")
    @ResponseBody
    public String imp(){
        try{
            //获取本地excel文件输入流,并创建工作薄对象
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("D:/学习资料/上课资料/framework/后期项目/日志1.xls")));
            //获取工作表
            HSSFSheet sheet = workbook.getSheet("Sheet0");
            //声明行对象
            HSSFRow row=null;
            //注意:获取数据 需排除标题行  从数据行开始读取
            for(int i=1;i<=sheet.getLastRowNum();i++){
                //获取当前工作表中的数据行信息 数据行索引从1开始
                row=sheet.getRow(i);
                ZLog zLog = new ZLog(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue(), row.getCell(3).getDateCellValue(),
                        row.getCell(4).getStringCellValue());
                logService.insert(zLog);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "导入success";
    }
}
