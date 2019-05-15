package com.dwring.springboot.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
 
 
/**
 * PDF工具类
 * @author LQX
 *
 */
public class PDFUtil {
 
    private static String DEFAULT_ENCODING="utf-8";
    private static String PDF_TYPE="application/pdf";
    private static boolean DEFAULT_NOCACHE=true;
    private static String HEADER_ENCODING="utf-8";
    private static String HEADER_NOCACHE="no-cache";
    
    private static Configuration freemarkerCfg = null;
 
    /**
     * 生成PDF文件流
     * @param ftlName 文件名称
     * @param root	数据
     * @return ByteArrayOutputStream
     * @throws Exception
     */
    public static ByteArrayOutputStream createPDF(String ftlName, Object root) throws Exception {
        //相对路径
        File file = new File(PDFUtil.class.getResource("/").getPath());
        Configuration cfg = new Configuration();
        try {
            cfg.setLocale(Locale.CHINA);
            cfg.setEncoding(Locale.CHINA, "UTF-8");
            //设置编码
            cfg.setDefaultEncoding("UTF-8");
            //设置模板路径
            cfg.setDirectoryForTemplateLoading(file);
            //获取模板
            Template template = cfg.getTemplate(ftlName);
            template.setEncoding("UTF-8");
            ITextRenderer iTextRenderer = new ITextRenderer();
            //设置字体
            ITextFontResolver fontResolver = iTextRenderer.getFontResolver();
            fontResolver.addFont(file.getPath() + "/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Writer writer = new StringWriter();
            //数据填充模板
            template.process(root, writer);
            //设置输出文件内容及路径
            String str = writer.toString();
            iTextRenderer.setDocumentFromString(str);
            /*iTextRenderer.getSharedContext().setBaseURL("");//共享路径*/
            iTextRenderer.layout();
            //生成PDF
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            iTextRenderer.createPDF(baos);
            baos.close();
            return baos;
        } catch(Exception e) {
            throw new Exception(e);
        }
    }
    public static void renderPdf(HttpServletResponse response, final byte[] bytes, final String filename) {
        initResponseHeader(response, PDF_TYPE);
        setFileDownloadHeader(response, filename, ".pdf");
        if (null != bytes) {
            try {
                response.getOutputStream().write(bytes);
                response.getOutputStream().flush();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
 
    /**
     * 分析并设置contentType与headers.
     */
    private static HttpServletResponse initResponseHeader(HttpServletResponse response, final String contentType, final String... headers) {
        // 分析headers参数
        String encoding = DEFAULT_ENCODING;
        boolean noCache = DEFAULT_NOCACHE;
        for (String header : headers) {
            String headerName = StringUtils.substringBefore(header, ":");
            String headerValue = StringUtils.substringAfter(header, ":");
            if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
                encoding = headerValue;
            } else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
                noCache = Boolean.parseBoolean(headerValue);
            } else {
                throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
            }
        }
        // 设置headers参数
        String fullContentType = contentType + ";charset=" + encoding;
        response.setContentType(fullContentType);
        if (noCache) {
            // Http 1.0 header
            response.setDateHeader("Expires", 0);
            response.addHeader("Pragma", "no-cache");
            // Http 1.1 header
            response.setHeader("Cache-Control", "no-cache");
        }
        return response;
    }
 
    /**
     * 设置让浏览器弹出下载对话框的Header.
     * @param
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName, String fileType) {
        try {
            // 中文文件名支持
            String encodedfileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + fileType + "\"");
        } catch (UnsupportedEncodingException e) {
        }
    }
    
    public static void createPdf(String content, String dest) throws IOException, DocumentException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
		document.open();
		XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
		fontImp.register("/simhei.ttf");
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(content.getBytes("UTF-8")),
				null, Charset.forName("UTF-8"), fontImp);
		document.close();

	}

	/**
	 * freemarker渲染html
	 */
	public  static  String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
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
	private static void setFreemarkerCfg() {
		freemarkerCfg = new Configuration();
		// freemarker的模板目录
		try {
			freemarkerCfg.setDirectoryForTemplateLoading(new ClassPathResource("templates").getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}