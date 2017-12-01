package com.dream.brick.admin.dao;

import com.dream.brick.admin.bean.Diss;
import com.dream.framework.dao.BaseDao;
import com.dream.framework.dao.IPager;

import java.util.List;
import java.util.Map;

/**
 * 用户数据库访问接口
 *
 * @author DC
 */
public interface IDissDao extends BaseDao {

    /**
     * 根据条件搜索用户
     *
     * @param pager
     * @param condition
     * @return
     * @throws Exception
     */
    public List<Diss> query(IPager pager, String name) throws Exception;

    /**
     * 用户列表
     *
     * @return
     * @throws Exception
     */
    public List<Diss> list() throws Exception;

    /**
     * 用户列表按部门查找(带分页)
     *
     * @param pager
     * @return
     * @throws Exception
     */
    public List<Diss> list(IPager pager, String deptId) throws Exception;

    /**
     * 用户列表(带分页)
     *
     * @param pager
     * @return
     * @throws Exception
     */
    public List<Diss> listAll(IPager pager) throws Exception;


//	/**
//	 * 根据角色ID和部门ID查找
//	 * @param roId
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Diss> findByRoId(String roId, String deptId)throws Exception;

    //根据部门id查找所以所有人员
    public List<Diss> findByDeptid(String deptId) throws Exception;

    //根据部门id查找部门一把手
//	public Diss findDeptLeader(String deptid) throws Exception;
//	/**根据账号查询用户**/
//	public Diss findByName(String name);
//	public List<Diss> findIdAndName(String deptId);

    /**
     * 根据用户id查询对应的角色
     **/
//	public List<Role> findRoles(String dissid);
//	public void addDiss(Diss diss);
//	public void updateDiss(Diss diss);
    public void deleteDiss(Diss diss);
//	public Diss findDissById(String id);
    /**
     * 查询所有人员的角色
     * **/
//	public Map<String,List<Role>> findAllDissRoles();

    /**
     * 根据dissId 查询用户的手机号
     ***/
    public String getPhoneByDissId(String dissId);
//	public List<Diss> cacheAllDiss();

//	/**
//	 * 修改考核大项的管理人员，把考核大项交给新人员管理
//	 * **/
//	public void changeInputDissId(String[] values);
//	public int checkIP(String ip);
//	public void updatePassword(Diss diss);

    /**
     * 缓存所有人员的部门ID
     ***/
    public Map<String, String> cacheDissDeptId();

    /***查询所有手机号**/
    public Map<String, String> findAllPhone();

    //	public void updateLoginKey(Diss diss);
    public Diss findByDissname(String dissname);

    public List<Diss> listShowPass() throws Exception;
}
