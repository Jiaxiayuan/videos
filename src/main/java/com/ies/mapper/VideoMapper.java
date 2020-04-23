package com.ies.mapper;

import com.ies.domain.Video;
import com.ies.domain.VideoCriteria;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface VideoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    int countByExample(VideoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    int deleteByExample(VideoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    int insert(Video record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    int insertSelective(Video record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    List<Video> selectByExampleWithRowbounds(VideoCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    List<Video> selectByExample(VideoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    Video selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    int updateByExampleSelective(@Param("record") Video record, @Param("example") VideoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    int updateByExample(@Param("record") Video record, @Param("example") VideoCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 13:24:27 CST 2020
     */
    int updateByPrimaryKeySelective(Video record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Tue Mar 31 1V3:24:27 CST 2020
     */
    int updateByPrimaryKey(Video record);

    Video selectRandomVideo1(@Param("exceptIds") Integer[] exceptIds);

    Video selectRandomVideo2(@Param("nameSet") Set<Integer> nameSet, @Param("exceptIds") Integer[] exceptIds);

    Video selectRandomVideo3(@Param("videoType") String videoType, @Param("exceptIds") Integer[] exceptIds);
}