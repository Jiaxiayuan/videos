package com.ies.service;

import com.ies.domain.School;
import com.ies.utils.DataGridView;
import com.ies.vo.SchoolVo;

import java.util.List;

public interface SchoolService {
    DataGridView queryAllSchool(SchoolVo schoolVo);

    void addSchool(SchoolVo schoolVo);

    School querySchoolById(Integer id);

    void updateSchool(SchoolVo schoolvo);

    void deleteSchool(Integer id);

    void deleteBatchRole(Integer[] ids);

    List<School> selectAll();

    List<School> selectByCity(SchoolVo schoolVo);
}
