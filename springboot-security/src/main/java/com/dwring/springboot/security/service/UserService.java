package com.dwring.springboot.security.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dwring.springboot.security.domain.AccessToken;
import com.dwring.springboot.security.domain.User;
import com.dwring.springboot.security.exception.BizException;
import com.dwring.springboot.security.repository.UserRepository;
import com.dwring.springboot.security.utils.JwtHelper;
import com.dwring.springboot.security.utils.RedisUtil;
import com.dwring.springboot.security.vo.UserRequest;
import com.dwring.springboot.security.vo.UserResponse;

@Service
public class UserService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	RedisUtil redisUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		logger.info("username:" + user.getName() + ";password:" + user.getPassword());
		return user;
	}

	@Transactional
	public UserResponse login(UserRequest request, Device device) {
		try {
			UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(request.getUsername(),
					request.getPassword());
			Authentication authentication = authenticationManager.authenticate(upToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			User user = (User) authentication.getPrincipal();
			UserResponse response = new UserResponse();
			response.setId(user.getId());
			response.setAccessToken(jwtHelper.generateToken(request, device));
			if (StringUtils.isEmpty(request.getImeiNo())) {
				this.refreshSession(request.getUsername(), response.getAccessToken());
			} else {
				user.setAccessToken(response.getAccessToken());
				userRepository.updateUser(response.getAccessToken(), user.getId());
			}
			logger.info("User login Response:{}", response);
			return response;
		} catch (DisabledException | BadCredentialsException e) {
			throw new BizException("用户名或密码错误");
		}
	}

	public User loadByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		logger.info("username:" + user.getName() + ";password:" + user.getPassword());
		return user;
	}

	/**
	 * Token失效校验.
	 * 
	 * @param token字符串
	 * @param loginInfo 用户信息
	 * @return
	 */
	public Boolean validateSessionToken(AccessToken accessToken) {
		try {
			String suffix = "_Session_Token";
			User user = this.loadByUsername(accessToken.getUsername());
			if (user != null) {
				if (StringUtils.isNotBlank(accessToken.getImeiNo())) {
					return accessToken.getAccessToken().equals(user.getAccessToken());
				} else {
					// redis token
					String redisToken =redisUtil.get(accessToken.getUsername() + suffix);
					logger.info("redisToken :{}", redisToken);
					logger.info("accessToken :{}", accessToken.getAccessToken());
					if (accessToken.getAccessToken().equals(redisToken)) {
						// 缓存session
						this.refreshSession(accessToken.getUsername(), accessToken.getAccessToken());
						return true;
					}
				}
			}
		} catch (Exception e) {
			throw new BizException(e.getLocalizedMessage());
		}
		return false;
	}

	/**
	 * @Title: refreshSession token
	 */
	private void refreshSession(String username, String token) {
		// redis expire time 1800s
		String suffix = "_Session_Token";
		long expireTime = (30 * 60);
		redisUtil.setExpire(username + suffix, token, expireTime);
		logger.info("refresh Session:{}", username);
	}

}
