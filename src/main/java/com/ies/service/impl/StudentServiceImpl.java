package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.*;
import com.ies.mapper.CountryMapper;
import com.ies.mapper.StudentMapper;
import com.ies.mapper.UserMapper;
import com.ies.service.StudentService;
import com.ies.utils.DataGridView;
import com.ies.utils.WebUtils;
import com.ies.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public DataGridView queryAllStudent(StudentVo studentVo) {
        User user = (User) WebUtils.getHttpSession().getAttribute("user");

        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        if(studentVo.getName()!=null){
            criteria.andNameLike("%"+studentVo.getName()+"%");
        }
        if(user.getType()==2){
            criteria.andUsernameEqualTo(user.getUsername());
        }
        Page<Object> page = PageHelper.startPage(studentVo.getPage(),studentVo.getLimit());
        List<Student> list = studentMapper.selectByExample(example);
        for(Student student:list){
           if(student.getCountryid()!=null){
               Country country = countryMapper.selectByPrimaryKey(student.getCountryid());
               student.setCountryname(country.getName());
           }
        }


        return new DataGridView(page.getTotal(),list);
    }

    @Override
    public void addStudent(StudentVo studentVo) {
        studentMapper.insert(studentVo);
        User user = new User();
        user.setUsername(studentVo.getUsername());
        user.setPassword(studentVo.getPassword());
        user.setType(2);
        userMapper.insert(user);
    }

    @Override
    public Student queryStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateStudent(StudentVo studentvo) {
        studentMapper.updateByPrimaryKey(studentvo);
        UserCriteria example = new UserCriteria();
        example.createCriteria().andUsernameEqualTo(studentvo.getUsername());
        List<User> users = userMapper.selectByExample(example);
        User user = users.get(0);
        user.setPassword(studentvo.getPassword());
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteStudent(Integer id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        UserCriteria example = new UserCriteria();
        example.createCriteria().andUsernameEqualTo(student.getUsername());
        userMapper.deleteByExample(example);
        studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer id:ids){
            deleteStudent(id);
        }
    }

    @Override
    public List<Student> getByname(StudentVo studentVo) {
        StudentExample example = new StudentExample();
        example.createCriteria().andUsernameEqualTo(studentVo.getUsername());
        return studentMapper.selectByExample(example);
    }
}
