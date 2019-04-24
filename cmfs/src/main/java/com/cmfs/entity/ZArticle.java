package com.cmfs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZArticle {
    @Id
    private String id;

    private String name;

    private String content;

    private String imgPath;

    private String masterid;

    private Date updateTime;

    private String readCount;

}