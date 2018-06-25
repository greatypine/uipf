package com.gasq.bdp.logn.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.PagingBean;
import com.gasq.bdp.logn.model.ParamsObject;
import com.gasq.bdp.logn.model.TSysTimerJobSftp;
import com.gasq.bdp.logn.utils.SFTPChannelUtil;

/**
 * 命令执行任务
 * @author Renmian
 *
 */
@Component
public class SFTPJob implements IJob {
	
	private static final long serialVersionUID = 1L;
	
	public SFTPJob() {
		super();
	}

	public Object execute(ParamsObject paramsObject, Object inputObj) throws WorkFlowJobException {
		TSysTimerJobSftp sftp = (TSysTimerJobSftp) paramsObject;
		SFTPChannelUtil sftputil = new SFTPChannelUtil();
		if(sftp.getType()) {//上传
			sftputil.put(sftp);
		}else {
			
		}
		destory();
		return null;
	}
	

	public void destory() {
		
	}

	@Override
	public Object execute(ParamsObject params, Object inputObj, PagingBean pb) throws WorkFlowJobException {
		return null;
	}
	
	//just for test---ok!!!
//	public static void main(String[] args) {
//		//String cmd = "java -version"; 	//test ok
//		String batFile = "D:\\IDE\\data-integration\\Spoon.bat";
//		CmdExcuteJob job = new CmdExcuteJob(batFile);
//		try {
//			job.execute();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.info(e.getMessage(),e);
//		}
//	}
}
