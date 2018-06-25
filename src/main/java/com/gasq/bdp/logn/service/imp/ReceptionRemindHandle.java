/**
 * 
 */
package com.gasq.bdp.logn.service.imp;

import com.gasq.bdp.logn.utils.WorkFlowUtil;

/**
 * @author Ju_weigang
 * @时间 2018年6月12日下午9:42:17
 * @项目路径 com.gasq.bdp.logn.service.imp
 * @描述 前台回访
 */
public class ReceptionRemindHandle implements RemindStrategyContains {

	@Override
	public void exeRemind(String name ,String group) {
		WorkFlowUtil.executeReceptionRemind(name, group);
	}

}
