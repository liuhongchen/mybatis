package com.liuhongchen.mybatis.entity;

import lombok.Data;

import javax.sql.DataSource;
import java.util.List;

@Data
public class Configuration {

    private DataSource dataSource;

    private List<Mapper> mappers;

}
