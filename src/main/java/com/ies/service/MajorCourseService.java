package com.ies.service;

import com.ies.domain.MajorCourse;
import com.ies.utils.DataGridView;
import com.ies.vo.MajorCourseVo;

public interface MajorCourseService {
    DataGridView queryAllMajorCourse(MajorCourseVo majorCourseVo);

    void addMajorCourse(MajorCourseVo majorCourseVo);

    MajorCourse queryMajorCourseById(Integer id);

    void updateMajorCourse(MajorCourseVo majorCoursevo);

    void deleteMajorCourse(Integer id);

    void deleteBatchRole(Integer[] ids);

}
