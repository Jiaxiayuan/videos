package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.*;
import com.ies.mapper.*;
import com.ies.service.EnrollService;
import com.ies.utils.DataGridView;
import com.ies.utils.WebUtils;
import com.ies.vo.EnrollVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EnrollServiceImpl implements EnrollService {
    @Autowired
    private EnrollMapper enrollMapper;
    @Autowired
    private SchoolMajorMapper schoolMajorMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Override
    public void addEnroll(Integer id) {
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        StudentExample example = new StudentExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<Student> students = studentMapper.selectByExample(example);
        SchoolMajor schoolMajor = schoolMajorMapper.selectByPrimaryKey(id);
        Enroll enroll = new Enroll();
        enroll.setSchoolid(schoolMajor.getSchoolid());
        enroll.setMajorid(schoolMajor.getId());
        enroll.setStudentid(students.get(0).getId());
        enroll.setCreatedate(new Date());
        enrollMapper.insert(enroll);
    }

    @Override
    public DataGridView queryAllEnroll(EnrollVo enrollVo) {

        User user = (User) WebUtils.getHttpSession().getAttribute("user");

        EnrollExample example = new EnrollExample();
        EnrollExample.Criteria criteria = example.createCriteria();
        if(enrollVo.getStartTime()!=null){
            criteria.andCreatedateGreaterThanOrEqualTo(enrollVo.getStartTime());
        }
        if(enrollVo.getEndTime()!=null){
            criteria.andCreatedateLessThanOrEqualTo(enrollVo.getEndTime());
        }
        if(user.getType()==2){
            StudentExample example1 = new StudentExample();
            example1.createCriteria().andUsernameEqualTo(user.getUsername());
            List<Student> students = studentMapper.selectByExample(example1);
            Student student = students.get(0);
            criteria.andStudentidEqualTo(student.getId());
        }

        Page<Object> page = PageHelper.startPage(enrollVo.getPage(),enrollVo.getLimit());
        List<Enroll> cars = enrollMapper.selectByExample(example);
        for(Enroll enroll:cars){
            SchoolMajor schoolMajor = schoolMajorMapper.selectByPrimaryKey(enroll.getMajorid());
            Major major = majorMapper.selectByPrimaryKey(schoolMajor.getMajorid());
            enroll.setMajorname(major.getName());
            School school = schoolMapper.selectByPrimaryKey(schoolMajor.getSchoolid());
            enroll.setSchoolname(school.getName());
            Student student = studentMapper.selectByPrimaryKey(enroll.getStudentid());
            enroll.setStudentname(student.getName());
        }
        return new DataGridView(page.getTotal(),cars);
    }

    @Override
    public void deleteEnroll(Integer id) {
        enrollMapper.deleteByPrimaryKey(id);

    }
}
