package com.gasq.bdp.logn.service.imp;
import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.gasq.bdp.logn.service.EmailManager;

@Service
public class EmailManagerImpl implements EmailManager{
	    @Autowired
	    private JavaMailSender mailSender;
	    
	    @Value("${spring.mail.username}")
		public static String fromEmailName;
//	    @Autowired
//	    private VelocityEngine velocityEngine;
	    /**
	     * 发送普通邮件
	     * 修改application.properties的用户，才能发送。
	     * @param fromEmailName 发件邮箱
	     * @param toEmailName   接受邮箱
	     * @param subject       邮件主题
	     * @param content       邮件内容
	     */
	    @Async
	    public void sendSimpleEmail(String fromEmailName,Object[] toEmailName,String subject,String content){
	       SimpleMailMessage message = new SimpleMailMessage();
	       if(toEmailName.length>0){
	    	   for (Object emailaddr : toEmailName) {
	    		   message.setFrom(fromEmailName);//发送者.
	    	       message.setTo(emailaddr.toString());//接收者.
	    	       message.setSubject(subject);//邮件主题.
	    	       message.setText(content);//邮件内容.
	    	       mailSender.send(message);//发送邮件
	    	   }
	       }
	    }
	    /**
	     * 发送附件邮件
	     * @throws MessagingException
	     * @param fromEmailName 发件邮箱
	     * @param toEmailName   接受邮箱
	     * @param subject       邮件主题
	     * @param content       邮件内容
	     * @param files         附件
	     */
	    @Async
	    public void sendAttachmentsEmail(String fromEmailName,String[] toEmailName,String subject,String content,File[] files) throws MessagingException{
	       //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
	       MimeMessage mimeMessage =  mailSender.createMimeMessage();
	       MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	       //基本设置.
	       if(toEmailName.length>0){
	    	   for (String emailaddr : toEmailName) {
	    		   helper.setFrom(fromEmailName);//发送者.
	    	       helper.setTo(emailaddr);//接收者.
	    	       helper.setSubject(subject);//邮件主题.
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
	     * @param fromEmailName 发件邮箱
	     * @param toEmailName   接受邮箱
	     * @param subject       邮件主题
	     * @param htmlContent   邮件内容  eg:<body>这是图片：<img src='cid:图片名称' /></body>
	     * @param files         附件
	     */
	    @Async
	    public void sendInlineMail(String fromEmailName,String[] toEmailName,String subject,String htmlContent,File[] files) throws Exception {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	      //基本设置.
	       helper.setFrom(fromEmailName);//发送者.
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
	    /**
	     * 发送模板邮件
	     * @param fromEmailName  发件人
	     * @param toEmailName    收件人
	     * @param subject        主题
	     * @param dataModel      数据
	     * @throws Exception
	     */
//	   public void sendTemplateMail(String fromEmailName,String toEmailName,String subject,Map<String, Object> dataModel) throws Exception {
//	        MimeMessage mimeMessage = mailSender.createMimeMessage();
//	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//	        helper.setFrom(fromEmailName);
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