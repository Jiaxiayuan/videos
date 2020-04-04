package com.ies.service;

import com.ies.domain.City;
import com.ies.utils.DataGridView;
import com.ies.vo.CityVo;

import java.util.List;

public interface CityService {
    DataGridView queryAllCity(CityVo cityVo);

    void addCity(CityVo cityVo);

    City queryCityById(Integer id);

    void updateCity(CityVo cityvo);

    void deleteCity(Integer id);

    void deleteBatchRole(Integer[] ids);

    List<City> selectAll(CityVo cityVo);


}
