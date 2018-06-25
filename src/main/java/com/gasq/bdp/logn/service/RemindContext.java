/**
 * 
 */
package com.gasq.bdp.logn.service;

import com.gasq.bdp.logn.service.imp.RemindStrategyContains;

/**
 * @author Ju_weigang
 * @时间 2018年6月12日下午9:50:31
 * @项目路径 com.gasq.bdp.logn.service
 * @描述 
 */
public class RemindContext {
	
	private RemindStrategyContains remindStrategyContains;

	public RemindContext(RemindStrategyContains remindStrategyContains) {
		super();
		this.remindStrategyContains = remindStrategyContains;
	}
	
	public void exeRemind(String name,String group) {
		remindStrategyContains.exeRemind(name, group);
	}

}
