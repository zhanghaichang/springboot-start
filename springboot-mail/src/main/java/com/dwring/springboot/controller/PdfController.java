package com.dwring.springboot.controller;

import com.dwring.springboot.utils.PDFUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: kevin
 * @Date: 2018/11/16
 */
@Slf4j
@RestController
public class PdfController {
	@Value("${DEST}")
	private String dest;
	@Value("${HTML}")
	private String html;
	@Value("${FONT}")
	private String font;
	private static Configuration freemarkerCfg = null;

	@RequestMapping(value = "projectExport", method = RequestMethod.GET)
	public void projectExport(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map map = new HashMap<String, Object>();
			map.put("test", "测试");
			ByteArrayOutputStream baos = PDFUtil.createPDF("templates/project.html", map);
			// 设置response文件头
			PDFUtil.renderPdf(response, baos.toByteArray(), "pdf文件");
			baos.close();
		} catch (Exception e) {
			log.info("导出报错", e);
		}
	}

	@RequestMapping(value = "helloPdf")
	public void showPdf(HttpServletResponse response) throws IOException, DocumentException {
		// 需要填充的数据
		Map<String, Object> data = new HashMap<>(16);
		data.put("name", "kevin");
		String content = freeMarkerRender(data, html);
		// 创建pdf
		createPdf(content, dest);
		// 读取pdf并预览
		readPdf(response);

	}

	public void createPdf(String content, String dest) throws IOException, DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
		// step 3
		document.open();
		// step 4
		XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
		fontImp.register(font);
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(content.getBytes("UTF-8")),
				null, Charset.forName("UTF-8"), fontImp);
		// step 5
		document.close();

	}

	/**
	 * freemarker渲染html
	 */
	public String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
		Writer out = new StringWriter();

		try {
			// 获取模板,并设置编码方式
			setFreemarkerCfg();
			Template template = freemarkerCfg.getTemplate(htmlTmp);
			// 将合并后的数据和模板写入到流中，这里使用的字符流
			template.process(data, out);
			out.flush();
			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 设置freemarkerCfg
	 */
	private void setFreemarkerCfg() {
		freemarkerCfg = new Configuration();
		// freemarker的模板目录
		try {
			freemarkerCfg.setDirectoryForTemplateLoading(new ClassPathResource("templates").getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取本地pdf,这里设置的是预览
	 */
	private void readPdf(HttpServletResponse response) {
		response.reset();
		response.setContentType("application/pdf");
		try {
			File file = new File(dest);
			FileInputStream fileInputStream = new FileInputStream(file);
			OutputStream outputStream = response.getOutputStream();
			IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
			response.setHeader("Content-Disposition", "inline; filename= file");
			outputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}