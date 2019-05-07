package com.dwring.springboot.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dwring.springboot.security.domain.AccessToken;
import com.dwring.springboot.security.service.UserService;
import com.dwring.springboot.security.utils.JwtHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * Token过滤器
 *
 * @author hackyo Created on 2017/12/8 9:28.
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UserService userService;

	// 1.从每个请求header获取token
	// 2.调用前面写的validateToken方法对token进行合法性验证
	// 3.解析得到username，并从database取出用户相关信息权限
	// 4.把用户信息(role等)以UserDetail形式放进SecurityContext以备整个请求过程使用。
	// （例如哪里需要判断用户权限是否足够时可以直接从SecurityContext取出去check）
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String tokenHeader = "Bearer ";
		if (authHeader != null && authHeader.startsWith(tokenHeader)) {
			String authToken = authHeader.substring(tokenHeader.length());
			log.info("========doFilterInternal authToken:{}",authToken);
			String username = jwtHelper.getUserNameFromToken(authToken);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userService.loadUserByUsername(username);
				AccessToken accessToken = jwtHelper.getAccessToken(authToken);
				if (userService.validateSessionToken(accessToken)) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					log.info("-------authenticationToken:{}", authenticationToken);
				} else {
					log.info("-------validateToken 无效！");
				}
			} else {
				log.error(request.getParameter("username") + " :Token is null");
			}
		}
		filterChain.doFilter(request, response);
	}

}
