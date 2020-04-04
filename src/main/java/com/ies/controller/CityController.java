package com.ies.controller;

import com.ies.domain.City;
import com.ies.service.CityService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.CityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 城市管理控制器
 */
@RestController
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("loadAllCity")
    public DataGridView loadAllCity(CityVo cityVo){
        return cityService.queryAllCity(cityVo);
    }

    @RequestMapping("addCity")
    public ResultObj addCity(CityVo cityVo){
        try{
            if(cityVo.getId()!=null){
                cityVo.setId(null);
            }
            cityService.addCity(cityVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateCity")
    public ResultObj updateCity(CityVo cityvo){
        try{
            cityService.updateCity(cityvo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteCity")
    public ResultObj deleteCity(CityVo cityvo){
        try{
            cityService.deleteCity(cityvo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("deleteBatchCity")
    public ResultObj deleteBatchCity(CityVo cityvo){
        try{
            cityService.deleteBatchRole(cityvo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 显示树加载
     */
    @RequestMapping("loadAllCityJson")
    public DataGridView loadAllCityJson(CityVo cityVo){
        List<City> list = this.cityService.selectAll(cityVo);
        return new DataGridView(list);
    }
}
