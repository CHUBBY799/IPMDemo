package com.demo.IPMDemo.demo.Dao;

import com.demo.IPMDemo.demo.model.Project;
import com.demo.IPMDemo.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectDao {
    String TABLE_NAME=" project ";
    String INSERT_FIELDS=" name,manager,teacherId,type,level,status,date ";
    String SELECT_FIELDS=" id,name,manager,teacherId,type,level,status,date ";

    List<Project> selectProjectByStatus(int status);
    @Select({"select * "+"from"+TABLE_NAME})
    List<Project> selectAllProject();
    @Insert({"insert into"+TABLE_NAME+"("+INSERT_FIELDS+")"+" values(#{name},#{manager},#{teacherId},#{type},#{level},#{status},#{date})"})
    int addProject(Project project);
    @Update({"update ", TABLE_NAME, " set name=#{name},manager=#{manager},teacherId=#{teacherId},type=#{type},level=#{level},status=#{status} where id=#{id}"})
    void updateproject(Project project);
    @Delete({"delete from", TABLE_NAME, " where id=#{id}"})
    void deleteproject(int id);
}
