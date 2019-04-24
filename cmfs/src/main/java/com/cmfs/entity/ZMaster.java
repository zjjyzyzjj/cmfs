package com.cmfs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZMaster {
    @Id
    private String id;

    private String name;

    private String status;

    private String headImg;

    private String summary;

}