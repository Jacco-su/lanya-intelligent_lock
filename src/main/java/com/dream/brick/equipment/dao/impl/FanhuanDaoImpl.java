package com.dream.brick.equipment.dao.impl;

import com.dream.brick.equipment.bean.Xccheck;
import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.Pager;
import com.dream.brick.equipment.bean.CheckDetail;
import com.dream.brick.equipment.bean.Fanhuan;
import com.dream.brick.equipment.bean.Project;
import com.dream.brick.equipment.dao.FanhuanDao;
import com.dream.util.StringUtil;
import com.dream.util.StringUtils;
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
public class FanhuanDaoImpl  extends BaseDaoImpl implements FanhuanDao {
	public Fanhuan findFanhuanById(String id, boolean hasdetail) {
		StringBuilder sb = new StringBuilder("");
		sb.append("select t.id,t.xuhao,t.ftbh,t.jfmoney,t.ftmoney,");
		sb.append("t.checkuser,t.projname,t.builder,t.ftbili,t.descrip,");
		sb.append("t.djtime,t.djren,t.djrenid,t.checkDetail.id,t.project.id,t.fttime ");
		sb.append(" from Fanhuan t where t.id='").append(id).append("'");
		String hql = sb.toString();
		List<Object[]> results = findList(hql);
		if (results.size() == 0) {
			return null;
		}
		Fanhuan fanhuan = new Fanhuan();
		for (Object[] object : results) {
			fanhuan.setId((String) object[0]);
			fanhuan.setXuhao((String) object[1]);
			fanhuan.setFtbh((String) object[2]);
			fanhuan.setJfmoney((Double) object[3]);
			fanhuan.setFtmoney((Double) object[4]);

			fanhuan.setCheckuser((String) object[5]);
			fanhuan.setProjname((String) object[6]);
			fanhuan.setBuilder((String) object[7]);
			fanhuan.setFtbili((String) object[8]);
			fanhuan.setDescrip((String) object[9]);

			fanhuan.setDjtime((String) object[10]);
			fanhuan.setDjren((String) object[11]);
			fanhuan.setDjrenid((String) object[12]);
			String detailId = String.valueOf(object[13]);
			CheckDetail chdetail = new CheckDetail();
			if (hasdetail) {
				chdetail = (CheckDetail) find(CheckDetail.class, detailId);
			} else {
				chdetail.setId(detailId);
			}

			Project project = new Project();
			project.setId((String) object[14]);

			fanhuan.setProject(project);
			fanhuan.setCheckDetail(chdetail);
			fanhuan.setFttime((String) object[15]);
		}
		return fanhuan;
	}

	public List<Fanhuan> findFanhuanList(Pager pager) {
		String areacode = pager.getParamValue("areacode");
		//地区编码，不同地区只能查看自己管辖的地区数据
		String ftbh = pager.getParamValue("ftbh");
		String danganbx = pager.getParamValue("danganbx");
		ftbh = StringUtil.dealParam(ftbh);
		String begindate=pager.getParamValue("begindate");
		String proj_name=pager.getParamValue("proj_name");
		String enddate=pager.getParamValue("enddate");
		begindate=StringUtil.dealParam(begindate);
		proj_name=StringUtil.dealParam(proj_name);
		enddate=StringUtil.dealParam(enddate);
		danganbx = StringUtil.dealParam(danganbx);
		String builder=StringUtil.dealParam(pager.getParamValue("builder"));
		StringBuilder sb = new StringBuilder("");
		sb.append("select t.id,t.xuhao,t.ftbh,t.jfmoney,t.ftmoney,");
		sb.append("t.checkuser,t.projname,t.builder,t.djtime,t.ftbili, ");
		sb.append(" t.descrip,t.checkDetail.id,t.checkDetail.danganbx,t.checkDetail.lxren,t.checkDetail.project.address,t.checkDetail.jfbz,t.fttime,t.checkDetail.jzhumj ");
		sb.append(" from Fanhuan t where t.project.areacode like '").append(areacode).append("%' ");
		if (!"".equals(proj_name)) {
			sb.append(" and t.project.name like '%").append(proj_name).append("%' ");
		}
		if (!"".equals(ftbh)) {
			sb.append(" and t.ftbh = '").append(ftbh).append("' ");
		}
		if (!"".equals(danganbx)) {
			sb.append(" and t.checkDetail.danganbx like '%").append(danganbx).append("%' ");
		}
		if(!"".equals(begindate)){
			sb.append(" and t.fttime>='").append(begindate).append("' ");
		}
		if(!"".equals(enddate)){
			sb.append(" and t.fttime<='").append(enddate).append("' ");
		}
		if(StringUtils.isNotEmpty(builder)){
			sb.append(" and t.project.builder like '%").append(builder).append("%'");
		}
		sb.append(" order by t.ftbh asc ");
		String hql = sb.toString();
		List<Object[]> results = query(hql, pager);
		List<Fanhuan> fhlist = new ArrayList<Fanhuan>();
		for (Object[] object : results) {
			Fanhuan fanhuan = new Fanhuan();
			fanhuan.setId((String) object[0]);
			fanhuan.setXuhao((String) object[1]);
			fanhuan.setFtbh((String) object[2]);
			fanhuan.setJfmoney((Double) object[3]);
			fanhuan.setFtmoney((Double) object[4]);

			fanhuan.setCheckuser((String) object[5]);
			fanhuan.setProjname((String) object[6]);
			fanhuan.setBuilder((String) object[7]);
			fanhuan.setDjtime((String) object[8]);
			fanhuan.setFtbili((String) object[9]);

			fanhuan.setDescrip((String) object[10]);
			String detailId = String.valueOf(object[11]);
			CheckDetail chdetail = new CheckDetail();
			chdetail.setDanganbx((String) object[12]);
			chdetail.setId(detailId);
			chdetail.setLxren((String) object[13]);
			chdetail.setJzhumj((Double) object[17]);

			Project project=new Project();
			project.setAddress((String) object[14]);

			chdetail.setProject(project);
			chdetail.setJfbz((String) object[15]);

			fanhuan.setCheckDetail(chdetail);

			fanhuan.setFttime((String) object[16]);
			fhlist.add(fanhuan);
		}
		return fhlist;
	}

	public List<Object[]> fanhuantj(Map<String, String> params) {
		String areacode = params.get("areacode");
		String projname = params.get("projname");
		String project_id = params.get("project_id");
		projname = StringUtil.dealParam(projname);
		StringBuilder sb = new StringBuilder();
		sb.append("select t.project.id,sum(t.ftmoney) from Fanhuan t ");
		sb.append(" where t.project.areacode like '").append(areacode).append("%' ");

		if (!"".equals(projname)) {
			sb.append(" and t.project.name like '%").append(projname).append("%' ");
		}
		if (!"".equals(project_id)) {
			sb.append(" and t.project.id='").append(project_id).append("' ");
		}
		sb.append(" group by t.project.id order by t.project.areacode ");
		List<Object[]> results = findList(sb.toString());
		return results;
	}
	@Override
	public Double sum(Pager pager) {
		StringBuilder sb=new StringBuilder();
		String areacode = pager.getParamValue("areacode");
		//地区编码，不同地区只能查看自己管辖的地区数据
		String ftbh = pager.getParamValue("ftbh");
		String danganbx = pager.getParamValue("danganbx");
		ftbh = StringUtil.dealParam(ftbh);
		String begindate=pager.getParamValue("begindate");
		String proj_name=pager.getParamValue("proj_name");
		String enddate=pager.getParamValue("enddate");
		begindate=StringUtil.dealParam(begindate);
		proj_name=StringUtil.dealParam(proj_name);
		enddate=StringUtil.dealParam(enddate);
		danganbx = StringUtil.dealParam(danganbx);
		String builder=StringUtil.dealParam(pager.getParamValue("builder"));
		sb.append("select SUM(t.ftmoney) from Fanhuan t ");
		sb.append(" where t.project.areacode like '").append(areacode).append("' ");
		if (!"".equals(proj_name)) {
			sb.append(" and t.project.name like '%").append(proj_name).append("%' ");
		}
		if (!"".equals(ftbh)) {
			sb.append(" and t.ftbh like '%").append(ftbh).append("%' ");
		}
		if (!"".equals(danganbx)) {
			sb.append(" and t.checkDetail.danganbx like '%").append(danganbx).append("%' ");
		}
		if(!"".equals(begindate)){
			sb.append(" and t.fttime>='").append(begindate).append("' ");
		}
		if(!"".equals(enddate)){
			sb.append(" and t.fttime<='").append(enddate).append("' ");
		}
		if(StringUtils.isNotEmpty(builder)){
			sb.append(" and t.project.builder like '%").append(builder).append("%'");
		}

		List<Double> results=findList(sb.toString());
		return results.get(0);
	}

	@Override
	public List<Fanhuan> serarch(Map<String, String> params) throws Exception {
		/**
		 * @author       陶乐乐(wangyiqianyi@qq.com)
		 * @Description:
		 * @date         2016-04-26 16:50:25
		 * @params       [params]
		 * @return       java.util.List<com.dream.brick.equipment.bean.Fanhuan>
		 * @throws
		 */
		String areaCode=params.get("areacode");
		StringBuilder sb=new StringBuilder("");
		sb.append("SELECT @rownum\\:=@rownum+1 AS xuhao,z.ftbh,x.danganbx,p.builder,p. NAME AS projname,p.address,x.lxren as lxr,x.jfbz,x.jfdate,x.jzhumj,x.jfmoney,z.ftmoney,z.ftbili,z.fttime,z.descrip");
		sb.append(" FROM (SELECT @rownum\\:=0) r,t_fanhuan z,t_project p,t_xc_detail x WHERE z.project_id = p.id and x.id=z.xcdetail_id AND p.areacode like '%").append(areaCode).append("'");
		sb.append(" order by z.ftbh asc");
		SQLQuery sqlQuery=createSQLQuery(sb.toString());
		sqlQuery.addScalar("xuhao", StandardBasicTypes.STRING);
		sqlQuery.addScalar("ftbh", StandardBasicTypes.STRING);
		sqlQuery.addScalar("danganbx", StandardBasicTypes.STRING);
		sqlQuery.addScalar("builder", StandardBasicTypes.STRING);
		sqlQuery.addScalar("projname", StandardBasicTypes.STRING);
		sqlQuery.addScalar("address", StandardBasicTypes.STRING);
		sqlQuery.addScalar("lxr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("jfbz", StandardBasicTypes.STRING);
		sqlQuery.addScalar("jfdate", StandardBasicTypes.STRING);
		sqlQuery.addScalar("jzhumj", StandardBasicTypes.DOUBLE);
		sqlQuery.addScalar("jfmoney", StandardBasicTypes.DOUBLE);
		sqlQuery.addScalar("ftmoney", StandardBasicTypes.DOUBLE);
		sqlQuery.addScalar("ftbili", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fttime", StandardBasicTypes.STRING);
		sqlQuery.addScalar("descrip", StandardBasicTypes.STRING);
		sqlQuery.setResultTransformer(Transformers
				.aliasToBean(Fanhuan.class));
		return sqlQuery.list();
	}
}

