package com.cmfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.cmfs.dao")
public class CmfsApplication {

    public static void main(String[] args) {
        System.out.println(111111111);
        SpringApplication.run(CmfsApplication.class, args);
    }

}
