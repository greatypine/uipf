/**
 * 
 */
package com.gasq.bdp.logn.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gasq.bdp.logn.mapper.TSysUserMapper;
import com.gasq.bdp.logn.model.RoleSign;
import com.gasq.bdp.logn.model.SystemUserInfo;
import com.gasq.bdp.logn.model.TSysUser;
import com.gasq.bdp.logn.service.EmailManager;

/**
 * @author Ju_weigang
 * @时间 2017年7月27日下午1:08:25
 * @项目路径 cn.gnkj.utils
 * @描述 
 */
public class SyncAction implements Runnable {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	// 可以容纳10个线程的执行器.  
    private final static ExecutorService exec = Executors.newFixedThreadPool(10);
	EmailManager emailManager;
	String mess;
	TSysUserMapper userMapper;
	Integer companyid;
	
	public SyncAction(EmailManager emailManager,TSysUserMapper userMapper,Integer companyid) {
		super();
		this.emailManager = emailManager;
		this.userMapper = userMapper;
		this.companyid = companyid;
	}


	@Override
	public void run() {
		this.doAction(mess,companyid);
	}
	
	private synchronized void doAction(String mess,Integer companyid){
		Map<String,Object> map = new HashMap<>();
		map.put("companyid", companyid);
//		List<String> sList = new ArrayList<String>();
//		sList.add(RoleSign.SADMIN);
		map.put("roles", RoleSign.Q_ALL);
		List<TSysUser> userlist = userMapper.queryQUserEmailAndPhone(map);
		Object[] emails = userlist.stream().map(f->f.getEmail()).distinct().toArray();
		emailManager.sendSimpleEmail(emails, "痘卫士-预约提醒",mess);
		logger.info("用户【"+SystemUserInfo.getSystemUser().getUser().getNickname()+"】邮件通知发送成功！发送内容为："+mess);
	}


	public static ExecutorService getExec() {
		return exec;
	}
}
