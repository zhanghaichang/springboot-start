package com.qf.springboot.mapper;

import com.qf.platform.annotation.QfBatisRepository;
import com.qf.springboot.model.City;

/**
 * @ClassName: CityMapper
 * @Description:
 * @author haichangzhang
 * @date 2017年6月2日 上午9:55:48
 * 
 */

@QfBatisRepository
public interface CityMapper {

	public City selectByPrimaryKey(Integer id);

	public void deleteByPrimaryKey(Integer id);

	public void insert(City country);

	public void updateByPrimaryKey(City country);
}
