package com.gasq.bdp.logn.service;

import java.io.File;

import javax.mail.MessagingException;

/**
 * @projectName：spring-boot-simple
 * @packageName：com.justin.component
 * @createDate：2016年6月21日 下午1:29:20
 * @creater： justin
 * @remark：
 */
public interface EmailManager {
	/**
     * 发送多人普通邮件
     * 修改application.properties的用户，才能发送。
     * @param fromEmailName 发件邮箱
     * @param emails   接受邮箱
     * @param subject       邮件主题
     * @param content       邮件内容
     */
	public void sendSimpleEmail(Object[] emails,String subject,String content);
	/**
     * 发送单人普通邮件
     * 修改application.properties的用户，才能发送。
     * @param fromEmailName 发件邮箱
     * @param emails   接受邮箱
     * @param subject       邮件主题
     * @param content       邮件内容
     */
	public void sendSimpleEmail(String email, String subject, String mess);
	 /**
     * 发送附件邮件
     * @throws MessagingException
     * @param fromEmailName 发件邮箱
     * @param toEmailName   接受邮箱
     * @param subject       邮件主题
     * @param content       邮件内容
     * @param files         附件
     */
	public void sendAttachmentsEmail(String[] toEmailName,String subject,String content,File[] files) throws MessagingException;
	/**
     * 邮件中使用静态资源
     * @throws MessagingException
     * @param fromEmailName 发件邮箱
     * @param toEmailName   接受邮箱
     * @param subject       邮件主题
     * @param htmlContent   邮件内容  eg:<body>这是图片：<img src='cid:图片名称' /></body>
     * @param files         附件
     */
	public void sendInlineMail(String[] toEmailName,String subject,String htmlContent,File[] files) throws Exception;
	/**
     * 发送模板邮件
     * @param fromEmailName  发件人
     * @param toEmailName    收件人
     * @param subject        主题
     * @param dataModel      数据
     * @throws Exception
     */
//	public void sendTemplateMail(String toEmailName,String subject,Map<String, Object> dataModel) throws Exception;
	void sendHtmlMail(String to, String subject, String content);
	void sendHtmlMails(Object[] to, String subject, String content);
}
