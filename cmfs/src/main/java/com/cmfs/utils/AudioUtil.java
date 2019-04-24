package com.cmfs.utils;


import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

/**
 * 计算音频时长的工具类
 */
public class AudioUtil {
    public static long getAudioLength(File file){
        Encoder encoder = new Encoder();
        MultimediaInfo m;
        long ls=0;
        try{
            m=encoder.getInfo(file);
            ls=m.getDuration()/1000;
            return ls;
        }catch(Exception e){
            throw new RuntimeException("获取音频时间有误:"+e.getMessage());
        }
    }
}
