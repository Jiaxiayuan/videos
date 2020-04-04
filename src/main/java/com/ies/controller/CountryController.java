package com.ies.controller;

import com.ies.constast.SysConstast;
import com.ies.domain.Country;
import com.ies.service.CountryService;
import com.ies.utils.AppFileUtils;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.CountryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 国家管理控制器
 */
@RestController
@RequestMapping("country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("loadAllCountry")
    public DataGridView loadAllCountry(CountryVo countryVo){
        return countryService.queryAllCountry(countryVo);
    }
    @RequestMapping("addCountry")
    public ResultObj addCountry(CountryVo countryVo){
        try{
            if(!countryVo.getImgage().equals(SysConstast.DEFAULT_CAR_IMG)){
                String filePath = AppFileUtils.updateFileName(countryVo.getImgage(), SysConstast.FILE_UPLOAD_TEMP);
                countryVo.setImgage(filePath);
            }
            if(countryVo.getId()!=null){
                countryVo.setId(null);
            }
            countryService.addCountry(countryVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateCountry")
    public ResultObj updateCountry(CountryVo countryvo){
        try{
            String countryimg = countryvo.getImgage();
            if (countryimg.endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
                String filePath =AppFileUtils.updateFileName(countryvo.getImgage(), SysConstast.FILE_UPLOAD_TEMP);
                countryvo.setImgage(filePath);
                //把原来的删除
                Country country = this.countryService.queryCountryById(countryvo.getId());
                AppFileUtils.removeFileByPath(country.getImgage());
            }
            countryService.updateCountry(countryvo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteCountry")
    public ResultObj deleteCountry(CountryVo countryvo){
        try{
            countryService.deleteCountry(countryvo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("deleteBatchCountry")
    public ResultObj deleteBatchCountry(CountryVo countryvo){
        try{
            countryService.deleteBatchRole(countryvo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 显示树加载
     */
    @RequestMapping("loadAllCountryJson")
    public DataGridView loadAllCountryJson(){
        List<Country> list = this.countryService.selectAll();
        return new DataGridView(list);
    }


}
