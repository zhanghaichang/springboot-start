package com.dwring.springboot.security.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.dwring.springboot.security.domain.AccessToken;
import com.dwring.springboot.security.vo.UserRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title: JwtHelper.java
 * @Package com.dwring.springboot.utils
 * @Description: jwt及解析jwt的帮助类.
 * @author haichangzhang
 * @date 2017年7月25日 下午3:23:18
 * @version V1.0
 */
@Slf4j
@Component
public class JwtHelper {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_PASSWORD = "passwd";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_IMEINO = "imeino";
	static final String CLAIM_KEY_CREATED = "created";

	private static final String AUDIENCE_UNKNOWN = "unknown";
	private static final String AUDIENCE_WEB = "web";
	private static final String AUDIENCE_MOBILE = "mobile";
	private static final String AUDIENCE_TABLET = "tablet";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * 生成token.
	 * 
	 * @param username 用户名
	 * @param device   org.springframework.mobile.device 设备判断对象
	 * @return
	 */
	public String generateToken(UserRequest request, Device device) {
		log.info("------userDetails:{}", request.getPassword());
		String password = request.getPassword();
		log.info("------password:{}", password);
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, request.getUsername());
		claims.put(CLAIM_KEY_PASSWORD, request.getPassword());
		if (StringUtils.isNotBlank(request.getImeiNo())) {
			claims.put(CLAIM_KEY_IMEINO, request.getImeiNo());
		}
		claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));
		claims.put(CLAIM_KEY_CREATED, new Date());
		log.info("------claims:{}", claims);
		return generateToken(claims);
	}

	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims)
				// 生成token 时间 = 当前时间+ expiration（properties中配置的失效时间）
				.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * 通过spring-mobile-device 的device 检测访问主体.
	 * 
	 * @param device
	 * @return
	 */
	private String generateAudience(Device device) {
		String audience = AUDIENCE_UNKNOWN;
		if (device.isNormal()) {
			audience = AUDIENCE_WEB;
		} else if (device.isMobile()) {
			audience = AUDIENCE_MOBILE;
		} else if (device.isTablet()) {
			audience = AUDIENCE_TABLET;
		}
		return audience;
	}

	/**
	 * 通过token 获取用户名.
	 * 
	 * @param authToken
	 * @return
	 */
	public String getUserNameFromToken(String authToken) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(authToken);
			username = claims.getSubject();

		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * 通过token 获取实体
	 * 
	 * @param authToken
	 * @return
	 */
	public AccessToken getAccessToken(String authToken) {
		AccessToken token = new AccessToken();
		try {
			final Claims claims = getClaimsFromToken(authToken);
			token.setUsername(claims.getSubject());
			token.setPassword((String) claims.get(CLAIM_KEY_PASSWORD));
			token.setImeiNo((String) claims.get(CLAIM_KEY_IMEINO));
			token.setAccessToken(authToken);
		} catch (Exception e) {
			token = null;
			log.error("getAccessToken Exception", e);
		}
		return token;
	}

	/**
	 * 判断token 失效时间是否到了
	 * 
	 * @param token
	 * @return
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getClaimsFromToken(token).getExpiration();
		return expiration.before(new Date());
	}

	/**
	 * Token失效校验.
	 * 
	 * @param token     token字符串
	 * @param loginInfo 用户信息
	 * @return
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		// 1.校验签名是否正确
		// 2.token是否过期
		final String username = getUserNameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
