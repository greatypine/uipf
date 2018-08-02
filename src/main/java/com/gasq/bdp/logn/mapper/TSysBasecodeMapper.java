package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysBasecode;
import com.gasq.bdp.logn.model.TSysBasecodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysBasecodeMapper {
    long countByExample(TSysBasecodeExample example);

    int deleteByExample(TSysBasecodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysBasecode record);

    int insertSelective(TSysBasecode record);

    List<TSysBasecode> selectByExampleWithRowbounds(TSysBasecodeExample example, RowBounds rowBounds);

    List<TSysBasecode> selectByExample(TSysBasecodeExample example);

    TSysBasecode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysBasecode record, @Param("example") TSysBasecodeExample example);

    int updateByExample(@Param("record") TSysBasecode record, @Param("example") TSysBasecodeExample example);

    int updateByPrimaryKeySelective(TSysBasecode record);

    int updateByPrimaryKey(TSysBasecode record);

	Integer getNextType(@Param("companyid") Integer companyid);
}