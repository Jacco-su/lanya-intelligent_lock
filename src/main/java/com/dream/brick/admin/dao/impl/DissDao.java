package com.dream.brick.admin.dao.impl;

import com.dream.brick.admin.bean.Diss;
import com.dream.brick.admin.dao.IDissDao;
import com.dream.framework.dao.BaseDaoImpl;
import com.dream.framework.dao.IPager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.dream.brick.admin.bean.DissRole;

/**
 * 用户数据库访问实现类
 *
 * @author DC
 */
@Transactional
@Repository()
public class DissDao extends BaseDaoImpl implements IDissDao {

    @Override
    public List<Diss> list(IPager pager, String deptId) throws Exception {
        String hql = "from Diss where deptId=?  order by sortorder ";
        return query(hql, pager, deptId);
    }

    @Override
    public List<Diss> listAll(IPager pager) throws Exception {
        String hql = "from Diss where name != 'admin' and name != 'alldiss' and status!=0 order by Id asc";
        return query(hql, pager);
    }

    @Override
    public List<Diss> listShowPass() throws Exception {
        return findList("from Diss  where name != 'admin' and status!=0 and haskh !='0'");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Diss> list() throws Exception {
        return findList("from Diss  where name != 'admin' and status!=0 order by Id, rdate asc");
    }

    @Override
    public List<Diss> query(IPager pager, String dissname) throws Exception {
        String hql = "from Diss where dissname like ? and name != 'admin' and name != 'alldiss' and status!=0 order by Id , rdate asc";
        return query(hql, pager, "%" + dissname + "%");
    }

//	@SuppressWarnings("unchecked")
//	@Override
//	public Diss findByPhone(String phone) throws Exception {
//		Session session = getSession();
//		String sql = "from Diss where phone=? and name != 'admin' and status!=0 order by orderId , rdate asc";
//		try {
//			Query query = session.createQuery(sql);
//			query.setString(0, phone);
//			List<Diss> list = query.list();
//			if (list.size() > 0) {
//				return list.get(0);
//			} else
//				return null;
//		} finally {
//			session.close();
//		}
//	}

//	@Override
//	public List<Diss> findByRoId(String roId) throws Exception {
//		// query("select u from Diss u,Role r where r.roId in elements (u.roles) and r.roId=?",
//		// 0, 0, roId);
//		List<Diss> query = query(
//				"select u from Diss u join u.roles r where r.roId=? and  u.status!=0", 0, 0,
//				roId);
//		return query;
//	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Diss> findByRoId(final String roId, final String deptId) throws Exception {
//		List query = query("select u from Diss u join u.roles r where r.roId=? and u.dept.id=? and  u.status!=0", 0, 0, roId, deptId);
//	    return query;
//	}

    @Override
    public List<Diss> findByDeptid(String deptId) throws Exception {
        // TODO Auto-generated method stub
        List query = query(" from Diss u  where  u.dept.id=? and u.id!='" + deptId + "' and u.name != 'alldiss' and  u.status!=0 order by sortorder", 0, 0, deptId);
        return query;
    }

    public Diss findDeptLeader(String deptid) throws Exception {
        List<Diss> query = query("from Diss u where u.dept.id=? and u.status!=0 order by Id", 0, 0, deptid);
        if (query != null && query.size() > 0) {
            return query.get(0);
        } else {
            query = query("from Diss u where u.dept.parentId=? and u.dept.name='局领导' and u.status!=0 order by Id", 0, 0, deptid);
            return query.get(0);
        }
    }

    public Diss findByName(String name) {
        String hql = "from Diss u where u.name=? or u.dissname=?";
        List<Diss> results = query(hql, 0, 0, name, name);
        if (results.size() > 0) {
            Diss diss = results.get(0);
            return diss;
        }
        return null;
    }

    //	public List<Diss> findIdAndName(String deptId){
//		String hql="select u.id,u.dissname,u.haskh,u.dept.id,u.dept.name from Diss u where status!=0 order by u.orderId";
//		if(deptId!=null&&!"".equals(deptId)){
//			hql="select u.id,u.dissname,u.haskh,u.dept.id,u.dept.name from Diss u where u.dept.id='"+deptId+"' and status!=0 order by u.orderId";
//		}
//		List<Object[]> results=query(hql, 0,0);
//		List<Diss> list=new ArrayList<Diss>();
//		for(int i=0;i<results.size();i++){
//			Object[] objs=results.get(i);
//			Diss diss=new Diss();
//			diss.setId(String.valueOf(objs[0]));
//			diss.setDissname(String.valueOf(objs[1]));
//			diss.setHaskh(String.valueOf(objs[2]));
//			Department dept=new Department();
//			dept.setId(String.valueOf(objs[3]));
//			if((diss.getId()).equals(dept.getId())){
//				continue;
//			}
//			dept.setName(String.valueOf(objs[4]));
//			diss.setDept(dept);
//			list.add(diss);
//		}
//		return list;
//	}
//	public List<Role> findRoles(String id){
//		String hql="select ur.role from DissRole ur where ur.diss.id=?";
//		List<Role> roles=query(hql,0,0,id);
//		return roles;
//	}
//	public Map<String,List<Role>> findAllDissRoles(){
//		Map<String,List<Role>> urmap=new HashMap<String,List<Role>>();
//		String hql="select ur.diss.id,ur.role.roId,ur.role.name from DissRole ur";
//		List<Object[]> results=query(hql,0,0);
//	    for(Object[] objs:results){
//	    	String dissid= String.valueOf(objs[0]);
//	    	Role role=new Role();
//			role.setRoId(String.valueOf(objs[1]));
//			role.setName(String.valueOf(objs[2]));
//			if(urmap.containsKey(dissid)){
//				urmap.get(dissid).add(role);
//			}else{
//				List<Role> list=new ArrayList<Role>();
//				list.add(role);
//				urmap.put(dissid, list);
//			}
//	    }
//		return urmap;
//	}
//	public void addDiss(Diss diss){
//		save(diss);
//		List<DissRole> roleList=diss.getRoleList();
//		for(DissRole ur:roleList){
//			ur.setDiss(diss);
//			save(ur);
//		}
//	}
//	public void updateDiss(Diss diss){
//		String hql="delete from DissRole ur where ur.diss.id=?";
//		executeUpdate(hql,diss.getId());
//		update(diss);
//		List<DissRole> roleList=diss.getRoleList();
//		for(DissRole ur:roleList){
//			ur.setDiss(diss);
//			save(ur);
//		}
//	}
    public void deleteDiss(Diss diss) {
        String hql = "delete from DissRole ur where ur.diss.id=?";
        executeUpdate(hql, diss.getId());

        hql = "update Diss set status=0 where id=?";
        executeUpdate(hql, diss.getId());
    }

    //	public Diss findDissById(String id){
//		Diss diss=(Diss)find(Diss.class,id);
//		List<Role> roles=findRoles(id);
//		diss.setRoles(roles);
//		return diss;
//	}
    public String getPhoneByDissId(String dissId) {
        String phone = null;
        String hql = "select t.phone from Diss t where t.id=? and t.phone is not null";
        List<String> results = query(hql, 0, 0, dissId);
        if (results.size() > 0) {
            phone = results.get(0);
            if (!phone.matches("\\d{11}")) {
                phone = null;
            }
        }
        return phone;
    }
//	public List<Diss> cacheAllDiss(){
//		String hql="select u.id,u.dissname,u.dept.id,u.dept.name from Diss u";
//		List<Object[]> results=query(hql, 0,0);
//		List<Diss> list=new ArrayList<Diss>();
//		for(int i=0;i<results.size();i++){
//			Object[] objs=results.get(i);
//			Diss diss=new Diss();
//			diss.setId(String.valueOf(objs[0]));
//			diss.setDissname(String.valueOf(objs[1]));
//			Department dept=new Department();
//			dept.setId(String.valueOf(objs[2]));
//			dept.setName(String.valueOf(objs[3]));
//			diss.setDept(dept);
//			list.add(diss);
//		}
//		return list;
//	}

    public void changeInputDissId(String[] values) {
        String deptId = values[0];
        String dissId = values[1];
        String update = "update CItemInput t set t.dissId=?,t.dissname=? where t.deptId=?";
        //把考核大项原有的管理人员修改为新人员
        executeUpdate(update, dissId, values[2], deptId);
    }

    public int checkIP(String ip) {
        String hql = "select count(*) from Ipctrl t where t.ip='" + ip + "'";
        int count = getResultNumber(hql);
        return count;
    }

    //	public void updatePassword(Diss diss){
//		String hql="update Diss set password=? where id=?";
//		executeUpdate(hql,diss.getPassword(),diss.getId());
//	}
    public Map<String, String> cacheDissDeptId() {
        Map<String, String> deptIdMap = new HashMap<String, String>();
        String hql = "select t.id,t.dept.id from Diss t";
        List<Object[]> results = query(hql, 0, 0);
        for (Object[] objs : results) {
            deptIdMap.put((String) objs[0], (String) objs[1]);
        }
        return deptIdMap;
    }

    public Map<String, String> findAllPhone() {
        String hql = "select t.id,t.phone from Diss t where t.phone is not null and t.phone!=''";
        List<Object[]> results = query(hql, 0, 0);
        Map<String, String> phoneMap = new HashMap<String, String>();
        for (Object[] objs : results) {
            phoneMap.put((String) objs[0], (String) objs[1]);
        }
        return phoneMap;
    }

    //	public void updateLoginKey(Diss diss){
//		String hql="update Diss set loginTime=?,loginKey=? where id=?";
//		this.executeUpdate(hql, new Object[]{diss.getLoginTime(),diss.getLoginKey(),diss.getId()});
//	}
    public Diss findByDissname(String dissname) {
        String hql = "select u from Diss u where u.dissname=?";
        List<Diss> results = query(hql, 0, 0, dissname);
        if (results.size() > 0) {
            Diss diss = results.get(0);
            return diss;
        }
        return null;
    }
}
