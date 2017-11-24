package com.dwring.springboot.service;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * @ClassName LocaleMessageSourceService
 * @Description TODO
 * @author zhanghaichang
 * @date: 2017年11月23日 上午11:19:46
 *
 */
@Service
public class LocaleMessageSourceService {

	@Autowired
	private MessageSource messageSource;

	/**
	 * @param code
	 *            ：对应messages配置的key.
	 * @return
	 */
	public String getMessage(String key) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(key,null, locale);
	}

}
