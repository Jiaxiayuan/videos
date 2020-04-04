package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.City;
import com.ies.domain.CityExample;
import com.ies.domain.Country;
import com.ies.mapper.CityMapper;
import com.ies.mapper.CountryMapper;
import com.ies.service.CityService;
import com.ies.utils.DataGridView;
import com.ies.vo.CityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CountryMapper countryMapper;

    @Override
    public DataGridView queryAllCity(CityVo cityVo) {
        CityExample example = new CityExample();
        CityExample.Criteria criteria = example.createCriteria();
        if(cityVo.getName()!=null){
            if(!cityVo.getName().equals("")){
                criteria.andNameLike("%"+cityVo.getName()+"%");
            }
        }
        if(cityVo.getCountryid()!=null){
            if(cityVo.getCountryid()!=0){
                criteria.andCountryidEqualTo(cityVo.getCountryid());
            }
        }
        Page<Object> page = PageHelper.startPage(cityVo.getPage(),cityVo.getLimit());
        List<City> list = cityMapper.selectByExample(example);
        for(City city:list){
            if(city.getCountryid()!=null){
                Country country = countryMapper.selectByPrimaryKey(city.getCountryid());
                city.setCountryname(country.getName());
            }
        }

        return new DataGridView(page.getTotal(),list);
    }

    @Override
    public void addCity(CityVo cityVo) {
        cityMapper.insert(cityVo);
    }

    @Override
    public City queryCityById(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateCity(CityVo cityvo) {
        cityMapper.updateByPrimaryKey(cityvo);
    }

    @Override
    public void deleteCity(Integer id) {
        cityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer id:ids){
            deleteCity(id);
        }
    }

    @Override
    public List<City> selectAll(CityVo cityVo) {
        CityExample example = new CityExample();
        example.createCriteria().andCountryidEqualTo(cityVo.getCountryid());
        return cityMapper.selectByExample(example);
    }
}
