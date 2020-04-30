package cn.tjhyyt.user.service;


import cn.tjhyyt.common.model.dao.Resource;

import java.util.List;

public interface ResourceService {
    List<Resource> getResourceListByMap();

    List<Resource>getResourceAndPermission();
}
