package com.ies.service;

import com.ies.domain.Student;
import com.ies.utils.DataGridView;
import com.ies.vo.StudentVo;

import java.util.List;

public interface StudentService {
    DataGridView queryAllStudent(StudentVo studentVo);

    void addStudent(StudentVo studentVo);

    Student queryStudentById(Integer id);

    void updateStudent(StudentVo studentvo);

    void deleteStudent(Integer id);

    void deleteBatchRole(Integer[] ids);

    List<Student> getByname(StudentVo studentVo);
}
