package com.gasq.bdp.logn.service.imp;
import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.model.InitProperties;
import com.gasq.bdp.logn.service.EmailManager;
import com.gasq.bdp.logn.utils.DateUtil;

@Service
public class EmailManagerImpl implements EmailManager{
		protected Logger logger = Logger.getLogger(this.getClass());
	    @Autowired
	    private JavaMailSender mailSender;
	    
//	    @Autowired
//	    private VelocityEngine velocityEngine;
	    /**
	     * 发送普通邮件
	     * 修改application.properties的用户，才能发送。
	     * @param InitProperties.EMAIL_SENDER 发件邮箱
	     * @param toEmailName   接受邮箱
	     * @param subject       邮件主题
	     * @param content       邮件内容
	     */
	    @Async("mailAsync")
	    public void sendSimpleEmail(Object[] toEmailName,String subject,String content){
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	try {
	    		if(toEmailName.length>0){
	    			for (Object emailaddr : toEmailName) {
	    				message.setFrom(InitProperties.EMAIL_SENDER);//发送者.
	    				message.setTo(emailaddr.toString());//接收者.
	    				message.setSubject(subject+DateUtil.getAllCurrentDate());//邮件主题.
	    				message.setText(content);//邮件内容.
	    				mailSender.send(message);//发送邮件
	    			}
	    		}
			} catch (Exception e) {
				logger.error("发送邮件失败！"+e.getMessage(), e);
			}
	    }
	    
	    /**
	     * 发送普通邮件
	     * 修改application.properties的用户，才能发送。
	     * @param InitProperties.EMAIL_SENDER 发件邮箱
	     * @param toEmailName   接受邮箱
	     * @param subject       邮件主题
	     * @param content       邮件内容
	     */
	    @Async("mailAsync")
	    public void sendSimpleEmail(String toEmailName,String subject,String content){
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	try {
				message.setFrom(InitProperties.EMAIL_SENDER);//发送者.
				message.setTo(toEmailName);//接收者.
				message.setSubject(subject+DateUtil.getAllCurrentDate());//邮件主题.
				message.setText(content);//邮件内容.
				mailSender.send(message);//发送邮件
			} catch (Exception e) {
				logger.error("发送邮件失败！"+e.getMessage(), e);
			}
	    }
	    /**
	     * 发送html格式的邮件
	     * @param to 接受者
	     * @param subject 主题
	     * @param content 内容
	     */
	    @Override
	    @Async("mailAsync")
	    public void sendHtmlMails(Object[] to, String subject, String content) {
	        MimeMessage message=mailSender.createMimeMessage();
	        try {
	            //true表示需要创建一个multipart message
	            MimeMessageHelper helper=new MimeMessageHelper(message,true);
	            for (Object object : to) {
	            	helper.setFrom(InitProperties.EMAIL_SENDER);
	            	helper.setTo(object.toString());
	            	helper.setSubject(subject+DateUtil.getAllCurrentDate());
	            	helper.setText("<html><body><div sytle='width:100%;'>"+content+"</div></body></html>",true);
	            	mailSender.send(message);
				}
	        }catch (Exception e){
	            logger.error("发送html格式邮件失败！"+e.getMessage(), e);
	        }
	    }
	    /**
	     * 发送html格式的邮件
	     * @param to 接受者
	     * @param subject 主题
	     * @param content 内容
	     */
	    @Override
	    @Async("mailAsync")
	    public void sendHtmlMail(String to, String subject, String content) {
	        MimeMessage message=mailSender.createMimeMessage();
	        try {
	            //true表示需要创建一个multipart message
	            MimeMessageHelper helper=new MimeMessageHelper(message,true);
	            helper.setFrom(InitProperties.EMAIL_SENDER);
	            helper.setTo(to);
	            helper.setSubject(subject+DateUtil.getAllCurrentDate());
	            helper.setText("<html><body><div sytle='width:100%;'>"+content+"</div></body></html>",true);
	            mailSender.send(message);
	        }catch (Exception e){
	            logger.error("发送html格式邮件失败！"+e.getMessage(), e);
	        }
	    }
	    /**
	     * 发送附件邮件
	     * @throws MessagingException
	     * @param InitProperties.EMAIL_SENDER 发件邮箱
	     * @param toEmailName   接受邮箱
	     * @param subject       邮件主题
	     * @param content       邮件内容
	     * @param files         附件
	     */
	    @Async("mailAsync")
	    public void sendAttachmentsEmail(String[] toEmailName,String subject,String content,File[] files) throws MessagingException{
	       //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
	       MimeMessage mimeMessage =  mailSender.createMimeMessage();
	       MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	       //基本设置.
	       if(toEmailName.length>0){
	    	   for (String emailaddr : toEmailName) {
	    		   helper.setFrom(InitProperties.EMAIL_SENDER);//发送者.
	    	       helper.setTo(emailaddr);//接收者.
	    	       helper.setSubject(subject+DateUtil.getAllCurrentDate());//邮件主题.
	    	       helper.setText(content);//邮件内容.
	    	       //org.springframework.core.io.FileSystemResource下的:
	    	       if(files.length>0){
	    	    	   for (int i = 0; i < files.length; i++) {
	    	    		   FileSystemResource file = new FileSystemResource(files[i]);
	    	    		   String filename = files[i].getName();
	    	    		   //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。
	    	    		   helper.addAttachment(filename, file);
	    	    	   }
	    	       }
	    	       mailSender.send(mimeMessage);
	    	   }
	       }
	       
	    }

	    /**
	     * 邮件中使用静态资源
	     * @throws MessagingException
	     * @param InitProperties.EMAIL_SENDER 发件邮箱
	     * @param toEmailName   接受邮箱
	     * @param subject       邮件主题
	     * @param htmlContent   邮件内容  eg:<body>这是图片：<img src='cid:图片名称' /></body>
	     * @param files         附件
	     */
	    @Async("mailAsync")
	    public void sendInlineMail(String[] toEmailName,String subject,String htmlContent,File[] files) throws Exception {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	      //基本设置.
	       helper.setFrom(InitProperties.EMAIL_SENDER);//发送者.
	       helper.setTo(toEmailName);//接收者.
	       helper.setSubject(subject);//邮件主题.
	       // 邮件内容，第二个参数指定发送的是HTML格式 
	        helper.setText(htmlContent, true);
	        if(files.length>0){
	        	for (int i = 0; i < files.length; i++) {
	        		FileSystemResource file = new FileSystemResource(files[i]);
	        		helper.addInline("head",file);
				}
	        }
	        mailSender.send(mimeMessage);
	    }
	    
	    public boolean sendInlineMail(String fromPos, String toPos, String subject, FileSystemResource file)
	    {
	    	MimeMessage msg = mailSender.createMimeMessage();
	    	try
	    	{
	    		//MimeMessageHelper构造器，如果要发送附件邮件，必须指定multipart参数为true
	    		MimeMessageHelper helper  = new MimeMessageHelper(msg, true);
	    		helper.setFrom(fromPos);
	    		helper.setTo(toPos);
	    		helper.setSubject(subject);
	    		helper.setText("<html><body>静态资源:<img src='cid:pic' /></body></html>", true);
	    		helper.addInline("pic", file);
	    		mailSender.send(msg);
	    	} 
	    	catch (MessagingException e)
	    	{
	    		return false;
	    	}
	    	return true;
	    }
	    /**
	     * 发送模板邮件
	     * @param InitProperties.EMAIL_SENDER  发件人
	     * @param toEmailName    收件人
	     * @param subject        主题
	     * @param dataModel      数据
	     * @throws Exception
	     */
//	   public void sendTemplateMail(String InitProperties.EMAIL_SENDER,String toEmailName,String subject,Map<String, Object> dataModel) throws Exception {
//	        MimeMessage mimeMessage = mailSender.createMimeMessage();
//	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//	        helper.setFrom(InitProperties.EMAIL_SENDER);
//	        helper.setTo(toEmailName);
//	        helper.setSubject(subject);
//	        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template.vm", "UTF-8", dataModel);
//	        helper.setText(text, true);
//	        mailSender.send(mimeMessage);
//	    }
	    
//	    public static void main(String[] args) {
////			邮件代理类调用邮件发送方法 并记录发送日志
////			EmailManager proxy = new EmailProxy<EmailManager>(email).getProxy();
////			proxy.sendSimpleEmail("princejwg.happy@163.com", "328335115@qq.com", "测试邮件", "测试普通邮件！");
//		}
}