package com.qf.springboot.service;

import com.qf.platform.annotation.DataSourceAnnotation;
import com.qf.springboot.mapper.UserInfoMapper;
import com.qf.springboot.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DataSourceAnnotation
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
 

    public UserInfo getById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        userInfoMapper.deleteByPrimaryKey(id);
    }

    public void save(UserInfo country) {
        if (country.getId() != null) {
            userInfoMapper.updateByPrimaryKey(country);
        } else {
            userInfoMapper.insert(country);
        }
    }
}
