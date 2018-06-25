//package com.gasq.bdp.logn.state;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * 
// * @author 巨伟刚
// * @packageName com.hadoop.state
// * @creatTime 2017年9月8日下午1:40:27
// * @remark
// */
//public class StateClient {
//	protected Logger log = LoggerFactory.getLogger(getClass());
////    public static void main(String[] args) {
////    	//初始化工作任务空间并设置空间状态
////        Context context = new Context();
////        context.setStatus(InitProperties.WORKFLOW_STATUS_READY);
////        context.setIgnoreError(false);
////        //初始化工作列表
////        List<WorkFlowState> list = new ArrayList<WorkFlowState>();
////        list.add(new ReadES2LocalWorkFlow());
////        list.add(new ShellScriptLuncherWorkFlow());
////        list.add(new HiveOptionWorkFlow());
////        StateClient sc = new StateClient();
////        //执行工作流中的任务列表
////        sc.doJobs(context,list);
////    }
//    
////    public void doJobs(StateContext context,List<WorkFlowState> list) {
////    	log.debug(context.getStatusName());
////    	if(context!=null && list.size()>0) {
////    		for (WorkFlowState workFlowState : list) {
////    			context.setWorkFlowState(workFlowState);
////				boolean b = context.run(1);
////				log.debug(context.getStatusName());
////				if(!b) break;
////    		}
////    		context.stop();
////    		log.debug(context.getStatusName());
////    	}
////    }
//}
