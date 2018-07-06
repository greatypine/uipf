package com.gasq.bdp.logn.mapper;

import com.gasq.bdp.logn.model.TCustomerComment;
import com.gasq.bdp.logn.model.TCustomerCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TCustomerCommentMapper {
    long countByExample(TCustomerCommentExample example);

    int deleteByExample(TCustomerCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerComment record);

    int insertSelective(TCustomerComment record);

    List<TCustomerComment> selectByExampleWithRowbounds(TCustomerCommentExample example, RowBounds rowBounds);

    List<TCustomerComment> selectByExample(TCustomerCommentExample example);

    TCustomerComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCustomerComment record, @Param("example") TCustomerCommentExample example);

    int updateByExample(@Param("record") TCustomerComment record, @Param("example") TCustomerCommentExample example);

    int updateByPrimaryKeySelective(TCustomerComment record);

    int updateByPrimaryKey(TCustomerComment record);
}