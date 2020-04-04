package com.ies.service;

import com.ies.domain.SchoolMajor;
import com.ies.utils.DataGridView;
import com.ies.vo.SchoolMajorVo;

import java.util.List;

public interface SchoolMajorService {
    DataGridView queryAllSchoolMajor(SchoolMajorVo schoolMajorVo);

    void addSchoolMajor(SchoolMajorVo schoolMajorVo);

    SchoolMajor querySchoolMajorById(Integer id);

    void updateSchoolMajor(SchoolMajorVo schoolMajorvo);

    void deleteSchoolMajor(Integer id);

    void deleteBatchRole(Integer[] ids);

    List<SchoolMajor> selectBySchool(SchoolMajorVo schoolMajorVo);

}
