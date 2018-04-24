package com.demo.IPMDemo.demo.Dao;

import com.demo.IPMDemo.demo.model.User;
import com.demo.IPMDemo.demo.model.apply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplyDao {
    String TABLE_NAME=" apply ";
    String INSERT_FIELDS=" name,content,innovation,budget,expect ";
    String SELECT_FIELDS=" id,name,content,innovation,budget,expect ";
    @Insert({"insert into"+TABLE_NAME+"("+INSERT_FIELDS+")"+" values(#{name},#{content},#{innovation},#{budget},#{expect})"})
    int addApply(apply apply);
}
