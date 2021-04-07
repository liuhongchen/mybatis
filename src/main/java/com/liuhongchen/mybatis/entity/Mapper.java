package com.liuhongchen.mybatis.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Mapper {

    private String namespace;

    private Map<String,Statement> statementMap;
}
