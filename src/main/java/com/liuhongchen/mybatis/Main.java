package com.liuhongchen.mybatis;

import com.liuhongchen.mybatis.entity.Configuration;
import com.liuhongchen.mybatis.entity.SqlSession;
import com.liuhongchen.mybatis.entity.SqlSessionFactory;
import com.liuhongchen.mybatis.utils.DocumentUtils;
import com.liuhongchen.mybatis.utils.Resources;
import com.liuhongchen.mybatis.utils.parser.XMLConfigParser;
import org.dom4j.Document;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        String resource="SqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        Document document = DocumentUtils.readDocument(in);

        XMLConfigParser xmlConfigParser=new XMLConfigParser();
        Configuration configuration = xmlConfigParser.parse(document.getRootElement());


        SqlSessionFactory sqlSessionFactory=new SqlSessionFactory();
        sqlSessionFactory.setConfiguration(configuration);

        SqlSession sqlSession = sqlSessionFactory.createSqlSession();


    }
}
