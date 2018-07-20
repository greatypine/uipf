package com.gasq.bdp.logn.service;

import java.util.List;

import com.gasq.bdp.logn.model.TCustomerImages;
import com.gasq.bdp.logn.model.TCustomerImagesExample;

public interface TCustomerImagesService {
    long countByExample(TCustomerImagesExample example);

    List<TCustomerImages> selectByExample(TCustomerImages bean);

	boolean saveOrUpdate(TCustomerImages bean);

	boolean delete(int id);

	TCustomerImages selectByPrimaryKey(Integer id);

	Integer getCount(TCustomerImages bean);
}