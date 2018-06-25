package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TSysTimerJobSftp;
import com.gasq.bdp.logn.model.TSysTimerJobSftpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysTimerJobSftpMapper {
    long countByExample(TSysTimerJobSftpExample example);

    int deleteByExample(TSysTimerJobSftpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysTimerJobSftp record);

    int insertSelective(TSysTimerJobSftp record);

    List<TSysTimerJobSftp> selectByExampleWithRowbounds(TSysTimerJobSftpExample example, RowBounds rowBounds);

    List<TSysTimerJobSftp> selectByExample(TSysTimerJobSftpExample example);

    TSysTimerJobSftp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysTimerJobSftp record, @Param("example") TSysTimerJobSftpExample example);

    int updateByExample(@Param("record") TSysTimerJobSftp record, @Param("example") TSysTimerJobSftpExample example);

    int updateByPrimaryKeySelective(TSysTimerJobSftp record);

    int updateByPrimaryKey(TSysTimerJobSftp record);
}