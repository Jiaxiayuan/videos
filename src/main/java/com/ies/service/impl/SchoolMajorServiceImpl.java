package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.*;
import com.ies.mapper.MajorMapper;
import com.ies.mapper.SchoolMajorMapper;
import com.ies.mapper.SchoolMapper;
import com.ies.service.SchoolMajorService;
import com.ies.utils.DataGridView;
import com.ies.vo.SchoolMajorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolMajorServiceImpl implements SchoolMajorService {
    @Autowired
    private SchoolMajorMapper schoolMajorMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private MajorMapper majorMapper;


    @Override
    public DataGridView queryAllSchoolMajor(SchoolMajorVo schoolMajorVo) {
        SchoolMajorExample example = new SchoolMajorExample();
        SchoolMajorExample.Criteria criteria = example.createCriteria();
        if(schoolMajorVo.getYear()!=null){
            if(!schoolMajorVo.getYear().equals("")){
                criteria.andYearEqualTo(schoolMajorVo.getYear());
            }
        }
        if(schoolMajorVo.getSchoolid()!=null){
            if(schoolMajorVo.getSchoolid()!=0){
                criteria.andSchoolidEqualTo(schoolMajorVo.getSchoolid());
            }
        }
        if(schoolMajorVo.getCountryid()!=null){
            if(schoolMajorVo.getCountryid()!=0){
                SchoolExample example1 = new SchoolExample();
                SchoolExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andCountryidEqualTo(schoolMajorVo.getCountryid());
                if(schoolMajorVo.getCityid()!=null){
                    if(schoolMajorVo.getCityid()!=0){
                        criteria1.andCityidEqualTo(schoolMajorVo.getCityid());
                    }
                }
                List<School> schools = schoolMapper.selectByExample(example1);
                List<Integer> sid = new ArrayList<>();
                if(schools.size()>=1){
                    for(School school:schools){
                        sid.add(school.getId());
                    }
                }else{
                    sid.add(0);
                }
                criteria.andSchoolidIn(sid);

            }
        }
        Page<Object> page = PageHelper.startPage(schoolMajorVo.getPage(),schoolMajorVo.getLimit());
        List<SchoolMajor> list = schoolMajorMapper.selectByExample(example);
        for(SchoolMajor schoolMajor:list){
            if(schoolMajor.getSchoolid()!=null){
                School school = schoolMapper.selectByPrimaryKey(schoolMajor.getSchoolid());
                schoolMajor.setSchoolname(school.getName());
                schoolMajor.setCountryid(school.getCountryid());
                schoolMajor.setCityid(school.getCityid());
            }
            if(schoolMajor.getMajorid()!=null){
                Major major = majorMapper.selectByPrimaryKey(schoolMajor.getMajorid());
                schoolMajor.setMajorname(major.getName());
            }
        }

        return new DataGridView(page.getTotal(),list);
    }

    @Override
    public void addSchoolMajor(SchoolMajorVo schoolMajorVo) {
        schoolMajorMapper.insert(schoolMajorVo);
    }

    @Override
    public SchoolMajor querySchoolMajorById(Integer id) {
        return schoolMajorMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateSchoolMajor(SchoolMajorVo schoolMajorvo) {
        schoolMajorMapper.updateByPrimaryKey(schoolMajorvo);
    }

    @Override
    public void deleteSchoolMajor(Integer id) {
        schoolMajorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer id:ids){
            deleteSchoolMajor(id);
        }
    }

    @Override
    public List<SchoolMajor> selectBySchool(SchoolMajorVo schoolMajorVo) {
        SchoolMajorExample example = new SchoolMajorExample();
        example.createCriteria().andSchoolidEqualTo(schoolMajorVo.getSchoolid());
        return schoolMajorMapper.selectByExample(example);
    }
}
