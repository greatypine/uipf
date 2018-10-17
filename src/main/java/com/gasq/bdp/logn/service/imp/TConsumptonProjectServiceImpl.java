/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TConsumptonProjectMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TConsumptonProject;
import com.gasq.bdp.logn.model.TConsumptonProjectExample;
import com.gasq.bdp.logn.model.TConsumptonProjectExample.Criteria;
import com.gasq.bdp.logn.model.TCustomerConsumptonAmount;
import com.gasq.bdp.logn.model.TInventory;
import com.gasq.bdp.logn.model.TInventoryLog;
import com.gasq.bdp.logn.model.TLtnCustomer;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.service.CustomerConsumptonAmountService;
import com.gasq.bdp.logn.service.CustomerService;
import com.gasq.bdp.logn.service.TConsumptonProjectService;
import com.gasq.bdp.logn.service.TInventoryLogService;
import com.gasq.bdp.logn.service.TInventoryService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日上午10:14:37
 * @remark 
 */
@Service
public class TConsumptonProjectServiceImpl implements TConsumptonProjectService {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TConsumptonProjectMapper consumptonProjectMapper;
	@Autowired TInventoryService inventoryService;
	@Autowired TInventoryLogService inventoryLogService;
	@Autowired CustomerConsumptonAmountService consumptonAmountService;
	@Autowired CustomerService customerService;

	@Override
	public long countByExample(TConsumptonProjectExample example) {
		return consumptonProjectMapper.countByExample(example);
	}
	
	public long countByExample(TConsumptonProject bean) {
		TConsumptonProjectExample example = new TConsumptonProjectExample();
		Criteria c = example.createCriteria();
		if(bean.getConsumptonAmountId()!=null) {
			c.andConsumptonAmountIdEqualTo(bean.getConsumptonAmountId());
		}
		if(bean.getProjectId()!=null) {
			c.andProjectIdEqualTo(bean.getProjectId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		return consumptonProjectMapper.countByExample(example);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean delete(int id) {
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			TConsumptonProject project = consumptonProjectMapper.selectByPrimaryKey(id);
			TInventory bean = inventoryService.selectByPrimaryKey(project.getProjectId());
			TCustomerConsumptonAmount consumptonAmount = consumptonAmountService.selectByPrimaryKey(project.getConsumptonAmountId());
			TLtnCustomer customer = customerService.selectByPrimaryKey(consumptonAmount.getCustomerId());
			BigDecimal dnumbs = project.getNumbs();
			bean.setInventory(bean.getInventory().add(dnumbs));
			inventoryService.saveOrUpdate(bean);
			TInventoryLog ilog = new TInventoryLog(project.getProjectId(),InitProperties.INVENTORY_OPTION_ORDER_BACK,customer.getCustomername(),customer.getUpdatetime(),consumptonAmount.getTotalAmount(),user.getCompanyid(),bean.getInventory(),user.getUsername());
			inventoryLogService.saveOrUpdate(ilog);
			return consumptonProjectMapper.deleteByPrimaryKey(id)>0?true:false;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}

	@Override
	public List<TConsumptonProject> selectByExample(TConsumptonProject bean) {
		TConsumptonProjectExample example = new TConsumptonProjectExample();
		Criteria c = example.createCriteria();
		if(bean.getConsumptonAmountId()!=null) {
			c.andConsumptonAmountIdEqualTo(bean.getConsumptonAmountId());
		}
		if(bean.getProjectId()!=null) {
			c.andProjectIdEqualTo(bean.getProjectId());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		example.setOrderByClause(" id asc ");
		return consumptonProjectMapper.selectByExample(example);
	}

	@Override
	public TConsumptonProject selectByPrimaryKey(Integer id) {
		return consumptonProjectMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TConsumptonProject bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		Map<String, Object> params= new  HashMap<String, Object>();
		if(bean.getConsumptonAmountId()!=null) {
			params.put("consumptonAmountId", bean.getConsumptonAmountId());
		}
		if(bean.getProjectId()!=null) {
			params.put("projectId", bean.getProjectId());
		}
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Map<String, Object>> listmaps = consumptonProjectMapper.queryPagingList(params);
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(listmaps);
		result.clear();
		result.put("rows",listmaps);
		result.put("total",pageinfo.getTotal());
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String,Object> saveOrUpdate(TConsumptonProject bean) throws SchedulerException {
		Map<String,Object> map = new HashMap<>();
		String mess = null;
		try {
			if(bean.getProjectId()==null || bean.getNumbs()==null) {
				mess = "传入的商品ID和商品数量存在为空！";
				logger.info(mess);
				map.put("mess", mess);
				map.put("status", false);
			}
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			Boolean enable = inventoryService.checkInventoryEnable(bean.getProjectId(), bean.getNumbs().doubleValue());
			if(enable) {
				bean.setUpdatetime(DateUtil.getSysCurrentDate());
				TInventory inventory = inventoryService.selectByPrimaryKey(bean.getProjectId());
				TCustomerConsumptonAmount consumptonAmount = consumptonAmountService.selectByPrimaryKey(bean.getConsumptonAmountId());
				TLtnCustomer customer = customerService.selectByPrimaryKey(consumptonAmount.getCustomerId());
				if(bean.getId()!=null) {
					TConsumptonProject cproject = consumptonProjectMapper.selectByPrimaryKey(bean.getId());
					BigDecimal oldusenumbs = cproject.getNumbs();
					/**
					 * 先退货以前的库存
					 */
					TInventoryLog ilog = new TInventoryLog(bean.getProjectId(),InitProperties.INVENTORY_OPTION_ORDER_BACK,customer.getCustomername(),customer.getUpdatetime(),consumptonAmount.getTotalAmount(),user.getCompanyid(),oldusenumbs,user.getUsername());
					inventoryLogService.saveOrUpdate(ilog);
					
					BigDecimal totalnumbs = inventory.getInventory();
					BigDecimal newtotalnumbs = totalnumbs.add(oldusenumbs);
					BigDecimal usenumbs = bean.getNumbs();
					/**
					 * 从库存中添加新的商品
					 */
					TInventoryLog ilog1 = new TInventoryLog(bean.getProjectId(),InitProperties.INVENTORY_OPTION_ORDER_ADD,customer.getCustomername(),customer.getUpdatetime(),consumptonAmount.getTotalAmount(),user.getCompanyid(),usenumbs,user.getUsername());
					inventoryLogService.saveOrUpdate(ilog1);
					inventory.setInventory(newtotalnumbs.subtract(usenumbs));
					bean.setUpdateuser(user.getUsername());
					consumptonProjectMapper.updateByPrimaryKeySelective(bean);
				}else {
					bean.setCreateuser(user.getUsername());
					BigDecimal totalnumbs = inventory.getInventory();
					BigDecimal usenumbs = bean.getNumbs();
					inventory.setInventory(totalnumbs.subtract(usenumbs));
					consumptonProjectMapper.insertSelective(bean);
					/**
					 * 从库存中添加商品
					 */
					TInventoryLog ilog1 = new TInventoryLog(bean.getProjectId(),InitProperties.INVENTORY_OPTION_ORDER_ADD,customer.getCustomername(),customer.getUpdatetime(),consumptonAmount.getTotalAmount(),user.getCompanyid(),usenumbs,user.getUsername());
					inventoryLogService.saveOrUpdate(ilog1);
				}
				inventoryService.saveOrUpdate(inventory);
				mess = "成功！";
				logger.info(mess);
				map.put("mess", mess);
				map.put("status", true);
			}else {
				mess = "库存不足，请联系存储管理人员后，再次尝试！";
				logger.info(mess);
				map.put("mess", mess);
				map.put("status", false);
			}
		} catch (Exception e) {
			mess = "添加失败，请检查数据无误后再次尝试！";
			logger.info(mess);
			map.put("mess", mess);
			map.put("status", false);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return map;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteByExample(TConsumptonProjectExample bean) {
		try {
			List<TConsumptonProject> list = consumptonProjectMapper.selectByExample(bean);
			for (TConsumptonProject tConsumptonProject : list) {
				Integer id = tConsumptonProject.getId();
				this.delete(id);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

}
