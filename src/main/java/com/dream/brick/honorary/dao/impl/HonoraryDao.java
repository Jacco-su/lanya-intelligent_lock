package com.dream.brick.honorary.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.IPager;
import com.dream.brick.honorary.bean.Honorary;
import com.dream.brick.honorary.dao.IHonoraryDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by taller on 16/2/23.
 */
@Transactional
@Repository
public class HonoraryDao extends BaseDaoImpl implements IHonoraryDao {
    @Override
    public void addHonorary(Honorary jnjs) {
        save(jnjs);
    }

    @Override
    public void updateHonorary(Honorary jnjs) {
            update(jnjs);
    }

    @Override
    public void deleteHonorary(Honorary jnjs) {
           delete(jnjs);
    }

    @Override
    public Honorary findHonoraryById(String id) {
        return (Honorary)find(Honorary.class,id);
    }

    @Override
    public List<Honorary> listAll(IPager pager) throws Exception {
        String hql = "from Honorary  order by year desc";
        return query(hql, pager);
    }
}
