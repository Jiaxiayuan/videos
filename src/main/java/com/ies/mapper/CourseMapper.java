package com.ies.mapper;

import com.ies.domain.Course;
import com.ies.domain.CourseExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper {
    int countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    @Select("SELECT * FROM course WHERE id IN(\n" +
            "SELECT courseid FROM major_course WHERE schoolmajorid IN\n" +
            "(SELECT id FROM school_major WHERE schoolid=#{id})\n" +
            ")")
    List<Course> selectCourse(Integer id);
}