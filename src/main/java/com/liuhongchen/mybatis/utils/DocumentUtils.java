package com.liuhongchen.mybatis.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class DocumentUtils {


    public static Document readDocument(InputStream in){
        SAXReader saxReader=new SAXReader();
        Document dodument = null;
        try {
            dodument = saxReader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return dodument;
    }
}
