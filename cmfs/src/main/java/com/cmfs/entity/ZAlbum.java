package com.cmfs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//专辑表
public class ZAlbum {
    @Id
    private String id;

    private String name;

    private String score;

    private String author;
    //播音
    private String beam;
    //集数
    private Integer count;
    //简介
    private String summary;
    //专辑图片存放位置
    private String imgPath;
    //发布日期
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;
    //专辑状态
    private String status;
    private List<ZChapter> chapters;
}