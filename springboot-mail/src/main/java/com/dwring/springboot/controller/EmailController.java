package com.dwring.springboot.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.model.BaseResponse;
import com.dwring.springboot.model.MailBean;
import com.dwring.springboot.utils.MailUtil;
import com.dwring.springboot.utils.PDFUtil;

@RequestMapping("/mail")
@RestController
public class EmailController {

	@Autowired
	private MailUtil mailUtil;
	
	
	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public BaseResponse<?> emailSend() {
		String html="/mail_code_template.ftl";
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", "342345");
		String content = PDFUtil.freeMarkerRender(data, html);
		MailBean mailBean=new MailBean();
		mailBean.setSubject("邮件验证码");
		mailBean.setRecipient("zhanghaichang@163.com");
		mailBean.setHtmlText(content);
		mailUtil.sendHTMLMail(mailBean);
		return new BaseResponse<>();
	}
}
