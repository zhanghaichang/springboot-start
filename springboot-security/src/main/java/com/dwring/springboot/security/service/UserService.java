package com.dwring.springboot.security.service;

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

import com.dwring.springboot.security.domain.User;
import com.dwring.springboot.security.exception.BizException;
import com.dwring.springboot.security.repository.UserRepository;
import com.dwring.springboot.security.utils.JwtHelper;
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
	public UserResponse login(String username, String password, Device device) {
		try {
			UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
			Authentication authentication = authenticationManager.authenticate(upToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails userDetails = this.loadUserByUsername(username);
			User user=(User)authentication.getPrincipal();
			UserResponse response = new UserResponse();
			response.setId(user.getId());
			response.setAccessToken(jwtHelper.generateToken(userDetails, device));
			user.setAccessToken(response.getAccessToken());
			userRepository.update(response.getAccessToken(), user.getId());
			return response;
		} catch (DisabledException | BadCredentialsException e) {
			throw new BizException("用户名或密码错误");
		}
	}

}
