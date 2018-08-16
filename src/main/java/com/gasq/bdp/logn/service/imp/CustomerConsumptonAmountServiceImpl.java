/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TCustomerConsumptonAmountMapper;
import com.gasq.bdp.logn.mapper.TLtnCustomerMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TConsumptonProjectExample;
import com.gasq.bdp.logn.model.TCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TCustomerConsumptonAmountExample;
import com.gasq.bdp.logn.service.CustomerConsumptonAmountService;
import com.gasq.bdp.logn.service.TConsumptonProjectService;
import com.gasq.bdp.logn.service.TInventoryLogService;
import com.gasq.bdp.logn.utils.CommonUtils;
import com.gasq.bdp.logn.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Ju_weigang
 * @时间 2018年1月22日下午3:59:06
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 
 */
@Service
public class CustomerConsumptonAmountServiceImpl implements CustomerConsumptonAmountService {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TCustomerConsumptonAmountMapper customerConsumptonAmountMapper;
	@Autowired TLtnCustomerMapper customerMapper;
	@Autowired TConsumptonProjectService consumptonProjectService;
	@Autowired TInventoryLogService inventoryLogService;

	@Override
	public long countByExample(TCustomerConsumptonAmountExample example) {
		return customerConsumptonAmountMapper.countByExample(example);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int deleteByExample(TCustomerConsumptonAmountExample example) {
		try {
			List<TCustomerConsumptonAmount> list = customerConsumptonAmountMapper.selectByExample(example);
			for (TCustomerConsumptonAmount TCustomerConsumptonAmount : list) {
				this.delete(TCustomerConsumptonAmount.getId());
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
			Boolean b = customerConsumptonAmountMapper.deleteByPrimaryKey(id)>0?true:false;
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】成功删除客户预约信息并添加预约关闭日志！");
			return b ;
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除订单消费金额操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return false;
	}
	
	@Override
	public int insertSelective(TCustomerConsumptonAmount record) {
		return customerConsumptonAmountMapper.insertSelective(record);
	}

	@Override
	public List<TCustomerConsumptonAmount> selectByExample(TCustomerConsumptonAmountExample example) {
		return customerConsumptonAmountMapper.selectByExample(example);
	}

	@Override
	public TCustomerConsumptonAmount selectByPrimaryKey(Integer id) {
		return customerConsumptonAmountMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TCustomerConsumptonAmount bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		List<Map<String,Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		bean.setPage(start);
		bean.setRows(number);
		bean.setCompanyid(SystemUserInfo.getSystemUser().getUser().getCompanyid());
		PageHelper.startPage(start, number);
		List<Map<String, Object>> listmaps = customerConsumptonAmountMapper.queryPagingList(bean);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】查询消费订单金额信息列表完成！查询条数："+pageinfo.getTotal()+",查询参数："+CommonUtils.bean2Json(bean));
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public TCustomerConsumptonAmount saveOrUpdate(TCustomerConsumptonAmount bean) {
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
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】更新订单消费金额操作成功！更新信息为："+CommonUtils.bean2Json(bean));
			}else {
				bean.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
				bean.setCreatetime(DateUtil.getSysCurrentDate());
				customerConsumptonAmountMapper.insertSelective(bean);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加订单消费金额操作成功！更新信息为："+CommonUtils.bean2Json(bean));
			}
			return bean;
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】插入或更新订单消费金额操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return null;
	}

}
