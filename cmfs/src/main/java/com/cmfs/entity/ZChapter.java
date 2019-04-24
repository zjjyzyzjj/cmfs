package com.cmfs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
//章节表
public class ZChapter {
    @Id
    private String id;

    private String name;
    //音频路径
    private String audioPath;
    //音频大小
    private Long audioSize;
    //音频时长
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Long audiotime;
    //上传时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date uptime;
    //播放次数
    private Integer plays;
    //专辑id
    private String aubumId;
    @Transient
    private ZAlbum zAlbum;

}