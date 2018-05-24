package com.dream.brick.equipment.action;

import com.alibaba.fastjson.JSON;
import com.dream.brick.admin.bean.User;
import com.dream.brick.equipment.bean.*;
import com.dream.brick.equipment.dao.*;
import com.dream.brick.listener.SessionData;
import com.dream.socket.entity.AuthModel;
import com.dream.socket.utils.ByteUtil;
import com.dream.socket.utils.Constants;
import com.dream.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 陶乐乐(wangyiqianyi@qq.com)
 * @ClassName: OfflineAction.java
 * @Description:
 * @date 2018-03-30 上午10:09
 */
@Controller
@Scope("prototype")
@RequestMapping("/offline")
public class OfflineAction {
    @Resource
    private RedisTemplate redisTemplate;
    private RedisTemplateUtil redisTemplateUtil = null;
    @Resource
    private IAuthLogDao authLogDao;
    @Resource
    private IKeysAuthDao keysAuthDao;
    @Resource
    private IKeyssDao ikeyssDao;
    @Resource
    private QgdisDao disDao;
    private boolean authStatus=false;
    private int lockNum=0;
    private int openCount;//授权成功次数
    @Resource
    private ILocksDao ilocksDao;
    //在线授权
    @RequestMapping("/prViewAuth")
    public String prViewAuth( ModelMap model) {
        return "admin/offline/authList";
    }
    /**
     * class_name:
     * param:
     * describe: 获取可用串口
     * creat_user: taller
     * creat_date: 2018/3/30
     * creat_time: 15:24
     **/
    @ResponseBody
    @RequestMapping("/serial")
    public String serial(HttpServletRequest request) {
        try
        {
            redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
            redisTemplateUtil.setList(Const.REDIS_PROJECT_KEY, "FAFB"+";"+request.getSession().getAttribute("userUUID")+";findPort");
        }catch (Exception e){
            e.printStackTrace();
            return  "";
        }
        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return  "";
        }
        Object o = redisTemplateUtil.get("FAFB"+";"+request.getSession().getAttribute("userUUID")+";findPort");
        if(o==null||o.toString()==""){
            return  "";
        }else {
            return o.toString().replace("[","").replace("]","");
        }
    }
    @ResponseBody
    @RequestMapping("/read/user")
    public String readAuthUser(String serial,String T,HttpServletRequest request){
        if("0".equals(serial)){
            return  StringUtil.jsonValue("1","暂无钥匙用户信息!");
        }
        String  authModel=null;
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        if("13".equals(T)){
            //获取钥匙Mac地址
            authModel = new AuthModel(new byte[]{13}).toString();
        }
        redisTemplateUtil.setList(Const.REDIS_PROJECT_KEY, authModel+";"+request.getSession().getAttribute("userUUID")+";"+serial);

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return  StringUtil.jsonValue("0","操作失败，请重新获取!");
        }
        try {
            String responseStr = "";
            Object o = redisTemplateUtil.get(authModel + ";" + request.getSession().getAttribute("userUUID") + ";" + serial);
            if (o == null || StringUtils.isEmpty(o.toString())) {
                responseStr = "暂未获取到信息，请重试！";
            } else {
                responseStr = ResponseSocketUtil.V(o.toString());
                responseStr = responseStr.replace("*", "");
                Map<String,String> params=new HashMap<>();
                if(responseStr.indexOf("->")>-1){
                    params.put("keyssMAC",responseStr.split("->")[1]);
                    List<Keyss> keyssList =ikeyssDao.findkeys(params);
                    if(keyssList!=null&&keyssList.size()>0){
                        Keyss keyss=keyssList.get(0);
                        responseStr=keyss.getUser().getUsername()+";"+keyss.getKeyssCode()+";"+keyss.getUser().getId()+";"+keyss.getKeyssMAC();
                    }else{
                        responseStr="暂无钥匙用户信息!";
                    }
                }else{
                    responseStr="暂无钥匙用户信息!";
                }
            }
            return  StringUtil.jsonValue("1",responseStr);
        }catch (Exception e){
            e.printStackTrace();
            return  StringUtil.jsonValue("0","操作失败，请重新获取!");
        }
    }

    @ResponseBody
    @RequestMapping("/read")
    public String readAuth(String serial,
                           String T,
                           String userId,
                           String lockNum,
                           String deptId,
                           String startDate,
                           String endDate,
                           String keysId,
                           String disId,
                           HttpServletRequest request) {
        String  authModel=null;
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        try {
            //钥匙绑定
            if("7".equals(T)){
                authModel=new AuthModel(new byte[]{7}, AuthModel.toBingKeyData(14, ByteUtil.hexStrToByteArray(ByteUtil.addZeroForNum(userId,8))), Constants.KEY).toString();
            }else if("12".equals(T)){
                //钥匙校时
                authModel=new AuthModel(new byte[]{12},AuthModel.toData(12,14),Constants.LOCK_KEY).toString();//校时成功
            }else if("1".equals(T)) {
                //获取门锁信息  key=0000000002,1,DF:98,
                authModel = new AuthModel(new byte[]{1}, AuthModel.toData(1, 1), Constants.KEY).toString();
                SessionData.createSyslog(request,9, "获取门锁信息");

            }else if("2".equals(T)){
                SessionData.createSyslog(request,9, "初始化锁");
                if(StringUtils.isEmpty(lockNum)||"".equals(lockNum)) {
                    Qgdis qgdis=null;
                    if (StringUtils.isEmpty(disId)) {
                        //disId = "135";
                        qgdis=new Qgdis();
                        qgdis.setLockCount(0);
                        qgdis.setOrderNum(1);
                    }else {
                        qgdis = disDao.find(Qgdis.class, disId);
                    }
                    if(deptId.length()<5){
                        deptId+= "-0001";
                    }
                    if(deptId.length()<10){
                            deptId+= "-0001";
                    }
                    String str = String.format("%04d", qgdis.getOrderNum());
                    lockNum = deptId + "-" + str;
                    if (qgdis.getLockCount() == null) {
                        lockNum += "-0001";
                    } else {
                        str = String.format("%04d", qgdis.getLockCount() + 1);
                        lockNum += "-" + str;
                    }
                }
                authModel=new AuthModel(new byte[]{2},AuthModel.toLockDataByte(32,lockNum),Constants.KEY).toString();
            }else if("5".equals(T)){
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                AuthLog authLog = new AuthLog();
                authLog.setId(uuid);
                authLog.setCreateTime(FormatDate.getYMdHHmmss());
                authLog.setAuthStartTime(FormatDate.dateSdfHHmmssParse(startDate));
                authLog.setAuthName("离线授权!");
                User user=new User();
                user.setId(userId);
                authLog.setUser(user);
                authLog.setAuthType(2);
                authLog.setAuthKeysId("");
                authLog.setAuthKeys("");
                authLog.setAuthLocks(lockNum);
                authLog.setAuthLocksId(lockNum);
                authLog.setAuthEndTime(FormatDate.dateSdfHHmmssParse(endDate));
                //authLogDao.save(authLog);
                authModel=new AuthModel(new byte[]{5},AuthModel.AuthorizationKeyX(userId,lockNum,keysId,startDate,endDate,1,1),Constants.LOCK_KEY).toString();


                String   clearAuthModel=new AuthModel(new byte[]{5},AuthModel.AuthorizationKeyX(userId,lockNum,keysId,"20160411101525","20990412101527",2,1),Constants.LOCK_KEY).toString();
                SessionData.createSyslog(request,9, "离线授权");

                redisTemplateUtil.setList(Const.REDIS_PROJECT_KEY, clearAuthModel+";"+request.getSession().getAttribute("userUUID")+";"+serial);
                Thread.sleep(5000);
            }else if("13".equals(T)){
                //获取钥匙Mac地址
                authModel = new AuthModel(new byte[]{13}).toString();
                SessionData.createSyslog(request,9, "钥匙地址获取");
            }else if("55".equals(T)){
                authModel=new AuthModel(new byte[]{5},AuthModel.AuthorizationKeyX(userId,"ffffffffffffffffffffffffffffffff",keysId,"20160411101525","20990412101527",2,0),Constants.LOCK_KEY).toString();
            }else if("14".equals(T)){
                //离线获取钥匙日志 by lwx
                authModel = new AuthModel(new byte[]{14}).toString();
                SessionData.createSyslog(request,9, "离线获取日志");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  StringUtil.jsonValue("0","操作失败，请重新获取!");
        }
        redisTemplateUtil.setList(Const.REDIS_PROJECT_KEY, authModel+";"+request.getSession().getAttribute("userUUID")+";"+serial);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return  StringUtil.jsonValue("0","操作失败，请重新获取!");
        }
        try{
            String responseStr="";
            Object o = redisTemplateUtil.get(authModel+";"+request.getSession().getAttribute("userUUID")+";"+serial);
            if(o==null||StringUtils.isEmpty(o.toString())){
                responseStr="暂未获取到信息，请重试！";
            }else{
                responseStr= ResponseSocketUtil.V(o.toString());
                responseStr=responseStr.replace("*","");
                if(responseStr.indexOf("授权成功")>-1){
                    KeysAuth keysAuth=new KeysAuth();
                    keysAuth.setKeysId(keysId);
                    keysAuth.setLockName("测试");
                    keysAuth.setLockNum(lockNum);
                    keysAuthDao.save(keysAuth);
                }
                if(responseStr.indexOf("门锁识别号")>-1){
                    Map<String,String> params =new HashMap<>();
                    params.put("lockCode",responseStr.split(";")[1]);
                  List<Locks>  locksList= ilocksDao.findLocks(params);
                  if(locksList.size()>0){
                      Locks locks=locksList.get(0);
                      //responseStr="门锁识别号已存在"+locks.getQgdis().getName();
                      responseStr=locks.getLockNum()+":"+locks.getLockCode()+":"+locks.getQgdis().getName()+":"+locks.getQgdis().getDept().getName();
                  }
                }
            }
            return  StringUtil.jsonValue("1",responseStr);
        }catch (Exception e){
            e.printStackTrace();
            return  StringUtil.jsonValue("0","操作失败，请重新获取!");
        }
    }
    @RequestMapping("/prViewAreaOfflineAuth")
    public String prViewAreaOfflineAuth(){
        return "admin/offline/authAreaList";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String add(@ModelAttribute AuthLog authLog,String serial,HttpServletRequest request) {
        authStatus=true;
        SessionData.createSyslog(request,5, "开始授权");
        String message = "";
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            authLog.setId(uuid);
            authLog.setAuthStatus("0");
            authLog.setCreateTime(FormatDate.getYMdHHmmss());
            authLogDao.save(authLog);
            SessionData.createSyslog(request,1, "多个离线授权");
            message = StringUtil.jsonValue("1", "添加授权任务成功！");
        } catch (Exception e) {
            e.printStackTrace();
            message = StringUtil.jsonValue("0", AppMsg.ADD_ERROR);
        }
        if(StringUtils.isNotEmpty(authLog.getDisId())){
            auth(authLog,serial,request);
        }else{
            auth(authLog,serial,request);
        }
        return message;
    }
    private void auth(AuthLog authLog,String serial,HttpServletRequest request) {
        if (StringUtils.isNotEmpty(authLog.getAuthLocksId())) {
            String[] locks = authLog.getAuthLocksId().split(",");
            if(locks.length>0){
                String  checkTimeAuthModel=new AuthModel(new byte[]{12},AuthModel.toData(12,14),Constants.LOCK_KEY).toString();//校时成功
                String authModel = new AuthModel(new byte[]{5}, AuthModel.AuthorizationKeyX(authLog.getUser().getId(), "ffffffffffffffffffffffffffffffff", authLog.getAuthKeysId(), "20160411101525","20990412101527",2,0), Constants.LOCK_KEY).toString();//
                redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
                redisTemplateUtil.setList(Const.REDIS_PROJECT_KEY, checkTimeAuthModel+";"+request.getSession().getAttribute("userUUID")+";"+serial);
                redisTemplateUtil.setList(Const.REDIS_PROJECT_KEY, authModel+";"+request.getSession().getAttribute("userUUID")+"Clear;"+serial);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < locks.length; i++) {
                if (StringUtils.isNotEmpty(locks[i])) {
                    String authModel = new AuthModel(new byte[]{5}, AuthModel.AuthorizationKeyX(authLog.getUser().getId(), locks[i], authLog.getAuthKeysId(), FormatDate.dateParse(authLog.getAuthStartTime()), FormatDate.dateParse(authLog.getAuthEndTime()),1,i+1), Constants.LOCK_KEY).toString();//
                    auth(authModel,serial,authLog,locks[i],authLog.getAuthKeysId(),request);
                }
            }

        }
    }
    private String  auth(String authModel,String serial,AuthLog authLogFirst,String lockNum,String keysId,HttpServletRequest request){
        redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        redisTemplateUtil.setList(Const.REDIS_PROJECT_KEY, authModel + ";" + request.getSession().getAttribute("userUUID") + ";" + serial);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (authStatus){
            Object o = redisTemplateUtil.get(authModel+";"+request.getSession().getAttribute("userUUID")+";"+serial);
            String responseStr="";
            if(o!=null){
                responseStr= ResponseSocketUtil.V(o.toString());
                responseStr =responseStr.replace("*", "");
                if (responseStr.indexOf("授权成功") > -1) {
                    System.out.println("第二次授权开始");
                    AuthLog authLog = authLogDao.find(AuthLog.class, authLogFirst.getId());
                    openCount++;
                    authLog.setAuthIndex(openCount);
                    authLog.setAuthStatus("1");
                    authLogDao.update(authLog);
                    /*KeysAuth keysAuth = new KeysAuth();
                    keysAuth.setKeysId(keysId);
                    keysAuth.setLockName("测试");
                    keysAuth.setLockNum(lockNum);
                    keysAuthDao.save(keysAuth);*/
                }
            }
        }
        return "";
    }
    //prViewOfflineAuth
    @RequestMapping("/prViewOfflineAuth")
    public String prViewOfflineAuth(){
        return "admin/offline/authOfflineList";
    }
    //串口离线添加钥匙
    @RequestMapping("/prAddKeys")
    public String prAddKeys() {
        return "admin/offline/addKeysList";
    }
    @RequestMapping("/prAddLock")
    public String prAddLock() {
        return "admin/offline/addLockList";
    }

    public static void main(String[] args) {
        String xx="xx,pp";
        System.out.println(JSON.toJSONString(xx));
    }
}
