package com.dwring.springboot.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dwring.springboot.domain.AccessToken;
import com.dwring.springboot.domain.LoginInfo;
import com.dwring.springboot.neum.UserInfoEnum;
import com.dwring.springboot.utils.JwtHelper;
import com.dwring.springboot.utils.Md5Util;

/**
 * @Title: JsonWebTokenController.java
 * @Package com.dwring.springboot.controller
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年7月25日 下午4:38:46
 * @version V1.0
 */
@RestController
@RequestMapping("/oauth")
public class JsonWebTokenController {

	@Autowired
	private JwtHelper jwtHelper;

	@RequestMapping(value = "/token",method=RequestMethod.POST)
	public JSONObject getAccessToken(HttpServletResponse response,@RequestBody LoginInfo loginInfo, Device device) {
		JSONObject resultMsg = new JSONObject();
		try {
			if (loginInfo.getClientId() == null) {
				resultMsg.put("errcode", 500);
				resultMsg.put("msg", "not is ClientId!");
				return resultMsg;
			}
			// 验证用户名密码
			// 1.验证用户是否存在.
			if (!UserInfoEnum.TEST.getUsername().equals(loginInfo.getUsername())
					&& !UserInfoEnum.ADMIN.getUsername().equals(loginInfo.getUsername())
					&& !UserInfoEnum.ZHANGSAN.getUsername().equals(loginInfo.getUsername())) {
				resultMsg.put("result_code", 500);
				resultMsg.put("msg", "no user!");
				return resultMsg;
			} else {
				// 2.用户存在,验证密码.
				String loginMD5Password = Md5Util.MD5(loginInfo.getPassword());
				// 获取匹配的密码.
				String password = null;
				switch (loginInfo.getUsername()) {
				case "admin":
					password = UserInfoEnum.ADMIN.getPassword();
					break;
				case "test":
					password = UserInfoEnum.TEST.getPassword();
					break;
				case "zhangsan":
					password = UserInfoEnum.ZHANGSAN.getPassword();
					break;
				}
				String userMD5Password = Md5Util.MD5(password);
				if (!loginMD5Password.equals(userMD5Password)) {
					resultMsg.put("result_code", 500);
					resultMsg.put("msg", "password error!");
					return resultMsg;
				}
	            //拼装accessToken
				String accessToken=jwtHelper.createJWT(loginInfo.getUsername(), device);
				//返回accessToken
				AccessToken accessTokenEntity=new AccessToken();
				accessTokenEntity.setAccessToken(accessToken);
				accessTokenEntity.setAccessType("bearer");
				accessTokenEntity.setExpiresIn(jwtHelper.getExpiration());
				resultMsg.put("result_code", 200);
	            resultMsg.put("msg", "success");
	            resultMsg.put("token", accessTokenEntity);
	            //设置cookie
	            Cookie cookie =new Cookie("token",accessToken);
	            cookie.setHttpOnly(true);
	            //注：Domain为设置Cookie的有效域，Path限制有效路径
	            //1、必须是1-9、a-z、A-Z、. 、- （注意是-不是_）这几个字符组成
	            //2、必须是数字或字母开头
	            //3、必须是数字或字母结尾
	            //cookie.setDomain("jwt");
	            response.addCookie(cookie);
	            return resultMsg;
			}

		} catch (Exception ex) {
			System.out.println(ex);
			resultMsg.put("result_code", 500);
			resultMsg.put("msg", "other error!");
			return resultMsg;
		}
	}

}
