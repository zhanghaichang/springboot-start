package com.dwring.springboot.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class MailBean implements Serializable {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	private String recipient; // 邮件接收人
	private String subject; // 邮件主题
	private String content; // 邮件内容
	private String htmlText; // 模板內容

}