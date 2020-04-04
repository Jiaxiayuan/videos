package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.Country;
import com.ies.domain.CountryExample;
import com.ies.mapper.CountryMapper;
import com.ies.service.CountryService;
import com.ies.utils.DataGridView;
import com.ies.vo.CountryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryMapper countryMapper;

    @Override
    public DataGridView queryAllCountry(CountryVo countryVo) {
        CountryExample example = new CountryExample();
        CountryExample.Criteria criteria = example.createCriteria();
        if(countryVo.getName()!=null){
            criteria.andNameLike("%"+countryVo.getName()+"%");
        }
        Page<Object> page = PageHelper.startPage(countryVo.getPage(),countryVo.getLimit());
        List<Country> cars = countryMapper.selectByExample(example);
        return new DataGridView(page.getTotal(),cars);
    }

    @Override
    public void addCountry(CountryVo countryVo) {
        countryMapper.insert(countryVo);
    }

    @Override
    public Country queryCountryById(Integer id) {
        return countryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateCountry(CountryVo countryvo) {
        countryMapper.updateByPrimaryKey(countryvo);
    }

    @Override
    public void deleteCountry(Integer id) {
        countryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer id:ids){
            deleteCountry(id);
        }
    }

    @Override
    public List<Country> selectAll() {
        CountryExample example = new CountryExample();
        return countryMapper.selectByExample(example);
    }

}
