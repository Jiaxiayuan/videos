package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.*;
import com.ies.mapper.CityMapper;
import com.ies.mapper.CountryMapper;
import com.ies.mapper.SchoolMapper;
import com.ies.service.SchoolService;
import com.ies.utils.DataGridView;
import com.ies.vo.SchoolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private CityMapper cityMapper;

    @Override
    public DataGridView queryAllSchool(SchoolVo schoolVo) {
        if(schoolVo.getType()!=null){
            schoolVo.setType(schoolVo.getType().replace(",",""));
        }
        SchoolExample example = new SchoolExample();
        SchoolExample.Criteria criteria = example.createCriteria();
        if(schoolVo.getName()!=null){
            if(!schoolVo.getName().equals("")) {
                criteria.andNameLike("%" + schoolVo.getName() + "%");
            }
        }
        if(schoolVo.getCountryid()!=null){
            if(schoolVo.getCountryid()!=0){
                criteria.andCountryidEqualTo(schoolVo.getCountryid());
            }
        }
        CountryExample schoolExample = new CountryExample();
        CountryExample.Criteria criteria1 = schoolExample.createCriteria();
        Boolean ss=false;
        if(schoolVo.getCompetitiveness()!=null){
            if(!schoolVo.getCompetitiveness().equals("")) {
                criteria1.andCompetitivenessEqualTo(schoolVo.getCompetitiveness());
                ss=true;
            }
        }
        if(schoolVo.getVisarequired()!=null){
            if(!schoolVo.getVisarequired().equals("")) {
                criteria1.andVisarequiredEqualTo(schoolVo.getVisarequired());
                ss=true;
            }
        }
        if(ss){
            List<Country> countries = countryMapper.selectByExample(schoolExample);
            List<Integer> il = new ArrayList<>();
            if(countries.size()>=1){
               for(Country country:countries){
                   il.add(country.getId());
                }
            }else{
                il.add(0);
            }
            criteria.andCountryidIn(il);
        }

        if(schoolVo.getCitytype()!=null){
            if(!schoolVo.getCitytype().equals("")) {
                CityExample cityExample = new CityExample();
                cityExample.createCriteria().andTypeEqualTo(schoolVo.getCitytype());
                List<City> cities = cityMapper.selectByExample(cityExample);
                List<Integer> il = new ArrayList<>();
                if(cities.size()>=1){
                    for(City city: cities){
                        il.add(city.getId());
                    }

                }else{
                    il.add(0);
                }
                criteria.andCityidIn(il);
            }
        }

        if(schoolVo.getCityid()!=null){
            if(schoolVo.getCityid()!=0){
                criteria.andCityidEqualTo(schoolVo.getCityid());
            }
        }
        if(schoolVo.getType()!=null){
            if(!schoolVo.getType().equals("")) {
                criteria.andTypeEqualTo(schoolVo.getType());
            }

        }
        if(schoolVo.getDorm()!=null){
            if(!schoolVo.getDorm().equals("")) {
                criteria.andDormEqualTo(schoolVo.getDorm());
            }

        }
        if(schoolVo.getVacation()!=null){
            if(!schoolVo.getVacation().equals("")) {
                criteria.andVacationEqualTo(schoolVo.getVacation());
            }
        }
        Page<Object> page = PageHelper.startPage(schoolVo.getPage(),schoolVo.getLimit());
        List<School> list = schoolMapper.selectByExample(example);
        for(School school:list){
            if(school.getCountryid()!=null){
                Country country = countryMapper.selectByPrimaryKey(school.getCountryid());
                school.setCountryname(country.getName());
            }
            if(school.getCityid()!=null){
                City city = cityMapper.selectByPrimaryKey(school.getCityid());
                school.setCityname(city.getName());
            }
        }

        return new DataGridView(page.getTotal(),list);
    }

    @Override
    public void addSchool(SchoolVo schoolVo) {
        schoolMapper.insert(schoolVo);
    }

    @Override
    public School querySchoolById(Integer id) {
        return schoolMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateSchool(SchoolVo schoolvo) {
        schoolMapper.updateByPrimaryKey(schoolvo);
    }

    @Override
    public void deleteSchool(Integer id) {
        schoolMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer id:ids){
            deleteSchool(id);
        }
    }

    @Override
    public List<School> selectAll() {
        SchoolExample example = new SchoolExample();
        return schoolMapper.selectByExample(example);
    }

    @Override
    public List<School> selectByCity(SchoolVo schoolVo) {
        SchoolExample example = new SchoolExample();
        SchoolExample.Criteria criteria = example.createCriteria();
        if(schoolVo.getCityid()!=null){
            criteria.andCityidEqualTo(schoolVo.getCityid());
        }
        return schoolMapper.selectByExample(example);
    }
}
