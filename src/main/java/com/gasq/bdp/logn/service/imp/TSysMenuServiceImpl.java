package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.iexception.WorkFlowStateException;
import com.gasq.bdp.logn.mapper.TSysMenuMapper;
import com.gasq.bdp.logn.mapper.TSysRoleMenuMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysMenu;
import com.gasq.bdp.logn.model.TSysMenuExample;
import com.gasq.bdp.logn.model.TSysMenuExample.Criteria;
import com.gasq.bdp.logn.model.TSysRoleMenuExample;
import com.gasq.bdp.logn.model.TSysRoleMenuKey;
import com.gasq.bdp.logn.service.TSysMenuService;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月15日下午3:03:23
 * @remark 
 */
@Service
public class TSysMenuServiceImpl implements TSysMenuService{
	@Autowired TSysMenuMapper menuMapper;
	@Autowired TSysRoleMenuMapper roleMenuMapper;
	@Override
	public long countByExample(TSysMenuExample example) {
		return 0;
	}

	@Override
	public int deleteByExample(TSysMenuExample example) {
		return 0;
	}

	@Override
	public int insert(TSysMenu record) {
		return menuMapper.insert(record);
	}

	@Override
	public int insertSelective(TSysMenu record) {
		return 0;
	}

	@Override
	public List<TSysMenu> selectByExample(TSysMenuExample example) {
		return menuMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(TSysMenu record, TSysMenuExample example) {
		return 0;
	}

	@Override
	public int updateByExampleWithBLOBs(TSysMenu record, TSysMenuExample example) {
		return 0;
	}

	@Override
	public int updateByExample(TSysMenu record, TSysMenuExample example) {
		return menuMapper.updateByExample(record, example);
	}
	
	public List<Map<String,Object>> convert(List<Map<String,Object>> list){
		List<Map<String, Object>> l=new ArrayList<>();
		Map<String, Object> map=null;
		for(int i=0;i<list.size();i++){
			map=new HashMap<>();
			map.put("table", list.get(i).values().iterator().next());
			map.put("id", i+1);
			l.add(map);
		}
		return l;
	}

	@Override
	public List<TSysMenu> selectMenuBeanByRole(Integer pid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pid", pid);
		params.put("roleid", SystemUserInfo.getSystemUser().getRole().stream().map(r->r.getId().intValue()).toArray());
		params.put("companyid", SystemUserInfo.getSystemUser().getCompany().getId());
		return menuMapper.selectMenuBeanByRole(params);
	}

	@Override
	public Map<String, Object> queryPagingList(TSysMenu bean) {
		Map<String, Object> result= new  HashMap<String, Object>();
		TSysMenuExample example = new TSysMenuExample();
		Criteria c = example.createCriteria();
		if(bean.getText()!=null && !"".equals(bean.getText())) {
			c.andTextLike("%"+bean.getText()+"%");
		}
		if(bean.getCode()!=null && !"".equals(bean.getCode())) {
			c.andCodeEqualTo(bean.getCode());
		}
		if(bean.getId()!=null) {
			c.andIdEqualTo(bean.getId());
		}
		if(bean.getState()!=null && !"".equals(bean.getState())) {
			c.andStateEqualTo(bean.getState());
		}
		if(bean.getStatus()!=null) {
			c.andStatusEqualTo(bean.getStatus());
		}
		c.andCompanyidEqualTo(SystemUserInfo.getSystemUser().getCompany().getId());
		example.setOrderByClause(" code asc ");
		int count = (int) menuMapper.countByExample(example);
		List<TSysMenu> list = null;
		int start = 0;
		int intPage = ( bean.getPage()==0) ? 1 : bean.getPage();
		int number = (bean.getRows()==0) ? 10 : bean.getRows();
		start = (intPage - 1) * number;
		RowBounds rowBounds = new RowBounds(start, bean.getRows());
		if(count>0) {
			list = menuMapper.selectByExampleWithRowbounds(example, rowBounds);
		}else {
			list = new ArrayList<TSysMenu>(); 
		}
		result.put("rows",list);
		result.put("total",count);
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveOrUpdate(TSysMenu bean) throws WorkFlowStateException  {
		try {
			bean.setCompanyid(SystemUserInfo.getSystemUser().getCompany().getId());
			if(bean.getId()!=null) {
				if(bean.getRoleids()!=null) {
					String[] roleids = bean.getRoleids().split(",");
					TSysRoleMenuExample example = new TSysRoleMenuExample();
					example.createCriteria().andMenuidEqualTo(bean.getId());
					roleMenuMapper.deleteByExample(example);
					for (String rid : roleids) {
						TSysRoleMenuKey record = new TSysRoleMenuKey(Integer.parseInt(rid),bean.getId(),SystemUserInfo.getSystemUser().getCompany().getId());
						roleMenuMapper.insert(record);
					}
				}
				menuMapper.updateByPrimaryKeySelective(bean);
			}else {
				Integer id = menuMapper.getMaxId();
				bean.setId(id);
				if(bean.getRoleids()!=null) {
					String[] roleids = bean.getRoleids().split(",");
					for (String rid : roleids) {
						TSysRoleMenuKey record = new TSysRoleMenuKey(Integer.parseInt(rid),bean.getId(),SystemUserInfo.getSystemUser().getCompany().getId());
						roleMenuMapper.insert(record);
					}
				}
				String code = menuMapper.getNextCode(bean.getCode());
				bean.setCode(code);
				menuMapper.insertSelective(bean);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean delete(Integer id) throws WorkFlowStateException {
		try {
			TSysRoleMenuExample example = new TSysRoleMenuExample();
			example.createCriteria().andMenuidEqualTo(id);
			roleMenuMapper.deleteByExample(example);
			menuMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> selectAllMenusByRole(Integer pid,Integer roleid) {
		int companyid = SystemUserInfo.getSystemUser().getCompany().getId();
		return menuMapper.selectAllMenusByRole(pid,roleid,companyid);
	}

	@Override
	public List<Map<String, Object>> selectAllMenusTreeByRole(Integer roleid) {
		int companyid = SystemUserInfo.getSystemUser().getCompany().getId();
		List<Map<String, Object>> list = menuMapper.selectAllMenusTreeByRole(roleid,companyid);
		List<Map<String, Object>> treelist = new ArrayList<Map<String, Object>>();
		Map<String,Object> tree = new HashMap<String,Object>();
		tree.put("id", 0);
		tree.put("text", "系统菜单");
		tree.put("state", "closed");
		tree.put("code", -1);
		tree.put("status", -1);
		tree.put("checked", (list.size()>0)?true:false);
		tree.put("parendId", -1);
		List<Map<String, Object>> plist = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			int pid = Integer.parseInt(map.get("parendId").toString());
			if(pid==0) {
				String c = map.get("checked").toString();
				if(c.equals("0")) map.put("checked",false);
				else if(c.equals("1"))map.put("checked",true);
				plist.add(map);
			}
		}
		for (Map<String, Object> map : plist) {
			List<Map<String, Object>> c1list = new ArrayList<Map<String, Object>>();
			int id = Integer.parseInt(map.get("id").toString());
			for (Map<String, Object> map2 : list) {
				int pid2 = Integer.parseInt(map2.get("parendId").toString());
				if(pid2==0)continue;
				int id2 = Integer.parseInt(map2.get("id").toString());
				String state = map2.get("state").toString();
				String c = map2.get("checked").toString();
				if(c.equals("0")) map2.put("checked",false);
				else if(c.equals("1"))map2.put("checked",true);
				if(id == pid2) {
					if(state.equals("closed")) {
						List<Map<String, Object>> c2list = new ArrayList<Map<String, Object>>();
						for (Map<String, Object> map3 : list) {
							int pid3 = Integer.parseInt(map3.get("parendId").toString());
							if(pid3==0)continue;
							if(pid3==pid2)continue;
							if(id2 == pid3) {
								String cc = map3.get("checked").toString();
								if(cc.equals("0")) map3.put("checked",false);
								else if(cc.equals("1"))map3.put("checked",true);
								c2list.add(map3);
							}
						}
						map2.put("children", c2list);
						c1list.add(map2);
					}else {
						c1list.add(map2);
					}
				}
			}
			map.put("children", c1list);
		}
		tree.put("children", plist);
		treelist.add(tree);
		return treelist;
	}
}
