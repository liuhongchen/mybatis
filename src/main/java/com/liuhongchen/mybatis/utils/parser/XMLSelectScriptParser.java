package com.liuhongchen.mybatis.utils.parser;

import com.liuhongchen.mybatis.entity.MappedStatement;
import com.liuhongchen.mybatis.entity.SqlNode;
import com.liuhongchen.mybatis.entity.Statement;
import org.dom4j.Element;

import java.util.List;

public class XMLSelectScriptParser implements XMLScriptParser {


    public MappedStatement parse(Element selectElement) {

            MappedStatement statement=new MappedStatement();
            String id = selectElement.attributeValue("id");
            statement.setId(id);
            String parameterType
                    = selectElement.attributeValue("parameterType");
            statement.setParameterType(parameterType);
            String resultType = selectElement.attributeValue("resultType");
            statement.setResultType(resultType);


            String sql = selectElement.getText();
            SqlNode sqlNode=parseSql(sql);
            statement.setSqlNode(sqlNode);


            return statement;

    }

    private SqlNode parseSql(String sql) {
        return null;
    }


}
