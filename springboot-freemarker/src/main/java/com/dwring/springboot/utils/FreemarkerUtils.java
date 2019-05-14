package com.dwring.springboot.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class FreemarkerUtils {

	
	 public static String getTemplate(String template, Map<String,Object> map) throws IOException, TemplateException {
	        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
	        String templatePath = FreemarkerUtils.class.getResource("/").getPath()+"/templates";
	        cfg.setDirectoryForTemplateLoading(new File(templatePath));
	        cfg.setDefaultEncoding("UTF-8");
	        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	        cfg.setLogTemplateExceptions(false);
	        Template temp = cfg.getTemplate(template);
	        StringWriter stringWriter = new StringWriter();
	        temp.process(map, stringWriter);
	        return stringWriter.toString();
	    }
}
