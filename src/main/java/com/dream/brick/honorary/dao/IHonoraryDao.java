package com.dream.brick.honorary.dao;

import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.IPager;
import com.dream.brick.honorary.bean.Honorary;

import java.util.List;

/**
 * 工作荣誉操作数据库接口
 * Created by taller on 16/2/23.
 */
public interface IHonoraryDao extends BaseDao {
    void addHonorary(Honorary jnjs);
    void updateHonorary(Honorary jnjs);
    void deleteHonorary(Honorary jnjs);
    Honorary findHonoraryById(String id);
    List<Honorary> listAll(IPager pager) throws Exception;
}
