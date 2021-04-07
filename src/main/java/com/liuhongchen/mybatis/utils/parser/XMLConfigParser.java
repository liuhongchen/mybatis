package com.liuhongchen.mybatis.utils.parser;

import com.liuhongchen.mybatis.entity.Configuration;
import com.liuhongchen.mybatis.entity.Mapper;
import com.liuhongchen.mybatis.entity.SqlNode;
import com.liuhongchen.mybatis.utils.DocumentUtils;
import com.liuhongchen.mybatis.utils.Resources;
import org.apache.commons.dbcp2.BasicDataSource;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class XMLConfigParser {

    private Configuration configuration;

    public XMLConfigParser() {
        configuration = new Configuration();
    }

    public Configuration parse(Element root) {
        //处理environments
        Element environments = root.element("environments");
        parseEnvironments(environments);

        //处理mappers
        Element mappersElement = root.element("mappers");
        parseMappers(mappersElement);

        return configuration;
    }

    private void parseEnvironments(Element environmentsElement) {
        Attribute defaultId = environmentsElement.attribute("default");
        if (defaultId == null) return;

        List<Element> envList = environmentsElement.elements("environment");

        for (Element env : envList) {
            Attribute envId = env.attribute("id");
            if (envId.getValue().equals(defaultId.getValue())) {
                parseDataSource(env.element("dataSource"));
            }
        }
    }

    private void parseMappers(Element mappersElement) {
        List<Element> mapperList = mappersElement.elements("mapper");

        for (Element mapperElement : mapperList) {
            parseMapper(mapperElement);
        }

    }

    private void parseMapper(Element mapperElement) {
        String resource = mapperElement.attributeValue("resource");
        InputStream in = Resources.getResourceAsStream(resource);
        Document document = DocumentUtils.readDocument(in);
        Element rootElement = document.getRootElement();
        XMLMapperParser xmlMapperParser=new XMLMapperParser();
        xmlMapperParser.parse(configuration,rootElement);
    }


    private void parseDataSource(Element dataSourceElement) {
        Properties properties = parseProperties(dataSourceElement);

        String type = dataSourceElement.attributeValue("type");

        if ("DBCP".equals(type)) {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            configuration.setDataSource(dataSource);
        }
    }

    private Properties parseProperties(Element dataSourceElement) {
        Properties properties = new Properties();

        List<Element> propertyElements = dataSourceElement.elements("property");
        for (Element propertyElement : propertyElements) {
            String name = propertyElement.attributeValue("name");
            String value = propertyElement.attributeValue("value");
            properties.put(name,value);
        }

        return properties;
    }


}
