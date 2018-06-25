package com.gasq.bdp.logn.utils;

import java.io.File;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.TSysTimerJobSftp;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPChannelUtil {
    Session session = null;
    Channel channel = null;

    private static final Logger LOG = Logger.getLogger(SFTPChannelUtil.class.getName());
    public ChannelSftp getChannel(TSysTimerJobSftp jobSftp) throws JSchException {
        String ftpHost = jobSftp.getHost();
        int port = jobSftp.getPort();
        String ftpUserName = jobSftp.getUsername();
        String ftpPassword = jobSftp.getPassword();
        int timeout = jobSftp.getTimeout();
        int ftpPort = jobSftp.getDefPort();
        if (port != 0) {
            ftpPort = port;
        }
        JSch jsch = new JSch(); // 创建JSch对象
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
        LOG.debug("Session created.");
        if (ftpPassword != null) {
            session.setPassword(ftpPassword); // 设置密码
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(timeout); // 设置timeout时间
        session.connect(); // 通过Session建立链接
        LOG.debug("Session connected.");
        LOG.debug("Opening Channel.");
        channel = session.openChannel("sftp"); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接
        LOG.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName  + ", returning: " + channel);
        return (ChannelSftp) channel;
    }

    public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
            LOG.debug("sftp channel closed!");
        }
        if (session != null) {
            session.disconnect();
            LOG.debug("sftp session closed!");
        }
    }
    
    public void put(TSysTimerJobSftp jobSftp) throws WorkFlowJobException {
    	ChannelSftp channelSftp = null;
		try {
			channelSftp = getChannel(jobSftp);
		} catch (JSchException e) {
			throw new WorkFlowJobException("获取STFP链接失败！",e);
		}
		String uploadfile = jobSftp.getUploadfile();
		File file = new File(uploadfile);
        long fileSize = file.length();
		try {
			channelSftp.put(uploadfile,jobSftp.getUploadtargetpath(),new FileProgressMonitor(fileSize),ChannelSftp.OVERWRITE);
		} catch (SftpException e1) {
			throw new WorkFlowJobException("SFTP上传文件失败！",e1);
		}
		LOG.debug("文件《"+uploadfile+"》上传文件成功！上传路径:《"+jobSftp.getUploadtargetpath()+"》");
		channelSftp.quit();
		try {
			closeChannel();
		} catch (Exception e) {
			throw new WorkFlowJobException("关闭失败！",e);
		}
    }
    
//    public static void main(String[] args) {
//    	SFTPChannelUtil stp = new SFTPChannelUtil();
//    	try {
//			stp.put(stp.getChannel(),"");
//			stp.closeChannel();
//		} catch (Exception e) {
//		}
//	}
}