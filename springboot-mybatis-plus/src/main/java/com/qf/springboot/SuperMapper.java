package com.qf.springboot;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**   
* @Title: SuperMapper.java 
* @Package com.qf.springboot 
* @Description: TODO
* @author haichangzhang   
* @date 2017年7月11日 下午6:37:56 
* @version V1.0   
*/
/**
 *  mapper 父类，注意这个类不要让 mp 扫描到！！
 */
public interface SuperMapper<T> extends BaseMapper<T> {

    // 这里可以放一些公共的方法
}