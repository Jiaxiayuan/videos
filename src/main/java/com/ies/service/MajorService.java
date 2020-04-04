package com.ies.service;

import com.ies.domain.Major;
import com.ies.utils.DataGridView;
import com.ies.vo.MajorVo;

import java.util.List;

public interface MajorService {
    DataGridView queryAllMajor(MajorVo majorVo);

    void addMajor(MajorVo majorVo);

    Major queryMajorById(Integer id);

    void updateMajor(MajorVo majorvo);

    void deleteMajor(Integer id);

    void deleteBatchRole(Integer[] ids);

    List<Major> selectAll();

}
