package com.liuhongchen.mybatis.utils.parser;

import com.liuhongchen.mybatis.entity.Configuration;
import com.liuhongchen.mybatis.entity.MappedStatement;
import com.liuhongchen.mybatis.entity.Mapper;
import com.liuhongchen.mybatis.entity.Statement;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLMapperParser {




    public void parse(Configuration configuration,Element rootElement) {
        Mapper mapper=new Mapper();

        String namespace = rootElement.attributeValue("namespace");
        mapper.setNamespace(namespace);

        XMLScriptParser xmlScriptParser=new XMLSelectScriptParser();

        List<Element> selectElements = rootElement.elements("select");
        for (Element selectElement : selectElements) {
            MappedStatement statement=  xmlScriptParser.parse(selectElement);

            if (mapper.getStatementMap()==null){
                Map<String, Statement> statementMap=new HashMap<>();
                statementMap.put(statement.getId(),statement);
                mapper.setStatementMap(statementMap);
            }else{
                mapper.getStatementMap().put(statement.getId(),statement);
            }

        }



        //TODO:还有update等等，先不写了

        if (configuration.getMappers()==null){
            List<Mapper> mappers=new ArrayList<>();
            mappers.add(mapper);
            configuration.setMappers(mappers);
        }else{
            configuration.getMappers().add(mapper);
        }
    }

    private void parseSelectElements(List<Element> selectElements) {
        for (Element selectElement : selectElements) {
            parseSelectElement(selectElement);
        }
    }

    private void parseSelectElement(Element selectElement) {

    }
}
