package com.yinyang.yy.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * 邮件发送工具类
 * <pre>使用方法详见main方法</pre>
 * @author 作者:尹洋 E-mail:vteiud@163.com
 * @date 创建时间：2015年9月6日 下午3:36:06
 * @version 1.0
 */
public class MailUtil {
	
	public static final String MAIL_TYPE_HTML = "text/html; charset=utf-8";
	public static final String MAIL_TYPE_TEXT = "test/plain; charset=UTF8";
	
	private static Properties mailProperties(String serverHost, int serverPort, boolean validate){
		Properties props = new Properties();
		props.put("mail.smtp.host", serverHost);
		props.put("mail.smtp.port", serverPort);
		props.put("mail.smtp.auth", validate);
		return props;
	}
	
	private static Session mailAuthenticatior(String username, String password, Properties properties){
		MailAuthenticator authenticator = null;
		System.out.println(properties);
		System.out.println(properties.get("mail.smtp.auth"));
		System.out.println(Boolean.parseBoolean(String.valueOf(properties.get("mail.smtp.auth"))));
		if (Boolean.parseBoolean(String.valueOf(properties.get("mail.smtp.auth")))) {
			System.out.println("xxxx");
			// 如果需要身份认证，则创建一个密码验证器
			//authenticator = new MailAuthenticator(username, password);
			authenticator = new MailUtil().new MailAuthenticator(username, password);
		}
		
		return Session.getDefaultInstance(properties, authenticator);
	}
	
	/**
	 * 邮件发送类
	 * @param server 内部类MailServer bean对象,存放邮箱服务器信息
	 * @param content 内部类MailContent bean对象,存放邮件内容信息
	 * @return
	 */
	public static boolean sendMail(MailServer server, MailContent content){
		
		Properties properties = mailProperties(server.getServerHost(), server.getServerPort(), server.isValidate());
		System.out.println(properties);
		Session session = mailAuthenticatior(server.getUsername(), server.getPassword(), properties);
		
		
		try {
			// 根据session创建一个邮件消息
			Message message = new MimeMessage(session);
			setMailInfo(message, content);
			
			// 设置邮件消息的主要内容
			Multipart multipart = new MimeMultipart();
			
			//设置邮件的显示类容
			setMailHtmlContent(multipart, content);
			
			//设置邮件附录文件
			setMailAdditionalContent(multipart, content);
			
			//将multipart对象放到message中
			message.setContent(multipart);
	        //保存邮件
			message.saveChanges();
			// 发送邮件
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static void setMailInfo(Message message, MailContent content) throws MessagingException{
		// 创建邮件发送者地址
		Address from = new InternetAddress(content.getFrom());
		// 设置邮件消息的发送者
		message.setFrom(from);
		// 创建邮件的接收者地址，并设置到邮件消息中
		Address to = new InternetAddress(content.getTo());
		message.setRecipient(Message.RecipientType.TO, to);
		// 设置邮件消息的主题
		message.setSubject(content.getTitle());
		// 设置邮件消息发送的时间
		message.setSentDate(new Date());
	}
	
	public static void setMailTextContent(Message message, MailContent content) throws MessagingException{
		message.setContent("content.getContent()", MAIL_TYPE_TEXT);
		//message.setText(content.getContent());
	}
	
	public static void setMailHtmlContent(Multipart multipart, MailContent content) throws MessagingException{
		//   设置邮件的文本内容
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(content.getContent(), MAIL_TYPE_HTML);
        multipart.addBodyPart(contentPart);
	}
	
	@SuppressWarnings("restriction")
	private static void setMailAdditionalContent(Multipart multipart, MailContent content) throws MessagingException{
		while (content.hasNext()) {
			//添加附件
            BodyPart messageBodyPart= new MimeBodyPart();
            DataSource source = new FileDataSource(content.getFilePath());
            //添加附件的内容
            messageBodyPart.setDataHandler(new DataHandler(source));
            //添加附件的标题
            //这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            messageBodyPart.setFileName("=?UTF-8?B?"+enc.encode(content.getFileName().getBytes())+"?=");
            multipart.addBodyPart(messageBodyPart);
		}
	}
	
	/**
	 * 邮件内容bean
	 * @author yinyang
	 */
	class MailContent {
		
		/** 发件邮箱 */
		private String from;
		/** 收件邮箱*/
		private String to;
		/** 邮件正文*/
		private String content;
		/** 邮件标题*/
		private String title;
		
		private int index = 0;
		
		private List<AdditionalFile>  additionals;
		
		class AdditionalFile{
			// 上传文件的名称
			private String fileName;
			// 上传文件的路径
			private String filePath;
			
			public String getFileName() {
				return fileName;
			}
			public void setFileName(String fileName) {
				this.fileName = fileName;
			}
			public String getFilePath() {
				return filePath;
			}
			public void setFilePath(String filePath) {
				this.filePath = filePath;
			}
			
		}
		
		/**
		 * 添加一个附件(可多次添加)
		 * @param fileName 文件名  eg:abc.xml
		 * @param filePath 文件路径 eg:F:/abc.xml
		 */
		public void addAdditionalFile(String fileName, String filePath){
			if(this.additionals == null){
				this.additionals = new ArrayList<AdditionalFile>();
			}
			
			AdditionalFile file = new AdditionalFile();
			file.setFileName(fileName);
			file.setFilePath(filePath);
			
			this.additionals.add(file);
		}
		
		// 是否有下一个附件
		public boolean hasNext(){
			if(this.additionals == null || this.additionals.isEmpty()){
				return false;
			}
			
			if(index >= this.additionals.size()){
				return false;
			}
			
			index ++ ;
			return true;
			
		}
		
		//获取文件名
		public String getFileName(){
			return this.additionals.get(index - 1).getFileName();
		}
		
		//获取文件路径
		public String getFilePath(){
			return this.additionals.get(index - 1).getFilePath();
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}
	
	/**
	 * 邮件服务器bean
	 * @author yinyang
	 */
	class MailServer {
		
		/** 邮件host eg:smtp.163.com*/
		private String serverHost;
		/** 邮件端口 eg:25*/
		private int serverPort;
		/** 邮件发件邮箱名称*/
		private String username;
		/** 邮件发件邮箱密码*/
		private String password;
		/** 是否验证*/
		private boolean validate = true;
		
		
		public String getServerHost() {
			return serverHost;
		}
		public void setServerHost(String serverHost) {
			this.serverHost = serverHost;
		}
		public int getServerPort() {
			return serverPort;
		}
		public void setServerPort(int serverPort) {
			this.serverPort = serverPort;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public boolean isValidate() {
			return validate;
		}
		public void setValidate(boolean validate) {
			this.validate = validate;
		}
	}
	
	/**
	 * 邮件验证类bean
	 * @author yinyang
	 */
	class MailAuthenticator extends Authenticator{
		String userName=null;  
	    String password=null;  
	       
	    public MailAuthenticator(){  
	    
	    }
	    
	    MailAuthenticator(String username, String password) {   
	        this.userName = username;   
	        this.password = password;   
	    }
	    
	    @Override
	    protected PasswordAuthentication getPasswordAuthentication(){  
	        return new PasswordAuthentication(userName, password);  
	    } 
	}
	
	public static void main(String[] args) {
		MailUtil mu = new MailUtil();
		MailServer mailServer = mu.new MailServer();
		
		mailServer.setServerHost("smtp.163.com");
		mailServer.setServerPort(25);
		mailServer.setUsername("******@163.com");
		mailServer.setPassword("******");
		
		MailContent mailContent = mu.new MailContent();
		mailContent.setFrom("*****@163.com");
		mailContent.setTo("106***62@qq.com");
		mailContent.setTitle("java 发送邮件MailUtil工具类");
		mailContent.setContent("发送邮件MailUtil工具类 /r/n This is a test! Hello World!");
		
		mailContent.addAdditionalFile("abc.xml", "F:/abc.xml");
		mailContent.addAdditionalFile("齐亚云.doc", "F:/齐亚云.doc");
		
		MailUtil.sendMail(mailServer, mailContent);
	}
}
