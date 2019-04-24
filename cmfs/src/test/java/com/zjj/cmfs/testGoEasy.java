package com.zjj.cmfs;

import com.cmfs.entity.ZUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.goeasy.GoEasy;
import org.junit.Test;

import java.util.UUID;

public class testGoEasy {
    @Test
    public void testgoeasy() throws JsonProcessingException {
        //将jackSon对象转为JSON
        ZUser zUser = new ZUser();
        zUser.setId(UUID.randomUUID().toString());
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(zUser);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-2b66fbf505a54de1a1ca0b060dc1be20");
        goEasy.publish("my_channel",s);
    }

}
