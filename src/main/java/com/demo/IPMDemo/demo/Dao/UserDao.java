package com.demo.IPMDemo.demo.Dao;

import com.demo.IPMDemo.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    String TABLE_NAME=" user ";
    String INSERT_FIELDS=" username,password,email,name,identity,other ";
    String SELECT_FIELDS=" id,username,password,email,name,identity,other ";
    @Insert({"insert into"+TABLE_NAME+"("+INSERT_FIELDS+")"+" values(#{username},#{password},#{email},#{name},#{identity},#{other})"})
    int addUser(User user);
    @Select({"select"+SELECT_FIELDS+"from"+TABLE_NAME+"where id=#{id}"})
    User selectById(int id);
    @Select({"select"+SELECT_FIELDS+"from"+TABLE_NAME+"where username=#{username}"})
    User selectByUsername(String username);
    @Select({"select * "+"from"+TABLE_NAME})
    List<User> selectAllUser();
    @Update({"update ", TABLE_NAME, " set username=#{username},password=#{password},email=#{email},identity=#{identity} where id=#{id}"})
    void updateuser(User user);
    @Delete({"delete from", TABLE_NAME, " where id=#{id}"})
    void deleteuser(int id);
}
