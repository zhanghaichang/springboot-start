package com.qf.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.qf.springboot.SuperMapper;
import com.qf.springboot.model.UserInfo;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2017-07-11
 */
public interface UserInfoMapper extends SuperMapper<UserInfo> {


    @Select("select * from user_info")
    List<UserInfo> getAll();
}