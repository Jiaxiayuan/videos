package com.ies.mapper;

import com.ies.domain.Enroll;
import com.ies.domain.EnrollExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnrollMapper {
    int countByExample(EnrollExample example);

    int deleteByExample(EnrollExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Enroll record);

    int insertSelective(Enroll record);

    List<Enroll> selectByExample(EnrollExample example);

    Enroll selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Enroll record, @Param("example") EnrollExample example);

    int updateByExample(@Param("record") Enroll record, @Param("example") EnrollExample example);

    int updateByPrimaryKeySelective(Enroll record);

    int updateByPrimaryKey(Enroll record);
}