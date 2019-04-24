package com.cmfs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZLesson {
    @Id
    private String id;

    private String name;

    private String mark;

    private Date createTime;

    private String userId;

}