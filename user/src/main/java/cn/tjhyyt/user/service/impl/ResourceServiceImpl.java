package cn.tjhyyt.user.service.impl;


import cn.tjhyyt.common.model.dao.Resource;
import cn.tjhyyt.user.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public List<Resource> getResourceListByMap(){
        Map<String, Object> param=null;
        return resourceMapper.getResourceListByMap(param);
    }
    @Override
    public List<Resource>getResourceAndPermission(){
        return resourceMapper.getResourceAndPermission();
    }

}
