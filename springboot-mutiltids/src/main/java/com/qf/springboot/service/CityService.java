package com.qf.springboot.service;

import com.qf.platform.annotation.DataSourceAnnotation;
import com.qf.springboot.mapper.CityMapper;
import com.qf.springboot.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**  
* @ClassName: CityService  
* @Description:  
* @author haichangzhang  
* @date 2017年7月6日 上午11:32:44  
*    
*/
@Service
@DataSourceAnnotation
public class CityService {

    @Autowired
    private CityMapper cityMapper;
 

    public City getById(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        cityMapper.deleteByPrimaryKey(id);
    }

    public void save(City country) {
        if (country.getId() != null) {
            cityMapper.updateByPrimaryKey(country);
        } else {
            cityMapper.insert(country);
        }
    }
}
