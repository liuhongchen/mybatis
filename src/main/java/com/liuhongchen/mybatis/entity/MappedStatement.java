package com.liuhongchen.mybatis.entity;

import lombok.Data;

@Data
public class MappedStatement implements Statement {

    private String id;
    private String parameterType;
    private String resultType;
    private SqlNode sqlNode;
}
