package com.dream.brick.equipment.dao.impl;

import com.dream.brick.equipment.bean.Project;
import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.CheckDetail;
import com.dream.brick.equipment.bean.Detailprod;
import com.dream.brick.equipment.bean.Product;
import com.dream.brick.equipment.dao.CheckDetailDao;
import com.dream.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class CheckDetailDaoImpl extends BaseDaoImpl implements CheckDetailDao{
	public List<CheckDetail> findCheckDetailList(Pager pager){

		String areacode=pager.getParamValue("areacode");
		String proj_name=pager.getParamValue("proj_name");
		String danganbx=pager.getParamValue("danganbx");
		String enddate=pager.getParamValue("enddate");
		String begindate=pager.getParamValue("begindate");
		begindate=StringUtil.dealParam(begindate);
		proj_name=StringUtil.dealParam(proj_name);
		enddate=StringUtil.dealParam(enddate);
		proj_name=StringUtil.dealParam(proj_name);
		danganbx=StringUtil.dealParam(danganbx);
		String builder=StringUtil.dealParam(pager.getParamValue("builder"));
		StringBuilder sb=new StringBuilder("");
		sb.append("select t from CheckDetail t where ");
		sb.append(" t.project.areacode like '").append(areacode).append("%' ");
		if(!"".equals(proj_name)){
			sb.append(" and t.project.name like '%").append(proj_name).append("%' ");
		}
		if(!"".equals(danganbx)){
			sb.append(" and t.danganbx like '%").append(danganbx).append("%' ");
		}
		if(!"".equals(begindate)){
			sb.append(" and t.jfdate>='").append(begindate).append("' ");
		}
		if(!"".equals(enddate)){
			sb.append(" and t.jfdate<='").append(enddate).append("' ");
		}
		if(com.dream.util.StringUtils.isNotEmpty(builder)){
			sb.append(" and t.project.builder like '%").append(builder).append("%'");
		}
		sb.append(" order by t.danganbx asc ");
		//地区编码，不同地区只能查看自己管辖的地区数据
		return query(sb.toString(),pager);
	}
	public void saveCheckDetail(CheckDetail checkDetail,List<Detailprod> list){
		save(checkDetail);
		for(Detailprod object:list){
			save(object);
		}
	}
	public void saveCheckDetail(CheckDetail checkDetail,Detailprod detailprod){
		save(checkDetail);
		detailprod.setCheckDetail(checkDetail);
			save(detailprod);
	}
	public void updateCheckDetail(CheckDetail checkDetail,List<Detailprod> list){
		String hql="delete from Detailprod t where t.checkDetail.id=?";
		//先删除墙材使用
		executeUpdate(hql,checkDetail.getId());
		update(checkDetail);
		for(Detailprod object:list){
			save(object);
		}
	}
	public void deleteCheckDetail(CheckDetail checkDetail){
		String hql="delete from Detailprod t where t.checkDetail.id=?";
		//先删除墙材使用
		executeUpdate(hql,checkDetail.getId());
		delete(checkDetail);
	}
	public List<Detailprod> findDprodByDetailId(String chdetailId){
		String hql="select t.id,t.prodname,t.produnit,t.gycount,t.qrzsbh,t.product.id " +
				" from Detailprod t where t.checkDetail.id='"+chdetailId+"' order by gycount desc";
		List<Object[]> results=findList(hql);
		List<Detailprod> dprodList=new ArrayList<Detailprod>();
		for(Object[] objs:results){
			Detailprod dprod=new Detailprod();
			dprod.setId((String)objs[0]);
			dprod.setProdname((String)objs[1]);
			dprod.setProdunit((String)objs[2]);
			dprod.setGycount((Integer)objs[3]);
			dprod.setQrzsbh((String)objs[4]);

			Product product=new Product();
			product.setId((String)objs[5]);

			dprod.setProduct(product);
			dprodList.add(dprod);
		}
		return dprodList;
	}
	@Override
	public CheckDetail getProjectByName(String name) {
		StringBuilder sb=new StringBuilder();
		sb.append("select t.id from CheckDetail t ");
		sb.append(" where t.project.name ='").append(name).append("' ");
		List<CheckDetail> checkDetailList= findList(sb.toString());
		if (checkDetailList.size()>0) {
			return  checkDetailList.get(0);
		}
		return null;
	}
	@Override
	public CheckDetail getCdh(String name) {
		/**
		 * @author       陶乐乐(wangyiqianyi@qq.com)
		 * @Description: 根据档案编号获取明细
		 * @date         2016-03-31 09:26:37
		 * @params       [name]
		 * @return       com.dream.brick.equipment.bean.CheckDetail
		 * @throws
		 */
		StringBuilder sb=new StringBuilder();
		sb.append("select t.id,t.project.id,t.jfmoney from CheckDetail t ");
		sb.append(" where t.danganbx ='").append(name).append("' ");
		List<Object[]> checkDetailList= findList(sb.toString());
		if (checkDetailList.size()>0) {
			Object[] ids=checkDetailList.get(0);
			CheckDetail checkDetail=new CheckDetail();
			checkDetail.setId(ids[0].toString());
			checkDetail.setJfmoney(Double.parseDouble(ids[2].toString()));
			Project project=new Project();
			project.setId(ids[1].toString());
			checkDetail.setProject(project);
			return  checkDetail;
		}
		return null;
	}
	@Override
	public Object[] sumCheckDetail(Pager pager) {
		StringBuilder sb=new StringBuilder();
		String areacode=pager.getParamValue("areacode");
		String proj_name=pager.getParamValue("proj_name");
		String danganbx=pager.getParamValue("danganbx");
		String jfdate=pager.getParamValue("jfdate");
		proj_name=StringUtil.dealParam(proj_name);
		danganbx=StringUtil.dealParam(danganbx);
		String builder=StringUtil.dealParam(pager.getParamValue("builder"));
		sb.append("select SUM(t.jzhumj),SUM(t.jfmoney) from CheckDetail t ");
		sb.append(" where t.project.areacode  like '").append(areacode).append("' ");
		if(!"".equals(proj_name)){
			sb.append(" and t.project.name like '%").append(proj_name).append("%' ");
		}
		if(!"".equals(danganbx)){
			sb.append(" and t.danganbx like '%").append(danganbx).append("%' ");
		}
		if(StringUtils.isNotEmpty(jfdate)){
			sb.append(" and t.jfdate ='").append(jfdate).append("' ");
		}
		if(com.dream.util.StringUtils.isNotEmpty(builder)){
			sb.append(" and t.project.builder like '%").append(builder).append("%'");
		}
		List<Object[]> results=findList(sb.toString());
		return results.get(0);
	}

	@Override
	public List<CheckDetail> serarch(Map<String, String> params) throws Exception {
		String areaCode=params.get("areacode");
		String proj_name=params.get("proj_name");
		String danganbx=params.get("danganbx");
		String enddate=params.get("enddate");
		String begindate=params.get("begindate");
		begindate=StringUtil.dealParam(begindate);
		proj_name=StringUtil.dealParam(proj_name);
		enddate=StringUtil.dealParam(enddate);
		proj_name=StringUtil.dealParam(proj_name);
		danganbx=StringUtil.dealParam(danganbx);
		String builder=StringUtil.dealParam(params.get("builder"));
		StringBuilder sb=new StringBuilder("");
		sb.append("SELECT @rownum\\:=@rownum+1 AS xuhao,z.danganbx,p.builder,p. NAME AS projectName,p.address,z.hcqk,z.fenduan,z.lxren,z.jfbz,z.jfdate,z.jzhumj,z.jfmoney,z.fhblqk");
		sb.append(" FROM (SELECT @rownum\\:=0) r,t_xc_detail z,t_project p WHERE z.project_id = p.id AND p.areacode like '%").append(areaCode).append("'");
		if(!"".equals(proj_name)){
			sb.append(" and p.name like '%").append(proj_name).append("%' ");
		}
		if(!"".equals(danganbx)){
			sb.append(" and z.danganbx like '%").append(danganbx).append("%' ");
		}
		if(!"".equals(begindate)){
			sb.append(" and z.jfdate>='").append(begindate).append("' ");
		}
		if(!"".equals(enddate)){
			sb.append(" and z.jfdate<='").append(enddate).append("' ");
		}
		if(com.dream.util.StringUtils.isNotEmpty(builder)){
			sb.append(" and p.builder like '%").append(builder).append("%'");
		}

		sb.append(" order by z.danganbx asc");
		SQLQuery sqlQuery=createSQLQuery(sb.toString());
		sqlQuery.addScalar("xuhao", StandardBasicTypes.STRING);
		sqlQuery.addScalar("danganbx", StandardBasicTypes.STRING);
		sqlQuery.addScalar("builder", StandardBasicTypes.STRING);
		sqlQuery.addScalar("projectName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("address", StandardBasicTypes.STRING);
		sqlQuery.addScalar("hcqk", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fenduan", StandardBasicTypes.STRING);
		sqlQuery.addScalar("lxren", StandardBasicTypes.STRING);
		sqlQuery.addScalar("jfbz", StandardBasicTypes.STRING);
		sqlQuery.addScalar("jfdate", StandardBasicTypes.STRING);

		sqlQuery.addScalar("jzhumj", StandardBasicTypes.DOUBLE);
		sqlQuery.addScalar("jfmoney", StandardBasicTypes.DOUBLE);
		sqlQuery.addScalar("fhblqk", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers
				.aliasToBean(CheckDetail.class));
		return sqlQuery.list();
	}
}
