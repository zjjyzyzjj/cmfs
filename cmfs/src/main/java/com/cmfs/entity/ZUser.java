package com.cmfs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZUser {
    @Id
    private String id;

    private String username;

    private String password;

    private String mobile;

    private String sex;

    private String nickname;

    private String realname;

    private String province;

    private String city;

    private String underwrite;

    private String headPic;

    private String status;

    private String salt;

    private Date regtime;

    private Date lastTime;


}