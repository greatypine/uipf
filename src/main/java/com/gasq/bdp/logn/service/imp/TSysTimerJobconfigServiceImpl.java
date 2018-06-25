/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gasq.bdp.logn.mapper.TSysTimerJobconfigMapper;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysTimerJobconfig;
import com.gasq.bdp.logn.model.TSysTimerJobconfigExample;
import com.gasq.bdp.logn.model.TSysTimerJobconfigExample.Criteria;
import com.gasq.bdp.logn.model.TSysTimerJobconfigKey;
import com.gasq.bdp.logn.model.TSysTimerWorkflow;
import com.gasq.bdp.logn.service.TSysTimerJobconfigService;
import com.gasq.bdp.logn.service.TSysTimerWorkflowService;
import com.gasq.bdp.logn.utils.DateUtil;

/**
 * @author 巨伟刚
 * @packageName com.gasq.bdp.logn.service.imp
 * @creatTime 2017年9月13日下午1:36:46
 * @remark 
 */
@Service
public class TSysTimerJobconfigServiceImpl implements TSysTimerJobconfigService {

	@Autowired
	TSysTimerJobconfigMapper jobMapper;
	@Autowired
	TSysTimerWorkflowService workflowMapper;
	@Override
	public long countByExample(TSysTimerJobconfigExample example) {
		return jobMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(TSysTimerJobconfigExample example) {
		return jobMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(TSysTimerJobconfigKey key) {
		return jobMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(TSysTimerJobconfig record) {
		return jobMapper.insertSelective(record);
	}

	@Override
	public List<TSysTimerJobconfig> queryList(TSysTimerJobconfigExample example) {
		return jobMapper.selectByExample(example);
	}

	@Override
	public TSysTimerJobconfig selectByPrimaryKey(TSysTimerJobconfigKey key) {
		return jobMapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(TSysTimerJobconfig record) {
		return jobMapper.updateByPrimaryKeySelective(record);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> queryJobViewList(int wfid) {
		Map<String,Object> statesBean = new HashMap<String,Object>();
		TSysTimerJobconfigExample jobexample = new TSysTimerJobconfigExample();
		Criteria criteria = jobexample.createCriteria();
		TSysTimerWorkflow workflow = null;
		if(wfid!=0) {
			criteria.andWorkflowIdEqualTo(wfid);
			workflow = new TSysTimerWorkflow();
			workflow.setId((long)wfid);
			List<TSysTimerWorkflow> listwf = workflowMapper.queryList(workflow);
			if(listwf.size()>0) workflow = listwf.get(0);
		}
		List<TSysTimerJobconfig> list = jobMapper.selectByExample(jobexample);
		Map<String,Object> mapBean = new HashMap<String,Object>();
		Map<String,Object> pathsBean = new HashMap<String,Object>();
		if(list.size()>0) {
			int i = 1;
			int j = list.size()+1;
			for (TSysTimerJobconfig tSysTimerJobconfig : list) {
				if(tSysTimerJobconfig.getModel()==0) {
					Map<String,Object> mapRectBean = new HashMap<String,Object>();
					if(tSysTimerJobconfig.getNodeType()==0) {
						mapRectBean.put("type","start");
					}else if(tSysTimerJobconfig.getNodeType()==1) {
						mapRectBean.put("type","task");
					}else if(tSysTimerJobconfig.getNodeType()==2) {
						mapRectBean.put("type","fork");
					}else if(tSysTimerJobconfig.getNodeType()==3) {
						mapRectBean.put("type","join");
					}else {
						mapRectBean.put("type","end");
					}
					Map<String,Object> mapTextBean = new HashMap<String,Object>();
					mapTextBean.put("text", (tSysTimerJobconfig.getText()==null)?"":tSysTimerJobconfig.getText());
					Map<String,Object> mapAttrBean = new HashMap<String,Object>();
					mapAttrBean.put("x", tSysTimerJobconfig.getX());
					mapAttrBean.put("y", tSysTimerJobconfig.getY());
					mapAttrBean.put("width", tSysTimerJobconfig.getWidth());
					mapAttrBean.put("height", tSysTimerJobconfig.getHeight());
					Map<String,Object> mapPropsBean = new HashMap<String,Object>();
					Map<String,Object> mapPropsTextvalBean = new HashMap<String,Object>();
					Map<String,Object> mapPropstemp1valBean = new HashMap<String,Object>();
					Map<String,Object> mapPropstemp2valBean = new HashMap<String,Object>();
					Map<String,Object> mapPropstemp3valBean = new HashMap<String,Object>();
					mapPropsTextvalBean.put("value", (tSysTimerJobconfig.getText()==null)?"":tSysTimerJobconfig.getText());
					mapPropsBean.put("text", mapPropsTextvalBean);
					mapPropstemp1valBean.put("value", tSysTimerJobconfig.getType());
					mapPropsBean.put("temp1", mapPropstemp1valBean);
					mapPropstemp2valBean.put("value", tSysTimerJobconfig.getExcuteId());
					mapPropsBean.put("temp2", mapPropstemp2valBean);
					mapPropstemp3valBean.put("value", tSysTimerJobconfig.getProps3());
					mapPropsBean.put("temp3", mapPropstemp3valBean);
					mapRectBean.put("text", mapTextBean);
					mapRectBean.put("attr", mapAttrBean);
					mapRectBean.put("props", mapPropsBean);
					mapBean.put("rect"+i, mapRectBean);
				}else {
					if(tSysTimerJobconfig.getStart()!=null&&tSysTimerJobconfig.getEnd()!=null) {
						Map<String,Object> pathBean = new HashMap<String,Object>();
						pathBean.put("from", tSysTimerJobconfig.getStart());
						pathBean.put("to", tSysTimerJobconfig.getEnd());
						pathBean.put("dots", (tSysTimerJobconfig.getPathDots()==null || "[]".equals(tSysTimerJobconfig.getPathDots()))?new ArrayList():tSysTimerJobconfig.getPathDots());
						Map<String,Object> pathTextValueBean = new HashMap<String,Object>();
						pathTextValueBean.put("text", (tSysTimerJobconfig.getText()==null)?"":tSysTimerJobconfig.getText());
						pathBean.put("text", pathTextValueBean);
						Map<String,Object> pathTextPosXYBean = new HashMap<String,Object>();
						pathTextPosXYBean.put("x", tSysTimerJobconfig.getX());
						pathTextPosXYBean.put("y", tSysTimerJobconfig.getY());
						pathBean.put("textPos", pathTextPosXYBean);
						Map<String,Object> pathPropsTextBean = new HashMap<String,Object>();
						Map<String,Object> pathPropsTextValueBean = new HashMap<String,Object>();
						pathPropsTextValueBean.put("value", (tSysTimerJobconfig.getPathPropsText()==null)?"":tSysTimerJobconfig.getPathPropsText());
						pathPropsTextBean.put("text", pathPropsTextValueBean);
						pathBean.put("props", pathPropsTextBean);
						pathsBean.put("path"+j, pathBean);
						j++;
					}
				}
				i++;
			}
		}
		Map<String,Object> propsPBean = new HashMap<String,Object>();
		Map<String,Object> propsCBean = new HashMap<String,Object>();
		Map<String,Object> propsNameValueBean = new HashMap<String,Object>();
		Map<String,Object> propsKeyValueBean = new HashMap<String,Object>();
		Map<String,Object> propsDescValueBean = new HashMap<String,Object>();
		propsNameValueBean.put("value", workflow.getName());
		propsKeyValueBean.put("value", workflow.getId());
		propsDescValueBean.put("value", workflow.getRemark());
		propsCBean.put("name", propsNameValueBean);
		propsCBean.put("key", propsKeyValueBean);
		propsCBean.put("desc", propsDescValueBean);
		propsPBean.put("props", propsCBean);
		statesBean.put("states", mapBean);
		statesBean.put("paths", pathsBean);
		statesBean.put("props", propsPBean);
		return statesBean;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveJobs(List<Map<String, Object>> data) {
		try {
			Map<String, Object> wfmap = null;
			for (Map<String, Object> map : data) {
				if(Integer.parseInt(map.get("model").toString())==2) {//节点
					wfmap = map;
					TSysTimerJobconfigExample example = new TSysTimerJobconfigExample();
					example.createCriteria().andWorkflowIdEqualTo(Integer.parseInt(wfmap.get("id").toString()));
					jobMapper.deleteByExample(example);
				}
			}
			for (Map<String, Object> map : data) {
				TSysTimerJobconfig job = new TSysTimerJobconfig();
				if(Integer.parseInt(map.get("model").toString())==0) {//节点
					String node_type = map.get("node_type").toString();
					String text = map.get("text").toString();
					String x = map.get("x").toString();
					String y = map.get("y").toString();
					String width = map.get("width").toString();
					String height = map.get("height").toString();
					String props1 = map.get("props1").toString();
					String props2 = map.get("props2").toString();
					String props3 = map.get("props3").toString();
					String model = map.get("model").toString();
					String code = map.get("code").toString();
					job.setCode(code);
					job.setX(Integer.parseInt(x));
					job.setY(Integer.parseInt(y));
					job.setWidth(Integer.parseInt(width));
					job.setHeight(Integer.parseInt(height));
					job.setText(text);
					job.setProps1(props1);
					job.setProps2(props2);
					if(Integer.parseInt(node_type)>0 && Integer.parseInt(node_type)<99) {
						if(StringUtils.isNotBlank(props1)) {
							job.setType(Integer.parseInt(props1));
						}
						if(StringUtils.isNotBlank(props2)) {
							job.setExcuteId(Integer.parseInt(props2));
						}
					}else {
						job.setType(Integer.parseInt(node_type));
					}
					job.setProps3(props3);
					job.setWorkflowId(Integer.parseInt(wfmap.get("id").toString()));
					job.setName(wfmap.get("name").toString());
					job.setModel(Integer.parseInt(model));
					job.setStatus(true);
					job.setNodeType(Integer.parseInt(node_type));
					insertOrUpdateNode(job);
				}else if(Integer.parseInt(map.get("model").toString())==1) {//线
					String start = map.get("start").toString();
					String end = map.get("end").toString();
					String x = map.get("x").toString();
					String y = map.get("y").toString();
					String path_dots = map.get("path_dots").toString();
					String path_props_text = map.get("path_props_text").toString();
					String text = map.get("text").toString();
					String model = map.get("model").toString();
					String code = map.get("code").toString();
					job.setCode(code);
					job.setStart(start);
					job.setEnd(end);
					job.setX(Integer.parseInt(x));
					job.setY(Integer.parseInt(y));
					job.setText(text);
					job.setPathDots(path_dots.toString());
					job.setPathPropsText(path_props_text);
					job.setWorkflowId(Integer.parseInt(wfmap.get("id").toString()));
					job.setName(wfmap.get("name").toString());
					job.setModel(Integer.parseInt(model));
					job.setStatus(true);
					insertOrUpdateNode(job);
				}
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;
	}

	private void insertOrUpdateNode(TSysTimerJobconfig record) {
		TSysTimerJobconfigExample example = new TSysTimerJobconfigExample();
		example.createCriteria().andWorkflowIdEqualTo(record.getWorkflowId()).andCodeEqualTo(record.getCode());
		List<TSysTimerJobconfig> lists = jobMapper.selectByExample(example);
		record.setUpdatetime(DateUtil.getSysCurrentDate());
		record.setGroupname(SystemUserInfo.getSystemUser().getUser().getGroupName());
		if(lists.size()>0) {
			record.setUpdateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			record.setId(lists.get(0).getId());
			jobMapper.updateByPrimaryKeySelective(record);
		}else {
			record.setCreateuser(SystemUserInfo.getSystemUser().getUser().getUsername());
			record.setCreatetime(DateUtil.getSysCurrentDate());
			jobMapper.insertSelective(record);
		}
	}

	@Override
	public List<TSysTimerJobconfig> getWorkFlowNodes(TSysTimerJobconfigKey jobconfigKey) {
		TSysTimerJobconfigExample example = new TSysTimerJobconfigExample();
		example.createCriteria().andWorkflowIdEqualTo(jobconfigKey.getWorkflowId()).andStatusEqualTo(true);
		List<TSysTimerJobconfig> list = jobMapper.selectByExample(example);
		List<TSysTimerJobconfig> rlist = new ArrayList<TSysTimerJobconfig>();
		TSysTimerJobconfig sjob = null;
		if(list.size()>0) {
			for (TSysTimerJobconfig tSysTimerJobconfig : list) {
				if(tSysTimerJobconfig.getNodeType()==0 && tSysTimerJobconfig.getModel()==0) {
					rlist.add(tSysTimerJobconfig);
					sjob = tSysTimerJobconfig;
					break;
				}
			}
			int wfid = sjob.getWorkflowId();
			String code = sjob.getCode();
			boolean b = true;
			while (b) {
				TSysTimerJobconfig jobc = getNextExeNode(wfid,code,list);
				rlist.add(jobc);
				wfid = jobc.getWorkflowId();
				code = jobc.getCode();
				if(jobc.getNodeType()==99) b = false;
			}
		}
		return rlist;
	}
	
	private TSysTimerJobconfig getNextExeNode(int wfid,String startcode,List<TSysTimerJobconfig> jobconfigs) {
		for (TSysTimerJobconfig tSysTimerJobconfig : jobconfigs) {
			if(tSysTimerJobconfig.getModel()==1 && tSysTimerJobconfig.getStart().equals(startcode)) {
				for (TSysTimerJobconfig tSysTimerJobconfig1 : jobconfigs) {
					if(tSysTimerJobconfig1.getCode().equals(tSysTimerJobconfig.getEnd())) {
						return tSysTimerJobconfig1;
					}
				}
				break;
			}
		}
		return null;
	}

	@Override
	public boolean removeNode(TSysTimerJobconfig job) {
		TSysTimerJobconfigExample example = new TSysTimerJobconfigExample();
		Criteria c = example.createCriteria();
		if(job.getWorkflowId()!=null) {
			c.andWorkflowIdEqualTo(job.getWorkflowId());
		}
		if(job.getCode()!=null) {
			c.andCodeEqualTo(job.getCode());
		}
		if(job.getId()!=null) {
			c.andIdEqualTo(job.getId());
		}
		return jobMapper.deleteByExample(example)>0?true:false;
	}
}
