/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TInventoryMapper;
import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TInventory;
import com.gasq.bdp.logn.model.TInventoryExample;
import com.gasq.bdp.logn.model.TInventoryLog;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.model.TInventoryExample.Criteria;
import com.gasq.bdp.logn.service.TInventoryLogService;
import com.gasq.bdp.logn.service.TInventoryService;
import com.gasq.bdp.logn.utils.DateUtil;
import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日上午10:14:37
 * @remark 
 */
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class TInventoryServiceImpl implements TInventoryService {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired TInventoryMapper inventoryMapper;
	@Autowired TInventoryLogService inventoryLogService;
	

	@Override
	@Transactional
	public long countByExample(TInventoryExample example) {
		return inventoryMapper.countByExample(example);
	}
	public long countByExample(TInventory bean) {
		TInventoryExample example = new TInventoryExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCompanyid()!=null) {
			c.andCompanyidEqualTo(bean.getCompanyid());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				c.andCompanyidEqualTo(SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		return inventoryMapper.countByExample(example);
	}

	@Override
	@Transactional
	public boolean delete(int id) {
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			TInventory bean = inventoryMapper.selectByPrimaryKey(id);
			TInventoryLog ilog = new TInventoryLog(bean.getId(),InitProperties.INVENTORY_OPTION_DELETE,null,null,null,user.getCompanyid(),bean.getInventory(),user.getUsername());
			inventoryLogService.saveOrUpdate(ilog);
			bean.setStatus(99);
			inventoryMapper.updateByPrimaryKeySelective(bean);
			logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除商品完成！");
			return true;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】删除库存信息操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return false;
	}

	@Override
	@Transactional
	public List<TInventory> selectByExample(TInventory bean) {
		TInventoryExample example = new TInventoryExample();
		Criteria c = example.createCriteria();
		if(bean.getName()!=null && !"".equals(bean.getName())) {
			c.andNameLike("%"+bean.getName()+"%");
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		if(bean.getCompanyid()!=null) {
			c.andCompanyidEqualTo(bean.getCompanyid());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		example.setOrderByClause(" id asc ");
		return inventoryMapper.selectByExample(example);
	}

	@Override
	@Transactional
	public TInventory selectByPrimaryKey(Integer id) {
		return inventoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> queryPagingList(TInventory bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		Map<String, Object> params= new  HashMap<String, Object>();
		params.put("index", start);
		params.put("pageSize", number);
		if(bean.getName()!=null) {
			params.put("name", bean.getName());
		}
		if(bean.getStatus()!=null) {
			params.put("status", bean.getStatus());
		}
		if(bean.getCompanyid()!=null) {
			params.put("companyid", bean.getCompanyid());
		}else {
			if(!WorkFlowUtil.hasAnyRoles(RoleSign.SADMIN,RoleSign.GENERALMANAGER,RoleSign.Q_AREA_SHOPMANAGER)) {
				params.put("companyid", SystemUserInfo.getSystemUser().getCompany().getId());
			}
		}
		list = inventoryMapper.queryPagingList(params);
		if(list==null) list = new ArrayList<Map<String,Object>>(); 
		Integer count = inventoryMapper.countByBean(params);
		result.put("rows",list);
		result.put("total",count);
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】查询库存操作完成！总查询【"+count+"】条!");
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdate(TInventory bean) throws SchedulerException {
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			bean.setUpdatetime(DateUtil.getSysCurrentDate());
			bean.setCompanyid(SystemUserInfo.getSystemUser().getCompany().getId());
			if(bean.getId()!=null) {
				bean.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
				inventoryMapper.updateByPrimaryKeySelective(bean);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】更新库存操作完成！");
				TInventoryLog ilog = new TInventoryLog(bean.getId(),InitProperties.INVENTORY_OPTION_UPDATE,null,null,null,user.getCompanyid(),bean.getInventory(),user.getUsername());
				inventoryLogService.saveOrUpdate(ilog);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加库存操作日志信息完成！");
			}else {
				bean.setCreatetime(DateUtil.getSysCurrentDate());
				bean.setCreateuser(user.getUsername());
				inventoryMapper.insertSelective(bean);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加库存操作完成！");
				TInventoryLog ilog = new TInventoryLog(bean.getId(),InitProperties.INVENTORY_OPTION_ADD,null,null,null,user.getCompanyid(),bean.getInventory(),user.getUsername());
				inventoryLogService.saveOrUpdate(ilog);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】添加库存操作日志信息完成！");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】新增或更新库存信息操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return true;
	}

	@Override
	@Transactional
	public Double getInventoryNumbs(Integer id) {
		return inventoryMapper.selectByPrimaryKey(id).getInventory().doubleValue();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Boolean checkInventoryEnable(Integer id, Double numbs)throws SchedulerException {
		try {
			TInventory inventory = inventoryMapper.selectByPrimaryKey(id);
			if(inventory!=null) {
				if(inventory.getInventory().doubleValue()>numbs) {
					return true;
				}
				logger.info("商品："+inventory.getName()+"\t 型号："+inventory.getCode()+"\t"+"，库存不足。请及时补充！");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】检查库存是否可用操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
		}
		return false;
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> appendInventory(TInventory bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		String mess = null;
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			TInventory inventory = inventoryMapper.selectByPrimaryKey(bean.getId());
			if(inventory!=null) {
				inventory.setInventory(inventory.getInventory().add(bean.getInventory()));
				inventoryMapper.updateByPrimaryKeySelective(inventory);
				result.put("status", true);
				TInventoryLog ilog = new TInventoryLog(bean.getId(),InitProperties.INVENTORY_OPTION_APPEND,null,null,null,user.getCompanyid(),bean.getInventory(),user.getUsername());
				inventoryLogService.saveOrUpdate(ilog);
				logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】商品入库完成！");
				return result;
			}
		}catch (Exception e) {
			mess = "入库操作失败！"+e.getMessage();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】商品入库操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		result.put("status", false);
		result.put("mess", mess);
		return result;
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> deliveryInventory(TInventory bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		String mess = null;
		try {
			TSysUser user = SystemUserInfo.getSystemUser().getUser();
			TInventory inventory = inventoryMapper.selectByPrimaryKey(bean.getId());
			if(inventory!=null) {
				BigDecimal inventory2 = inventory.getInventory();
				BigDecimal inventory3 = bean.getInventory();
				if(inventory2.doubleValue()>inventory3.doubleValue()) {//库存够用
					inventory.setInventory(inventory2.subtract(inventory3));
					inventoryMapper.updateByPrimaryKeySelective(inventory);
					result.put("status", true);
					TInventoryLog ilog = new TInventoryLog(bean.getId(),InitProperties.INVENTORY_OPTION_DELIVERY,null,null,null,user.getCompanyid(),bean.getInventory(),user.getUsername());
					inventoryLogService.saveOrUpdate(ilog);
					return result;
				}else {
					mess = "商品："+inventory.getName()+"\t 型号："+inventory.getCode()+"\t"+"，库存不足，出库失败，请及时补充库存！";
					logger.info(mess);
				}
				inventory.setInventory(inventory.getInventory().add(bean.getInventory()));
				inventoryMapper.updateByPrimaryKeySelective(inventory);
			}
		}catch (Exception e) {
			mess = "出库操作失败！"+e.getMessage();
			logger.error("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】出库操作失败，事务回滚！错误信息如下：\n"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		result.put("status", false);
		result.put("mess", mess);
		return result;
	}

}
