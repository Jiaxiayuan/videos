package com.ies.service;

import com.ies.domain.Course;
import com.ies.utils.DataGridView;
import com.ies.vo.CourseVo;

import java.util.List;

public interface CourseService {
    DataGridView queryAllCourse(CourseVo courseVo);

    void addCourse(CourseVo courseVo);

    Course queryCourseById(Integer id);

    void updateCourse(CourseVo coursevo);

    void deleteCourse(Integer id);

    void deleteBatchRole(Integer[] ids);

    List<Course> selectAll();

}
