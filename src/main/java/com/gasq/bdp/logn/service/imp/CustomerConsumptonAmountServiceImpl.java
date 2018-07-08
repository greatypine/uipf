/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TLtnCustomerConsumptonAmountMapper;
import com.gasq.bdp.logn.mapper.TLtnCustomerMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TConsumptonProjectExample;
import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TLtnCustomerConsumptonAmountExample;
import com.gasq.bdp.logn.service.CustomerConsumptonAmountService;
import com.gasq.bdp.logn.service.TConsumptonProjectService;
import com.gasq.bdp.logn.service.TInventoryLogService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author Ju_weigang
 * @时间 2018年1月22日下午3:59:06
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class CustomerConsumptonAmountServiceImpl implements CustomerConsumptonAmountService {
	@Autowired TLtnCustomerConsumptonAmountMapper customerConsumptonAmountMapper;
	@Autowired TLtnCustomerMapper customerMapper;
	@Autowired TConsumptonProjectService consumptonProjectService;
	@Autowired TInventoryLogService inventoryLogService;

	@Override
	public long countByExample(TLtnCustomerConsumptonAmountExample example) {
		return customerConsumptonAmountMapper.countByExample(example);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int deleteByExample(TLtnCustomerConsumptonAmountExample example) {
		try {
			List<TLtnCustomerConsumptonAmount> list = customerConsumptonAmountMapper.selectByExample(example);
			for (TLtnCustomerConsumptonAmount tLtnCustomerConsumptonAmount : list) {
				this.delete(tLtnCustomerConsumptonAmount.getId());
			}
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return 0;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean delete(int id) {
		try {
			TConsumptonProjectExample cpe = new TConsumptonProjectExample();
			cpe.createCriteria().andConsumptonAmountIdEqualTo(id);
			consumptonProjectService.deleteByExample(cpe);
			return customerConsumptonAmountMapper.deleteByPrimaryKey(id)>0?true:false;
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}
	
	@Override
	public int insertSelective(TLtnCustomerConsumptonAmount record) {
		return customerConsumptonAmountMapper.insertSelective(record);
	}

	@Override
	public List<TLtnCustomerConsumptonAmount> selectByExample(TLtnCustomerConsumptonAmountExample example) {
		return customerConsumptonAmountMapper.selectByExample(example);
	}

	@Override
	public TLtnCustomerConsumptonAmount selectByPrimaryKey(Integer id) {
		return customerConsumptonAmountMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TLtnCustomerConsumptonAmount bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		List<Map<String,Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		bean.setPage(start);
		bean.setRows(number);
		bean.setCompanyid(SystemUserInfo.getSystemUser().getUser().getCompanyid());
		list = customerConsumptonAmountMapper.queryPagingList(bean);
		if(list==null || list.size()<=0)list = new ArrayList<Map<String,Object>>(); 
		Integer count = customerConsumptonAmountMapper.countByBean(bean);
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public TLtnCustomerConsumptonAmount saveOrUpdate(TLtnCustomerConsumptonAmount bean) {
		try {
			
			bean.setUpdatetime(DateUtil.getSysCurrentDate());
			BigDecimal arrears = bean.getArrears();//欠款
			BigDecimal repayment = bean.getRepayment();//还款金额
			BigDecimal movePayment = bean.getMovePayment();//移动支付
			BigDecimal cashIncome = bean.getCashIncome();//现金收入
			BigDecimal creditCashIncome = bean.getCreditCashIncome();//刷卡收入
			if(arrears==null) arrears = new BigDecimal(0);
			if(repayment==null) repayment = new BigDecimal(0);
			if(movePayment==null) movePayment = new BigDecimal(0);
			if(cashIncome==null) cashIncome = new BigDecimal(0);
			if(creditCashIncome==null) creditCashIncome = new BigDecimal(0);
			//总收入：还款金额+移动支付+现金收入+刷卡收入-欠款
			BigDecimal total = repayment.add(movePayment).add(cashIncome).add(creditCashIncome).subtract(arrears);
			BigDecimal discount = new BigDecimal(bean.getDiscount());
			discount = discount.divide(new BigDecimal(100));
			bean.setDiscount(discount.doubleValue());
			bean.setTotalAmount(total);
			if(bean.getId()!=null) {
				bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
				customerConsumptonAmountMapper.updateByPrimaryKeySelective(bean);
			}else {
				bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
				bean.setCreatetime(DateUtil.getSysCurrentDate());
				customerConsumptonAmountMapper.insertSelective(bean);
			}
			return bean;
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return null;
	}

}
