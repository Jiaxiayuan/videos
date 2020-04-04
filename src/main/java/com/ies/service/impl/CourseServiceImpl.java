package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.Course;
import com.ies.domain.CourseExample;
import com.ies.mapper.CourseMapper;
import com.ies.service.CourseService;
import com.ies.utils.DataGridView;
import com.ies.vo.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public DataGridView queryAllCourse(CourseVo courseVo) {
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        if(courseVo.getName()!=null){
            criteria.andNameLike("%"+courseVo.getName()+"%");
        }
        Page<Object> page = PageHelper.startPage(courseVo.getPage(),courseVo.getLimit());
        List<Course> cars = courseMapper.selectByExample(example);
        return new DataGridView(page.getTotal(),cars);
    }

    @Override
    public void addCourse(CourseVo courseVo) {
        courseMapper.insert(courseVo);
    }

    @Override
    public Course queryCourseById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateCourse(CourseVo coursevo) {
        courseMapper.updateByPrimaryKey(coursevo);
    }

    @Override
    public void deleteCourse(Integer id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer id:ids){
            deleteCourse(id);
        }
    }

    @Override
    public List<Course> selectAll() {
        CourseExample example = new CourseExample();
        return courseMapper.selectByExample(example);
    }

}
