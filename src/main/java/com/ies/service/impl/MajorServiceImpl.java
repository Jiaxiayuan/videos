package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.domain.Major;
import com.ies.domain.MajorExample;
import com.ies.mapper.MajorMapper;
import com.ies.service.MajorService;
import com.ies.utils.DataGridView;
import com.ies.vo.MajorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    @Override
    public DataGridView queryAllMajor(MajorVo majorVo) {
        MajorExample example = new MajorExample();
        MajorExample.Criteria criteria = example.createCriteria();
        if(majorVo.getName()!=null){
            criteria.andNameLike("%"+majorVo.getName()+"%");
        }
        Page<Object> page = PageHelper.startPage(majorVo.getPage(),majorVo.getLimit());
        List<Major> cars = majorMapper.selectByExample(example);
        return new DataGridView(page.getTotal(),cars);
    }

    @Override
    public void addMajor(MajorVo majorVo) {
        majorMapper.insert(majorVo);
    }

    @Override
    public Major queryMajorById(Integer id) {
        return majorMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateMajor(MajorVo majorvo) {
        majorMapper.updateByPrimaryKey(majorvo);
    }

    @Override
    public void deleteMajor(Integer id) {
        majorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer id:ids){
            deleteMajor(id);
        }
    }

    @Override
    public List<Major> selectAll() {
        MajorExample example = new MajorExample();
        return majorMapper.selectByExample(example);
    }


}
