package com.liuhongchen.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlSessionFactory {

    private Configuration configuration;


    public SqlSession createSqlSession(){
        return new SqlSession();
    }

}
