package com.dwring.springboot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwring.springboot.model.UserInfo;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2017-07-11
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {


    @Select("select * from user_info")
    List<UserInfo> getAll();
}