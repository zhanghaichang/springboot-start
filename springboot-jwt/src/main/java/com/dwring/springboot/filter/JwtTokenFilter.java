package com.dwring.springboot.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import com.dwring.springboot.domain.LoginInfo;
import com.dwring.springboot.neum.UserInfoEnum;
import com.dwring.springboot.utils.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Title: JwtTokenFilter.java
 * @Package com.dwring.springboot.filter
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年7月25日 下午3:18:53
 * @version V1.0
 */
@WebFilter(filterName = "jwtTokenFilter", urlPatterns = { "/oauth/user" })
public class JwtTokenFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	private JwtHelper jwtHelper;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// 从cookie 中获取jwt token
		Cookie[] cookies = httpRequest.getCookies();
		 if (cookies == null) {
		 // 验证token 失败.
		 resultWrite((HttpServletResponse) response);
		 return;
		 }
		String authToken = null;
		for (Cookie cookie : cookies) {
			if ("token".equals(cookie.getName())) {
				authToken = cookie.getValue();
			}
		}
		String username = jwtHelper.getUserNameFromToken(authToken);
		logger.info("checking authentication for user " + username);
		if (username != null) {
			// 这一步通常去数据库获取用户信息
			LoginInfo loginInfo = new LoginInfo();
			switch (username) {
			case "admin":
				loginInfo.setId(UserInfoEnum.ADMIN.getId());
				loginInfo.setUsername(UserInfoEnum.ADMIN.getUsername());
				loginInfo.setPassword(UserInfoEnum.ADMIN.getPassword());
				break;
			case "test":
				loginInfo.setId(UserInfoEnum.TEST.getId());
				loginInfo.setUsername(UserInfoEnum.TEST.getUsername());
				loginInfo.setPassword(UserInfoEnum.TEST.getPassword());
				break;
			case "zhangsan":
				loginInfo.setId(UserInfoEnum.ZHANGSAN.getId());
				loginInfo.setUsername(UserInfoEnum.ZHANGSAN.getUsername());
				loginInfo.setPassword(UserInfoEnum.ZHANGSAN.getPassword());
				break;
			}
			// 验证 token 有效性.
			if (jwtHelper.validateToken(authToken, loginInfo)) {
				filterChain.doFilter(request, response);
			} else {
				// 验证token 失败.
				resultWrite((HttpServletResponse) response);
				return;
			}
		} else {
			// 验证token 失败.
			resultWrite((HttpServletResponse) response);
			return;
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	private void resultWrite(HttpServletResponse servletResponse) throws IOException {
		HttpServletResponse httpServletResponse = servletResponse;
		httpServletResponse.setContentType("application/json; charset=utf-8");
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ObjectMapper mapper = new ObjectMapper();
		JSONObject resultMsg = new JSONObject();
		resultMsg.put("result_code", 500);
		resultMsg.put("msg", "authentication failure!");
		httpServletResponse.getWriter().write(mapper.writeValueAsString(resultMsg));
	}

}
