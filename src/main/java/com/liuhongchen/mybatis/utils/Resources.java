package com.liuhongchen.mybatis.utils;

import java.io.InputStream;

public class Resources {


    public static InputStream getResourceAsStream(String resource){
        return Resources.class.getClassLoader().getResourceAsStream(resource);
    }

}
