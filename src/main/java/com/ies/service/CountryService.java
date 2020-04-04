package com.ies.service;

import com.ies.domain.Country;
import com.ies.utils.DataGridView;
import com.ies.vo.CountryVo;

import java.util.List;

public interface CountryService {
    DataGridView queryAllCountry(CountryVo countryVo);

    void addCountry(CountryVo countryVo);

    Country queryCountryById(Integer id);

    void updateCountry(CountryVo countryvo);

    void deleteCountry(Integer id);

    void deleteBatchRole(Integer[] ids);

    List<Country> selectAll();

}
