package com.demo.IPMDemo.demo.Dao;

import com.demo.IPMDemo.demo.model.Review;
import com.demo.IPMDemo.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewDao {
    String TABLE_NAME=" review ";
    String INSERT_FIELDS=" name,review,overview,teacherId ";
    String SELECT_FIELDS=" id,name,review,overview,teacherId ";
    @Insert({"insert into"+TABLE_NAME+"("+INSERT_FIELDS+")"+" values(#{name},#{review},#{overview},#{teacherId})"})
    int addReview(Review review);
}
