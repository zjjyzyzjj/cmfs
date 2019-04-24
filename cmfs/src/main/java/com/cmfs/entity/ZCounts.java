package com.cmfs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZCounts {
    @Id
    private String id;

    private String name;

    private Integer count;

    private Date createTime;

    private Date lastTime;

    private String userId;

    private String lessonId;

}