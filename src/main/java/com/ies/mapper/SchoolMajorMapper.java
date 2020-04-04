package com.ies.mapper;

import com.ies.domain.SchoolMajor;
import com.ies.domain.SchoolMajorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolMajorMapper {
    int countByExample(SchoolMajorExample example);

    int deleteByExample(SchoolMajorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchoolMajor record);

    int insertSelective(SchoolMajor record);

    List<SchoolMajor> selectByExample(SchoolMajorExample example);

    SchoolMajor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchoolMajor record, @Param("example") SchoolMajorExample example);

    int updateByExample(@Param("record") SchoolMajor record, @Param("example") SchoolMajorExample example);

    int updateByPrimaryKeySelective(SchoolMajor record);

    int updateByPrimaryKey(SchoolMajor record);
}