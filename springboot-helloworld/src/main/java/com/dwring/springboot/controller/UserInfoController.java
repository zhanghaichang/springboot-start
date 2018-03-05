/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.dwring.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.model.UserInfo;


 
/**  
* @ClassName: UserInfoController  
* @Description:  
* @author haichangzhang  
* @date 2017年7月26日 下午6:06:59  
*    
*/
@RestController
@RequestMapping("/users")
public class UserInfoController {


  
    @RequestMapping(value = "/user")
    public UserInfo load() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setEmail("zhanghaichang@163.com");
        userInfo.setRealname("zhanghaichang");
        userInfo.setUsername("haichang");
        userInfo.setTel("13585822222");
        return userInfo;
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        return "Hello World!";
    }

  
}
