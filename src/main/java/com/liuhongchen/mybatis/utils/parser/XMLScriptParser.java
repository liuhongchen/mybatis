package com.liuhongchen.mybatis.utils.parser;


import com.liuhongchen.mybatis.entity.MappedStatement;
import org.dom4j.Element;

public interface XMLScriptParser {

     MappedStatement parse(Element element);
}

