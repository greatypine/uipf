//package com.gasq.bdp.logn.utils;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import jcifs.smb.SmbFile;
//import jcifs.smb.SmbFileInputStream;
//import jcifs.smb.SmbFileOutputStream;
///**
// * 这个工具类提供了从Samba服务器上下载文件到本地目录，以及上传本地文件到Samba服务器指定目录的方法
// * @author charles.wang
// *
// */
//public class SambaFileAccessUtil {
//            
//	/**
//	 * 利用JSch包实现SFTP下载、上传文件
//	 * @param ip 主机IP
//	 * @param user 主机登陆用户名
//	 * @param psw  主机登陆密码
//	 * @param port 主机ssh2登陆端口，如果取默认值，传-1
//	 */
//	public static void sshSftp(String ip, String user, String psw ,int port) throws Exception{
//		Session session = null;
//		Channel channel = null;
//
//		
//		JSch jsch = new JSch();
//		
//		
//		if(port <=0){
//			//连接服务器，采用默认端口
//			session = jsch.getSession(user, ip);
//		}else{
//			//采用指定的端口连接服务器
//			session = jsch.getSession(user, ip ,port);
//		}
//
//		//如果服务器连接不上，则抛出异常
//		if (session == null) {
//			throw new Exception("session is null");
//		}
//		
//		//设置登陆主机的密码
//		session.setPassword(psw);//设置密码   
//		//设置第一次登陆的时候提示，可选值：(ask | yes | no)
//		session.setConfig("StrictHostKeyChecking", "no");
//		//设置登陆超时时间   
//		session.connect(30000);
//			
//		try {
//			//创建sftp通信通道
//			channel = (Channel) session.openChannel("sftp");
//			channel.connect(1000);
//			ChannelSftp sftp = (ChannelSftp) channel;
//			
//			
//			//进入服务器指定的文件夹
//			sftp.cd("domains");
//			
//			//列出服务器指定的文件列表
//			Vector v = sftp.ls("*.txt");
//			for(int i=0;i<v.size();i++){
//				System.out.println(v.get(i));
//			}
//			
//			//以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
//			OutputStream outstream = sftp.put("1.txt");
//			InputStream instream = new FileInputStream(new File("c:/print.txt"));
//			
//			byte b[] = new byte[1024];
//			int n;
//		    while ((n = instream.read(b)) != -1) {
//		    	outstream.write(b, 0, n);
//		    }
//		    
//		    outstream.flush();
//		    outstream.close();
//		    instream.close();
//		} catch (Exception e) {
//			logger.info(e.getMessage(),e);
//		} finally {
//			session.disconnect();
//			channel.disconnect();
//		}
//	}
//	/**
//	 * 利用JSch包实现SFTP下载、上传文件
//	 * @param ip 主机IP
//	 * @param user 主机登陆用户名
//	 * @param psw  主机登陆密码
//	 * @param port 主机ssh2登陆端口，如果取默认值(默认值22)，传-1
//	 * @param privateKey 密钥文件路径
//	 * @param passphrase 密钥的密码
//	 * 
//	 */
//	public static void sshSftp(String ip, String user, String psw 
//			,int port ,String privateKey ,String passphrase) throws Exception{
//		Session session = null;
//		Channel channel = null;
//
//		
//		JSch jsch = new JSch();
//		
//		//设置密钥和密码
//		if (privateKey != null && !"".equals(privateKey)) {
//            if (passphrase != null && "".equals(passphrase)) {
//            	//设置带口令的密钥
//                jsch.addIdentity(privateKey, passphrase);
//            } else {
//            	//设置不带口令的密钥
//                jsch.addIdentity(privateKey);
//            }
//        }
//
//		
//		
//		if(port <=0){
//			//连接服务器，采用默认端口
//			session = jsch.getSession(user, ip);
//		}else{
//			//采用指定的端口连接服务器
//			session = jsch.getSession(user, ip ,port);
//		}
//
//		//如果服务器连接不上，则抛出异常
//		if (session == null) {
//			throw new Exception("session is null");
//		}
//		
//		//设置登陆主机的密码
//		session.setPassword(psw);//设置密码   
//		//设置第一次登陆的时候提示，可选值：(ask | yes | no)
//		session.setConfig("StrictHostKeyChecking", "no");
//		//设置登陆超时时间   
//		session.connect(30000);
//			
//		try {
//			//创建sftp通信通道
//			channel = (Channel) session.openChannel("sftp");
//			channel.connect(1000);
//			ChannelSftp sftp = (ChannelSftp) channel;
//			
//			
//			//进入服务器指定的文件夹
//			sftp.cd("domains");
//			
//			//列出服务器指定的文件列表
//			Vector v = sftp.ls("*.txt");
//			for(int i=0;i<v.size();i++){
//				System.out.println(v.get(i));
//			}
//			
//			//以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
//			OutputStream outstream = sftp.put("1.txt");
//			InputStream instream = new FileInputStream(new File("c:/print.txt"));
//			
//			byte b[] = new byte[1024];
//			int n;
//		    while ((n = instream.read(b)) != -1) {
//		    	outstream.write(b, 0, n);
//		    }
//		    
//		    outstream.flush();
//		    outstream.close();
//		    instream.close();
//		} catch (Exception e) {
//			logger.info(e.getMessage(),e);
//		} finally {
//			session.disconnect();
//			channel.disconnect();
//		}
//	}
//    
//    public static void main(String[] args) {
//    	upload();
//	}
//    public static void download() {
//    	//Demo1: 演示从Samba服务器上下载指定的文件到本地
//        System.out.println("Demo1: Downloading File from Samba Server to Local");
//        String demo1RemoteSambaFileURL= "smb://samba:samba_password@192.168.71.43/samba/demo1/testFile1.jpg";
//        String demo1LocalDir = "D:\\Framework Study\\Samba\\DemoTest\\demo1";
//        SambaFileAccessUtil.downloadFileFromSamba(demo1RemoteSambaFileURL, demo1LocalDir);
//    }
//    public static void upload() {
//    	//Demo2: 演示上传文件到Samba服务器指定目录
//        System.out.println("Demo2:Uploading File from Local to Samba Server");
//        String date = DateUtil.getDiyStrDateTime(-1, DateUtil.DATE_NO_FLAG_DATE_FORMAT);
//        String demo2RemoteSambaDirURL="smb://root:Adminmock1234@10.16.31.166/root";
//        String demo2LocalFile= "E:\\projects\\logs\\"+date+".log";
//        SambaFileAccessUtil.uploadFileToSamba(demo2RemoteSambaDirURL, demo2LocalFile);
//    }
//}