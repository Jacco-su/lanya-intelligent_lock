package com.dream.brick.equipment.dao.impl;

import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.ProductNew;
import com.dream.brick.equipment.dao.ProductNewDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by taller on 16/3/16.
 */
@Transactional
@Repository
public class ProductNewDaoImpl extends BaseDaoImpl implements ProductNewDao {
    @Override
    public List<ProductNew> findProductNewList(Pager pager) {
        String areacode=pager.getParamValue("areacode");
        String name=pager.getParamValue("name");
        String compname=pager.getParamValue("compname");
        String tcyy=pager.getParamValue("tcyy");
        //地区编码，不同地区只能查看自己管辖的地区数据
        StringBuilder sb=new StringBuilder();
        sb.append(" from ProductNew t where ");
        sb.append(" t.areacode like '").append(areacode).append("%' ");
        if(StringUtils.isNotEmpty(name)){
            sb.append(" and t.productName like '%").append(name).append("%' ");
        }
        if(StringUtils.isNotEmpty(compname)){
            sb.append(" and t.companyName like '%").append(compname).append("%' ");
        }
        if (StringUtils.isNotEmpty(tcyy)) {
            if("0".equals(tcyy)){
                sb.append(" and t.isTestResult ='0'");
            }else{
                sb.append(" and t.isTestResult ='1'");
            }
        }
        sb.append(" order by t.areacode asc");
        String hql=sb.toString();
        return query(hql,pager);
    }

    @Override
    public List<ProductNew> searchProductNewList(Pager pager) {
        return null;
    }

    @Override
    public ProductNew findBasicProductNew(String id) {
        return null;
    }

    @Override
    public List<ProductNew> searchProductNewList(String id) {
        return null;
    }
}
