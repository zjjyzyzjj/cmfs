package com.cmfs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZTurn {
    @Id
    private String id;

    private String title;

    private String imgPath;

    private String imgDesc;

    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date uptime;

    private String imga;


}