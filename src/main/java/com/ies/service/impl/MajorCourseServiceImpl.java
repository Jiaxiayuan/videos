package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.*;
import com.ies.mapper.*;
import com.ies.service.MajorCourseService;
import com.ies.utils.DataGridView;
import com.ies.vo.MajorCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorCourseServiceImpl implements MajorCourseService {
    @Autowired
    private MajorCourseMapper majorCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private SchoolMajorMapper schoolMajorMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private SchoolMapper schoolMapper;


    @Override
    public DataGridView queryAllMajorCourse(MajorCourseVo majorCourseVo) {
        MajorCourseExample example = new MajorCourseExample();
        MajorCourseExample.Criteria criteria = example.createCriteria();
        //如果有学校专业id时
        if(majorCourseVo.getSchoolmajorid()!=null){
            if(majorCourseVo.getSchoolmajorid()!=0){
                criteria.andSchoolmajoridEqualTo(majorCourseVo.getSchoolmajorid());
            }
            //如果只有学校id时
        }else if(majorCourseVo.getSchoolid()!=null){
            if(majorCourseVo.getSchoolid()!=0){
                SchoolMajorExample schoolMajorExample = new SchoolMajorExample();
                schoolMajorExample.createCriteria().andSchoolidEqualTo(majorCourseVo.getSchoolid());
                List<SchoolMajor> schoolMajors = schoolMajorMapper.selectByExample(schoolMajorExample);
                if(schoolMajors.size()>=1){
                    List<Integer> sl1 = new ArrayList<>();
                    for(SchoolMajor schoolMajor:schoolMajors){
                        sl1.add(schoolMajor.getId());
                    }
                    criteria.andSchoolmajoridIn(sl1);
                }else{
                    criteria.andSchoolmajoridEqualTo(0);
                }

            }
        }

        //只有国家或城市搜索时
        if(majorCourseVo.getCountryid()!=null){
            if(majorCourseVo.getCountryid()!=0){
                SchoolExample schoolExample = new SchoolExample();
                SchoolExample.Criteria criteria1 = schoolExample.createCriteria();
                criteria1.andCountryidEqualTo(majorCourseVo.getCountryid());
                if(majorCourseVo.getCityid()!=null){
                    if(majorCourseVo.getCityid()!=0){
                        criteria1.andCityidEqualTo(majorCourseVo.getCityid());
                    }
                }
                List<School> schools = schoolMapper.selectByExample(schoolExample);
                if(schools.size()>=1){
                    List<Integer> sl = new ArrayList<>();
                    //查询学校专业
                    for(School s :schools){
                        sl.add(s.getId());
                    }
                    SchoolMajorExample schoolMajorExample = new SchoolMajorExample();
                    SchoolMajorExample.Criteria criteria2 = schoolMajorExample.createCriteria();
                    criteria2.andSchoolidIn(sl);
                    List<SchoolMajor> schoolMajors = schoolMajorMapper.selectByExample(schoolMajorExample);
                    if(schoolMajors.size()>=1){
                        List<Integer> sl1 = new ArrayList<>();
                        for(SchoolMajor schoolMajor:schoolMajors){
                            sl1.add(schoolMajor.getId());
                        }
                        criteria.andSchoolmajoridIn(sl1);
                    }else {
                        criteria.andSchoolmajoridEqualTo(0);
                    }

                }else{
                    criteria.andSchoolmajoridEqualTo(0);
                }


            }
        }

        Page<Object> page = PageHelper.startPage(majorCourseVo.getPage(),majorCourseVo.getLimit());
        List<MajorCourse> list = majorCourseMapper.selectByExample(example);
        for(MajorCourse majorCourse:list){
            if(majorCourse.getCourseid()!=null){
                Course course = courseMapper.selectByPrimaryKey(majorCourse.getCourseid());
                majorCourse.setCoursename(course.getName());
            }
            if(majorCourse.getSchoolmajorid()!=null){
                SchoolMajor schoolMajor = schoolMajorMapper.selectByPrimaryKey(majorCourse.getSchoolmajorid());
                Major major = majorMapper.selectByPrimaryKey(schoolMajor.getMajorid());
                majorCourse.setMajorname(major.getName());
                School school = schoolMapper.selectByPrimaryKey(schoolMajor.getSchoolid());
                majorCourse.setCountryid(school.getCountryid());
                majorCourse.setCityid(school.getCityid());
                majorCourse.setSchoolid(schoolMajor.getSchoolid());
            }
        }


        return new DataGridView(page.getTotal(),list);
    }

    @Override
    public void addMajorCourse(MajorCourseVo majorCourseVo) {
        majorCourseMapper.insert(majorCourseVo);
    }

    @Override
    public MajorCourse queryMajorCourseById(Integer id) {
        return majorCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateMajorCourse(MajorCourseVo majorCoursevo) {
        majorCourseMapper.updateByPrimaryKey(majorCoursevo);
    }

    @Override
    public void deleteMajorCourse(Integer id) {
        majorCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer id:ids){
            deleteMajorCourse(id);
        }
    }
}
